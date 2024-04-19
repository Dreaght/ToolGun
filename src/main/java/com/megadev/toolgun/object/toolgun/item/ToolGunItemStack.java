package com.megadev.toolgun.object.toolgun.item;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.config.manager.UserManager;
import com.megadev.toolgun.object.toolgun.Spawnable;
import com.megadev.toolgun.object.toolgun.ToolGunStuff;
import com.megadev.toolgun.util.Color;
import com.megadev.toolgun.util.ParsePlaceholder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class ToolGunItemStack implements ToolGunStuff, Spawnable {
    private final UserManager userManager = ConfigManager.getInstance().getUserManager();

    private final UUID uuid;
    private final IStack iStack;

    public ToolGunItemStack(UUID uuid, IStack iStack) {
        this.uuid = uuid;
        this.iStack = iStack;
    }

    @Override
    public void spawn() {
        String name = iStack.getName();
        if (iStack instanceof ItemsAdderStack) {
            name = "itemsadder:" + name;
        }

        if (userManager.getUserConfig(uuid.toString()).setIStack(iStack, name)) {
            Player player = Bukkit.getPlayer(uuid);

            if (player != null) {
                String chosenMsgRaw = ConfigManager.getInstance().getMessageConfig().getString("chosen-block");

                String chosenMsg = ParsePlaceholder.parseWithBraces(chosenMsgRaw,
                        new String[]{"MATERIAL"},
                        new String[]{iStack.getTitle()});

                player.sendMessage(Color.getTranslated(chosenMsg));
            }
        }
    }

    @Override
    public void remove() {

    }
}
