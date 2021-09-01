package com.company.ui.controller.implAction.actionRoom;

import com.company.business.exception.RoomNotFoundException;
import com.company.business.service.ServiceRoom;
import com.company.ui.controller.IAction;
import com.company.di.annotations.ConfigProperty;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

@Singleton
public class ActionShowLastGuestsRoom implements IAction {

    @InjectByType
    private ServiceRoom serviceRoom;
    @InjectByType
    private Printer printer;
    @ConfigProperty("quanityOfLastGuests")
    private int quanityOfLastGuests;

    private static final Logger logger = LogManager.getLogger(ActionShowLastGuestsRoom.class);

    public ActionShowLastGuestsRoom() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            printer.print("Room №:");
            int num = scanner.nextInt();
            printer.printList(serviceRoom.showLastGuestsRoom(num, quanityOfLastGuests));
        } catch (RoomNotFoundException e) {
            logger.error("Room not found exception");
            printer.print("Error! Room not found.\n");
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        }
    }
}
