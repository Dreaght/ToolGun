package com.megadev.toolgun.config;

import com.megadev.toolgun.config.manager.UserManager;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
    @Getter private static ConfigManager instance;
    @Getter private final UserManager userManager;
    @Getter private final ToolGunConfig toolGunConfig;
    @Getter private final BlocksConfig blocksConfig;
    @Getter private final MenuConfig menuConfig;
    @Getter private final LimitsConfig limitsConfig;
    @Getter private final MessageConfig messageConfig;

    private ConfigManager(Plugin plugin) {
        this.userManager = UserManager.init(plugin, "userdata");
        this.toolGunConfig = new ToolGunConfig(plugin, "toolgun");
        this.blocksConfig = new BlocksConfig(plugin, "blocks");
        this.menuConfig = new MenuConfig(plugin, "menu");
        this.limitsConfig = new LimitsConfig(plugin, "limits");
        this.messageConfig = new MessageConfig(plugin, "messages");
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }
    }
}
