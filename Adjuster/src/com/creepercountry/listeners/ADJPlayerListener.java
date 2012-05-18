package com.creepercountry.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.creepercountry.main.ADJPlugin;
import com.creepercountry.util.BukkitUtils;

public class ADJPlayerListener implements Listener
{
	/**
	 * The Plugin instance
	 */
	private ADJPlugin plugin;

	/**
	 * constructor
	 * @param plugin
	 */
	public ADJPlayerListener(ADJPlugin plugin)
    {
        this.plugin = plugin;
    }
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event)
	{
		// assign variables, to inprove code readability;
		Player player = event.getPlayer();
		String command = event.getMessage().toLowerCase();
		
    	if (!player.isPermissionSet("adjuster.spy.override") && player.isOnline())
    	{
    		if (command.startsWith("/pl "))
    		{
        		event.setCancelled(plugin.getConfig().getBoolean("blockcommands.blockplugin", true));
        		if (event.isCancelled())
        		{
        			player.sendMessage(ChatColor.RED + "/plugin is a restricted command, you do not have access to use this.");
        			BukkitUtils.info(player.getName() + " was denied access to /plugin");
        		}
        	}
    		
    		else if (command.startsWith("/ver"))
    		{
    			event.setCancelled(plugin.getConfig().getBoolean("blockcommands.blockver", true));
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/version is a restricted command, you do not have access to use this.");
        			BukkitUtils.info(player.getName() + " was denied access to /version");
    			}
    		}
    		
    		else if (command.startsWith("/gc"))
    		{
    			event.setCancelled(plugin.getConfig().getBoolean("blockcommands.blockgc", true));
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/gc is a restricted command, you do not have access to use this.");
    				BukkitUtils.info(player.toString() + " was denied access to /gc");
    			}
    		}
    		
    		else if (command.startsWith("/balancetop"))
    		{
    			event.setCancelled(plugin.getConfig().getBoolean("blockcommands.blockbaltop", true));
    			if (event.isCancelled())
    			{
            		player.sendMessage(ChatColor.RED + "/balancetop is a restricted command, you do not have access to use this.");
            		BukkitUtils.info(player.toString() + " was denied access to /balancetop");	
    			}
    		}
    		
    		else if (command.startsWith("/reload"))
    		{
    			event.setCancelled(plugin.getConfig().getBoolean("blockcommands.blockreload", true));
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/reload has the chance of causing server memory loss.");
    				player.sendMessage(ChatColor.RED + "if you still want a reload type /confirm reload");
    				BukkitUtils.info(player.toString() + " was denied access to /reload");
    				
    				// TODO: use scheduler to timeout a confirm command
    			}
    		}
    		
    		else if (command.startsWith("/ping"))
    		{
    			event.setCancelled(plugin.getConfig().getBoolean("blockcommands.blockping", true));
    			if (event.isCancelled())
    			{
        			player.sendMessage(ChatColor.RED + "soz bro... DENIED!!! >:D");	
        			BukkitUtils.info(player.toString() + " was denied ping. soz bro, thems the breaks.");
    			}
    			
    			else if (!event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "ping is used to determine if essentials has loaded to the server," +
    						" and in no way is this a determination of latency.");
    			}
    		}
    	}
    }
}