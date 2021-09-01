package com.company.ui.controller.implAction.actionGuest;

import com.company.business.exception.GuestNotFoundException;
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
public class ActionPaymentAmount implements IAction {

    @InjectByType
    private ServiceGuest serviceGuest;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionPaymentAmount.class);

    public ActionPaymentAmount() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            printer.print("Passport ID:");
            int passportData = scanner.nextInt();
            printer.print("Аmount paid by the guest is " + serviceGuest.paymentAmount(passportData) + ".\n");
        } catch (InputMismatchException e) {
            logger.error("Input mismatch exception");
            printer.print("Error! Enter correct data.\n");
        } catch (GuestNotFoundException e) {
            logger.error("Service not found exception");
            printer.print("Error! Service not found.\n");
        }
    }
}
