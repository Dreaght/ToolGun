package com.megadev.toolgun.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LimitsData implements Data<AtomicInteger> {
    private final Map<UUID, AtomicInteger> limitsData = new HashMap<>();

    @Override
    public Map<UUID, AtomicInteger> getData() {
        return limitsData;
    }

    public void fillLimit(UUID uuid, int amount) {
        limitsData.put(uuid, new AtomicInteger(amount));
    }

    public boolean increase(UUID key, int max) {
        if (!contains(key))
            limitsData.put(key, new AtomicInteger(max));

        if (getValue(key).get() >= max) {
            return false;
        }

        getValue(key).incrementAndGet();
        return true;
    }

    public boolean decrease(UUID key, int max) {
        if (!contains(key))
            limitsData.put(key, new AtomicInteger(max));

        if (getValue(key).get() == 0) {
            return false;
        }

        getValue(key).decrementAndGet();
        return true;
    }

    @Override
    public AtomicInteger getValue(UUID key) {
        return limitsData.get(key);
    }

    public AtomicInteger getValue(UUID key, int defValue) {
        if (!contains(key))
            limitsData.put(key, new AtomicInteger(defValue));
        return limitsData.get(key);
    }

    @Override
    public boolean contains(UUID key) {
        return limitsData.containsKey(key);
    }

    @Override
    public boolean addValue(UUID key, AtomicInteger value) {
        limitsData.put(key, value);
        return true;
    }

    @Override
    public void remove(UUID key) {
        limitsData.remove(key);
    }

    @Override
    public void clear() {
        limitsData.clear();
    }
}
