# How to deadlock ur Spigot server in 6 simple steps:

1. Install [this plugin](https://github.com/Jannyboy11/SpigotInventoryBug/releases)
2. Log on to your server
3. Make sure your player has the permission `foo.bar` effectively.
4. Execute the command `/testinv <InventoryType>` with your player.
5. Shift-click an item into the inventory
6. If your inventory is not a chest-like type, your server's main thread will loop indefinitely!

This plugin was created as a demonstration plugin for [a bug report](https://hub.spigotmc.org/jira/browse/SPIGOT-3500),
[A fix](https://hub.spigotmc.org/stash/projects/SPIGOT/repos/craftbukkit/pull-requests/398/overview) was created today, so hopefully this bug will be patched soon.
