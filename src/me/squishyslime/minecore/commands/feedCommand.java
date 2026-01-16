package me.squishyslime.minecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class feedCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(!sender.hasPermission("minecore.heal")) {
			sender.sendMessage(Component.text("You don't have permission to execute this command!",TextColor.color(0xe0523f)));
			return false;
		}
		if(args.length == 1) {
			Player target = Bukkit.getPlayerExact(args[0]);
			
			if(target == null) {
				sender.sendMessage(Component.text("That player isn't online!",TextColor.color(0xe0523f)));
				return false;
			}
			target.setFoodLevel(20);
			sender.sendMessage(Component.text(target.getName() + " has been feeded!",TextColor.color(0x39db64)));
			target.sendMessage(Component.text("You have been feeded by " + sender.getName(),TextColor.color(0x39db64)));
			return false;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(Component.text("You need to be a player to execute this command or specify a player!",TextColor.color(0xe0523f)));
			return false;
		}
		Player p = (Player) sender;
		p.setFoodLevel(20);
		p.sendMessage(Component.text("You have been feeded!",TextColor.color(0x39db64)));
		return true;
	}

}
