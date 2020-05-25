package com.mcgill.bluband;

import java.util.Date;

public class Child {
    public String name, gender, address, phoneNumber, contactPerson;
    public Date dateOfBirth;

    public Child(){
        //Default Constructor
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Date getDateOfBirth(){
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public void addChild(String name, String gender, Date dateOfBirth, String address, String phoneNumber, String contactPerson){
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contactPerson = contactPerson;
    }
}
