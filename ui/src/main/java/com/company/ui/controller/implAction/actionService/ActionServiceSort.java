package com.company.ui.controller.implAction.actionService;

import com.company.business.service.ServiceService;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

@Singleton
public class ActionServiceSort implements IAction {

    @InjectByType
    private ServiceService serviceService;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionServiceSort.class);

    public ActionServiceSort() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        printer.print("Press:\n1 - Sort price\t2 - Sort type");
        try {
            int press = scanner.nextInt();
            switch (press) {
                case 1:
                    printer.printList(serviceService.servicePricesSortPrice());
                    break;
                case 2:
                    printer.printList(serviceService.servicePricesSortType());
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
