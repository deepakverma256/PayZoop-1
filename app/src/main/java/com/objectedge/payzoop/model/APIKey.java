package com.objectedge.payzoop.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deepak.verma on 23-12-2016.
 */
public class APIKey {

    @SerializedName("id_token")
    String id_token;

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

}
