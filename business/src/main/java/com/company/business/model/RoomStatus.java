package com.company.business.model;

public enum RoomStatus {
    SERVISED(true),     //Обслуживаемый номер
    REPAIRED(false),    //Ремонтируемый номер
    BOOKED(false);      //Забронированный номер

    private boolean status;

    RoomStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
