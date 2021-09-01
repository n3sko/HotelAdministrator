package com.company.ui.controller.implAction.actionRoom;

import com.company.business.exception.RoomNumberExistsException;
import com.company.business.model.Room;
import com.company.business.model.RoomType;
import com.company.business.service.ServiceRoom;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

@Singleton
public class ActionAddRoom implements IAction {

    @InjectByType
    private ServiceRoom serviceRoom;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionAddRoom.class);

    public ActionAddRoom() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        printer.print("Room №:");
        int num = scanner.nextInt();
        printer.print("Type:\n1 - SGL\t2 - DBL\t3 - TRPL");
        int press = scanner.nextInt();
        RoomType type = null;
        switch (press) {
            case 1:
                type = RoomType.SGL;
                break;
            case 2:
                type = RoomType.DBL;
                break;
            case 3:
                type = RoomType.TRPL;
                break;
        }
        printer.print("Star:");
        int star = scanner.nextInt();
        printer.print("Price:");
        double price = scanner.nextDouble();
        try {
            if (serviceRoom.addRoom(new Room(num, type, star, price))) {
                printer.print("Room № " + num + " added.\n");
            }
        } catch (RoomNumberExistsException e) {
            logger.error("Room number exists exception");
            printer.print("Error! Room number exists.\n");
        }
    }
}
