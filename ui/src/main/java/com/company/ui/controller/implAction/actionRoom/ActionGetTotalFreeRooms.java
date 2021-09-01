package com.company.ui.controller.implAction.actionRoom;

import com.company.business.service.ServiceRoom;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;

@Singleton
public class ActionGetTotalFreeRooms implements IAction {

    @InjectByType
    private ServiceRoom serviceRoom;
    @InjectByType
    private Printer printer;

    public ActionGetTotalFreeRooms() {
    }

    @Override
    public void execute() {
        printer.print("Total number of free rooms: " + serviceRoom.getTotalFreeRooms() + "\n");
    }
}
