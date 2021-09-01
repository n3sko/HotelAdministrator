package com.company.business.service;

import com.company.business.exception.ServiceNotFoundException;
import com.company.business.exception.ServiceNumberExistsException;
import com.company.business.model.Service;
import com.company.business.repository.ServiceRepository;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Singleton
public class ServiceService {

    @InjectByType
    private ServiceRepository serviceRepository;

    public ServiceService() {
    }

    //Добавить сервис/услугу
    public boolean addService(Service service) throws ServiceNumberExistsException {
        if (service == null) {
            throw new NullPointerException();
        }
        if (service.getNumberService() < 1) {
            throw new IllegalArgumentException("Number must be > 1");
        }
        if (service.getName().trim().length() < 1) {
            throw new IllegalArgumentException("Wrong name");
        }
        if (service.getServiceType() == null) {
            throw new IllegalArgumentException("Type not selected");
        }
        if (service.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be > 0");
        }
        boolean flag = false;
        for (Service service1 : serviceRepository.getAll()) {
            if (service1.getNumberService() == service.getNumberService()) {
                throw new ServiceNumberExistsException("Service number exists");
            }
        }
        if (flag == false) {
            serviceRepository.getAll().add(service);
            flag = true;
        }
        return flag;
    }

    //Изменить цену услуги
    public boolean changePriceService (int numService, double price) throws ServiceNotFoundException {
        if (numService < 1 || price <= 0) {
            throw new IllegalArgumentException();
        }
        boolean flag = false;
        for (Service service : serviceRepository.getAll()) {
            if (service.getNumberService() == numService) {
                service.setPrice(price);
                flag = true;
                break;
            }
        }
        if (flag == false) {
            throw new ServiceNotFoundException("Service not found");
        }
        return flag;
    }

    //Цены услуг (сортировать по цене)
    public List<Service> servicePricesSortPrice() {
        ArrayList<Service> services = new ArrayList<>();
        for (Service service : serviceRepository.getAll()) {
            services.add(service);
        }
        services.sort(Comparator.comparing(Service::getPrice).thenComparing(Service::getServiceType));
        return services;
    }

    //Цены услуг (сортировать по разделу)
    public List<Service> servicePricesSortType() {
        ArrayList<Service> services = new ArrayList<>();
        for (Service service : serviceRepository.getAll()) {
            services.add(service);
        }
        services.sort(Comparator.comparing(Service::getServiceType).thenComparing(Service::getPrice));
        return services;
    }
}

