package com.megadev.toolgun.object.toolgun.item;

import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

public class BukkitItemStack implements IStack {
    private final UUID uuid;
    private final ItemStack itemStack;

    public BukkitItemStack(UUID uuid, ItemStack itemStack) {
        this.uuid = uuid;
        this.itemStack = itemStack;
    }

    public BukkitItemStack(ItemStack itemStack) {
        this.uuid = null;
        this.itemStack = itemStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BukkitItemStack that = (BukkitItemStack) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public String getName() {
        return itemStack.getType().name();
    }

    @Override
    public String getTitle() {
        return Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
    }
}
