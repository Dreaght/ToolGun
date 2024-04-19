package com.megadev.toolgun.limits;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.LimitsConfig;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class LimitHandler {
    private static final LuckPerms api = LuckPermsProvider.get();
    private final LimitsConfig limitsConfig = ConfigManager.getInstance().getLimitsConfig();
    private final Player player;
    private final User user;

    public LimitHandler(Player player) {
        this.player = player;
        this.user = getUser();
    }

    public LimitHandler(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
        this.user = getUser();
    }

    public int getLimit() {
        Object limitObj = limitsConfig.getValue("limits" + "." + getUserGroup());

        if (limitObj == null) {
            limitObj = limitsConfig.getValue("limits" + "." + "default");
        }

        return (int) limitObj;
    }

    public boolean hasForceBreakablePermission() {
        String permission = limitsConfig.getString("breakable-blocks-permission");
        return player.hasPermission(permission);
    }

    public boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public String getUserGroup() {
        return user.getPrimaryGroup();
    }

    private User getUser() {
        return api.getPlayerAdapter(Player.class).getUser(player);
    }
}
