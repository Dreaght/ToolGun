package com.megadev.toolgun.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class Configurable {
    protected Plugin plugin;
    protected FileConfiguration config;
    protected File configFile;
    protected File subFolder;

    protected Configurable(@NotNull Plugin plugin, String dataFolderName, String fileName, Map<String, Object> defaults) {
        this.plugin = plugin;
        File dataFolder = plugin.getDataFolder();
        this.subFolder = new File(dataFolder, dataFolderName);

        if (!subFolder.exists()) {
            subFolder.mkdirs();
        }

        this.configFile = new File(subFolder, fileName + ".yml");

        saveResource(fileName);

        this.config = getConfig();
        saveDefaults(defaults);
    }

    public void saveDefaults(Map<String, Object> defaults) {
        for (Map.Entry<String, Object> entry : defaults.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!config.contains(key)) {
                if (value == null) {
                    config.createSection(key);
                } else {
                    config.set(key, value);
                }
            }
        }
        saveConfig();
    }

    public Object getValue(String path) {
        return config.get(path);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    public void setValue(String key, String value) {
        config.set(key, value);
        saveConfig();
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save configuration file: " + configFile.getName());
        }
    }

    private void saveResource(String fileName) {
        if (plugin.getResource(fileName + "." + "yml") != null) {
            plugin.saveResource(fileName + "." + "yml", false);
        } else {
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private FileConfiguration getConfig() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                config = new YamlConfiguration();
                config.options().copyDefaults(true);
                saveConfig();
            } catch (IOException e) {
                plugin.getLogger().warning("Failed to create configuration file: " + configFile.getName());
            }
        }

        YamlConfiguration yamlConfig = new YamlConfiguration();
        try {
            yamlConfig.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().warning("Failed to load configuration file: " + configFile.getName());
        }
        return yamlConfig;
    }

    public void deleteConfig() {
        if (configFile.exists()) {
            configFile.delete();
            plugin.getLogger().info("Deleted configuration file: " + configFile.getName());
        }
    }
}
