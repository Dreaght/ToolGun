package com.megadev.toolgun.object.gui.menu.toolgun;

import com.megadev.toolgun.object.gui.menu.Content;
import com.megadev.toolgun.object.gui.menu.MenuData;
import org.bukkit.inventory.ItemStack;

public class MenuContent implements Content {
    private final MenuData menuData;
    private ItemStack[] contents;

    public MenuContent(MenuData menuData) {
        this.menuData = menuData;
    }

    @Override
    public ItemStack[] getContents() {
        return contents;
    }

    @Override
    public void loadContent() {
        BaseContent baseContent = new BaseContent(new EmptyContent(menuData));
        baseContent.loadContent();

        BlocksContent blocksContent = new BlocksContent(baseContent);
        blocksContent.loadContent();

        setContent(blocksContent);
    }

    @Override
    public void setContent(Content content) {
        this.contents = content.getContents();
    }
}
