package com.megadev.toolgun.listener.item;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.manager.UserConfig;
import com.megadev.toolgun.config.manager.UserManager;
import com.megadev.toolgun.manager.BlocksManager;
import com.megadev.toolgun.manager.MenuManager;
import com.megadev.toolgun.object.toolgun.block.BukkitBlock;
import com.megadev.toolgun.object.toolgun.block.IBlock;
import com.megadev.toolgun.object.toolgun.block.ItemsAdderBlock;
import com.megadev.toolgun.object.transfer.ToolGunBlockTransfer;
import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class UseItemHandler {
    private UUID uuid;
    private final PlayerInteractEvent event;
    private final BlocksManager blocksManager = BlocksManager.getInstance();
    private final MenuManager menuManager = MenuManager.getInstance();
    private final UserManager userManager = ConfigManager.getInstance().getUserManager();

    public UseItemHandler(PlayerInteractEvent event) {
        this.event = event;
    }

    public boolean handleEvent() {
        uuid = event.getPlayer().getUniqueId();

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
        return blocksManager.onRemove(new ToolGunBlockTransfer(uuid, getIBlock(block, true)));
    }

    private boolean rightClickHandle(Block block) {
        BlockFace blockFace = event.getBlockFace();

        if (block.getRelative(blockFace).getType() != Material.AIR) {
            return false;
        }
        return blocksManager.onAdd(new ToolGunBlockTransfer(uuid, getIBlock(block.getRelative(blockFace), false)));
    }

    private IBlock getIBlock(Block block, boolean byPlaced) {
        CustomBlock customBlock;

        if (byPlaced) {
            customBlock = CustomBlock.byAlreadyPlaced(block);
        } else {
            customBlock = CustomBlock.getInstance(getChosenMaterial());
        }

        if (customBlock != null) {
            return new ItemsAdderBlock(uuid, customBlock, block.getLocation());
        } else {
            return new BukkitBlock(uuid, block);
        }
    }

    private String getChosenMaterial() {
        UserConfig userConfig = userManager.getUserConfig(uuid.toString());
        return userConfig.getIStack().getName();
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
