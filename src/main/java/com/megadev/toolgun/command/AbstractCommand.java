package com.megadev.toolgun.command;

import com.megadev.toolgun.util.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        }

        commandHandler(player, args);

        return true;
    }

    public abstract List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
                                               @NotNull String alias, @NotNull String[] args);

    public void sendUsageMessage(Player player, String message) {
        player.sendMessage(Color.getTranslated(String.format("&cUsage: /%s %s", "toolgun", message)));
    }

    public abstract void commandHandler(Player player, String[] args);

    public abstract void sendUsageMessage(Player player);

    public abstract String getPermission();
}
