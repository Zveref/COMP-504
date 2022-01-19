package edu.rice.comp504.adapter;

import edu.rice.comp504.model.ChatAppWorld;
import edu.rice.comp504.model.channel.Channel;
import edu.rice.comp504.model.message.Message;
import edu.rice.comp504.model.message.MessageFac;
import edu.rice.comp504.model.user.AUser;
import edu.rice.comp504.model.util.InfoToAMsg;
import edu.rice.comp504.model.util.ItemFinder;
import edu.rice.comp504.model.util.SpeechDetector;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a web socket for the server.
 */
@WebSocket
public class WebSocketAdapter {
    private ChatAppWorld cw =ChatAppWorld.getOnlyWord();


    /**
     * Open user's session.
     * @param session The user whose session is opened.
     */
    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception{
        System.out.println("connect here");
        List<String> userInfo = session.getUpgradeRequest().getParameterMap().get("userId");
        if (userInfo.size() == 1) {
            int userId = Integer.parseInt(userInfo.get(0));
            AUser user = ChatAppWorld.getUserById(userId);
            ChatAppWorld.setSession(user, session);
        }


//        InfoToAMsg helper = new InfoToAMsg(info);
//        MessageFac MsgFac = MessageFac.makeFac(helper.getSender(), helper.getReceiver(), helper.getChannel(), helper.getDate(), helper.getContent());
//        cw.getJoinedChannels(helper.getSender()).forEach(channel -> {
//                channel.broadcastNoti(MsgFac.makeNoti("join", helper.getSender()) );
//        });
    }

    /**
     * Close the user's session.
     * @param session The use whose session is closed.
     */
    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String info) {
        InfoToAMsg helper = new InfoToAMsg(info);
        MessageFac MsgFac = MessageFac.makeFac(helper.getSender(), helper.getReceiver(), helper.getChannel(), helper.getDate(), helper.getContent());
        cw.getJoinedChannels(helper.getSender()).forEach(channel -> {
            channel.broadcastNoti(MsgFac.makeNoti("leave", helper.getSender()) );
        });

    }

    /**
     * Send a message.
     * @param session  The session user sending the message.
     * @param info The message from JS.
     */
    @OnWebSocketMessage
    public void onMessage(Session session, String info) {
        InfoToAMsg helper = new InfoToAMsg(info);
        MessageFac MsgFac = MessageFac.makeFac(helper.getSender(), helper.getReceiver(), helper.getChannel(), helper.getDate(), helper.getContent());

        if(helper.getSender() == -1){          //-1 means sent by system -->  Notification
            cw.getJoinedChannels(ItemFinder.findNum(helper.getContent())).forEach(channel -> {
                channel.broadcastNoti(MsgFac.makeNoti(ItemFinder.findString(helper.getContent()), ItemFinder.findNum(helper.getContent())));
            });
            return;
        }

        if(helper.getSender() > -1 && helper.getReceiver() == 0){       //0 means to all roommates --->Message
            int senderId = helper.getSender();
            AUser sender = cw.getUserById(senderId);
            cw.getJoinedChannels(senderId).forEach(channel -> {
                if(channel.ifBlocked(sender)){                                  //if is blocked
                    channel.broadcastNoti(MsgFac.makeNoti("block", helper.getSender()));
                    channel.broadcastMessage(MsgFac.makeMsg(false, helper.getContent()));
                    return;
                }else if(sender.getMuteStatus()){                               //if is muted
                    channel.broadcastNoti(MsgFac.makeNoti("mute", helper.getSender()));
                    channel.broadcastMessage(MsgFac.makeMsg(false, helper.getContent()));
                    return;
                }else if(SpeechDetector.detectDir(helper.getContent())){         //if contains hate words
                    channel.broadcastNoti(MsgFac.makeNoti("warn", helper.getSender()));
                    channel.broadcastMessage(MsgFac.makeMsg(true, helper.getContent()));
                    sender.addWarnNum();
                    if(sender.getWarnNum() > 1 ){
                        sender.setMuteStatus(true);
                    }
                    return;
                }
                Message message= MsgFac.makeMsg(true, helper.getContent());
//                String test2 = String.valueOf(message.toObject());
                channel.broadcastMessage(message);
                return;
            });
        }

        if(helper.getSender() > 0 && helper.getReceiver() > 0){       // --->DM
            AUser dmSender = cw.getUserById(helper.getSender());
            cw.getJoinedChannels(helper.getSender()).forEach(channel -> {
                if(channel.ifBlocked(dmSender)){                                  //if is blocked
                    channel.broadcastNoti(MsgFac.makeNoti("block", helper.getSender()));
                    channel.directMessage(MsgFac.makeDm(false, helper.getContent()));
                    return;
                }else if(dmSender.getMuteStatus()){                               //if is muted
                    channel.broadcastNoti(MsgFac.makeNoti("mute", helper.getSender()));
                    channel.directMessage(MsgFac.makeDm(false, helper.getContent()));
                    return;
                }else if(SpeechDetector.detectDir(helper.getContent())){         //if contains hate words
                    channel.broadcastNoti(MsgFac.makeNoti("warn", helper.getSender()));
                    channel.directMessage(MsgFac.makeDm(true, helper.getContent()));
                    dmSender.addWarnNum();
                    if(dmSender.getWarnNum() > 1 ){
                        dmSender.setMuteStatus(true);
                    }
                    return;
                }
                channel.directMessage(MsgFac.makeDm(true, helper.getContent()));
                return;
            });
        }

        if(helper.getSender() == helper.getReceiver()){         //edit or recall own message
            Channel editChannel = cw.getChannelById(helper.getChannel());
            if(editChannel != null){
                editChannel.editMessage(MsgFac.makeEmsg(ItemFinder.findNum(helper.getContent()), ItemFinder.findString(helper.getContent())));
                String notiType = ItemFinder.findString(helper.getContent()) == null ? "recall" : "edit";
                editChannel.broadcastNoti(MsgFac.makeNoti(notiType,helper.getSender()));
                return;
            }
        }

        if(cw.getUserById(helper.getSender()) == cw.getChannelById(helper.getChannel()).getAdmin()){
            Channel editChannel = cw.getChannelById(helper.getChannel());
            editChannel.editMessage(MsgFac.makeEmsg(ItemFinder.findNum(helper.getContent()), null));
            editChannel.broadcastNoti(MsgFac.makeNoti("delete",helper.getSender()));
        }


    }


}
