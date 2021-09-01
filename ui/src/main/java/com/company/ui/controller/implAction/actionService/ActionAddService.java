package com.company.ui.controller.implAction.actionService;

import com.company.business.exception.ServiceNumberExistsException;
import com.company.business.model.Service;
import com.company.business.model.ServiceType;
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
public class ActionAddService implements IAction {

    @InjectByType
    private ServiceService serviceService;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionAddService.class);

    public ActionAddService() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            printer.print("Service №");
            int num = scanner.nextInt();
            scanner.nextLine();
            printer.print("Title:");
            String name = scanner.nextLine();
            printer.print("Type:\n1 - MAIN\t2 - ADDITIONAL\t3 - INDIVIDUAL");
            int str = scanner.nextInt();
            ServiceType type = null;
            switch (str) {
                case 1:
                    type = ServiceType.MAIN;
                    break;
                case 2:
                    type = ServiceType.ADDITIONAL;
                    break;
                case 3:
                    type = ServiceType.INDIVIDUAL;
                    break;
            }
            printer.print("Price:");
            double price = scanner.nextDouble();
            if (serviceService.addService(new Service(num, name, type, price))) {
                printer.print("Service " + name + " №" + num + " added.\n");
            }
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid arguments");
            printer.print("Error! Enter correct arguments.\n");
        } catch (NullPointerException e) {
            logger.error("Invalid link");
            printer.print("Error! Invalid link.\n");
        } catch (ServiceNumberExistsException e) {
            logger.error("Room number exists exception");
            printer.print("Error! Service number exists.\n");
        }
    }
}
