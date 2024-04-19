package com.megadev.toolgun.listener.status;

import com.megadev.toolgun.limits.LimitHandler;
import com.megadev.toolgun.manager.BlocksManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class StatusListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        BlocksManager.getInstance().getLimitsData().fillLimit(uuid, new LimitHandler(uuid).getLimit());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        BlocksManager.getInstance().removeBlocks(uuid);
        BlocksManager.getInstance().getLimitsData().remove(uuid);
    }
}
