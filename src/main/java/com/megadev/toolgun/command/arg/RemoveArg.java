package com.megadev.toolgun.command.arg;

import com.megadev.toolgun.command.AbstractCommand;
import com.megadev.toolgun.limits.LimitHandler;
import com.megadev.toolgun.manager.BlocksManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RemoveArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("remove");
        } else if (args.length == 2) {
            completions.add("all");
        }

        return completions;
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        if (args.length < 1) {
            sendUsageMessage(player);
            return;
        }

        if (args[0].equals("remove")) {
            if (args.length == 2 && args[1].equals("all"))
                handleRemoveAllArg(player.getUniqueId());
            else
                handleRemoveLastArg(player.getUniqueId());
        } else {
            sendUsageMessage(player);
        }
    }

    private void handleRemoveLastArg(UUID uuid) {
        BlocksManager blocksManager = BlocksManager.getInstance();
        blocksManager.removeLastToolGunBlock(uuid);
        BlocksManager.getInstance().getLimitsData().increase(uuid, new LimitHandler(uuid).getLimit());
    }

    private void handleRemoveAllArg(UUID uuid) {
        BlocksManager blocksManager = BlocksManager.getInstance();
        blocksManager.removeBlocks(uuid);
        BlocksManager.getInstance().getLimitsData().fillLimit(uuid, new LimitHandler(uuid).getLimit());
    }

    @Override
    public void sendUsageMessage(Player player) {
        sendUsageMessage(player, "remove <all (optional)>");
    }

    @Override
    public String getPermission() {
        return "";
    }
}
