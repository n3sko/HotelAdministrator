package com.company.business.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Guest extends Person implements Serializable {

    private static final long serialVersionUID = 8487038010748305084L;
    private String phoneNum;
    private int passportData;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int numRoom;
    private ArrayList<Service> guestServices;

    public Guest(String name, String surName, int age, String phoneNum, int passportData, LocalDate arrivalDate, LocalDate departureDate) {
        super(name, surName, age);
        this.phoneNum = phoneNum;
        this.passportData = passportData;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        guestServices = new ArrayList<>();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getPassportData() {
        return passportData;
    }

    public void setPassportData(int passportData) {
        this.passportData = passportData;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    public ArrayList<Service> getGuestServices() {
        return guestServices;
    }

    public void setGuestServices(ArrayList<Service> guestServices) {
        this.guestServices = guestServices;
    }

    @Override
    public String toString() {
        return "PassportData: " + getPassportData() +
                "   " + getName() +
                " " + getSurName() +
                "   Room №" + numRoom +
                "   Departure date: " + departureDate + "\n";
    }
}