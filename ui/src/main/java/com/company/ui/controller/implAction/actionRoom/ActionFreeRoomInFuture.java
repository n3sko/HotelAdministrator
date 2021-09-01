package com.company.ui.controller.implAction.actionRoom;

import com.company.business.service.ServiceRoom;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Singleton
public class ActionFreeRoomInFuture implements IAction {

    @InjectByType
    private ServiceRoom serviceRoom;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionFreeRoomInFuture.class);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ActionFreeRoomInFuture() {
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            printer.print("Date (DD/MM/YYYY):");
            String date = scanner.next();
            serviceRoom.freeRoomInFuture(date);
            printer.printList(serviceRoom.freeRoomInFuture(date));
        } catch (DateTimeParseException e) {
            logger.error("Invalid date format");
            printer.print("Error! Enter correct date format.\n");
        }
    }
}
