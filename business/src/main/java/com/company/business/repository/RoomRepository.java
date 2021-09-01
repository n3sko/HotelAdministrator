package com.company.business.repository;

import com.company.business.model.Room;
import java.util.List;

public interface RoomRepository {

    void addRoom (Room room);

    List<Room> getAll();

    Room getRoom(int index);

    void update(List obj);
}
