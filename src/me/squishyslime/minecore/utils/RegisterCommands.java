package me.squishyslime.minecore.utils;

import me.squishyslime.minecore.Main;
import me.squishyslime.minecore.commands.BalanceCommand;
import me.squishyslime.minecore.commands.EnderchestCommand;
import me.squishyslime.minecore.commands.FlyCommand;
import me.squishyslime.minecore.commands.GamemodeCommand;
import me.squishyslime.minecore.commands.GodCommand;
import me.squishyslime.minecore.commands.InvseeCommand;
import me.squishyslime.minecore.commands.PickupCommand;
import me.squishyslime.minecore.commands.RepairCommand;
import me.squishyslime.minecore.commands.VanishCommand;
import me.squishyslime.minecore.commands.WorkbenchCommand;
import me.squishyslime.minecore.commands.feedCommand;
import me.squishyslime.minecore.commands.healCommand;

public class RegisterCommands {
	private Main plugin;
	public RegisterCommands(Main plugin) {
		this.plugin = plugin;
	}
	
	public void register() {
		plugin.getCommand("fly").setExecutor(new FlyCommand());
		plugin.getCommand("vanish").setExecutor(new VanishCommand(plugin));
		plugin.getCommand("pickup").setExecutor(new PickupCommand(plugin));
		plugin.getCommand("invsee").setExecutor(new InvseeCommand());
		plugin.getCommand("balance").setExecutor(new BalanceCommand(plugin));
		plugin.getCommand("gm").setExecutor(new GamemodeCommand());	
		plugin.getCommand("heal").setExecutor(new healCommand());
		plugin.getCommand("feed").setExecutor(new feedCommand());
		plugin.getCommand("repair").setExecutor(new RepairCommand());
		plugin.getCommand("workbench").setExecutor(new WorkbenchCommand());
		plugin.getCommand("enderchest").setExecutor(new EnderchestCommand());
		plugin.getCommand("god").setExecutor(new GodCommand(plugin));
		}
}
