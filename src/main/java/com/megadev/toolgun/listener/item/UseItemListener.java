package com.megadev.toolgun.listener.useitem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

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

        new UseItemHandler(event).handleEvent();

        lastUseTime.put(player, System.currentTimeMillis());
    }
}
