package com.megadev.toolgun.object.gui.menu;

import lombok.Getter;

public class MenuData {
    public static final int SLOTS_IN_ROW = 9;
    @Getter private String title;
    @Getter private int menuRows;

    public MenuData(String title, int menuRows) {
        this.title = title;
        this.menuRows = menuRows;
    }
}
