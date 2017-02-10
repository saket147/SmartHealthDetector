package com.example.socket.smarthealthdetector;

import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by socket on 9/2/17.
 */

public class Disease {
    String name;
    int id;
    JSONArray hsl;
    public Disease(String name,int id,JSONArray hsl){
            this.name = name;
            this.id = id;
        this.hsl = hsl;
    }
    /*public Disease(List<Integer>[] healthSymptomLocationIDs){
        this.healthSymptomLocationIDs = healthSymptomLocationIDs;
    }
*/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JSONArray getHsl() {
        return hsl;
    }

    /*public void sethealthSymptomLocationIDs(List<Integer> healthSymptomLocationIDs) {
        return healthSymptomLocationIDs;
    }*/
}
