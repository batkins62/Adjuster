package com.creepercountry.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.creepercountry.main.ADJPlugin;
import com.creepercountry.main.Adjuster;

public class ADJPlayerListener implements Listener
{
	
    /**
     * The Adjuster instance
     */
    private Adjuster adj;
    
    /**
     * The plugin instance
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
	
	//
	//
	// if player dies and is in quest, increment the deathcount
	//
	//
	
	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event)
	{
		
	}
	
	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event)
	{
		// assign variables, to inprove code readability
		Player player = event.getPlayer();
		String goldpath = player.toString() + ".gold.isActive";
		String diamondpath = player.toString() + ".diamond.isActive"; 
		String redstonepath = player.toString() + ".redstone.isActive";
		String lapispath = player.toString() + ".lapis.isActive";
		
		// grab options and variables from the config
		boolean goldQuesting = plugin.getConfig().getBoolean(goldpath);
		boolean diamondQuesting = plugin.getConfig().getBoolean(diamondpath);
		boolean redstoneQuesting = plugin.getConfig().getBoolean(redstonepath);
		boolean lapisQuesting = plugin.getConfig().getBoolean(lapispath);
		
		if (goldQuesting = true)
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
		
		else if (diamondQuesting = true)
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
		
		else if (redstoneQuesting = true)
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
		
		else if (lapisQuesting = true)
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
		
		// grab options from the config
		boolean blockplugin = plugin.getConfig().getBoolean("blockplugin");
		boolean blockver = plugin.getConfig().getBoolean("blockversion");
		boolean blockgc = plugin.getConfig().getBoolean("blockgc");
		boolean blockbaltop = plugin.getConfig().getBoolean("blockbaltop");
		boolean blockreload = plugin.getConfig().getBoolean("blockreload");
		boolean blockping = plugin.getConfig().getBoolean("blockping");

		
    	if (!player.isPermissionSet("adjuster.spy.override"))
    	{
    		if (command.startsWith("/pl ") || command.equals("/plugin"))
    		{
        		event.setCancelled(blockplugin);
        		if (event.isCancelled())
        		{
        			player.sendMessage(ChatColor.RED + "/plugin is a restricted command, you do not have access to use this.");
        			adj.log(player.toString() + " was denied access to /plugin");
        		}
        	}
    		
    		else if (command.startsWith("/ver") || command.equals("/version"))
    		{
    			event.setCancelled(blockver);
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/version is a restricted command, you do not have access to use this.");
        			adj.log(player.toString() + " was denied access to /version");
    			}
    		}
    		
    		else if (command.startsWith("/gc") || command.equals("/gc"))
    		{
    			event.setCancelled(blockgc);
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/gc is a restricted command, you do not have access to use this.");
        			adj.log(player.toString() + " was denied access to /gc");
    			}
    		}
    		
    		else if (command.startsWith("/balancetop") || command.equals("/balancetop"))
    		{
    			event.setCancelled(blockbaltop);
    			if (event.isCancelled())
    			{
            		player.sendMessage(ChatColor.RED + "/balancetop is a restricted command, you do not have access to use this.");
            		adj.log(player.toString() + " was denied access to /balancetop");	
    			}
    		}
    		
    		else if (command.startsWith("/reload") || command.equals("/reload"))
    		{
    			event.setCancelled(blockreload);
    			if (event.isCancelled())
    			{
    				player.sendMessage(ChatColor.RED + "/reload has the chance of causing server memory loss.");
    				player.sendMessage(ChatColor.RED + "if you still want a reload type /confirm reload");
    				adj.log(player.toString() + " was denied access to /reload");
    				
    				// TODO: use scheduler to timeout a confirm command
    			}
    		}
    		
    		else if (command.startsWith("/ping") || command.equals("/ping") || command.startsWith("/sudo ping ste"))
    		{
    			event.setCancelled(blockping);
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
	
	@EventHandler
	public void onPlayerTeleportEvent(PlayerTeleportEvent event)
	{
		
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event)
	{
		
	}

   

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerCommandPreprocessHigh(PlayerCommandPreprocessEvent event)
    {
        if(event.getMessage().toLowerCase().startsWith("/reload"))
        {
            event.setCancelled(true);
            if(event.getPlayer().isOnline())
            {
                event.getPlayer().sendMessage("Reloads have a possibility of creating server side problems, are you sure you want to reload?");
                event.getPlayer().sendMessage((new StringBuilder()).append(ChatColor.RED).append("Use /reload confirm to reload the server").toString());
            }
        }
        if((event.getMessage().toLowerCase().contains("ping") || event.getMessage().toLowerCase().contains("pong") || event.getMessage().toLowerCase().contains("ding")) && event.getPlayer().isOnline())
        {
            event.getPlayer().sendMessage("ping is used to determine if essentials has loaded to the server, and in no way is this a determination of latency.");
            log.warning("Player attempted to ping the server, but failed to understand what it does.");
        }
        if(event.getMessage().toLowerCase().contains("sudo stealth ping") || event.getMessage().toLowerCase().contains("sudo stealth62 ping"))
        {
            event.setCancelled(true);
            if(event.getPlayer().isOnline())
            {
                event.getPlayer().sendMessage("Stealth does not want your ping");
                event.getPlayer().setHealth(0);
            }
        }
    }
}