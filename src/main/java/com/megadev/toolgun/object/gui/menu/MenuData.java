package com.megadev.toolgun.object.gui.menu;

import lombok.Getter;

public class ToolGunMenuObj {
    @Getter private String title;
    @Getter private int menuRows;

    public ToolGunMenuObj(String title, int menuRows) {
        this.title = title;
        this.menuRows = menuRows;
    }
}
