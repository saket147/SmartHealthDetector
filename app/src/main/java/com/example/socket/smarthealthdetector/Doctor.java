package com.example.socket.smarthealthdetector;

/**
 * Created by HP-User on 4/9/2017.
 */

public class Doctor {
    private String name;
    private String contactNo;
    private String address;
    private String speciality;

    public String getContactNo() {
        return contactNo;
    }

    public Doctor() {
    }

    public Doctor(String name, String contactNo, String address, String speciality) {
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
        this.speciality = speciality;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}

