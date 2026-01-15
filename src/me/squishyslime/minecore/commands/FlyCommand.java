package me.squishyslime.minecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(!sender.hasPermission("example.fly")) {
			sender.sendMessage(Component.text("You don't have permission to execute this command!",TextColor.color(0xe0523f)));
			return false;
		}
		if(args.length == 1) {
			Player target = Bukkit.getPlayerExact(args[0]);
			if (target == null) {
				sender.sendMessage(Component.text("That player isn't online!",TextColor.color(0xe0523f)));
				return false;
			}
			boolean canFly = target.getAllowFlight();
			target.setAllowFlight(!canFly);
			if(canFly) {
				sender.sendMessage(Component.text(target.getName() + " can fly now!",TextColor.color(0x39db64)));
				target.sendMessage(Component.text("You can fly now!",TextColor.color(0x39db64)));
			} else {
				sender.sendMessage(Component.text(target.getName() + " can't fly now!",TextColor.color(0xe0523f)));
				target.sendMessage(Component.text("You can't fly now!",TextColor.color(0xe0523f)));
			}
		} else {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Component.text("You need to be a player to use this command or specify a player. Usage: /fly <player>",TextColor.color(0xe0523f)));
				return false;
			}
			Player p = (Player) sender;
			boolean canFly = p.getAllowFlight();
			p.setAllowFlight(!canFly);
			if(!canFly) {
				p.sendMessage(Component.text("You can fly now!",TextColor.color(0x39db64)));
			} else {
				p.sendMessage(Component.text("You can't fly now!",TextColor.color(0xe0523f)));
			}
		}
		return true;
	}

}
