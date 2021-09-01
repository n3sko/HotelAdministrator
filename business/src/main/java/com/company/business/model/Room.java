package com.company.business.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private static final long serialVersionUID = 6638678646700479113L;
    private int numberRoom;
    private RoomType type;
    private int star;
    private double price;
    private RoomStatus roomStatus;
    private Guest guest;
    private ArrayList<Guest> guestsHistory;

    public Room(int numberRoom, RoomType type, int star, double price) {
        this.numberRoom = numberRoom;
        this.type = type;
        this.star = star;
        this.price = price;
        roomStatus = RoomStatus.SERVISED;
        guest = null;
        guestsHistory = new ArrayList<>();
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public ArrayList<Guest> getGuestsHistory() {
        return guestsHistory;
    }

    @Override
    public String toString() {
        return "Room №" + numberRoom +
                "   type: " + type +
                "   star: " + star +
                "   price: " + price;
    }
}
