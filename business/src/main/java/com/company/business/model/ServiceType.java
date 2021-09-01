package com.company.business.model;

public enum ServiceType {
    MAIN(1),           //Основные услуги
    ADDITIONAL(2),     //Дополнительные услуги
    INDIVIDUAL(3);     //Индивидуальные услуги

    private int num;

    ServiceType(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
