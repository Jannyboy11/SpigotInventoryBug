package me.jan.invtest;

import org.bukkit.plugin.java.JavaPlugin;

public class InventoryTestPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getCommand("testinv").setExecutor(new OpenInvCommandExecutor());
	}

}
