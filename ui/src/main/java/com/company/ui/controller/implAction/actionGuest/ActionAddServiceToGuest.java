package com.company.ui.controller.implAction.actionGuest;

import com.company.business.exception.ServiceNotFoundException;
import com.company.business.service.ServiceGuest;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Singleton
public class ActionAddServiceToGuest implements IAction {

    @InjectByType
    private ServiceGuest serviceGuest;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionAddServiceToGuest.class);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ActionAddServiceToGuest() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            printer.print("Passport ID:");
            int passport = scanner.nextInt();
            printer.print("Service №:");
            int numService = scanner.nextInt();
            printer.print("Date (DD/MM/YYYY):");
            String date = scanner.next();
            LocalDate date1 = LocalDate.parse(date, formatter);
            if (serviceGuest.addServiceToGuest(passport, numService, date1)) {
                printer.print("Service № " + numService + " added to client.\n");
            }
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        } catch (DateTimeParseException e) {
            logger.error("Invalid date format");
            printer.print("Error! Enter correct date format.\n");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid arguments");
            printer.print("Error! Enter correct arguments.\n");
        } catch (ServiceNotFoundException e) {
            logger.error("Service not found exception");
            printer.print("Error! Service not found.\n");
        }
    }
}
