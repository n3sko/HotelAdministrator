package com.company.business.service;

import com.company.business.exception.GuestNotFoundException;
import com.company.business.exception.RoomNotFoundException;
import com.company.business.exception.ServiceNotFoundException;
import com.company.business.model.Guest;
import com.company.business.model.Room;
import com.company.business.model.RoomStatus;
import com.company.business.model.Service;
import com.company.business.repository.GuestRepository;
import com.company.business.repository.RoomRepository;
import com.company.business.repository.ServiceRepository;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Singleton
public class ServiceGuest {

    @InjectByType
    private GuestRepository guestRepository;
    @InjectByType
    private RoomRepository roomRepository;
    @InjectByType
    private ServiceRepository serviceRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ServiceGuest() {
    }

    //Поселить гостя в номер
    public boolean putInRoom(Guest guest, int numRoom) throws RoomNotFoundException {
        if (guest.getName().trim().length() < 1) {
            throw new IllegalArgumentException("Wrong name");
        }
        if (guest.getSurName().trim().length() < 1) {
            throw new IllegalArgumentException("Wrong surname");
        }
        if (guest.getAge() < 0) {
            throw new IllegalArgumentException("Wrong age");
        }
        if (guest.getPassportData() < 0) {
            throw new IllegalArgumentException("Wrong password");
        }
        boolean flag = false;
        for (Room room : roomRepository.getAll()) {
            if (room.getNumberRoom() == numRoom && room.getRoomStatus() == RoomStatus.SERVISED) {
                guest.setNumRoom(numRoom);
                room.setGuest(guest);
                room.getGuestsHistory().add(guest);
                room.setRoomStatus(RoomStatus.BOOKED);
                guestRepository.addGuest(guest);
                flag = true;
                break;
            }
        }
        if (flag == false) {
            throw new RoomNotFoundException("Room not found");
        }
        return flag;
    }

    //Выселить гостя из номера
    public boolean evictFromRoom(int numRoom) throws RoomNotFoundException, GuestNotFoundException {
        if (numRoom < 1) {
            throw new IllegalArgumentException();
        }
        boolean flag = false;
        for (Room room : roomRepository.getAll()) {
            if (room.getNumberRoom() == numRoom) {
                room.setGuest(null);
                room.setRoomStatus(RoomStatus.SERVISED);
                for (Guest guest : guestRepository.getAll()) {
                    if (guest.getNumRoom() == numRoom) {
                        guestRepository.getAll().remove(guest);
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    throw new GuestNotFoundException("Guest not found");
                }
            }
            break;
        }
        if (flag == false) {
            throw new RoomNotFoundException("Room not found");
        }
        return flag;
    }

    //Добавить услугу гостю
    public boolean addServiceToGuest(int passport, int numService, LocalDate date) throws ServiceNotFoundException {
        //LocalDate localDate = LocalDate.parse(date, formatter);
        if (numService < 1) {
            throw new IllegalArgumentException();
        }
        if (passport < 1) {
            throw new IllegalArgumentException();
        }
        boolean flag = false;
        outerloop:
        for (Service service : serviceRepository.getAll()) {
            if (service.getNumberService() == numService) {
                for (Guest guest : guestRepository.getAll()) {
                    if (guest.getPassportData() == passport) {
                        service.setDate(date);
                        guest.getGuestServices().add(service);
                        flag = true;
                        break outerloop;
                    }
                }
            }
        }
        if (flag == false) {
            throw new ServiceNotFoundException("Service not found");
        }
        return flag;
    }

    //Список постояльцев и их номеров (сортировать по алфавиту)
    public List<Guest> guestsSortAlphabetically() {
        List<Guest> guests = new ArrayList<>();
        for (Guest guest : guestRepository.getAll()) {
            guests.add(guest);
        }
        guests.sort(Comparator.comparing(Guest::getName).thenComparing(Guest::getSurName).thenComparing(Guest::getDepartureDate));
        return guests;
    }

    //Список постольяльцев и их номеров (сортировать по дате освобождения) +
    public List<Guest> guestsSortDepartureDate() {
        List<Guest> guests = new ArrayList<>();
        for (Guest guest : guestRepository.getAll()) {
            guests.add(guest);
        }
        guests.sort(Comparator.comparing(Guest::getDepartureDate).thenComparing(Guest::getName).thenComparing(Guest::getSurName));
        return guests;
    }

    //Общее число постояльцев
    public int getTotalGuests() {
        int count = 0;
        for (Room room : roomRepository.getAll()) {
            if (room.getGuest() != null) {
                count++;
            }
        }
        return count;
    }

    //Сумма оплаты за номер, которую должен оплатить постоялец
    public double paymentAmount (int passport) throws GuestNotFoundException {
        double totalPrice = 0;
        for (Guest guest : guestRepository.getAll()) {
            if (guest.getPassportData() == passport) {
                for (Room room : roomRepository.getAll()) {
                    if (room.getNumberRoom() == guest.getNumRoom()) {
                        long days = ChronoUnit.DAYS.between(room.getGuest().getArrivalDate(), room.getGuest().getDepartureDate());
                        totalPrice = room.getPrice() * days;
                    }
                }
            }
            for (Service service : guest.getGuestServices()) {
                totalPrice += service.getPrice();
            }
        }
        if (totalPrice == 0) {
            throw new GuestNotFoundException("Guest not found");
        }
        return totalPrice;
    }

    //Посмотреть список услуг постояльца и их цену (сортировать по цене)
    public List<Service> showGuestServicesSortPrice(int passport) throws GuestNotFoundException {
        boolean flag = false;
        List<Service> services = new ArrayList<>();
        for (Guest guest : guestRepository.getAll()) {
            if (guest.getPassportData() == passport) {
                flag = true;
                for (Service service : guest.getGuestServices()) {
                    services.add(service);
                }
            }
        }
        if (flag == false) {
            throw new GuestNotFoundException("Guest not found");
        }
        services.sort(Comparator.comparing(Service::getPrice).thenComparing(Service::getDate));
        return services;
    }

    //Посмотреть список услуг постояльца и их цену (сортировать по дате)
    public List<Service> showGuestServicesSortDate(int passport) {
        List<Service> services = new ArrayList<>();
        for (Guest guest : guestRepository.getAll()) {
            if (guest.getPassportData() == passport) {
                for (Service service : guest.getGuestServices()) {
                    services.add(service);
                }
            }
        }
        services.sort(Comparator.comparing(Service::getDate).thenComparing(Service::getPrice));
        return services;
    }
}
