package com.megadev.toolgun.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Color {
    public static List<String> getTranslated(List<String> strings) {
        List<String> list = new ArrayList<>();
        for (String s : strings) {
            list.add(getTranslated(s));
        }
        return list;
    }

    public static String getTranslated(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
