package com.company.ui.view;

import com.company.di.annotations.Singleton;
import java.util.List;

@Singleton
public class Printer {

    public Printer() {
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void print(Object o) {
        System.out.println(o.toString());
    }

    public void printList(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println();
    }
}
