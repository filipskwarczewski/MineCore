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

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    private static final TextColor ERROR = TextColor.color(0xe0523f);
    private static final TextColor SUCCESS = TextColor.color(0x39db64);

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {

        if (!sender.hasPermission("minecore.gm")) {
            sender.sendMessage(Component.text(
                    "You don't have permission to execute this command!",
                    ERROR
            ));
            return true;
        }

        if (args.length != 1 && args.length != 2) {
            sender.sendMessage(Component.text(
                    "Usage: /gm <0/1/2/3|s/c/a/sp> [player]",
                    ERROR
            ));
            return true;
        }

        Player target;

        if (args.length == 2) {
            target = Bukkit.getPlayerExact(args[1]);
            if (target == null) {
                sender.sendMessage(Component.text(
                        "That player doesn't exist!",
                        ERROR
                ));
                return true;
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Component.text(
                        "You must specify a player from console!",
                        ERROR
                ));
                return true;
            }
            target = (Player) sender;
        }

        GameMode gameMode = switch (args[0].toLowerCase()) {
            case "0", "s", "survival" -> GameMode.SURVIVAL;
            case "1", "c", "creative" -> GameMode.CREATIVE;
            case "2", "a", "adventure" -> GameMode.ADVENTURE;
            case "3", "sp", "spectator" -> GameMode.SPECTATOR;
            default -> null;
        };

        if (gameMode == null) {
            sender.sendMessage(Component.text(
                    "Invalid gamemode!",
                    ERROR
            ));
            return true;
        }

        target.setGameMode(gameMode);

        target.sendMessage(Component.text(
                "Your gamemode has been set to " + gameMode.name().toLowerCase() + "!",
                SUCCESS
        ));

        if (sender != target) {
            sender.sendMessage(Component.text(
                    "Set " + target.getName() + "'s gamemode to " + gameMode.name().toLowerCase() + "!",
                    SUCCESS
            ));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {

        if (!sender.hasPermission("minecore.gm")) {
            return null;
        }

        if (args.length == 1) {
            return List.of(
                    "0", "1", "2", "3",
                    "s", "c", "a", "sp",
                    "survival", "creative", "adventure", "spectator"
            );
        }

        if (args.length == 2) {
            List<String> players = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                players.add(p.getName());
            }
            return players;
        }

        return null;
    }
}
