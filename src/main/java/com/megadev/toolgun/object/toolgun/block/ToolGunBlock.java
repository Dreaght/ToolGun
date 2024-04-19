package com.megadev.toolgun.object.toolgun.block;

import com.megadev.toolgun.object.toolgun.Spawnable;
import com.megadev.toolgun.object.toolgun.ToolGunStuff;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

public class ToolGunBlock implements ToolGunStuff, Spawnable {
    @Getter private final UUID uuid;
    @Getter private IBlock iBlock;

    public ToolGunBlock(UUID uuid, IBlock iBlock) {
        this.uuid = uuid;
        this.iBlock = iBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToolGunBlock that = (ToolGunBlock) o;

        return Objects.equals(iBlock.getLocation(), that.getIBlock().getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(iBlock);
    }

    @Override
    public void spawn() {
        iBlock.spawn();
    }

    @Override
    public void remove() {
        iBlock.remove();
    }
}
