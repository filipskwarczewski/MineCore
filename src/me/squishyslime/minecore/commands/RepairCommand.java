package me.squishyslime.minecore.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class RepairCommand implements CommandExecutor, TabCompleter {
    private static final TextColor ERROR = TextColor.color(0xe0523f);
    private static final TextColor SUCCESS = TextColor.color(0x39db64);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String @NotNull [] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Component.text("You need to be a player to use this command!",ERROR));
			return false;
		}
		if(!sender.hasPermission("minecore.repair.cmd")) {
			sender.sendMessage(Component.text("You don't have permission to use this command!",ERROR));
			return false;
		}
		Player p = (Player) sender;
		if(args.length >= 2) {
			p.sendMessage(Component.text("Usage: /repair <all>",ERROR));
			return false;
		}
		String type = args[0];
		switch(type) {
			case "all":
				if(!sender.hasPermission("minecore.repair.all")) {
					sender.sendMessage(Component.text("You don't have permission to repair all of the items!",ERROR));
				}
				for(ItemStack itemStack : p.getInventory().getContents()) {
					Damageable meta = (Damageable) itemStack.getItemMeta();
					meta.setDamage(itemStack.getType().getMaxDurability());
					itemStack.setItemMeta(meta);
				}
				p.sendMessage(Component.text("Items in inventory successfully repaired!",SUCCESS));
			default:
				ItemStack itemStack = p.getInventory().getItemInMainHand();
				Damageable meta = (Damageable) itemStack.getItemMeta();
				meta.setDamage(itemStack.getType().getMaxDurability());
				itemStack.setItemMeta(meta);
				p.sendMessage(Component.text("Item in main hand successfully repaired!",SUCCESS));
		}
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
			@NotNull String label, @NotNull String @NotNull [] args) {
		if(args.length == 1) {
			return List.of("all");
		}
		return null;
	}

}
