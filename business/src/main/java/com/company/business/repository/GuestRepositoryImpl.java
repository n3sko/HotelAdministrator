package com.company.business.repository;

import com.company.di.annotations.Singleton;
import com.company.business.model.Guest;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class GuestRepositoryImpl implements GuestRepository {

    private List<Guest> guests;

    public GuestRepositoryImpl() {
        guests = new ArrayList<>();
    }


    @Override
    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    @Override
    public List<Guest> getAll() {
        return guests;
    }

    @Override
    public Guest getOne(int index) {
        return guests.get(index);
    }

    @Override
    public void update(List obj) {
        guests = (List<Guest>) obj;
    }
}
