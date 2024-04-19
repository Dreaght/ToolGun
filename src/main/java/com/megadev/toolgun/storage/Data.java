package com.megadev.toolgun.storage;

import java.util.Map;
import java.util.UUID;

public interface Data<T> {
    Map<UUID, T> getData();

    T getValue(UUID key);

    boolean contains(UUID key);

    boolean addValue(UUID key, T value);

    void remove(UUID key);

    void clear();
}
