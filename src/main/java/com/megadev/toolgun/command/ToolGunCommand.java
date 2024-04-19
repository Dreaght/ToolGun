package com.megadev.toolgun.command;

import com.megadev.toolgun.command.arg.GiveArg;
import com.megadev.toolgun.command.arg.RemoveArg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ToolGunCommand implements CommandExecutor, TabCompleter {
    private final Map<String, AbstractCommand> subcommands;

    public ToolGunCommand() {
        this.subcommands = new HashMap<>();
        subcommands.put("give", new GiveArg());
        subcommands.put("remove", new RemoveArg());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0 && contains(args[0])) {
            subcommands.get(args[0].toLowerCase()).onCommand(sender, command, label, args);
        } else {
            sender.sendMessage(String.format("Usage: /toolgun %s", subcommands.keySet()));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(subcommands.keySet());
        } else if (subcommands.containsKey(args[0].toLowerCase())) {
            return subcommands.get(args[0].toLowerCase()).onTabComplete(sender, command, alias, args);
        }
        return Collections.emptyList();
    }

    private boolean contains(String argStr) {
        return subcommands.containsKey(argStr.toLowerCase());
    }
}
