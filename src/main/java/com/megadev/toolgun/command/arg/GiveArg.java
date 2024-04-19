package com.megadev.toolgun.command.arg;

import com.megadev.toolgun.command.AbstractCommand;
import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.manager.ToolGunManager;
import com.megadev.toolgun.object.transfer.ToolGunTransfer;
import com.megadev.toolgun.util.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class GiveArg extends AbstractCommand {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("give");
        } else if (args.length == 2) {
            return null;
        }

        return completions;
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        if (args.length > 2) {
            sendUsageMessage(player);
        }

        if (args.length == 1) {
            ToolGunManager.getInstance().onAdd(new ToolGunTransfer(player.getUniqueId()));
        } else {
            UUID targetUUID;
            try {
                targetUUID = Objects.requireNonNull(Bukkit.getPlayer(args[1])).getUniqueId();
            } catch (NullPointerException exception) {
                String excMessage = ConfigManager.getInstance().getMessageConfig().getString("invalid-player-arg");
                player.sendMessage(Color.getTranslated(excMessage));
                return;
            }

            ToolGunManager.getInstance().onAdd(new ToolGunTransfer(targetUUID));
        }


    }

    @Override
    public void sendUsageMessage(Player player) {
        sendUsageMessage(player, "<give> (<player> optional)");
    }

    @Override
    public String getPermission() {
        return "";
    }
}
