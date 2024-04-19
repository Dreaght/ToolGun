package com.megadev.toolgun.object.transfer;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.ToolGunConfig;
import com.megadev.toolgun.object.toolgun.ToolGunStuff;
import com.megadev.toolgun.object.toolgun.item.ToolGunItem;
import com.megadev.toolgun.object.toolgun.item.ToolGunObj;
import com.megadev.toolgun.util.Color;
import org.bukkit.Material;

import java.util.List;
import java.util.UUID;

public class ToolGunTransfer implements Transfer {
    private final UUID uuid;
    private final ToolGunItem toolGunItem;

    public ToolGunTransfer(UUID uuid, ToolGunItem toolGunItem) {
        this.uuid = uuid;
        this.toolGunItem = toolGunItem;
    }

    public ToolGunTransfer(UUID uuid) {
        this.uuid = uuid;
        this.toolGunItem = generateToolGun();
    }

    public ToolGunItem generateToolGun() {
        ToolGunConfig toolGunConfig = ConfigManager.getInstance().getToolGunConfig();

        String name = Color.getTranslated(toolGunConfig.getString("name"));
        List<String> lore = Color.getTranslated(toolGunConfig.getStringList("lore"));
        Material material = Material.valueOf(toolGunConfig.getString("item"));
        int customModelData = (int) toolGunConfig.getValue("custom-model-data");

        ToolGunObj toolGunObj = new ToolGunObj(name, lore, material, customModelData);

        ToolGunItem toolGunItem = new ToolGunItem(uuid, toolGunObj);
        return toolGunItem;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public ToolGunStuff getStuff() {
        return toolGunItem;
    }
}
