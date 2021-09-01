package com.company.ui;

import com.company.di.ApplicationContext;
import com.company.di.ApplicationRunner;
import com.company.ui.controller.MenuController;

public class AppRunner {

    public static void main(String[] args) {
        ApplicationRunner applicationRunner = new ApplicationRunner();
        ApplicationContext context = applicationRunner.run("com.company");
        context.getObject(MenuController.class, null).run();
    }
}