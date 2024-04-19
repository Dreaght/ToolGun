package com.megadev.toolgun.config.manager;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.Configurable;
import com.megadev.toolgun.object.toolgun.item.BukkitItemStack;
import com.megadev.toolgun.object.toolgun.item.IStack;
import com.megadev.toolgun.object.toolgun.item.ItemsAdderStack;
import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserConfig extends Configurable {
    protected UserConfig(@NotNull Plugin plugin, String dataFolderName, String fileName) {
        super(plugin, dataFolderName, fileName, createDefaults());
    }

    private static Map<String, Object> createDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("chosen-material", "STONE");
        return defaults;
    }

    public boolean setIStack(IStack iStack, String customName) {
        if (iStack.equals(getAvailable(iStack))) {
            setValue("chosen-material", customName);
            return true;
        }
        return false;
    }

    public IStack getIStack() {
        String stackName = getString("chosen-material");
        IStack iStack = getIStack(stackName);

        return getAvailable(iStack);
    }

    private IStack getAvailable(IStack iStack) {
        List<IStack> availableStacks =  ConfigManager.getInstance().getBlocksConfig().getAvailableBlocks();

        boolean containsMaterial = availableStacks.contains(iStack);

        if (!containsMaterial) {
            if (!availableStacks.isEmpty())
                return availableStacks.get(0);
        }
        return iStack;
    }

    private IStack getIStack(String stackName) {
        IStack iStack = new BukkitItemStack(new ItemStack(Material.AIR));
        if (stackName.contains("itemsadder:")) {
            stackName = stackName.split(":", 2)[1];

            boolean inReg = CustomBlock.isInRegistry(stackName);

            if (inReg) {
                return new ItemsAdderStack(CustomStack.getInstance(stackName));
            }
        } else {
            try {
                iStack = new BukkitItemStack(new ItemStack(Material.valueOf(stackName)));
            } catch (IllegalArgumentException e) {
                Bukkit.getLogger().warning("INVALID Stack name: " + stackName);
            }

        }

        return iStack;
    }
}
