package com.mcgill.bluband;

import com.google.firebase.database.Exclude;

import java.util.Date;

public class Child {
    public String name, gender, address, contactPerson, dateOfBirth, phone;
    public GlucoseDB glucoseDB;
    public String key;

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }


    public Child(){
        //Default Constructor
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Child(String name, String gender, String dateOfBirth, String address, String contactPerson, GlucoseDB glucoseDB){
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactPerson = contactPerson;
        this.glucoseDB = glucoseDB;
    }

    public Child(String name, String gender, String dateOfBirth, String address, String contactPerson, String phone){
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactPerson = contactPerson;
        this.phone = phone;
    }

    public GlucoseDB getGlucoseDB() { return glucoseDB; }

    public void setGlucoseDB(GlucoseDB glucoseDB) {
        this.glucoseDB = glucoseDB;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
}
