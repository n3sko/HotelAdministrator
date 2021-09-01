package com.company.ui.controller.implAction.actionGuest;

import com.company.business.service.ServiceGuest;
import com.company.ui.controller.IAction;
import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;

@Singleton
public class ActionGetTotalGuests implements IAction {

    @InjectByType
    private ServiceGuest serviceGuest;
    @InjectByType
    private Printer printer;

    public ActionGetTotalGuests() {
    }

    @Override
    public void execute() {
        serviceGuest.getTotalGuests();
        printer.print("Total number of guests: " + serviceGuest.getTotalGuests() + "\n");
    }
}
