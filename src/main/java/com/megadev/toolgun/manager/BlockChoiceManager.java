package com.megadev.toolgun.manager;

import com.megadev.toolgun.object.toolgun.item.ToolGunItemStack;
import com.megadev.toolgun.object.transfer.Transfer;
import com.megadev.toolgun.storage.ChosenBlockData;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class BlockChoiceManager implements Manager, Addable {
    @Getter private static BlockChoiceManager instance;
    @Getter private ChosenBlockData chosenBlockData;
    private Plugin plugin;

    public BlockChoiceManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void init(Plugin plugin) {
        instance = new BlockChoiceManager(plugin);
    }

    @Override
    public void load() {
        chosenBlockData = new ChosenBlockData();
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
        ToolGunItemStack toolGunItemStack = (ToolGunItemStack) transfer.getStuff();

        if (chosenBlockData.addValue(uuid, toolGunItemStack)) {
            toolGunItemStack.spawn();
            return true;
        }

        return false;
    }

    @Override
    public boolean onRemove(Transfer transfer) {
        return false;
    }
}
