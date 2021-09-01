package com.company.ui.controller;

import java.util.List;

public interface IMenu {

    String getName();

    void setName(String name);

    List<MenuItem> getMenuItems();

    void setMenuItems(List<MenuItem> menuItems);
}
