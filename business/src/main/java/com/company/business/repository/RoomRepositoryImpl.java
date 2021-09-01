package com.company.business.repository;

import com.company.di.annotations.PostConstruct;
import com.company.di.annotations.Singleton;
import com.company.business.model.Room;
import com.company.business.model.RoomType;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class RoomRepositoryImpl implements RoomRepository {

    private List<Room> rooms = new ArrayList<>();

    public RoomRepositoryImpl() {
    }
    @PostConstruct
    public void init(){
        rooms.add(new Room(1, RoomType.SGL, 5, 2000));
        rooms.add(new Room(2, RoomType.SGL, 4, 1900));
        rooms.add(new Room(3, RoomType.DBL, 3, 1900));
        rooms.add(new Room(4, RoomType.TRPL, 3, 2100));
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public List<Room> getAll() {
        return rooms;
    }

    @Override
    public Room getRoom(int index) {
        return rooms.get(index);
    }

    @Override
    public void update(List obj) {
        rooms = (List<Room>) obj;
    }
}
