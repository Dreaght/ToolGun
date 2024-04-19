package com.megadev.toolgun.manager;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.MenuConfig;
import com.megadev.toolgun.object.gui.menu.Menu;
import com.megadev.toolgun.object.gui.menu.MenuData;
import com.megadev.toolgun.object.gui.menu.toolgun.MenuContent;
import com.megadev.toolgun.object.gui.menu.toolgun.ToolGunMenu;
import com.megadev.toolgun.util.Color;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

public class MenuManager implements Manager {
    @Getter private static MenuManager instance;

    @Getter private Plugin plugin;
    @Getter private Menu toolGunMenu;

    private MenuManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void init(Plugin plugin) {
        instance = new MenuManager(plugin);
    }

    @Override
    public void load() {
        MenuConfig menuConfig = ConfigManager.getInstance().getMenuConfig();

        MenuData menuData = new MenuData(
                Color.getTranslated(menuConfig.getString("title")),
                (int) menuConfig.getValue("size"));

        MenuContent menuContent = new MenuContent(menuData);
        menuContent.loadContent();

        toolGunMenu = new ToolGunMenu(menuData, menuContent);
    }

    @Override
    public void update() {
    }

    @Override
    public void stop() {
    }
}
