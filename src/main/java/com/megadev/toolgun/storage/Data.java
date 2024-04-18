package com.megadev.toolgun.manager;

import com.megadev.toolgun.object.toolgun.block.ToolGunBlock;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Data<T> {
    Map<UUID, T> getData();

    void addValue(UUID key, T value);

    void remove(UUID key);

    void clear();
}
