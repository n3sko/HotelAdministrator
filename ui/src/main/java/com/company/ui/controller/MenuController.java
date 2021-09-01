package com.company.ui.controller;

import com.company.business.repository.GuestRepository;
import com.company.business.repository.RoomRepository;
import com.company.business.repository.ServiceRepository;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.util.Serialization;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

@Singleton
public class MenuController {
    @InjectByType
    private Builder builder;
    @InjectByType
    private Navigator navigator;
    @InjectByType
    private Serialization serialization;
    @InjectByType
    private Printer printer;
    @InjectByType
    private GuestRepository guestRepository;
    @InjectByType
    private RoomRepository roomRepository;
    @InjectByType
    private ServiceRepository serviceRepository;

    private static final Logger logger = LogManager.getLogger(MenuController.class);

    public MenuController() {
    }

    public void run() {
        try {
            guestRepository.update(serialization.readData("guest"));
            roomRepository.update(serialization.readData("room"));
            serviceRepository.update(serialization.readData("service"));
        } catch (IOException e) {
            logger.error("Deserialization error");
            printer.print("Error! Deserialization error.\n");
        } catch (ClassNotFoundException e) {
            logger.error("Deserialization error. Class not found.");
            printer.print("Error! Deserialization error. Class not found.\n");
        }
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                navigator.printMenu();
                String index = scanner.nextLine();
                navigator.navigate(Integer.parseInt(index) - 1);
            } catch (IndexOutOfBoundsException e) {
                logger.error("Index out of bounds exception");
                printer.print(e.getMessage() + ". Enter correct data.\n");
            } catch (NumberFormatException e) {
                logger.error("Number format exception");
                printer.print(e.getMessage() + ". Enter correct menu item.\n");
            }
        }
    }
}