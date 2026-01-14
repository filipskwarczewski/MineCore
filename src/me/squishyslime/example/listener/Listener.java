package me.squishyslime.example.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.squishyslime.example.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class Listener implements org.bukkit.event.Listener {
	private Main plugin;
	public Listener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UUID playerUUID = p.getUniqueId();
		if(plugin.vanishedPlayers.contains(playerUUID)) {
			e.joinMessage(null);
			for(Player pO : Bukkit.getOnlinePlayers()) {
				if(!pO.hasPermission("example.vanish.see")) {
					pO.hidePlayer(plugin,p);
				} else {
					if(!(pO.getUniqueId() == p.getUniqueId())) {
					pO.sendMessage(Component.text(p.getName() + " joined in vanish!",TextColor.color(0x636363)));
					}
				}
			}
			p.sendMessage(Component.text("You are still in vanish!",TextColor.color(0x636363)));
		}
		if(!plugin.em.isOnConfig(playerUUID)) {
			plugin.em.setBalance(playerUUID, 100);
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		UUID playerUUID = p.getUniqueId();
		if(plugin.vanishedPlayers.contains(playerUUID)) {
			e.quitMessage(null);
			for(Player pO : Bukkit.getOnlinePlayers()) {
				if(pO.hasPermission("example.vanish.see")) {
					if(!(pO.getUniqueId() == p.getUniqueId())) {
						pO.sendMessage(Component.text(p.getName() + " quit in vanish!",TextColor.color(0x636363)));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPickup(PlayerAttemptPickupItemEvent e) {
		Player p = e.getPlayer();
		UUID playerUUID = p.getUniqueId();
		if(plugin.canPickup.contains(playerUUID)) {
			e.setCancelled(true);
		}
	}
}
