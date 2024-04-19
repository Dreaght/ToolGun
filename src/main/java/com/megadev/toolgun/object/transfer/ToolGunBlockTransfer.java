package com.megadev.toolgun.object.transfer;

import com.megadev.toolgun.object.toolgun.ToolGunStuff;
import com.megadev.toolgun.object.toolgun.block.IBlock;
import com.megadev.toolgun.object.toolgun.block.ToolGunBlock;

import java.util.UUID;

public class ToolGunBlockTransfer implements Transfer {
    private final UUID uuid;
    private final ToolGunBlock toolGunBlock;

    public ToolGunBlockTransfer(UUID uuid, ToolGunBlock toolGunBlock) {
        this.uuid = uuid;
        this.toolGunBlock = toolGunBlock;
    }

    public ToolGunBlockTransfer(UUID uuid, IBlock block) {
        this.uuid = uuid;
        this.toolGunBlock = getToolGunBlock(block);
    }

    private ToolGunBlock getToolGunBlock(IBlock block) {
        return new ToolGunBlock(uuid, block);
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public ToolGunStuff getStuff() {
        return toolGunBlock;
    }
}
