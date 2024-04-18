package com.megadev.toolgun.object.gui.menu;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ToolGunMenu {
    @Getter private ToolGunMenuObj toolGunMenuObj;
    @Getter private Inventory

    public ToolGunMenu(ToolGunMenuObj toolGunMenuObj) {
        this.toolGunMenuObj = toolGunMenuObj;
    }

    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(
                null,
                toolGunMenuObj.getMenuRows(),
                toolGunMenuObj.getTitle());


    }
}
