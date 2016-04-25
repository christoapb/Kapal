package com.github.brnunes.swipeablecardview;

/**
 * Created by asus on 24-Apr-16.
 */
public class User {
    String name, email,username,password,status;

    public User(String name, String status, String username, String password){
        this.name= name;
        this.status = status;
        this.username = username;
        this.password = password;
    }

    public User(String email, String password){
        this.email= email;
        this.password = password;
        this.status = "";
        this.username="";
        this.name= "";

    }
}
