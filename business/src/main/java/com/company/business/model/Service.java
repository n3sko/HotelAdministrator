package com.company.business.model;
import java.io.Serializable;
import java.time.LocalDate;

public class Service implements Serializable {

    private static final long serialVersionUID = -8689154944601059863L;
    private int numberService;
    private String name;
    private double price;
    private ServiceType serviceType;
    private LocalDate date;

    public Service(int numberService, String name, ServiceType serviceType, double price) {
        this.numberService = numberService;
        this.name = name;
        this.serviceType = serviceType;
        this.price = price;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberService() {
        return numberService;
    }

    public void setNumberService(int numberService) {
        this.numberService = numberService;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Service № " + numberService +
                "   " + name +
                "   type:" + serviceType +
                "   price: " + price +
                "   date: " + date;
    }
}
