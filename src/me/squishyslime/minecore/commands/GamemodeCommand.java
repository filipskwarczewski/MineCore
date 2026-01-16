package me.squishyslime.minecore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class GamemodeCommand implements CommandExecutor,TabCompleter {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(!sender.hasPermission("minecore.gm")) {
			sender.sendMessage(Component.text("You don't have permission to execute this command!",TextColor.color(0xe0523f)));
			return false;
		}
		if(args.length != 1 || args.length != 2) {
			sender.sendMessage(Component.text("Usage: /gm <0/1/2/3/> or <s/c/a/sp> <player>",TextColor.color(0xe0523f)));
			return false;
		}
		if(args.length == 1) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Component.text("You need to be a player to use this command or specify a player!",TextColor.color(0xe0523f)));
				return false;
			}
			Player p = (Player) sender;
			String gamemode = args[0];
			switch(gamemode) {
			case "0", "s","survival":
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(Component.text("Set your gamemode to survival!",TextColor.color(0x39db64)));
			case "1", "c","creative":
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(Component.text("Set your gamemode to creative!",TextColor.color(0x39db64)));
			case "2","a","adventure":
				p.setGameMode(GameMode.ADVENTURE);
				p.sendMessage(Component.text("Set your gamemode to adventure!",TextColor.color(0x39db64)));
			case "3","sp","spectator":
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage(Component.text("Set your gamemode to spectator!",TextColor.color(0x39db64)));
			default:
				p.sendMessage(Component.text("Usage: /gm <0/1/2/3/> or <s/c/a/sp> <player>",TextColor.color(0xe0523f)));
				
			}
		}
		if(args.length == 2) {
			Player p = Bukkit.getPlayerExact(args[1]);
			if(p == null) {
				sender.sendMessage(Component.text("That player doesn't exist!",TextColor.color(0x39db64)));
				return false;
			}
			String gamemode = args[0];
			switch(gamemode) {
			case "0", "s","survival":
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(Component.text("Set your gamemode to survival!",TextColor.color(0x39db64)));
				sender.sendMessage(Component.text("Set " + p.getName() + "'s gamemode to survival!",TextColor.color(0x39db64)));
			case "1", "c","creative":
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(Component.text("Set your gamemode to creative!",TextColor.color(0x39db64)));
				sender.sendMessage(Component.text("Set " + p.getName() + "'s gamemode to creative!",TextColor.color(0x39db64)));
			case "2","a","adventure":
				p.setGameMode(GameMode.ADVENTURE);
				p.sendMessage(Component.text("Set your gamemode to adventure!",TextColor.color(0x39db64)));
				sender.sendMessage(Component.text("Set " + p.getName() + "'s gamemode to adventure!",TextColor.color(0x39db64)));
			case "3","sp","spectator":
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage(Component.text("Set your gamemode to spectator!",TextColor.color(0x39db64)));
				sender.sendMessage(Component.text("Set " + p.getName() + "'s gamemode to spectator!",TextColor.color(0x39db64)));
			default:
				p.sendMessage(Component.text("Usage: /gm <0/1/2/3/> or <s/c/a/sp> <player>",TextColor.color(0xe0523f)));
				
			}
		}
		return false;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
			@NotNull String label, @NotNull String @NotNull [] args) {
		if(args.length == 1) {
			if(sender.hasPermission("minecore.gm")) {
				return List.of("0","1","2","3","s","c","a","sp","survival","creative","adventure","spectator");
			}
		}
		if(args.length == 2) {
			ArrayList<String> onlinePlayers = new ArrayList<>();
			if(sender.hasPermission("minecore.gm")) {
				for(Player pO : Bukkit.getOnlinePlayers()) {
					onlinePlayers.add(pO.getName());
				}
			}
		}
		return null;
	}

}
