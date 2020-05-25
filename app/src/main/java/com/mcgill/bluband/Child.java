package com.mcgill.bluband;

import java.util.Date;

public class Child {
    public String name, gender, address, contactPerson, dateOfBirth;
    public int glucose;
    public Child(){
        //Default Constructor
    }
    public void Child(String name, String gender, String dateOfBirth, String address, String contactPerson, int glucose){
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactPerson = contactPerson;
        this.glucose = glucose;
    }

    public int getGlucose() {
        return glucose;
    }

    public void setGlucose(int glucose) {
        this.glucose = glucose;
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
