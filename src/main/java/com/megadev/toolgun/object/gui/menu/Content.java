package com.megadev.toolgun.object.gui.menu;

import org.bukkit.inventory.ItemStack;

public interface Content {
    ItemStack[] getContents();

    void loadContent();

    void setContent(Content content);
}
