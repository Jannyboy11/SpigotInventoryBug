package me.jan.invtest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class OpenInvCommandExecutor implements TabExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player in order to execute this command.");
			return true;
		}
		

        Player player = (Player) sender;
        InventoryType inventoryType = null;
		
		if (args.length == 0) {
		    sender.sendMessage("Using default inventory type.., use /testinv <InventoryType> to open other types of inventories.");
		} else {
		    try {
		        inventoryType = InventoryType.valueOf(args[0].toUpperCase());
		    } catch (IllegalArgumentException e) {
		        sender.sendMessage("InventoryTypes: " + Arrays.toString(InventoryType.values()));
		    }
		}
		
		PluginCommand pluginCommand = (PluginCommand) command;
		OpenInvInventoryHolder holder = new OpenInvInventoryHolder(pluginCommand.getPlugin(), inventoryType);
		player.openInventory(holder.getInventory());
		
		return true;
	}

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return Arrays.stream(InventoryType.values()).map(InventoryType::name).collect(Collectors.toList());
    }
}
