package com.megadev.toolgun.object.gui.menu.toolgun;

import com.megadev.toolgun.object.gui.menu.Content;
import com.megadev.toolgun.object.gui.menu.Menu;
import com.megadev.toolgun.object.gui.menu.MenuData;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ToolGunMenu implements Menu {
    @Getter private MenuData menuData;
    @Getter private Content content;
    private final Inventory inventory;

    public ToolGunMenu(MenuData menuData, Content content, Inventory inventory) {
        this.menuData = menuData;
        this.content = content;
        this.inventory = inventory;
    }

    public ToolGunMenu(MenuData menuData, Content content) {
        this.menuData = menuData;
        this.content = content;
        this.inventory = createInventory();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    private Inventory createInventory() {
        Inventory inventory = Bukkit.createInventory(
                null,
                menuData.getMenuRows() * MenuData.SLOTS_IN_ROW,
                menuData.getTitle());

        inventory.setContents(content.getContents());
        return inventory;
    }
}
