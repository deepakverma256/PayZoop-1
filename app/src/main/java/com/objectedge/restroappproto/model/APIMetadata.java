package com.objectedge.restroappproto.model;

/**
 * Created by deepak.verma on 23-12-2016.
 */
public class APIMetadata {

    String username;
    String password;

    public APIMetadata(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
