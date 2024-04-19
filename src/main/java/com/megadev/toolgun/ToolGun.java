package com.megadev.toolgun;

import com.megadev.toolgun.command.ToolGunCommand;
import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.listener.block.BreakBlockListener;
import com.megadev.toolgun.listener.menu.MenuListener;
import com.megadev.toolgun.listener.status.StatusListener;
import com.megadev.toolgun.listener.item.UseItemListener;
import com.megadev.toolgun.manager.BlockChoiceManager;
import com.megadev.toolgun.manager.MenuManager;
import com.megadev.toolgun.manager.BlocksManager;
import com.megadev.toolgun.manager.ToolGunManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ToolGun extends JavaPlugin {
    @Override
    public void onEnable() {
        setupManagers();

        Objects.requireNonNull(getCommand("toolgun")).setExecutor(new ToolGunCommand());
        getServer().getPluginManager().registerEvents(new UseItemListener(), this);
        getServer().getPluginManager().registerEvents(new StatusListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new BreakBlockListener(), this);
    }

    private void setupManagers() {
        ConfigManager.init(this);

        BlocksManager.init(this);
        BlocksManager.getInstance().load();

        ToolGunManager.init(this);
        ToolGunManager.getInstance().load();

        MenuManager.init(this);
        MenuManager.getInstance().load();

        BlockChoiceManager.init(this);
        BlockChoiceManager.getInstance().load();
    }

    @Override
    public void onDisable() {
        BlocksManager.getInstance().stop();
    }
}
