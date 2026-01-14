package me.squishyslime.example.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class InvseeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Component.text("You need to be a player to execute this command.",TextColor.color(0xe0523f)));
			return false;
		}
		if(!sender.hasPermission("example.invsee")) {
			sender.sendMessage(Component.text("You don't have permission to execute this command.",TextColor.color(0xe0523f)));
			return false;
		}
		Player p = (Player) sender;
		if(args.length != 1) {
			p.sendMessage(Component.text("Usage: /invsee <player>",TextColor.color(0xe0523f)));
		}
		Player target = Bukkit.getPlayerExact(args[0]);
		if(target == null) {
			p.sendMessage(Component.text("That player isn't online!",TextColor.color(0xe0523f))); 
			return false;
		}
		Inventory targetInv = target.getInventory();
		p.openInventory(targetInv);
		p.sendMessage(Component.text("Successfully opened " + target.getName() + "'s inventory!",TextColor.color(0x39db64)));
		return true;
	}

}
