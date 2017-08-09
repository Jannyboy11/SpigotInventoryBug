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
	
	public OpenInvInventoryHolder(Plugin plugin) {
		this.server = plugin.getServer();
		this.inventory = server.createInventory(this, InventoryType.DISPENSER, "foo bar");
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
		server.getLogger().info("clicked inventory = " + event.getClickedInventory());
		
		InventoryHolder holder = event.getView().getTopInventory().getHolder();
		if (holder == this) {
			server.getLogger().info("(server == this) holds true"); 
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
			server.getLogger().info("(server == this) holds true");
			
			HandlerList.unregisterAll(this);
			server.getLogger().info("unregistered this listener");
		}
		
	}

}
