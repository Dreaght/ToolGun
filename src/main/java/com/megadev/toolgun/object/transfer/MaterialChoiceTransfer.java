package com.megadev.toolgun.object.transfer;

import com.megadev.toolgun.object.toolgun.ToolGunStuff;
import com.megadev.toolgun.object.toolgun.item.ToolGunItemStack;

import java.util.UUID;

public class MaterialChoiceTransfer implements Transfer {
    private final UUID uuid;
    private final ToolGunItemStack material;

    public MaterialChoiceTransfer(UUID uuid, ToolGunItemStack material) {
        this.uuid = uuid;
        this.material = material;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public ToolGunStuff getStuff() {
        return material;
    }
}
