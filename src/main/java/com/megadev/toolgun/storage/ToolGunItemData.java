package com.megadev.toolgun.storage;

import com.megadev.toolgun.object.toolgun.item.ToolGunItem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ToolGunItemData implements Data<ToolGunItem> {
    private final Map<UUID, ToolGunItem> itemData = new HashMap<>();

    @Override
    public Map<UUID, ToolGunItem> getData() {
        return itemData;
    }

    @Override
    public ToolGunItem getValue(UUID key) {
        return itemData.get(key);
    }

    @Override
    public boolean contains(UUID key) {
        return itemData.containsKey(key);
    }

    @Override
    public boolean addValue(UUID key, ToolGunItem value) {
        itemData.put(key, value);
        return false;
    }

    @Override
    public void remove(UUID key) {
        itemData.remove(key);
    }

    @Override
    public void clear() {
        itemData.clear();
    }
}
