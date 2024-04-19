package com.megadev.toolgun.object.toolgun.item;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

public class ItemsAdderStack implements IStack {
    private final UUID uuid;
    private final CustomStack itemStack;

    public ItemsAdderStack(UUID uuid, CustomStack itemStack) {
        this.uuid = uuid;
        this.itemStack = itemStack;
    }

    public ItemsAdderStack(CustomStack itemStack) {
        this.uuid = null;
        this.itemStack = itemStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsAdderStack that = (ItemsAdderStack) o;

        return Objects.equals(getName(), that.getName());
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack.getItemStack();
    }

    @Override
    public String getName() {
        return itemStack.getNamespacedID();
    }

    @Override
    public String getTitle() {
        return itemStack.getDisplayName();
    }
}
