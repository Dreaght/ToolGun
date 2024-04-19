package com.megadev.toolgun.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MenuConfig extends Configurable {
    protected MenuConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName, createDefaults());
    }

    private static Map<String, Object> createDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("title", "&7Available blocks");
        defaults.put("size", 6);
        return defaults;
    }
}
