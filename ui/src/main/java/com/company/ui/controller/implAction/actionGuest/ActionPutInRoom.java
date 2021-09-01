package com.company.ui.controller.implAction.actionGuest;

import com.company.business.exception.RoomNotFoundException;
import com.company.business.model.Guest;
import com.company.business.repository.RoomRepository;
import com.company.business.service.ServiceGuest;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Singleton
public class ActionPutInRoom implements IAction {

    @InjectByType
    private ServiceGuest serviceGuest;
    @InjectByType
    private RoomRepository roomRepository;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionPutInRoom.class);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ActionPutInRoom() {
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            printer.print("Room №:");
            int numRoom = scanner.nextInt();
            printer.print("Name:");
            String name = scanner.next();
            printer.print("Surname:");
            String surName = scanner.next();
            printer.print("Age:");
            int age = scanner.nextInt();
            printer.print("Phone number:");
            String phoneNum = scanner.next();
            printer.print("Passport ID:");
            int passportData = scanner.nextInt();
            printer.print("Arrival date (DD/MM/YYYY):");
            String date1 = scanner.next();
            printer.print("Departure date (DD/MM/YYYY):");
            String date2 = scanner.next();
            LocalDate arrivalDate = LocalDate.parse(date1, formatter);
            LocalDate departureDate = LocalDate.parse(date2, formatter);
            if (serviceGuest.putInRoom(new Guest(name, surName, age, phoneNum, passportData, arrivalDate, departureDate), numRoom)) {
                printer.print("Client " + name + " " + surName + " is settled in the room № " + numRoom + ".\n");
            }
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        } catch (DateTimeParseException e) {
            logger.error("Invalid date format");
            printer.print("Error! Enter correct date format.\n");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid arguments");
            printer.print("Error! Enter correct arguments.\n");
        } catch (RoomNotFoundException e) {
            logger.error("Room not found exception");
            printer.print("Error! Room not found.\n");
        }
    }
}
