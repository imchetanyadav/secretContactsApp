package com.chetan.contacts;

import android.content.ContentValues;

import java.io.Serializable;

public class Contact implements Serializable {

    private int id;
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String address;
    private final String moreInfo;

    public Contact(String name, String phoneNumber, String email, String address, String moreInfo) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.moreInfo = moreInfo;
    }

    public Contact(int id, String name, String phoneno, String email, String address, String moreinfo) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneno;
        this.email = email;
        this.address = address;
        this.moreInfo = moreinfo;
    }

    @Override
    public String toString(){
        return name;
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put("name", name);
        data.put("phoneno", phoneNumber);
        data.put("email", email);
        data.put("address", address);
        data.put("moreinfo", moreInfo);
        return data;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
