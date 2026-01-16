package me.squishyslime.minecore;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import me.squishyslime.minecore.listener.Listener;
import me.squishyslime.minecore.utils.EconomyManager;
import me.squishyslime.minecore.utils.HistoryManager;
import me.squishyslime.minecore.utils.registerCommands;

public class Main extends JavaPlugin {
	public HashSet<UUID> vanishedPlayers = new HashSet<>();
	public HashSet<UUID> canPickup = new HashSet<>();
	
	public EconomyManager em;
	public registerCommands reg;
	public HistoryManager hm;
	
	@Override
	public void onEnable() {
		getLogger().info("MineCore has started!");
		em = new EconomyManager(this);
		hm = new HistoryManager(this);
		reg = new registerCommands(this);
		saveConfig();
		getServer().getPluginManager().registerEvents(new Listener(this), this);
		
		reg.register();
	}
}
