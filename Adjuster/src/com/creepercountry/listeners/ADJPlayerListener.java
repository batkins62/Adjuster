package com.creepercountry.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

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
	
	@EventHandler (priority = EventPriority.HIGH)
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
}