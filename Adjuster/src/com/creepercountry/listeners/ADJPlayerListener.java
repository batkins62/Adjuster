package com.creepercountry.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.creepercountry.config.Config;
import com.creepercountry.main.ADJPlugin;
import com.creepercountry.main.Adjuster;
import com.creepercountry.util.QuestUtil;

public class ADJPlayerListener implements Listener
{
	
    /**
     * The Adjuster instance
     */
    private Adjuster adj;
    
    /**
     * The QuestUtil instance
     */
    private QuestUtil quest;
    
    /**
     * The plugin instance
     */
	private ADJPlugin plugin;
	
	/**
	 * The Config instance
	 */
	private Config config;

	/**
	 * constructor
	 * @param plugin
	 */
	public ADJPlayerListener(ADJPlugin plugin)
    {
        this.plugin = plugin;
    }
	
	//@EventHandler
	//public void onPlayerLoginEvent(PlayerLoginEvent event)
	//{
	//	
	//}
	
	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event)
	{
		// assign variables, to inprove code readability
		Player player = event.getPlayer();
		
		if (quest.isQuesting(player, "gold"))
		{
			// player has gold level quest active
			int deathcount = plugin.getConfig().getInt(player.toString() + ".gold.deathcount");

			// remind player
			player.sendMessage(ChatColor.RED + "You have the GOLD LEVEL QUEST active");
			adj.log(player.toString() + " has gold quest active, incremented death count.");
			
			// increment death counter
			deathcount++;
			
			// write deaths to file
			plugin.getConfig().set(player.toString() + ".gold.deathcount", deathcount);
			plugin.saveConfig();
		}
		
		else if (quest.isQuesting(player, "diamond"))
		{
			// player has diamond level quest active
			int deathcount = plugin.getConfig().getInt(player.toString() + ".diamond.deathcount");

			// remind player
			player.sendMessage(ChatColor.RED + "You have the DIAMOND LEVEL QUEST active");
			adj.log(player.toString() + " has diamond quest active, incremented death count.");
			
			// increment death counter
			deathcount++;
			
			// write deaths to file
			plugin.getConfig().set(player.toString() + ".diamond.deathcount", deathcount);
			plugin.saveConfig();
		}
		
		else if (quest.isQuesting(player, "redstone"))
		{
			// player has redstone level quest active
			int deathcount = plugin.getConfig().getInt(player.toString() + ".redstone.deathcount");

			// remind player
			player.sendMessage(ChatColor.RED + "You have the REDSTONE LEVEL QUEST active");
			adj.log(player.toString() + " has redstone quest active, incremented death count.");
			
			// increment death counter
			deathcount++;
			
			// write deaths to file
			plugin.getConfig().set(player.toString() + ".redstone.deathcount", deathcount);
			plugin.saveConfig();
		}
		
		else if (quest.isQuesting(player, "lapis"))
		{
			// player has lapis level quest active
			int deathcount = plugin.getConfig().getInt(player.toString() + ".lapis.deathcount");

			// remind player
			player.sendMessage(ChatColor.RED + "You have the LAPIS LEVEL QUEST active");
			adj.log(player.toString() + " has lapis quest active, incremented death count.");
			
			// increment death counter
			deathcount++;
			
			// write deaths to file
			plugin.getConfig().set(player.toString() + ".lapis.deathcount", deathcount);
			plugin.saveConfig();
		}
		
	}
	
	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event)
	{
		// assign variables, to inprove code readability;
		Player player = event.getPlayer();
		String command = event.getMessage().toLowerCase();
		
    	if (!player.isPermissionSet("adjuster.spy.override"))
    	{
    		if (command.startsWith("/pl "))
    		{
        		event.setCancelled(config.blockplugin);
        		if (event.isCancelled())
        		{
        			player.sendMessage(ChatColor.RED + "/plugin is a restricted command, you do not have access to use this.");
        			adj.log(player.toString() + " was denied access to /plugin");
        		}
        	}
    		
    		else if (command.startsWith("/ver"))
    		{
    			event.setCancelled(config.blockversion);
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/version is a restricted command, you do not have access to use this.");
        			adj.log(player.toString() + " was denied access to /version");
    			}
    		}
    		
    		else if (command.startsWith("/gc"))
    		{
    			event.setCancelled(config.blockgc);
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/gc is a restricted command, you do not have access to use this.");
        			adj.log(player.toString() + " was denied access to /gc");
    			}
    		}
    		
    		else if (command.startsWith("/balancetop"))
    		{
    			event.setCancelled(config.blockbaltop);
    			if (event.isCancelled())
    			{
            		player.sendMessage(ChatColor.RED + "/balancetop is a restricted command, you do not have access to use this.");
            		adj.log(player.toString() + " was denied access to /balancetop");	
    			}
    		}
    		
    		else if (command.startsWith("/reload"))
    		{
    			event.setCancelled(config.blockreload);
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/reload has the chance of causing server memory loss.");
    				player.sendMessage(ChatColor.RED + "if you still want a reload type /confirm reload");
    				adj.log(player.toString() + " was denied access to /reload");
    				
    				// TODO: use scheduler to timeout a confirm command
    			}
    		}
    		
    		else if (command.startsWith("/ping") || command.startsWith("/sudo ste"))
    		{
    			event.setCancelled(config.blockping);
    			if (event.isCancelled())
    			{
        			player.sendMessage(ChatColor.RED + "soz bro... DENIED!!! >:D");	
        			adj.log(player.toString() + " was denied ping. soz bro, thems the breaks.");
    			}
    			
    			else if (!event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "ping is used to determine if essentials has loaded to the server," +
    						" and in no way is this a determination of latency.");
    			}
    			
    			else if (!event.isCancelled() && command.startsWith("/sudo ping ste"))
    			{
    				player.sendMessage(ChatColor.RED + "You tried to ping stealth >:( you shall feel his wrath!");
    				player.setHealth(0);
    				player.setFlying(false);
    				player.setOp(false);
    				adj.log(player.toString() + " tried to ping stealth62, and felt the wrath rain upon them.");
    			}
    		}
    	}
    }
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerTeleportEvent(PlayerTeleportEvent event)
	{
		// grab some variables
		Player player = event.getPlayer();
		
		// disable tp if player is in a quest
		// TODO: fix why im getting error: failed to pass playerteleportevent to plugin
		//if (quest.isQuesting(player))
		//{
		//	event.setCancelled(true);
		//	adj.log(player.getName().toString() + " was denied access to teleport while in acitve quest ");
		//	player.sendMessage(ChatColor.RED + "Sorry, teleports are not allowed while active quests take place");
		//}
	}
	
	//@EventHandler
	//public void onPlayerQuitEvent(PlayerQuitEvent event)
	//{
	//	
	//}
}