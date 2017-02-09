package com.example.socket.smarthealthdetector;

/**
 * Created by socket on 9/2/17.
 */

public class Disease {
    String name;
    int id;
        public Disease(String name,int id){
            this.name = name;
            this.id = id;
        }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
