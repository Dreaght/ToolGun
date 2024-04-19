package com.megadev.toolgun.config;

import com.megadev.toolgun.object.toolgun.item.BukkitItemStack;
import com.megadev.toolgun.object.toolgun.item.IStack;
import com.megadev.toolgun.object.toolgun.item.ItemsAdderStack;
import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BlocksConfig extends Configurable {
    protected BlocksConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName, createDefaults());
    }

    private static Map<String, Object> createDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("blocks", null);
        return defaults;
    }

    public List<IStack> getAvailableBlocks() {
        List<IStack> iStackList = new ArrayList<>();

        ConfigurationSection blocksSection = config.getConfigurationSection("blocks");
        if (blocksSection != null) {
            Set<String> keys = blocksSection.getKeys(false);
            for (String key : keys) {
                Object blockObj = blocksSection.get(key);
                if (blockObj instanceof ConfigurationSection) {
                    processNestedBlock(key, (ConfigurationSection) blockObj, iStackList);
                } else {
                    handleUnsupportedBlock(key, blockObj);
                }
            }
        }

        return iStackList;
    }

    private void processNestedBlock(String key, ConfigurationSection blockConfig, List<IStack> iStackList) {
        try {
            String stackName = key;
            String title = blockConfig.getString("title");
            title = ChatColor.translateAlternateColorCodes('&', title);

            processStack(stackName, title, iStackList);
        } catch (Exception e) {
            Bukkit.getLogger().warning("Error processing block configuration for '" + key + "': " + e.getMessage());
        }
    }

    private void processStack(String stackName, String title, List<IStack> iStackList) {
        try {
            if (stackName.startsWith("itemsadder:")) {
                stackName = stackName.split(":", 2)[1];
                boolean inReg = CustomBlock.isInRegistry(stackName);
                if (inReg) {
                    CustomStack stack = CustomStack.getInstance(stackName);
                    stack.setDisplayName(title);
                    iStackList.add(new ItemsAdderStack(stack));
                    return;
                }
            }
            ItemStack itemStack = new ItemStack(Material.valueOf(stackName));

            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(title);
            itemStack.setItemMeta(itemMeta);

            iStackList.add(new BukkitItemStack(itemStack));
        } catch (IllegalArgumentException e) {
            Bukkit.getLogger().warning("Material not found: " + stackName);
        }
    }

    private void handleUnsupportedBlock(String key, Object blockObj) {
        Bukkit.getLogger().warning("Unsupported block configuration type for '" + key + "': " + blockObj.getClass().getSimpleName());
    }
}
