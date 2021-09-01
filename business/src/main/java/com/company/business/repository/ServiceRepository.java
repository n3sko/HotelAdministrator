package com.company.business.repository;

import com.company.business.model.Service;
import java.util.List;

public interface ServiceRepository {

    void addService (Service service);

    List<Service> getAll();

    Service getOne(int index);

    void update(List obj);
}
