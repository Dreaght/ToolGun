package com.megadev.toolgun.object.toolgun.item;

import com.megadev.toolgun.object.toolgun.Spawnable;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemToolGun implements Spawnable {
    @Getter private Player holder;
    @Getter private ToolGunObj toolGunObj;
    
    public ItemToolGun(Player holder, ToolGunObj toolGunObj) {
        this.holder = holder;
        this.toolGunObj = toolGunObj;
    }

    @Override
    public void spawn() {
        holder.getInventory().addItem(getItemStack());
    }

    @Override
    public void remove() {
        holder.getInventory().remove(getItemStack());
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
}
