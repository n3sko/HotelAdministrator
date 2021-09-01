package com.company.ui.controller.implAction.actionRoom;

import com.company.business.exception.RoomNotFoundException;
import com.company.business.exception.RoomStatusException;
import com.company.business.repository.RoomRepository;
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
public class ActionEditRoomStatus implements IAction {

    @InjectByType
    private ServiceRoom serviceRoom;
    @InjectByType
    private RoomRepository roomRepository;
    @InjectByType
    private Printer printer;
    @ConfigProperty("changeRoomStatus")
    private boolean changeRoomStatus;

    private static final Logger logger = LogManager.getLogger(ActionEditRoomStatus.class);

    public ActionEditRoomStatus() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        if (changeRoomStatus) {
            try {
                printer.print("Room №:");
                int num = scanner.nextInt();
                printer.print("Press:\n1 - SERVISED\t2 - REPAIRED");
                int press = scanner.nextInt();
                switch (press) {
                    case 1:
                        if (serviceRoom.setStatusServised(num)) {
                            printer.print("Status room № " + num + " changed to SERVISED.\n");
                        }
                        break;
                    case 2:
                        if (serviceRoom.setStatusRepaired(num)) {
                            printer.print("Status room № " + num + " changed to REPAIRED.\n");
                        }
                        break;
                    default:
                        printer.print("Select the correct menu item.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                logger.error("Input mismatch exception");
                printer.print("Error! Enter correct data.\n");
            } catch (IllegalArgumentException e) {
                logger.error("Invalid arguments");
                printer.print("Error! Enter correct arguments.\n");
            } catch (RoomStatusException e) {
                logger.error("Room status exception");
                printer.print("Error! Room is BOOKED.\n");
            } catch (RoomNotFoundException e) {
                logger.error("Room not found exception");
                printer.print("Error! Room not found.\n");
            }
        } else {
            logger.error("Access limited by the configuration of the property");
            printer.print("Access restricted!\n");
        }
    }
}
