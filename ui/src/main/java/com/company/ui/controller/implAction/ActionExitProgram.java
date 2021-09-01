package com.company.ui.controller.implAction;

import com.company.business.repository.GuestRepository;
import com.company.business.repository.RoomRepository;
import com.company.business.repository.ServiceRepository;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.util.Serialization;
import com.company.ui.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

@Singleton
public class ActionExitProgram implements IAction {

    @InjectByType
    private GuestRepository guestRepository;
    @InjectByType
    private RoomRepository roomRepository;
    @InjectByType
    private ServiceRepository serviceRepository;
    @InjectByType
    private Serialization serialization;
    @InjectByType
    private Printer printer;

    private static final Logger logger = LogManager.getLogger(ActionExitProgram.class);

    public ActionExitProgram() {
    }

    @Override
    public void execute() {
        try {
            serialization.writeData(guestRepository.getAll(), "guest");
            serialization.writeData(roomRepository.getAll(), "room");
            serialization.writeData(serviceRepository.getAll(), "service");
        } catch (IOException e) {
            logger.error("Serialization error");
            printer.print("Error! Serialization error.\n");
        }
        System.exit(0);
    }
}
