package me.squishyslime.example.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.squishyslime.example.Main;

public class EconomyManager {
	
	private File moneyConfigFile;
	private FileConfiguration moneyConfig;
	private Main plugin;
	public FileConfiguration getMoneyConfig() {
		return this.moneyConfig;
	}
	
	public EconomyManager(Main plugin) {
		this.plugin = plugin;
		this.moneyConfigFile = new File(plugin.getDataFolder(),"money.yml");
		if(!moneyConfigFile.exists()) {
			moneyConfigFile.getParentFile().mkdirs();
			plugin.saveResource("money.yml", false);
		}
		
		moneyConfig = new YamlConfiguration();
		try {
			moneyConfig.load(moneyConfigFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void reloadConfig() {
		moneyConfig = YamlConfiguration.loadConfiguration(moneyConfigFile);
        final InputStream defConfigStream = plugin.getResource("money.yml");
        if (defConfigStream == null) {
            return;
        }

        moneyConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)));
	}
	
	public int getBalance(UUID player) {
		reloadConfig();
		return moneyConfig.getInt("balances." + player.toString());
	}
	
	public void setBalance(UUID player,int amount) {
		moneyConfig.set("balances." + player.toString(), amount);
		plugin.saveResource("money.yml", false);
        try {
            moneyConfig.save(moneyConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + moneyConfigFile, ex);
        }
        reloadConfig();
	}
	public void addMoney(UUID player,int amount) {
		moneyConfig.set("balances." + player.toString(), getBalance(player) + amount);
		plugin.saveResource("money.yml", false);
        try {
            moneyConfig.save(moneyConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + moneyConfigFile, ex);
        }
        reloadConfig();
	}
	public boolean isOnConfig(UUID player) {
		return moneyConfig.contains("balances." + player.toString());
	}
	public void removeMoney(UUID player, int amount) {
		moneyConfig.set("balances." + player.toString(),getBalance(player) - amount);
		plugin.saveResource("money.yml", false);
        try {
            moneyConfig.save(moneyConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + moneyConfigFile, ex);
        }
        reloadConfig();
	}
	
	
}
