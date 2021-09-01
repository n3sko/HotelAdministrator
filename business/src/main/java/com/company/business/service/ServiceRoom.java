package com.company.business.service;

import com.company.business.exception.RoomNotFoundException;
import com.company.business.exception.RoomNumberExistsException;
import com.company.business.exception.RoomStatusException;
import com.company.business.model.Guest;
import com.company.business.model.Room;
import com.company.business.model.RoomStatus;
import com.company.business.repository.RoomRepository;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Singleton
public class ServiceRoom {

    @InjectByType
    private RoomRepository roomRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ServiceRoom() {
    }

    //Добавить номер(room)
    public boolean addRoom(Room room) throws RoomNumberExistsException {
        if (room == null) {
            throw new NullPointerException();
        }
        if (room.getNumberRoom() < 1) {
            throw new IllegalArgumentException("Number must be > 0");
        }
        if (room.getType() == null) {
            throw new IllegalArgumentException("Type not selected");
        }
        if (room.getStar() < 1 && room.getStar() > 5) {
            throw new IllegalArgumentException("Star must be 1-5");
        }
        if (room.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be > 0");
        }
        boolean flag = false;
        for (Room room1 : roomRepository.getAll()) {
            if (room1.getNumberRoom() == room.getNumberRoom()) {
                throw new RoomNumberExistsException("Room number exists");
            }
        }
        if (flag == false) {
            roomRepository.getAll().add(room);
            flag = true;
        }
        return flag;
    }

    //Изменить цену номера
    public boolean changePriceRoom (int numRoom, double price) throws RoomNotFoundException {
        if (numRoom < 1 || price <= 0) {
            throw new IllegalArgumentException();
        }
        boolean flag = false;
        for (Room room : roomRepository.getAll()) {
            if (room.getNumberRoom() == numRoom) {
                room.setPrice(price);
                flag = true;
                break;
            }
        }
        if (flag == false) {
            throw new RoomNotFoundException("Room not found");
        }
        return flag;
    }

    //Изменить статус номера на ремонтируемый
    public boolean setStatusRepaired(int numRoom) throws RoomStatusException, RoomNotFoundException {
        if (numRoom < 1) {
            throw new IllegalArgumentException();
        }
        boolean flag = false;
        for (Room room : roomRepository.getAll()) {
            if (room.getNumberRoom() == numRoom) {
                if (room.getGuest() == null) {
                    room.setRoomStatus(RoomStatus.REPAIRED);
                    flag = true;
                    break;
                } else if (room.getGuest() != null) {
                    throw new RoomStatusException("Room status is booked");
                }
            }
        }
        if (flag == false) {
            throw new  RoomNotFoundException("Room not found");
        }
        return flag;
    }

    //Изменить статус номера на обслуживаемый
    public boolean setStatusServised(int numRoom) throws RoomStatusException, RoomNotFoundException {
        if (numRoom < 1) {
            throw new IllegalArgumentException();
        }
        boolean flag = false;
        for (Room room : roomRepository.getAll()) {
            if (room.getNumberRoom() == numRoom) {
                if (room.getGuest() == null) {
                    room.setRoomStatus(RoomStatus.SERVISED);
                    flag = true;
                    break;
                } else if (room.getGuest() != null) {
                    throw new RoomStatusException("Room status is booked");
                }
            }
        }
        if (flag == false) {
            throw new  RoomNotFoundException("Room not found");
        }
        return flag;
    }

    //Список номеров (сортировать по цене)
    public List<Room> allRoomSortPrice() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            rooms.add(room);
        }
        rooms.sort(Comparator.comparing(Room::getPrice).thenComparing(Room::getType).thenComparing(Room::getStar));
        return rooms;
    }

    //Список номеров (сортировать по звездам)
    public List<Room> allRoomSortStar() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            rooms.add(room);
        }
        rooms.sort(Comparator.comparing(Room::getStar).thenComparing(Room::getPrice).thenComparing(Room::getType));
        return rooms;
    }

    //Cписок номеров (сортировать по вместимости)
    public List<Room> allRoomSortСapacity() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            rooms.add(room);
        }
        rooms.sort(Comparator.comparing(Room::getType).thenComparing(Room::getPrice).thenComparing(Room::getStar));
        return rooms;
    }

    //Список свободных номеров (сортировать по цене)
    public List<Room> freeRoomSortPrice() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            if (room.getGuest() == null) {
                rooms.add(room);
            }
        }
        rooms.sort(Comparator.comparing(Room::getPrice).thenComparing(Room::getType).thenComparing(Room::getStar));
        return rooms;
    }

    //Список свободных номеров (сортировать по звездам)
    public List<Room> freeRoomSortStar() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            if (room.getGuest() == null) {
                rooms.add(room);
            }
        }
        rooms.sort(Comparator.comparing(Room::getStar).thenComparing(Room::getPrice).thenComparing(Room::getType));
        return rooms;
    }

    //Список свободных номеров (сортировать по вместимости)
    public List<Room> freeRoomSortCapacity() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            if (room.getGuest() == null) {
                rooms.add(room);
            }
        }
        rooms.sort(Comparator.comparing(Room::getType).thenComparing(Room::getPrice).thenComparing(Room::getStar));
        return rooms;
    }

    //Общее число свободных номеров
    public int getTotalFreeRooms() {
        int count = 0;
        for (int i = 0; i < roomRepository.getAll().size(); i++) {
            if (roomRepository.getAll().get(i).getGuest() == null) {
                count++;
            }
        }
        return count;
    }

    //Список номеров, которые будут свободны по определенной дате в будущем
    public List<Room> freeRoomInFuture (String date) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            if (room.getGuest() == null) {
                rooms.add(room);
            }
            else if (room.getGuest() != null) {
                if (localDate.isAfter(room.getGuest().getDepartureDate())) {
                    rooms.add(room);
                }
            }
        }
        rooms.sort(Comparator.comparing(Room::getNumberRoom));
        return rooms;
    }

    //Посмотреть 3-х последних постояльцев номера и даты их пребывания
    public List<Guest> showLastGuestsRoom (int numRoom, int quanity) throws RoomNotFoundException {
        ArrayList<Guest> guestsHistory = new ArrayList<>();
        boolean flag = false;
        for (Room room : roomRepository.getAll()) {
            if (room.getNumberRoom() == numRoom) {
                flag = true;
                for (int i = room.getGuestsHistory().size() - 1; i > room.getGuestsHistory().size() - quanity - 1; i--) {
                    guestsHistory.add(room.getGuestsHistory().get(i));
                    if (i == 0) {
                        break;
                    }
                }
            }
        }
        if (flag == false) {
            throw new RoomNotFoundException("Room not found");
        }
        return guestsHistory;
    }

    //Посмотреть детали отдельного номера
    public Room roomInfo (int numRoom) throws RoomNotFoundException {
        if (numRoom < 1) {
            throw new IllegalArgumentException();
        }
        Room room = null;
        for (Room room1 : roomRepository.getAll()) {
            if (room1.getNumberRoom() == numRoom) {
                room = room1;
                break;
            }
        }
        if (room == null) {
            throw new  RoomNotFoundException("Room not found");
        }
        return room;
    }
}
