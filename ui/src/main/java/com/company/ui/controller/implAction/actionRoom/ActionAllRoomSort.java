package com.company.ui.controller.implAction.actionRoom;

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
public class ActionAllRoomSort implements IAction {

    @InjectByType
    private ServiceRoom serviceRoom;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionAllRoomSort.class);

    public ActionAllRoomSort() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        printer.print("Press:\n1 - Sort price\t2 - Sort star\t3 - Sort capacity");
        try {
            int press = scanner.nextInt();
            switch (press) {
                case 1:
                    printer.printList(serviceRoom.allRoomSortPrice());
                    break;
                case 2:
                    printer.printList(serviceRoom.allRoomSortStar());
                    break;
                case 3:
                    printer.printList(serviceRoom.allRoomSortСapacity());
                    break;
                default:
                    printer.print("Select the correct menu item.\n");
                    break;
            }
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        }
    }
}
