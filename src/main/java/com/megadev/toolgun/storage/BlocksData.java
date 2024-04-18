package com.megadev.toolgun.manager;

import com.megadev.toolgun.object.toolgun.block.ToolGunBlock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BlocksData implements Data<List<ToolGunBlock>> {
    Map<UUID, List<ToolGunBlock>> blocksData = new HashMap<>();

    @Override
    public Map<UUID, List<ToolGunBlock>> getData() {
        return blocksData;
    }

    @Override
    public void addValue(UUID key, List<ToolGunBlock> value) {
        blocksData.put(key, value);
    }

    @Override
    public void remove(UUID key) {
        blocksData.remove(key);
    }

    @Override
    public void clear() {
        blocksData.clear();
    }
}
