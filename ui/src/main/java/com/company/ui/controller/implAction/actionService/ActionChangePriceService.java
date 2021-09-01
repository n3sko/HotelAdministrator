package com.company.ui.controller.implAction.actionService;

import com.company.business.exception.ServiceNotFoundException;
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
public class ActionChangePriceService implements IAction {

    @InjectByType
    private ServiceService serviceService;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionChangePriceService.class);

    public ActionChangePriceService() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            printer.print("Service №:");
            int num = scanner.nextInt();
            printer.print("Edit price:");
            double price = scanner.nextInt();
            if (serviceService.changePriceService(num, price)) {
                printer.print("Price of service № " + num +
                        " has been changed to " + price + ".\n");
            }
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid arguments");
            printer.print("Error! Enter correct arguments.\n");
        } catch (ServiceNotFoundException e) {
            logger.error("Service not found exception");
            printer.print("Error! Service not found.\n");
        }
    }
}
