package com.megadev.toolgun.object.toolgun;

import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

public class ToolGunObj {
    @Getter private String name;
    @Getter private List<String> lore;
    @Getter private int customModelData = 10000;
    @Getter private Material material;

    public ToolGunObj(String name, List<String> lore, Material material, int customModelData) {
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.customModelData = customModelData;
    }

    public ToolGunObj(String name, List<String> lore) {
        this.name = name;
        this.lore = lore;
    }
}
