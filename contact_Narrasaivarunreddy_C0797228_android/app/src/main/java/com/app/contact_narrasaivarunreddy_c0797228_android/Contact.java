package com.app.contact_narrasaivarunreddy_c0797228_android;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String fName;
    private String lName;
    private String emailAddress;
    private String phoneNumber;
    private String address;

    public Contact(String fName, String lName, String emailAddress, String phoneNumber, String address) {
        this.fName = fName;
        this.lName = lName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
