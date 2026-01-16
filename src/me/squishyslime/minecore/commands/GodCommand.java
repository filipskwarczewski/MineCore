package me.squishyslime.minecore.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.squishyslime.minecore.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class GodCommand implements CommandExecutor {
	private Main plugin;
	public GodCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(!sender.hasPermission("minecore.god")) {
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
			if(plugin.godMode.contains(playerUUID)) {
				plugin.godMode.remove(playerUUID);
				sender.sendMessage(Component.text(target.getName() + " don't have god mode!",TextColor.color(0xe0523f)));
				return true;
			} else {
				plugin.godMode.add(playerUUID);
				sender.sendMessage(Component.text(target.getName() + " have god mode!",TextColor.color(0x39db64)));
				return true;
			}
		} else {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Component.text("You need to be a player to execute this command or specify a player. Usage: /pickup <player>",TextColor.color(0xe0523f)));
				return false;
			}
			Player p = (Player) sender;
			UUID playerUUID = p.getUniqueId();
			if(plugin.godMode.contains(playerUUID)) {
				plugin.godMode.remove(playerUUID);
				sender.sendMessage(Component.text("You don't have god mode!",TextColor.color(0xe0523f)));
				return true;
			} else {
				plugin.godMode.add(playerUUID);
				sender.sendMessage(Component.text("You have god mode!",TextColor.color(0x39db64)));
				return true;
			}
		}
	}

}
