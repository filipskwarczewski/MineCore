package me.squishyslime.minecore.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.squishyslime.minecore.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class BalanceCommand implements CommandExecutor {
	private Main plugin;
	public BalanceCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(args.length == 1) {
			OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
			if(!target.hasPlayedBefore()) {
				sender.sendMessage(Component.text("That player wasn't on this server!",TextColor.color(0xe0523f)));
				return false;
			}
			UUID targetUUID = target.getUniqueId();
			int targetBalance = plugin.em.getBalance(targetUUID);
			sender.sendMessage(Component.text(args[0] + "'s balance: $" + targetBalance,TextColor.color(0x39db64)));
			return false;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(Component.text("You need to be a player to execute this command or specify a player!",TextColor.color(0xe0523f)));
			return false;
		}
		Player p = (Player) sender;
		UUID playerUUID = p.getUniqueId();
		int playerBalance = plugin.em.getBalance(playerUUID);
		sender.sendMessage(Component.text("Your balance: $" + playerBalance,TextColor.color(0x39db64)));
		return true;
	}

}
