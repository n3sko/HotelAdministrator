package com.company.ui.controller.implAction.actionGuest;

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
public class ActionGuestsSort implements IAction {

    @InjectByType
    private ServiceGuest serviceGuest;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionGuestsSort.class);

    public ActionGuestsSort() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        printer.print("Press:\n1 - Sort alphabetically\t2 - Sort Departure Date");
        try {
            int press = scanner.nextInt();
            switch (press) {
                case 1:
                    printer.printList(serviceGuest.guestsSortAlphabetically());
                    break;
                case 2:
                    printer.printList(serviceGuest.guestsSortDepartureDate());
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
