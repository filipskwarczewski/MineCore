package me.squishyslime.minecore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class EnderchestCommand implements CommandExecutor {
    private static final TextColor ERROR = TextColor.color(0xe0523f);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		// TODO Auto-generated method stub
		if(!(sender instanceof Player)) {
			sender.sendMessage(Component.text("You have to be a player to execute this command!",ERROR));
			return false;
		}
		if(!sender.hasPermission("minecore.enderchest")) {
			sender.sendMessage(Component.text("You don't have permission to execute this command!",ERROR));
			return false;
		}
		Player p = (Player) sender;
		p.openInventory(p.getEnderChest());
		return true;
	}

}
