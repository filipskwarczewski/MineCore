package me.squishyslime.minecore.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.squishyslime.minecore.Main;

public class HistoryManager {

    private final File historyConfigFile;
    private final FileConfiguration historyConfig;

    private static final int MAX_LOGS = 20; // max logs per player

    public HistoryManager(Main plugin) {
        this.historyConfigFile = new File(plugin.getDataFolder(), "history.yml");

        if (!historyConfigFile.exists()) {
            historyConfigFile.getParentFile().mkdirs();
            plugin.saveResource("history.yml", false);
        }

        this.historyConfig = new YamlConfiguration();
        try {
            historyConfig.load(historyConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getHistoryConfig() {
        return historyConfig;
    }

    /* -------------------------
     * Core methods
     * ------------------------- */

    public void addLog(UUID target, UUID staff, String action) {
        String path = "logs." + target.toString();

        List<String> logs = historyConfig.getStringList(path);

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String entry = time + " | " + action + " | by " + staff;

        logs.add(0, entry); // newest on top

        // trim old logs
        if (logs.size() > MAX_LOGS) {
            logs = logs.subList(0, MAX_LOGS);
        }

        historyConfig.set(path, logs);
        save();
    }

    public List<String> getLogs(UUID player) {
        return historyConfig.getStringList("logs." + player.toString());
    }

    public boolean hasHistory(UUID player) {
        return historyConfig.contains("logs." + player.toString());
    }

    public void clearHistory(UUID player) {
        historyConfig.set("logs." + player.toString(), null);
        save();
    }

    public void save() {
        try {
            historyConfig.save(historyConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            historyConfig.load(historyConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
