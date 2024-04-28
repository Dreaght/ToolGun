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
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ToolGun extends JavaPlugin {
    @Override
    public void onEnable() {
        setupManagers();
        registerListeners();
        Objects.requireNonNull(getCommand("toolgun")).setExecutor(new ToolGunCommand());
        loadMenuDelayed();
    }

    private void setupManagers() {
        ConfigManager.init(this);
        BlocksManager.init().load();
        ToolGunManager.init().load();
        BlockChoiceManager.init().load();
        MenuManager.init(this);
    }

    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new UseItemListener(), this);
        pluginManager.registerEvents(new StatusListener(), this);
        pluginManager.registerEvents(new MenuListener(), this);
        pluginManager.registerEvents(new BreakBlockListener(), this);
    }

    private int taskID;

    private void loadMenuDelayed() {
        taskID = Bukkit.getScheduler().runTaskTimer(this, () -> {
            MenuManager menuManager = MenuManager.getInstance();

            menuManager.load();

            if (menuManager.isLoaded()) {
                Bukkit.getScheduler().cancelTask(taskID);
            }

        }, 5, 5).getTaskId();
    }

    @Override
    public void onDisable() {
        BlocksManager.getInstance().stop();
    }
}
