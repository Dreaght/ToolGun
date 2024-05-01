package com.megadev.toolgun.manager;

import com.megadev.toolgun.config.ConfigManager;
import com.megadev.toolgun.limits.LimitHandler;
import com.megadev.toolgun.object.toolgun.block.ToolGunBlock;
import com.megadev.toolgun.object.transfer.Transfer;
import com.megadev.toolgun.storage.BlocksData;
import com.megadev.toolgun.storage.LimitsData;
import com.megadev.toolgun.util.ActionBar;
import com.megadev.toolgun.util.Color;
import com.megadev.toolgun.util.ParsePlaceholder;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BlocksManager implements Manager, Addable {
    @Getter private static BlocksManager instance;
    @Getter private BlocksData blocksData;
    @Getter private LimitsData limitsData;

    private BlocksManager() {
        this.blocksData = new BlocksData();
    }

    public static Manager init() {
        instance = new BlocksManager();
        return instance;
    }

    @Override
    public void load() {
        blocksData = new BlocksData();
        limitsData = new LimitsData();
    }

    @Override
    public void update() {

    }

    @Override
    public void stop() {
        try {
            blocksData.getData().values().stream()
                    .flatMap(List::stream)
                    .forEach(ToolGunBlock::remove);
        } catch (NoClassDefFoundError ignore) {
        }
    }

    public void removeLastToolGunBlock(UUID key) {
        List<ToolGunBlock> blocks = blocksData.getData().get(key);
        if (blocks != null && !blocks.isEmpty()) {
            blocks.get(blocks.size() - 1).remove();
            blocks.remove(blocks.size() - 1);
            if (blocks.isEmpty()) {
                blocksData.remove(key);
            }
        }
    }

    public void removeBlocks(UUID uuid) {
        List<ToolGunBlock> playerBlocks = blocksData.getData().get(uuid);
        if (playerBlocks != null) {
            playerBlocks.forEach(ToolGunBlock::remove);
            playerBlocks.clear();
        }
    }

    @Override
    public boolean onAdd(Transfer transfer) {
        UUID uuid = transfer.getUUID();
        ToolGunBlock toolGunBlock = (ToolGunBlock) transfer.getStuff();
        toolGunBlock.spawn();

        if (blocksData.addToolGunBlock(uuid, toolGunBlock)) {
            if (limitsData.decrease(uuid, getLimits(uuid).getLimit())) {
                sendLimitInfo(uuid);
                return true;
            }
        }

        toolGunBlock.remove();
        blocksData.removeBlock(toolGunBlock);
        return false;
    }

    @Override
    public boolean onRemove(Transfer transfer) {
        UUID uuid = transfer.getUUID();
        ToolGunBlock toolGunBlock = (ToolGunBlock) transfer.getStuff();

        LimitHandler limitHandler = getLimits(uuid);

        boolean canForceBreak = limitHandler.hasForceBreakablePermission();

        if (canForceBreak) {
            if (blocksData.removeBlock(toolGunBlock)) {
                UUID getOwnerUUID = toolGunBlock.getUuid();
                limitsData.increase(getOwnerUUID, getLimits(getOwnerUUID).getLimit());

                sendLimitInfo(uuid);

                toolGunBlock.remove();
                return true;
            }
        }

        if (blocksData.removeOwningBlock(uuid, toolGunBlock)) {
            limitsData.increase(uuid, getLimits(uuid).getLimit());

            sendLimitInfo(uuid);
            toolGunBlock.remove();

            return true;
        }


        return false;
    }

    private void sendLimitInfo(UUID uuid) {
        String limitRaw = ConfigManager.getInstance().getMessageConfig().getString("limit-actionbar");
        BlocksManager blocksManager = BlocksManager.getInstance();

        int limit = blocksManager.getLimitsData().getValue(
                uuid,
                new LimitHandler(uuid).getLimit()
        ).get();

        String limitMsg = ParsePlaceholder.parseWithBraces(limitRaw,
                new String[]{"LIMIT"},
                new Object[]{limit});

        ActionBar.sendActionBar(Objects.requireNonNull(Bukkit.getPlayer(uuid)), Color.getTranslated(limitMsg));
    }

    private LimitHandler getLimits(UUID uuid) {
        return new LimitHandler(uuid);
    }
}
