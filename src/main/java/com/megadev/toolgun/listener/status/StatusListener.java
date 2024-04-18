package com.megadev.toolgun.listener.status;

import com.megadev.toolgun.manager.BlocksManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        BlocksManager.getInstance().removeBlocks(uuid);
    }
}
