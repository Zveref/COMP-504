package edu.rice.comp504.model.user;

import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.Date;

public class NullUser extends AUser{

    public NullUser() {
        this.name = "";
        this.birthDate = new Date();
        this.school = "";
        this.interest = "";
        this.password = "";
        this.id = -1;
    }


    @Override
    public ArrayList<Integer> addChannel(int channelId) {
        return null;
    }

    @Override
    public ArrayList<Integer> removeChannel(int channelId) {
        return null;
    }
}
