package com.creepercountry.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.creepercountry.main.ADJPlugin;
import com.creepercountry.main.Adjuster;

public class ADJPlayerListener implements Listener
{
	
    /**
     * The Adjuster instance
     */
    private Adjuster adj;

	public ADJPlayerListener(ADJPlugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        if(event.getMessage().toLowerCase().startsWith("/pl ") || event.getMessage().toLowerCase().endsWith("plugin") || event.getMessage().toLowerCase().startsWith("/ver") || event.getMessage().toLowerCase().contains("gc") || event.getMessage().toLowerCase().endsWith("version"))
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