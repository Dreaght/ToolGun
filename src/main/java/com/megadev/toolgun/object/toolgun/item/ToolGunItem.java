package com.megadev.toolgun.object.toolgun.item;

import com.megadev.toolgun.object.toolgun.Spawnable;
import com.megadev.toolgun.object.toolgun.ToolGunStuff;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class ToolGunItem implements ToolGunStuff, Spawnable {
    @Getter private UUID uuid;
    @Getter private ToolGunObj toolGunObj;
    
    public ToolGunItem(UUID uuid, ToolGunObj toolGunObj) {
        this.uuid = uuid;
        this.toolGunObj = toolGunObj;
    }

    @Override
    public void spawn() {
        getPlayer().getInventory().addItem(getItemStack());
    }

    @Override
    public void remove() {
        getPlayer().getInventory().remove(getItemStack());
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(toolGunObj.getMaterial());

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(toolGunObj.getName());
        itemMeta.setCustomModelData(toolGunObj.getCustomModelData());
        itemMeta.setLore(toolGunObj.getLore());

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
