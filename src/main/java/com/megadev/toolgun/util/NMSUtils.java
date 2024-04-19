package com.megadev.toolgun.util;

import org.bukkit.Bukkit;

public class NMSUtils {

    public static double version = getNMSVersion();

    public static double getNMSVersion() {
        if (version != 0)
            return version;
        String var1 = Bukkit.getServer().getClass().getPackage().getName();
        String[] arrayOfString = var1.replace(".", ",").split(",")[3].split("_");
        String var2 = arrayOfString[0].replace("v", "");
        String var3 = arrayOfString[1];
        return version = Double.parseDouble(var2 + "." + var3);
    }

    public static boolean hasShulker() {
        return !isOneHand();
    }

    public static boolean hasBarrel() {
        final double version = getNMSVersion();
        return !(version == 1.7 || version == 1.8 || version == 1.9 || version == 1.10 || version == 1.11
                || version == 1.12 || version == 1.13);
    }

    public static boolean isNewVersion() {
        return !isOldVersion();
    }

    public static boolean isOneHand() {
        return getNMSVersion() == 1.7 || getNMSVersion() == 1.8;
    }

    public static boolean isVeryOldVersion() {
        return getNMSVersion() == 1.7;
    }

    public static boolean isUnbreakable() {
        return version == 1.7 || version == 1.8 || version == 1.9 || version == 1.10;
    }

    public static boolean isOldVersion() {
        return version == 1.7 || version == 1.8 || version == 1.9 || version == 1.10 || version == 1.12
                || version == 1.11;
    }

    public static boolean isNewNMSVersion() {
        final double version = getNMSVersion();
        return !(version == 1.7 || version == 1.8 || version == 1.9 || version == 1.10 || version == 1.11
                || version == 1.12 || version == 1.13 || version == 1.14 || version == 1.15 || version == 1.16);
    }

    public static boolean isNewNBTVersion() {
        final double version = getNMSVersion();
        return !(version == 1.7 || version == 1.8 || version == 1.9 || version == 1.10 || version == 1.11
                || version == 1.12 || version == 1.13 || version == 1.14 || version == 1.15 || version == 1.16
                || version == 1.17);
    }

    public static boolean isHexColor() {
        return !(version == 1.7 || version == 1.8 || version == 1.9 || version == 1.10 || version == 1.11
                || version == 1.12 || version == 1.13 || version == 1.14 || version == 1.15);
    }

}
