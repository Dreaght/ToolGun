package com.megadev.toolgun.listener.menu;

import com.megadev.toolgun.manager.MenuManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        Inventory origInv = MenuManager.getInstance().getToolGunMenu().getInventory();

        if (inventory == null) {
            return;
        }

        if (inventory.equals(origInv)) {
            new ChoiceBlockHandle(event).handleEvent();
            event.setCancelled(true);
        }
    }
}
