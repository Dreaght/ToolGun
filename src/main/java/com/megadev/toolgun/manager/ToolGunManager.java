package com.megadev.toolgun.manager;

import com.megadev.toolgun.object.toolgun.item.ToolGunItem;
import com.megadev.toolgun.object.transfer.ToolGunTransfer;
import com.megadev.toolgun.object.transfer.Transfer;
import com.megadev.toolgun.storage.ToolGunItemData;
import lombok.Getter;

import java.util.UUID;

public class ToolGunManager implements Manager, Addable {
    @Getter private static ToolGunManager instance;
    @Getter private ToolGunItemData toolGunItemData;
    @Getter private ToolGunItem basicToolGun;

    public static Manager init() {
        instance = new ToolGunManager();
        return instance;
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
