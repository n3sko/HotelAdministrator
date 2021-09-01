package com.company.ui.controller.implAction.actionRoom;

import com.company.business.exception.RoomNotFoundException;
import com.company.business.service.ServiceRoom;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

@Singleton
public class ActionChangePriceRoom implements IAction {

    @InjectByType
    private ServiceRoom serviceRoom;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionChangePriceRoom.class);

    public ActionChangePriceRoom() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        printer.print("Room №:");
        try {
            int num = scanner.nextInt();
            printer.print("Edit price:");
            double price = scanner.nextInt();
            if (serviceRoom.changePriceRoom(num, price)) {
                printer.print("Price of room № " + num +
                        " has been changed to " + price + ".\n");
            }
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid arguments");
            printer.print("Error! Enter correct arguments.\n");
        } catch (RoomNotFoundException e) {
            logger.error("Room not found exception");
            printer.print("Error! Room not found.\n");
        }
    }
}