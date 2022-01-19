package edu.rice.comp504.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.adapter.WebSocketAdapter;
import edu.rice.comp504.model.ChatAppWorld;
import edu.rice.comp504.model.channel.Channel;
import edu.rice.comp504.model.user.AUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * The chat app controller communicates with all the clients on the web socket.
 */
public class ChatAppController {

    /**
     * Chat App entry point.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFiles.location("/public");
        Gson gson = new Gson();
        ChatAppWorld cw = ChatAppWorld.getOnlyWord();
        DispatchAdapter da = new DispatchAdapter(cw);
        webSocket("/chatapp", WebSocketAdapter.classq);
        init();

        // return AUser
        post("/signUp", (request, response) -> {
            String userName = request.queryParams("userName");
            String birthDate = request.queryParams("birthDate");
            String school = request.queryParams("school");
            String interest = request.queryParams("interest");
            String password = request.queryParams("password");
            //da.signUp(userName, birthDate, school, interest, password)
//            System.out.println(userName + " " + birthDate + " " + school + " " + interest + " " + password);
//            return gson.toJson("login");
            return gson.toJson(da.signUp(userName, birthDate, school, interest, password));
        });

        //return true/false
        post("/login", (request, response) -> {
            String userName = request.queryParams("userName");
            String password = request.queryParams("password");
            JsonObject js = new JsonObject();
            if (da.login(userName, password)) {
                js.addProperty("isLogin", true);
                js.addProperty("userName", da.getUserByName(userName).getName());
                js.addProperty("birthDate", da.getUserBirthStr(userName));
                js.addProperty("school", da.getUserByName(userName).getSchool());
                js.addProperty("interest", da.getUserByName(userName).getInterest());
                js.addProperty("userId", da.getUserByName(userName).getId());

                System.out.println("ID "+da.getUserByName(userName).getId());
            } else {
                js.addProperty("isLogin", false);
            }
            return js;
        });

        post("/createChannel", (request, response) -> {
            //String sessionInfo = request.params(":session");
            int adminId = Integer.parseInt(request.queryParams("adminId"));
            int capacity = Integer.parseInt(request.queryParams("capacity"));
            String channelName = request.queryParams("channelName");
            Boolean isPrivate = Boolean.parseBoolean(request.queryParams("isPrivate"));
            Channel channel = da.createChannel(adminId, isPrivate, capacity, channelName);
//            AUser user = ChatAppWorld.getUserById(adminId);
//            System.out.println(user);
            return gson.toJson(channel);
//            return gson.toJson("user can create channel");
        });


        post("/quitChannel", (request, response) -> {
            int userId = Integer.parseInt(request.params(":userId"));
            int channelId = Integer.parseInt(request.params(":channelId"));
            return gson.toJson("SingleQuitSuccess ? :" + da.quitChannel(userId, channelId));
        });

        get("/quitAllChannels", (request, response) -> {
            boolean indicator = true;
            int userId = Integer.parseInt(request.params(":userId"));
            ArrayList<Channel> list = da.getJoinedChannels(userId);
            for(Channel temp : list){
                indicator = da.quitChannel(userId, temp.getChannelId());
            }
            return gson.toJson("QuitAllSuccess ? :" + indicator);
        });

        get("/switchChannel/:channelId", (request, response) -> {

            int channelId = Integer.parseInt(request.params(":channelId"));
            return gson.toJson(da.getChannelById(channelId));
        });

        post("/viewAllChannels", (request, response) -> {
            Map<Integer, Channel> allChannels = da.getAllChannels();
            return gson.toJson(allChannels);
        });

        post("/viewAllChannels", (request, response) -> {
            Map<Integer, Channel> allChannels = da.getAllChannels();
            return gson.toJson(allChannels);
        });

        post("/viewChannel", (request, response) -> {
            int userId = Integer.parseInt(request.params(":userId"));
            int channelId = Integer.parseInt(request.params(":channelId"));
            return gson.toJson(da.joinChannel(userId, channelId));
        });

        post("/searchChannel", (request, response) -> {
            String keywords = request.params(":keyWords");
            Map<Integer,Channel> allChannels = da.getAllChannels();
            Map<String, Channel> targetChannels = new HashMap<String, Channel>();
            for(Channel temp : allChannels.values()){
                if(temp.getChannelName().contains(keywords) || keywords.contains(temp.getChannelName())){
                    targetChannels.put(temp.getChannelName(), temp);
                }
            }
            return gson.toJson(targetChannels);
        });



        get("/invite/:channelId/:userId", (request, response) -> {
            int channelId = Integer.parseInt(request.params(":channelId"));
            int userId = Integer.parseInt(request.params(":userId"));
            Channel thisChannel = da.getChannelById(channelId);
            AUser user = da.getUserById(userId);
            thisChannel.addUser(user);
            user.addChannel(thisChannel.getChannelId());
            return gson.toJson("InviteSuccess");
        });



        get("/block/:channelId/:userId", (request, response) -> {
            int channelId = Integer.parseInt(request.params(":channelId"));
            int userId = Integer.parseInt(request.params(":userId"));
            AUser user = da.getUserById(userId);
            Channel channel = da.getChannelById(channelId);
            channel.blockUser(user);
            return gson.toJson("BlockSuccess");
        });

        get("/mute/:userId", (request, response) -> {
            int userId = Integer.parseInt(request.params(":userId"));
            AUser user = da.getUserById(userId);
            user.setMuteStatus(true);
            return gson.toJson("MuteSuccess");
        });

        get("/joinedChannels/:userId", (request, response) -> {
            String userId = request.params(":userId");
            da.getJoinedChannels(Integer.parseInt(userId));
            return gson.toJson(da.getJoinedChannels(Integer.parseInt(userId)));
        });



    }

    /**
     * Get the heroku assigned port number.
     * @return The heroku assigned port number
     */
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set.
    }
}
