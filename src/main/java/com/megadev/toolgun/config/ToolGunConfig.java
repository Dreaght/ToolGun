package com.megadev.toolgun.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ToolGunConfig extends Configurable {
    protected ToolGunConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName, createDefaults());
    }

    private static Map<String, Object> createDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("item", "stick");
        defaults.put("custom-model-data", 10000);
        defaults.put("name", "&dTool Gun");
        return defaults;
    }
}
