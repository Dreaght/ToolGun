package com.megadev.toolgun.storage;

import com.megadev.toolgun.object.toolgun.block.ToolGunBlock;
import org.bukkit.block.Block;

import java.util.*;

public class BlocksData implements Data<List<ToolGunBlock>> {
    private final Map<UUID, List<ToolGunBlock>> blocksData = new HashMap<>();

    @Override
    public Map<UUID, List<ToolGunBlock>> getData() {
        return blocksData;
    }

    @Override
    public List<ToolGunBlock> getValue(UUID key) {
        return blocksData.get(key);
    }

    @Override
    public boolean contains(UUID key) {
        return blocksData.containsKey(key);
    }

    @Override
    public boolean addValue(UUID key, List<ToolGunBlock> value) {
        blocksData.put(key, value);
        return false;
    }

    public boolean addToolGunBlock(UUID key, ToolGunBlock block) {
        blocksData.computeIfAbsent(key, k -> new ArrayList<>());
        return blocksData.get(key).add(block);
    }

    @Override
    public void remove(UUID key) {
        blocksData.remove(key);
    }

    public boolean removeOwningBlock(UUID key, ToolGunBlock block) {
        blocksData.computeIfAbsent(key, k -> new ArrayList<>());
        return blocksData.get(key).remove(block);
    }

    public boolean removeBlock(ToolGunBlock block) {
        boolean removed = false;
        for (List<ToolGunBlock> blocks : blocksData.values()) {
            removed = blocks.remove(block) || removed;
        }

        return removed;
    }

    public boolean isToolGunBlock(Block block) {
        for (List<ToolGunBlock> blocks : blocksData.values()) {
            for (ToolGunBlock toolGunBlock : blocks) {
                if (toolGunBlock.getIBlock().getBlock().equals(block))
                    return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        blocksData.clear();
    }
}
