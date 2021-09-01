package com.company.ui.controller;

import com.company.di.annotations.InjectByTypeForName;
import com.company.di.annotations.Singleton;
import java.util.ArrayList;

@Singleton
public class Builder {
    @InjectByTypeForName("ActionExitProgram")
    private IAction actionExitProgram;

    @InjectByTypeForName("ActionAddService")
    private IAction actionAddService;
    @InjectByTypeForName("ActionChangePriceService")
    private IAction actionChangePriceService;
    @InjectByTypeForName("ActionServiceSort")
    private IAction actionServiceSort;

    @InjectByTypeForName("ActionAddRoom")
    private IAction actionAddRoom;
    @InjectByTypeForName("ActionChangePriceRoom")
    private IAction actionChangePriceRoom;
    @InjectByTypeForName("ActionEditRoomStatus")
    private IAction actionEditRoomStatus;
    @InjectByTypeForName("ActionAllRoomSort")
    private IAction actionAllRoomSort;
    @InjectByTypeForName("ActionFreeRoomSort")
    private IAction actionFreeRoomSort;
    @InjectByTypeForName("ActionGetTotalFreeRooms")
    private IAction actionGetTotalFreeRooms;
    @InjectByTypeForName("ActionRoomInfo")
    private IAction actionRoomInfo;
    @InjectByTypeForName("ActionFreeRoomInFuture")
    private IAction actionFreeRoomInFuture;
    @InjectByTypeForName("ActionShowLastGuestsRoom")
    private IAction actionShowLastGuestsRoom;

    @InjectByTypeForName("ActionPutInRoom")
    private IAction actionPutInRoom;
    @InjectByTypeForName("ActionEvictFromRoom")
    private IAction actionEvictFromRoom;
    @InjectByTypeForName("ActionAddServiceToGuest")
    private IAction actionAddServiceToGuest;
    @InjectByTypeForName("ActionGuestsSort")
    private IAction actionGuestsSort;
    @InjectByTypeForName("ActionGetTotalGuests")
    private IAction actionGetTotalGuests;
    @InjectByTypeForName("ActionPaymentAmount")
    private IAction actionPaymentAmount;
    @InjectByTypeForName("ActionShowGuestServices")
    private IAction actionShowGuestServices;

    public Builder() {
    }

    private Menu rootMenu;

    public void buildMenu() {

        ArrayList<MenuItem> listServiceMenu = new ArrayList<>();
        ArrayList<MenuItem> listRoomMenu = new ArrayList<>();
        ArrayList<MenuItem> listGuestMenu = new ArrayList<>();

        Menu menuService = new Menu("Меню услуг", listServiceMenu);
        Menu menuRoom = new Menu("Меню комнат", listRoomMenu);
        Menu menuGuest = new Menu("Меню клиентов", listGuestMenu);

        ArrayList<MenuItem> listRootMenu = new ArrayList<>();
        MenuItem rootMenuItem1 = new MenuItem("1 -> Меню клиентов", null, menuGuest);
        MenuItem rootMenuItem2 = new MenuItem("2 -> Меню комнат", null, menuRoom);
        MenuItem rootMenuItem3 = new MenuItem("3 -> Меню сервисов", null, menuService);
        MenuItem rootMenuItem4 = new MenuItem("4 -> Выход", actionExitProgram, null);
        listRootMenu.add(rootMenuItem1);
        listRootMenu.add(rootMenuItem2);
        listRootMenu.add(rootMenuItem3);
        listRootMenu.add(rootMenuItem4);
        rootMenu = new Menu("Главное меню", listRootMenu);

        MenuItem serviceMenuItem1 = new MenuItem("1 -> Добавить услугу", actionAddService, null);
        MenuItem serviceMenuItem2 = new MenuItem("2 -> Изменить цену услуги", actionChangePriceService, null);
        MenuItem serviceMenuItem3 = new MenuItem("3 -> Список всех услуг", actionServiceSort, null);
        MenuItem serviceMenuItem4 = new MenuItem("4 <- Главное меню", null, getRootMenu());
        listServiceMenu.add(serviceMenuItem1);
        listServiceMenu.add(serviceMenuItem2);
        listServiceMenu.add(serviceMenuItem3);
        listServiceMenu.add(serviceMenuItem4);

        MenuItem roomMenuItem1 = new MenuItem("1 -> Добавить комнату", actionAddRoom, null);
        MenuItem roomMenuItem2 = new MenuItem("2 -> Изменить цену комнаты", actionChangePriceRoom, null);
        MenuItem roomMenuItem3 = new MenuItem("3 -> Изменить статус комнаты", actionEditRoomStatus, null);
        MenuItem roomMenuItem4 = new MenuItem("4 -> Cписок всех комнат", actionAllRoomSort, null);
        MenuItem roomMenuItem5 = new MenuItem("5 -> Список свободных комнат", actionFreeRoomSort, null);
        MenuItem roomMenuItem6 = new MenuItem("6 -> Количество свободных номеров", actionGetTotalFreeRooms, null);
        MenuItem roomMenuItem7 = new MenuItem("7 -> Посмотреть детали комнаты", actionRoomInfo, null);
        MenuItem roomMenuItem8 = new MenuItem("8 -> Список свободных номеров по определенной дате", actionFreeRoomInFuture, null);
        MenuItem roomMenuItem9 = new MenuItem("9 -> Посмотреть последних гостей номера", actionShowLastGuestsRoom, null);
        MenuItem roomMenuItem10 = new MenuItem("10 <- Главное меню", null, getRootMenu());
        listRoomMenu.add(roomMenuItem1);
        listRoomMenu.add(roomMenuItem2);
        listRoomMenu.add(roomMenuItem3);
        listRoomMenu.add(roomMenuItem4);
        listRoomMenu.add(roomMenuItem5);
        listRoomMenu.add(roomMenuItem6);
        listRoomMenu.add(roomMenuItem7);
        listRoomMenu.add(roomMenuItem8);
        listRoomMenu.add(roomMenuItem9);
        listRoomMenu.add(roomMenuItem10);

        MenuItem guestMenuItem1 = new MenuItem("1 -> Поселить гостя в номер", actionPutInRoom, null);
        MenuItem guestMenuItem2 = new MenuItem("2 -> Выселить гостя из номера", actionEvictFromRoom, null);
        MenuItem guestMenuItem3 = new MenuItem("3 -> Добавить услугу гостю", actionAddServiceToGuest, null);
        MenuItem guestMenuItem4 = new MenuItem("4 -> Список постояльцев", actionGuestsSort, null);
        MenuItem guestMenuItem5 = new MenuItem("5 -> Количество постояльцев в отеле", actionGetTotalGuests, null);
        MenuItem guestMenuItem6 = new MenuItem("6 -> Итоговый счет гостя", actionPaymentAmount, null);
        MenuItem guestMenuItem7 = new MenuItem("7 -> Список услуг гостя", actionShowGuestServices, null);
        MenuItem guestMenuItem8 = new MenuItem("8 <- Главное меню", null, getRootMenu());

        listGuestMenu.add(guestMenuItem1);
        listGuestMenu.add(guestMenuItem2);
        listGuestMenu.add(guestMenuItem3);
        listGuestMenu.add(guestMenuItem4);
        listGuestMenu.add(guestMenuItem5);
        listGuestMenu.add(guestMenuItem6);
        listGuestMenu.add(guestMenuItem7);
        listGuestMenu.add(guestMenuItem8);
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
