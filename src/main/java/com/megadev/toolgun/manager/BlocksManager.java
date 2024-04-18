package com.megadev.toolgun.manager;

import com.megadev.toolgun.object.toolgun.block.ToolGunBlock;
import com.megadev.toolgun.storage.BlocksData;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;

public class ToolGunManager implements Manager {
    @Getter private static ToolGunManager instance;

    @Getter private Plugin plugin;
    @Getter private BlocksData data;
    @Getter private LuckPerms api;

    private ToolGunManager(Plugin plugin, LuckPerms api) {
        this.plugin = plugin;
        this.data = new BlocksData();

        this.api = api;
    }

    public static void init(Plugin plugin, LuckPerms api) {
        instance = new ToolGunManager(plugin, api);
    }

    public void onRightClick(Player player, Block block) {
        UUID uuid = player.getUniqueId();

        if (!canPlaceBlock(uuid)) {
            return;
        }

        ToolGunBlock toolGunBlock = new ToolGunBlock(player, block);

        if (!data.contains(uuid)) {
            data.addValue(uuid, List.of());
        }

        data.getValue(uuid).add(toolGunBlock);
    }

    public boolean canPlaceBlock(UUID uuid) {
        int size = data.getValue(uuid).size();

        User user = api.getUserManager().getUser(uuid);
        assert user != null;

        user.getPrimaryGroup();

        return false;
    }

    public void onLeftClick(Player player, Block block) {
        UUID uuid = player.getUniqueId();

        if (!data.contains(uuid)) {
            return;
        }

        List<ToolGunBlock> toolGunBlocks = data.getValue(uuid);

        for (ToolGunBlock tgBlock : toolGunBlocks) {
            if (!tgBlock.getBlock().equals(block)) {
                continue;
            }

            tgBlock.remove();
            toolGunBlocks.remove(tgBlock);
        }
    }

    public boolean canRemoveBlock(UUID uuid) {
        return false;
    }

    @Override
    public void load() {

    }

    @Override
    public void update() {

    }

    @Override
    public void stop() {

    }
}
