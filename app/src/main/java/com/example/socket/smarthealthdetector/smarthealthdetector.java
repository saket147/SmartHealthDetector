package com.example.socket.smarthealthdetector;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class smarthealthdetector {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("HasRedFlag")
    @Expose
    private Boolean hasRedFlag;
    @SerializedName("HealthSymptomLocationIDs")
    @Expose
    private List<Integer> healthSymptomLocationIDs = null;
    @SerializedName("ProfName")
    @Expose
    private String profName;
    @SerializedName("Synonyms")
    @Expose
    private List<String> synonyms = null;

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

    public Boolean getHasRedFlag() {
        return hasRedFlag;
    }

    public void setHasRedFlag(Boolean hasRedFlag) {
        this.hasRedFlag = hasRedFlag;
    }

    public List<Integer> getHealthSymptomLocationIDs() {
        return healthSymptomLocationIDs;
    }

    public void setHealthSymptomLocationIDs(List<Integer> healthSymptomLocationIDs) {
        this.healthSymptomLocationIDs = healthSymptomLocationIDs;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

}

