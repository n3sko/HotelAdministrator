package com.company.business.model;

public enum RoomType {
    SGL(1),     //Single room
    DBL(2),     //Double room
    TRPL(3);    //Triple room

    private int number;

    RoomType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
