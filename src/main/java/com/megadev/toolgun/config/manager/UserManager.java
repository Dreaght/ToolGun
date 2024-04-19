package com.megadev.toolgun.config.manager;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

public class UserManager {
    @Getter private static UserManager instance;
    private final Plugin plugin;
    private final String dataFolder;

    public UserManager(Plugin plugin, String dataFolder) {
        this.plugin = plugin;
        this.dataFolder = dataFolder;
    }

    public static UserManager init(Plugin plugin, String dataFolder) {
        if (instance == null) {
            instance = new UserManager(plugin, dataFolder);
        }

        return instance;
    }

    public UserConfig getUserConfig(String uuid) {
        return new UserConfig(plugin, dataFolder, uuid);
    }
}
