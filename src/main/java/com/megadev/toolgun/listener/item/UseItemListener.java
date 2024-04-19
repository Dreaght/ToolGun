package com.megadev.toolgun.listener.item;

import com.megadev.toolgun.manager.ToolGunManager;
import com.megadev.toolgun.object.toolgun.item.ToolGunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class UseItemListener implements Listener {
    private static final Map<Player, Long> lastUseTime = new HashMap<>();
    private static final long COOLDOWN_MS = 50;

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (lastUseTime.containsKey(player)) {
            long lastTime = lastUseTime.get(player);
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime < COOLDOWN_MS) {
                return;
            }
        }

        if (isToolGunItem(event)) {
            new UseItemHandler(event).handleEvent();
        }

        lastUseTime.put(player, System.currentTimeMillis());
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (isToolGunItem(event)) {
            event.setCancelled(true);
        }
    }

    private boolean isToolGunItem(PlayerInteractEvent event) {
        ItemStack item = event.getItem();

        return isToolGunItem(item);
    }

    private boolean isToolGunItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();

        return isToolGunItem(item);
    }

    private boolean isToolGunItem(ItemStack item) {
        ToolGunManager toolGunManager = ToolGunManager.getInstance();

        ToolGunItem toolGunItem = toolGunManager.getBasicToolGun();
        if (toolGunItem == null) {
            return false;
        }

        if (item == null || !item.equals(toolGunItem.getItemStack())) {
            return false;
        }

        return true;
    }
}
