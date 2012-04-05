package com.creepercountry.listeners;

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
		
	}
	
	@EventHandler (ignoreCancelled = true)
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event)
	{
        if(event.getMessage().toLowerCase().startsWith("/pl ") || event.getMessage().toLowerCase().endsWith("plugin"))
        {
        	if (!event.getPlayer().hasPermission("adjuster.spy.allow") || !event.getPlayer().isOp())
        	{
        		event.setCancelled(true)
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

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        if(event.getMessage().toLowerCase().startsWith("/pl ") 
        		|| event.getMessage().toLowerCase().endsWith("plugin") 
        		|| event.getMessage().toLowerCase().startsWith("/ver") 
        		|| event.getMessage().toLowerCase().startsWith("/gc") 
        		|| event.getMessage().toLowerCase().endsWith("version"))
        {
            if(event.getPlayer().isPermissionSet("adjuster.view.allow"))
                if(!event.getPlayer().isOp());
            		event.setCancelled(true);
            		adj.log((new StringBuilder()).append(event.getPlayer()).append(" was denied access to ").append(event.getMessage()).toString());
        }
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