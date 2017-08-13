package me.jan.invtest;

import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;

public class OpenInvInventoryHolder implements InventoryHolder, Listener {
	
	private final Server server;
	private final Inventory inventory;
	
	public OpenInvInventoryHolder(Plugin plugin, InventoryType type) {
		this.server = plugin.getServer();
		this.inventory = type == null ? server.createInventory(this, 9, "foo bar") : server.createInventory(this, type, "foo bar");
		server.getPluginManager().registerEvents(this, plugin);
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		server.getLogger().info("Click event!");
		server.getLogger().info("click type = " + event.getClick());
		server.getLogger().info("slot type = " + event.getSlotType());
		server.getLogger().info("action = " + event.getAction());
		server.getLogger().info("slot = " + event.getSlot());
		server.getLogger().info("raw slot = " + event.getRawSlot());
		
		InventoryHolder holder = event.getView().getTopInventory().getHolder();
		if (holder == this) {
			server.getLogger().info("(holder == this) holds true"); 
		}
		//from this point server seems to deadlock upon shiftclicking an item into the dispenser inventory.
		//cannot reproduce with a chest inventory.
		//note that the EventHandler for the InventoryClickEvent is not required to have this crash occur.
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		server.getLogger().info("Close event!");
		
		InventoryHolder holder = event.getView().getTopInventory().getHolder();
		if (holder == this) {
			server.getLogger().info("(holder == this) holds true");
			
			HandlerList.unregisterAll(this);
			server.getLogger().info("unregistered this listener");
		}
		
	}

}
