package com.megadev.toolgun.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class LimitsConfig extends Configurable {
    protected LimitsConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName, createDefaults());
    }

    private static Map<String, Object> createDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("breakable-blocks-permission", "toolgun.ob");
        defaults.put("limits", null);
        defaults.put("limits.default", 1500);
        return defaults;
    }
}
