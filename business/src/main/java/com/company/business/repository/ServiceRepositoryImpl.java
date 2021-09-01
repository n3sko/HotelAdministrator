package com.company.business.repository;

import com.company.di.annotations.Singleton;
import com.company.business.model.Service;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ServiceRepositoryImpl implements ServiceRepository {

    private List<Service> services = new ArrayList<>();

    public ServiceRepositoryImpl() {
    }

    @Override
    public void addService(Service service) {
        services.add(service);
    }

    @Override
    public List<Service> getAll() {
        return services;
    }

    @Override
    public Service getOne(int index) {
        return services.get(index);
    }

    @Override
    public void update(List obj) {
        services = (List<Service>) obj;
    }
}
