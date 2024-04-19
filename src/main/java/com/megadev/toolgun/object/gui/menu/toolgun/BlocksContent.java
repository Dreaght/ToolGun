package com.megadev.toolgun.object.gui.menu.toolgun;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.object.gui.menu.Content;
import com.megadev.toolgun.object.toolgun.item.IStack;
import com.megadev.toolgun.util.Color;
import com.megadev.toolgun.util.ParsePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BlocksContent implements Content {
    private Content content;
    private ItemStack[] contents;

    public BlocksContent(Content content) {
        this.content = content;
        this.contents = content.getContents();
    }

    @Override
    public ItemStack[] getContents() {
        return contents;
    }

    @Override
    public void loadContent() {
        ConfigManager configManager = ConfigManager.getInstance();
        List<IStack> iStackList = configManager.getBlocksConfig().getAvailableBlocks();

        String rawName = Color.getTranslated(configManager.getMenuConfig().getString("block-name"));

        List<String> lore = Color.getTranslated(configManager.getMenuConfig().getStringList("block-lore"));

        for (int i = 0; i < iStackList.size(); i++) {
            if (iStackList.get(i) != null) {
                String parsedName = ParsePlaceholder.parseWithBraces(rawName,
                        new String[]{"BLOCK_NAME"}, new String[]{iStackList.get(i).getTitle()});
                contents[i] = getItemStack(iStackList.get(i), parsedName, lore);
            } else {
                Bukkit.getLogger().warning("Invalid block defined in 'blocks.yml' config!");
            }
        }
    }

    private ItemStack getItemStack(IStack iStack, String name, List<String> lore) {
        ItemStack itemStack = iStack.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void setContent(Content content) {
        this.content = content;
    }
}
