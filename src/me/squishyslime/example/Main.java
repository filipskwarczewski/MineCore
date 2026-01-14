package me.squishyslime.example;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import me.squishyslime.example.commands.BalanceCommand;
import me.squishyslime.example.commands.FlyCommand;
import me.squishyslime.example.commands.InvseeCommand;
import me.squishyslime.example.commands.PickupCommand;
import me.squishyslime.example.commands.VanishCommand;
import me.squishyslime.example.listener.Listener;
import me.squishyslime.example.utils.EconomyManager;

public class Main extends JavaPlugin {
	public HashSet<UUID> vanishedPlayers = new HashSet<>();
	public HashSet<UUID> canPickup = new HashSet<>();
	public EconomyManager em;
	@Override
	public void onEnable() {
		getLogger().info("Example Plugin has started!");
		em = new EconomyManager(this);
		saveConfig();
		getServer().getPluginManager().registerEvents(new Listener(this), this);
		
		getCommand("fly").setExecutor(new FlyCommand());
//		Commands to do: /gm, /home,/spawn,/back,/heal,/ban,/unban,/eco
		getCommand("vanish").setExecutor(new VanishCommand(this));
		getCommand("pickup").setExecutor(new PickupCommand(this));
		getCommand("invsee").setExecutor(new InvseeCommand());
		getCommand("balance").setExecutor(new BalanceCommand(this));
//		getCommand("eco").setExecutor(new EcoCommand(this));
	}

}
