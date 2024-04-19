package com.megadev.toolgun.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MessageConfig extends Configurable {
    protected MessageConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName, createDefaults());
    }

    private static Map<String, Object> createDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("limit-actionbar", "&eYou have %LIMIT% blocks remained!");
        defaults.put("invalid-player-arg", "&cPlease, input a valid player!");
        defaults.put("chosen-block", "&eYou've chosen %MATERIAL% as material for your ToolGun!");
        return defaults;
    }
}
