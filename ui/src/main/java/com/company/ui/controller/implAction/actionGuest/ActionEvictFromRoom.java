package com.company.ui.controller.implAction.actionGuest;

import com.company.business.exception.GuestNotFoundException;
import com.company.business.exception.RoomNotFoundException;
import com.company.business.service.ServiceGuest;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

@Singleton
public class ActionEvictFromRoom implements IAction {

    @InjectByType
    private ServiceGuest serviceGuest;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionEvictFromRoom.class);

    public ActionEvictFromRoom() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            printer.print("Room №:");
            int numRoom = scanner.nextInt();
            if (serviceGuest.evictFromRoom(numRoom)) {
                printer.print("The client is evicted from the room № " + numRoom + " .\n");
            }
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid arguments");
            printer.print("Error! Enter correct arguments.\n");
        } catch (GuestNotFoundException e) {
            logger.error("Guest not found exception");
            printer.print("Error! Guest not found.\n");
        } catch (RoomNotFoundException e) {
            logger.error("Room not found exception");
            printer.print("Error! Room not found.\n");
        }
    }
}
