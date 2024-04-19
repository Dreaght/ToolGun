package com.megadev.toolgun.object.toolgun.block;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.manager.UserManager;
import dev.lone.itemsadder.api.CustomBlock;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Objects;
import java.util.UUID;

@Getter
public class ItemsAdderBlock implements IBlock {
    private final UserManager userManager = ConfigManager.getInstance().getUserManager();

    private final UUID uuid;
    private final CustomBlock block;
    private final Location location;

    public ItemsAdderBlock(UUID uuid, CustomBlock block) {
        this.uuid = uuid;
        this.block = block;
        this.location = null;
    }

    public ItemsAdderBlock(UUID uuid, CustomBlock block, Location location) {
        this.uuid = uuid;
        this.block = block;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsAdderBlock that = (ItemsAdderBlock) o;
        return Objects.equals(getBlock(), that.getBlock());
    }

    @Override
    public Block getBlock() {
        return block.getBlock();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void spawn() {
        block.place(location);
    }

    @Override
    public void remove() {
        block.remove();
    }
}
