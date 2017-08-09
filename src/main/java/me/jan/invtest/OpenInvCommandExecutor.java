package me.jan.invtest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

public class OpenInvCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player in order to execute this command.");
			return true;
		}
		
		Player player = (Player) sender;
		
		PluginCommand pluginCommand = (PluginCommand) command;
		OpenInvInventoryHolder holder = new OpenInvInventoryHolder(pluginCommand.getPlugin());
		player.openInventory(holder.getInventory());
		
		return true;
	}
	

}
