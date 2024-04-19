package com.megadev.toolgun.storage;

import com.megadev.toolgun.object.toolgun.item.ToolGunItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChosenBlockData implements Data<ToolGunItemStack> {
    private final Map<UUID, ToolGunItemStack> chosenMaterialData = new HashMap<>();

    @Override
    public Map<UUID, ToolGunItemStack> getData() {
        return chosenMaterialData;
    }

    @Override
    public ToolGunItemStack getValue(UUID key) {
        return chosenMaterialData.get(key);
    }

    @Override
    public boolean contains(UUID key) {
        return chosenMaterialData.containsKey(key);
    }

    @Override
    public boolean addValue(UUID key, ToolGunItemStack value) {
        chosenMaterialData.put(key, value);
        return true;
    }

    @Override
    public void remove(UUID key) {
        chosenMaterialData.remove(key);
    }

    @Override
    public void clear() {
        chosenMaterialData.clear();
    }
}
