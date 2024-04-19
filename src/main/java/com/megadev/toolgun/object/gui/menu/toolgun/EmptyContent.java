package com.megadev.toolgun.object.gui.menu.toolgun;

import com.megadev.toolgun.object.gui.menu.Content;
import com.megadev.toolgun.object.gui.menu.MenuData;
import org.bukkit.inventory.ItemStack;

public class EmptyContent implements Content {
    private final MenuData menuData;

    public EmptyContent(MenuData menuData) {
        this.menuData = menuData;
    }

    @Override
    public ItemStack[] getContents() {
        return new ItemStack[menuData.getMenuRows() * MenuData.SLOTS_IN_ROW];
    }

    @Override
    public void loadContent() {

    }

    @Override
    public void setContent(Content content) {

    }
}
