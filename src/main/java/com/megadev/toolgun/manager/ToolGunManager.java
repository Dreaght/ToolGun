package com.megadev.toolgun.manager;

import com.megadev.toolgun.object.toolgun.item.ToolGunItem;
import com.megadev.toolgun.object.transfer.ToolGunTransfer;
import com.megadev.toolgun.object.transfer.Transfer;
import com.megadev.toolgun.storage.ToolGunItemData;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class ToolGunManager implements Manager, Addable {
    @Getter private static ToolGunManager instance;
    @Getter private ToolGunItemData toolGunItemData;
    @Getter private ToolGunItem basicToolGun;
    private Plugin plugin;

    private ToolGunManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void init(Plugin plugin) {
        instance = new ToolGunManager(plugin);
    }

    @Override
    public void load() {
        toolGunItemData = new ToolGunItemData();
        basicToolGun = new ToolGunTransfer(UUID.randomUUID()).generateToolGun();
    }

    @Override
    public void update() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean onAdd(Transfer transfer) {
        UUID uuid = transfer.getUUID();
        ToolGunItem toolGunItem = (ToolGunItem) transfer.getStuff();

        toolGunItemData.addValue(uuid, toolGunItem);
        toolGunItem.spawn();
        return true;
    }

    @Override
    public boolean onRemove(Transfer transfer) {
        return false;
    }
}
