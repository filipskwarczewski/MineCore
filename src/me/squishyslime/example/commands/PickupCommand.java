package me.squishyslime.example.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.squishyslime.example.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class PickupCommand implements CommandExecutor {
	private Main plugin;
	public PickupCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(!sender.hasPermission("example.pickup")) {
			sender.sendMessage(Component.text("You don't have permission to execute this command!",TextColor.color(0xe0523f)));
			return false;
		}
		if(args.length == 1) {
			Player target = Bukkit.getPlayerExact(args[0]);
			if (target == null) {
				sender.sendMessage(Component.text("That player doesn't exist!",TextColor.color(0xe0523f)));
				return false;
			}
			UUID playerUUID = target.getUniqueId();
			if(plugin.canPickup.contains(playerUUID)) {
				plugin.canPickup.remove(playerUUID);
				sender.sendMessage(Component.text(target.getName() + " can pickup items now!",TextColor.color(0x39db64)));
				return true;
			} else {
				plugin.canPickup.add(playerUUID);
				sender.sendMessage(Component.text(target.getName() + " can't pickup items now!",TextColor.color(0xe0523f)));
				return true;
			}
		} else {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Component.text("You need to be a player to execute this command or specify a player. Usage: /pickup <player>",TextColor.color(0xe0523f)));
				return false;
			}
			Player p = (Player) sender;
			UUID playerUUID = p.getUniqueId();
			if(plugin.canPickup.contains(playerUUID)) {
				plugin.canPickup.remove(playerUUID);
				sender.sendMessage(Component.text("You can pickup items now!",TextColor.color(0x39db64)));
				return true;
			} else {
				plugin.canPickup.add(playerUUID);
				sender.sendMessage(Component.text("You can't pickup items now!",TextColor.color(0xe0523f)));
				return true;
			}
		}
	}

}
