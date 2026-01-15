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

public class VanishCommand implements CommandExecutor {
	private Main plugin;
	public VanishCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(!sender.hasPermission("example.vanish")) {
			sender.sendMessage(Component.text("You don't have permission to execute this command!",TextColor.color(0xe0523f)));
			return false;
		}
		if(args.length == 1) {
			Player target = Bukkit.getPlayerExact(args[0]);
			if(target == null) {
				sender.sendMessage(Component.text("That player isn't online!",TextColor.color(0xe0523f)));
				return false;
			}
			UUID targetUUID = target.getUniqueId();
			if(plugin.vanishedPlayers.contains(targetUUID)) {
				plugin.vanishedPlayers.remove(targetUUID);
				plugin.canPickup.remove(targetUUID);
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.showPlayer(plugin, target);
				}
				target.sendMessage(Component.text("You are now visible to others!",TextColor.color(0xe0523f)));
				sender.sendMessage(Component.text(target.getName() + " is now visible to others!",TextColor.color(0xe0523f)));
			} else {
				plugin.vanishedPlayers.add(targetUUID);
				plugin.canPickup.add(targetUUID);
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(!p.hasPermission("example.vanish.see")) {
						p.hidePlayer(plugin,target);
					}
				}
				target.sendMessage(Component.text("You are now invisible!",TextColor.color(0x39db64)));
				sender.sendMessage(Component.text(target.getName() + " is now invisible!",TextColor.color(0x39db64)));
			}
		}
		else {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Component.text("You need to be a player to execute this command or specify a player.",TextColor.color(0xe0523f)));
				return false;
			}
			Player p = (Player) sender;
			UUID playerUUID = p.getUniqueId();
			if(plugin.vanishedPlayers.contains(playerUUID)) {
				plugin.vanishedPlayers.remove(playerUUID);
				plugin.canPickup.remove(playerUUID);
				for(Player pO : Bukkit.getOnlinePlayers()) {
					pO.showPlayer(plugin, p);
				}
				p.sendMessage(Component.text("You are now visible to others!",TextColor.color(0xe0523f)));
			} else {
				plugin.vanishedPlayers.add(playerUUID);
				plugin.canPickup.add(playerUUID);
				for(Player pO : Bukkit.getOnlinePlayers()) {
					pO.hidePlayer(plugin,p);
				}
				p.sendMessage(Component.text("You are now invisible!",TextColor.color(0x39db64)));
			}
		}
		return true;
	}

}
