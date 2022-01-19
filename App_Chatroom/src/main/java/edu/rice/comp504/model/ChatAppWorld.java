package edu.rice.comp504.model;

import edu.rice.comp504.model.channel.Channel;
import edu.rice.comp504.model.cmd.PrivateCreator;
import edu.rice.comp504.model.cmd.PublicCreator;
import edu.rice.comp504.model.user.AUser;
import edu.rice.comp504.model.user.UserFact;
import edu.rice.comp504.model.util.DateCaster;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.jetty.websocket.api.Session;

public class ChatAppWorld {
    private static ChatAppWorld ONLYWORLD;
    public static UserFact USERFAC = null;
    protected static Map<Integer, Channel> channelMap = new ConcurrentHashMap<>();
    protected static int userId = 0;
    protected static Map<String, AUser> userMapByName = new ConcurrentHashMap<>();
    protected static Map<Integer, AUser> userMapById = new ConcurrentHashMap<>();
    protected static Map<AUser, Session> userToSession = new ConcurrentHashMap<>();

    private ChatAppWorld() {
    }

    public static ChatAppWorld getOnlyWord(){
        if(ONLYWORLD == null){
            ONLYWORLD = new ChatAppWorld();
        }
        return ONLYWORLD;
    }

    public static UserFact getUserFact() {
        if (USERFAC == null) {
            USERFAC = new UserFact();
        }
        return USERFAC;

    }

    public static void setSession(AUser user, Session userSession) {
        userToSession.put(user, userSession);
    }

    public static Session getSession(AUser user) {
        return userToSession.getOrDefault(user, null);

    }


    public static Map<String, AUser> getUserMapByName() {
        return userMapByName;
    }

    public static Map<Integer, AUser> getUserMapById() {
        return userMapById;
    }

    public static AUser getUserByName(String username) {
        return userMapByName.get(username);
    }

    public static AUser getUserById(int id) {
        return userMapById.get(id);
    }

    public static Channel getChannelById(int id) {
        if(channelMap.containsKey(id)){
            return channelMap.get(id);
        }
        return null;
    }

    public static void mapChannelById(int id, Channel channel) {

        channelMap.put(id, channel);
    }

    public void mapUserByName(String userName, AUser user) {
        userMapByName.put(userName, user);
    }

    public void mapUserById(int id, AUser user) {
        userMapById.put(id, user);
    }

    public AUser createUser(String userName, String birthDate, String school, String interest, String password) {
        AUser newUser = getUserFact().make("user");
        newUser.setName(userName);
        Date date = DateCaster.StrToDate(birthDate);
        newUser.setBirthDate(date);
        newUser.setSchool(school);
        newUser.setInterest(interest);
        newUser.setPassword(password);
        userId = userId + 1;
        int id = userId;
        newUser.setId(id);
        newUser.createChannelList();
        mapUserByName(userName, newUser);
        mapUserById(id, newUser);
        return newUser;
    }

    public Channel createChannel(int userId, boolean isPrivate, int capacity, String channelName) {
        AUser user = ChatAppWorld.getUserById(userId);
        Channel channel;
        if (isPrivate) {
            channel = PrivateCreator.make(user).create(capacity, channelName);
        } else {
            channel = PublicCreator.make(user).create(capacity, channelName);
        }
        int channelId = channel.getChannelId();
        mapChannelById(channelId, channel);
        user.addChannel(channelId);
        return channel;
    }


    public static ArrayList<Channel> getJoinedChannels(int userId) {
        AUser user = getUserById(userId);

        ArrayList<Integer> channelIds = user.getChannelList();
        System.out.println(channelIds);
        ArrayList<Channel> channels = new ArrayList<>();
        for (int channelId : channelIds) {
            channels.add(channelMap.get(channelId));
        }
        System.out.println(channels);
        return channels;
    }

    public Channel joinChannel(int userId, int channelId) {
        AUser user = getUserById(userId);
        user.addChannel(channelId);
        Channel channel = getChannelById(channelId);
        channel.addUser(user);
        return channel;
    }

    public boolean leaveChannel(int userId, int channelId) {
        boolean indicator = false;
        AUser user = getUserById(userId);
        Channel channel = getChannelById(channelId);
        if(channel.deleteUser(user)){
            user.removeChannel(channelId);
            indicator = true;
        }
        return indicator;
    }

    public static Map<Integer, Channel> getAllChannels(){
        return channelMap;
    }


}
