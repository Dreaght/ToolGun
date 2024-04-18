package com.megadev.toolgun.listener.useitem;

import com.megadev.toolgun.manager.BlocksManager;
import com.megadev.toolgun.manager.MenuManager;
import com.megadev.toolgun.manager.ToolGunManager;
import com.megadev.toolgun.object.toolgun.item.ToolGunItem;
import com.megadev.toolgun.object.transfer.ToolGunBlockTransfer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class UseItemHandler {
    private UUID uuid;
    private final PlayerInteractEvent event;
    private final BlocksManager blocksManager = BlocksManager.getInstance();
    private final MenuManager menuManager = MenuManager.getInstance();

    public UseItemHandler(PlayerInteractEvent event) {
        this.event = event;
    }

    public boolean handleEvent() {
        ItemStack item = event.getItem();

        ToolGunManager toolGunManager = ToolGunManager.getInstance();
        uuid = event.getPlayer().getUniqueId();

        ToolGunItem toolGunItem = toolGunManager.getBasicToolGun();
        if (toolGunItem == null) {
            return false;
        }

        if (item == null || !item.equals(toolGunItem.getItemStack())) {
            return false;
        }

        if (!handleClick()) {
            event.setCancelled(true);
            return false;
        }
        return true;
    }

    private boolean handleClick() {
        Action clickType = event.getAction();
        Block block = event.getClickedBlock();

        switch (clickType) {
            case LEFT_CLICK_BLOCK -> {
                return leftClickHandle(block);
            }
            case RIGHT_CLICK_BLOCK -> {
                return rightClickHandle(block);
            }
            case RIGHT_CLICK_AIR -> {
                return rightClickAirHandle();
            }
            default -> {
                return false;
            }
        }
    }

    private boolean leftClickHandle(Block block) {
        return blocksManager.onRemove(new ToolGunBlockTransfer(uuid, block));
    }

    private boolean rightClickHandle(Block block) {
        BlockFace blockFace = event.getBlockFace();
        return blocksManager.onAdd(new ToolGunBlockTransfer(uuid, block.getRelative(blockFace)));
    }

    private boolean rightClickAirHandle() {
        Player player = event.getPlayer();

        if (player.isSneaking()) {
            Inventory inventory = menuManager.getToolGunMenu().getInventory();
            player.openInventory(inventory);
            return true;
        }
        return false;
    }
}
