package com.megadev.toolgun.object.gui.menu.toolgun;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.MenuConfig;
import com.megadev.toolgun.object.gui.menu.Content;
import com.megadev.toolgun.util.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BaseContent implements Content {
    private Content content;
    private ItemStack[] contents;

    public BaseContent(Content content) {
        this.content = content;
        this.contents = content.getContents();
    }

    @Override
    public ItemStack[] getContents() {
        return contents;
    }

    @Override
    public void loadContent() {
        MenuConfig menuManager = ConfigManager.getInstance().getMenuConfig();
        Material emptyMaterial = Material.valueOf(menuManager.getString("empty-slot-item"));
        String emptyName = Color.getTranslated(menuManager.getString("empty-slot-name"));

        for (int i = 0; i < content.getContents().length; i++) {
            contents[i] = getItemStack(emptyMaterial, emptyName);
        }
    }

    private ItemStack getItemStack(Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);
        }

        return itemStack;
    }

    @Override
    public void setContent(Content content) {
        this.content.setContent(content);
    }
}
