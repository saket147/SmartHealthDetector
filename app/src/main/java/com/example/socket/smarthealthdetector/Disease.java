package com.example.socket.smarthealthdetector;

import java.util.List;

/**
 * Created by socket on 9/2/17.
 */

public class Disease {
    String name;
    int id;
    private List<Integer> healthSymptomLocationIDs;

    public Disease(String name,int id){
            this.name = name;
            this.id = id;
    }
    public Disease(List<Integer> healthSymptomLocationIDs){
        this.healthSymptomLocationIDs = healthSymptomLocationIDs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getHealthSymptomLocationIDs() {
        return healthSymptomLocationIDs;
    }
}
