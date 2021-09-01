package com.company.ui.controller;

import com.company.di.annotations.InjectByType;
import com.company.di.annotations.Singleton;
import com.company.ui.view.Printer;

@Singleton
public class Navigator {

    @InjectByType
    private Printer printer;

    private Menu currentMenu;

    public Navigator() {
    }

    public void printMenu() {
        printer.print(currentMenu.getName());
        for (MenuItem menuItem : currentMenu.getMenuItems()) {
            printer.print(menuItem.getTitle());
        }
    }

    public void navigate(Integer index) {
        if (currentMenu.getMenuItems().get(index).getAction() == null) {
            currentMenu = currentMenu.getMenuItems().get(index).getNextMenu();
        } else {
            currentMenu.getMenuItems().get(index).doAction();
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
