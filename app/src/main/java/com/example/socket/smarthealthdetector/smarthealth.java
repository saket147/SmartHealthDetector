package com.example.socket.smarthealthdetector;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class smarthealth
{
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Name")
    @Expose
    private String name;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
