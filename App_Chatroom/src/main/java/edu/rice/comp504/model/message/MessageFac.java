package edu.rice.comp504.model.message;

import java.util.Date;
import java.util.UUID;

public class MessageFac {
    private static MessageFac ONLY;
    private int senderId;
    private int channelId;
    private int receiverId;
    private Date messageTime;
    private String messageContent;
    private int messageSerial;

    /**
     * Constructor.
     */
    private MessageFac(int senderId, int receiverId, int channelId, Date messageTime, String messageContent) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.channelId = channelId;
        this.messageTime = messageTime;
        this.messageContent = messageContent;
        this.messageSerial = 1;
    }

    public static MessageFac makeFac(int senderId, int receiverId, int channelId, Date messageTime, String messageContent) {
        if(ONLY == null) {
            ONLY = new MessageFac(senderId, receiverId, channelId, messageTime, messageContent);
        }
        return ONLY;
    }


    public Message makeMsg(boolean receivable, String messageContent){
        messageSerial++;
        return new Message(messageSerial, senderId, receiverId, channelId, "message",  messageTime, messageContent, receivable );
    }

    public DirectMessage makeDm(boolean receivable, String messageContent){
        messageSerial++;
        return new DirectMessage(messageSerial, senderId, receiverId, channelId, "directMessage",  messageTime, messageContent, receivable );
    }

    public EditMessage makeEmsg(int trackId, String editContent){
        messageSerial++;
        return new EditMessage(messageSerial, senderId, receiverId, channelId, "message",  messageTime, trackId, editContent);
    }


    public AMessage makeNoti(String notiType, int subjectId) {
        messageSerial++;

        switch (notiType) {

            case "invite":
                return new Notification(messageSerial, senderId, receiverId, channelId, "notification", messageTime,  "invite", subjectId);
            case "join":
                return new Notification(messageSerial, senderId, receiverId, channelId, "notification", messageTime, "join", subjectId);
            case "leave":
                return new Notification(messageSerial, senderId, receiverId, channelId, "notification", messageTime, "leave", subjectId);
            case "connectionLost":
                return new Notification(messageSerial, senderId, receiverId, channelId, "notification",  messageTime,"connectionLost", subjectId);
            case "block":
                return new Notification(messageSerial, senderId, receiverId, channelId,  "notification", messageTime,"block", subjectId);
            case "warne":
                return new Notification(messageSerial, senderId, receiverId, channelId, "notification",  messageTime,"warn", subjectId);
            case "mute":
                return new Notification(messageSerial, senderId, receiverId, channelId,"notification",  messageTime, "mute", subjectId);
//            case "report":
//                return new Notification(messageSerial, senderId, receiverId, channelId, messageTime, "notification", "invite", subjectId);
            case "recall":
                return new Notification(messageSerial, senderId, receiverId, channelId, "notification", messageTime, "recall", subjectId);
            case "delete":
                return new Notification(messageSerial, senderId, receiverId, channelId,"notification",  messageTime, "delete", subjectId);
            case "edit":
                return new Notification(messageSerial, senderId, receiverId, channelId, "notification", messageTime, "edit", subjectId);
            default:
                return NullMessage.make();
        }
    }









}
