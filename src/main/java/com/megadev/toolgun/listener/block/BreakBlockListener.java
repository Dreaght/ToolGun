package com.megadev.toolgun.listener.block;

import com.megadev.toolgun.manager.BlocksManager;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlockListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        BlocksManager blocksManager = BlocksManager.getInstance();

        boolean isToolGunBlock = blocksManager.getBlocksData().isToolGunBlock(block);

        if (isToolGunBlock) {
            event.setCancelled(true);
        }
    }
}
