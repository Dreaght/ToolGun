package com.megadev.toolgun.object.toolgun.block;

import com.megadev.toolgun.object.toolgun.Spawnable;
import org.bukkit.Location;
import org.bukkit.block.Block;

public interface IBlock extends Spawnable {
    Block getBlock();
    Location getLocation();

    @Override
    boolean equals(Object o);
}
