package me.squishyslime.minecore.utils;

import me.squishyslime.minecore.Main;
import me.squishyslime.minecore.commands.BalanceCommand;
import me.squishyslime.minecore.commands.FlyCommand;
import me.squishyslime.minecore.commands.GamemodeCommand;
import me.squishyslime.minecore.commands.InvseeCommand;
import me.squishyslime.minecore.commands.PickupCommand;
import me.squishyslime.minecore.commands.VanishCommand;

public class registerCommands {
	private Main plugin;
	public registerCommands(Main plugin) {
		this.plugin = plugin;
	}
	
	public void register() {
		plugin.getCommand("fly").setExecutor(new FlyCommand());
		plugin.getCommand("vanish").setExecutor(new VanishCommand(plugin));
		plugin.getCommand("pickup").setExecutor(new PickupCommand(plugin));
		plugin.getCommand("invsee").setExecutor(new InvseeCommand());
		plugin.getCommand("balance").setExecutor(new BalanceCommand(plugin));
		plugin.getCommand("gm").setExecutor(new GamemodeCommand());;	
		
	}
}
