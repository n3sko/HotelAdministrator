package com.company.business.repository;

import com.company.business.model.Guest;
import java.util.List;

public interface GuestRepository {

    void addGuest (Guest guest);

    List<Guest> getAll();

    Guest getOne(int index);

    void update(List obj);
}
