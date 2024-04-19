package com.megadev.toolgun.object.toolgun.block;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.manager.UserConfig;
import com.megadev.toolgun.config.manager.UserManager;
import com.megadev.toolgun.object.toolgun.item.BukkitItemStack;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Objects;
import java.util.UUID;

@Getter
public class BukkitBlock implements IBlock {
    private final UserManager userManager = ConfigManager.getInstance().getUserManager();

    private final UUID uuid;
    private final Block block;

    public BukkitBlock(UUID uuid, Block block) {
        this.uuid = uuid;
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BukkitBlock that = (BukkitBlock) o;
        return Objects.equals(getBlock(), that.getBlock());
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public Location getLocation() {
        return block.getLocation();
    }

    @Override
    public void spawn() {
        block.getWorld().getBlockAt(block.getLocation()).setType(getChosenMaterial());
    }

    private Material getChosenMaterial() {
        UserConfig userConfig = userManager.getUserConfig(uuid.toString());
        return userConfig.getIStack().getItemStack().getType();
    }

    @Override
    public void remove() {
        block.getWorld().getBlockAt(block.getLocation()).setType(Material.AIR);
    }
}
