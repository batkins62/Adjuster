package com.creepercountry.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.creepercountry.main.ADJPlugin;
import com.creepercountry.util.BukkitUtils;

public class ADJBlockListener implements Listener
{
	/**
     * The plugin instance
     */
	@SuppressWarnings("unused")
	private ADJPlugin plugin;

	/**
	 * constructor
	 * @param plugin
	 */
	public ADJBlockListener(ADJPlugin plugin)
    {
        this.plugin = plugin;
    }
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event)
	{
		// set variables to improve readability
		Player player = event.getPlayer();
		String username = player.getDisplayName();
		
		if (!player.hasPermission("adjuster.spawner.override"))
		{
			if (event.getBlock().getTypeId() == 52)
			{
				BukkitUtils.sendMessage(player, "You have recieved 300 for breaking a mobspawner!");
				ADJPlugin.econ.depositPlayer(player.getName(), 300.00);
				BukkitUtils.info(username + " has been rewarded 300 for breaking a mob spawner");
			}
		}
	}
}
