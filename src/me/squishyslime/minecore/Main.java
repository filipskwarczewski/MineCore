package me.squishyslime.minecore;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import me.squishyslime.minecore.commands.BalanceCommand;
import me.squishyslime.minecore.commands.FlyCommand;
import me.squishyslime.minecore.commands.InvseeCommand;
import me.squishyslime.minecore.commands.PickupCommand;
import me.squishyslime.minecore.commands.VanishCommand;
import me.squishyslime.minecore.listener.Listener;
import me.squishyslime.minecore.utils.EconomyManager;
import me.squishyslime.minecore.utils.HistoryManager;

public class Main extends JavaPlugin {
	public HashSet<UUID> vanishedPlayers = new HashSet<>();
	public HashSet<UUID> canPickup = new HashSet<>();
	public EconomyManager em;
	public HistoryManager hm;
	@Override
	public void onEnable() {
		getLogger().info("MineCore has started!");
		em = new EconomyManager(this);
		hm = new HistoryManager(this);
		saveConfig();
		getServer().getPluginManager().registerEvents(new Listener(this), this);
		
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("vanish").setExecutor(new VanishCommand(this));
		getCommand("pickup").setExecutor(new PickupCommand(this));
		getCommand("invsee").setExecutor(new InvseeCommand());
		getCommand("balance").setExecutor(new BalanceCommand(this));
	}

}
