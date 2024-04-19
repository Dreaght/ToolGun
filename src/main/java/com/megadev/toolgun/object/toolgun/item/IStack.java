package com.megadev.toolgun.object.toolgun.item;

import org.bukkit.inventory.ItemStack;


public interface IStack {
    ItemStack getItemStack();
    String getName();
    String getTitle();

    @Override
    boolean equals(Object o);
}
