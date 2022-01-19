package edu.rice.comp504.model.user;


import java.util.ArrayList;
import java.util.Date;

public class User extends AUser {

    public User() {
    }

    public User(String name, Date birthdate, String school, String interest, String password, int id) {
        this.name = name;
        this.birthDate = birthdate;
        this.school = school;
        this.interest = interest;
        this.password = password;
        this.id = id;
    }

    @Override
    public ArrayList<Integer> addChannel(int channelId) {
        channelList.add(channelId);
        return this.channelList;
    }

    @Override
    public ArrayList<Integer> removeChannel(int channelId) {
        channelList.remove(channelId);
        return this.channelList;
    }




}
