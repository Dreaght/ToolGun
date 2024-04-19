package com.megadev.toolgun.listener.menu;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.manager.BlockChoiceManager;
import com.megadev.toolgun.object.toolgun.item.BukkitItemStack;
import com.megadev.toolgun.object.toolgun.item.IStack;
import com.megadev.toolgun.object.toolgun.item.ItemsAdderStack;
import com.megadev.toolgun.object.toolgun.item.ToolGunItemStack;
import com.megadev.toolgun.object.transfer.MaterialChoiceTransfer;
import com.megadev.toolgun.util.Color;
import com.megadev.toolgun.util.ParsePlaceholder;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ChoiceBlockHandle {
    private UUID uuid;
    private final InventoryClickEvent event;
    private final BlockChoiceManager blockChoiceManager = BlockChoiceManager.getInstance();

    public ChoiceBlockHandle(InventoryClickEvent event) {
        this.event = event;
    }

    public boolean handleEvent() {
        uuid = event.getWhoClicked().getUniqueId();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null) {
            return false;
        }

        IStack iStack;

        CustomStack customStack = CustomStack.byItemStack(itemStack);

        if (customStack != null) {
            iStack = new ItemsAdderStack(customStack);
        } else {
            if (!itemStack.getType().isBlock()) {
                return false;
            }

            iStack = new BukkitItemStack(itemStack);
        }

        return blockChoiceHandle(iStack);
    }

    private boolean blockChoiceHandle(IStack iStack) {
        return blockChoiceManager.onAdd(new MaterialChoiceTransfer(uuid, new ToolGunItemStack(uuid, iStack)));
    }
}
