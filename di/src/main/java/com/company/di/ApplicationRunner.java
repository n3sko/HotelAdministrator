package com.company.di;

public class ApplicationRunner {
    public static ApplicationContext run(String packageToScan) {
        ApplicationContext context = new ApplicationContext(packageToScan);
        return context;
    }
}
