package com.creepercountry.listeners;

import com.creepercountry.main.ADJPlugin;
import com.creepercountry.main.Adjuster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class ADJServerListener implements Listener
{
    /**
     * The plugin instance
     */
    private ADJPlugin plugin;
    
    /**
     * The Adjuster instance
     */
    private Adjuster adj;

    public ADJServerListener(ADJPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    /**
     * Grab variables from config
     */
    boolean running = plugin.getConfig().getBoolean("isrunning");
    boolean debug = plugin.getConfig().getBoolean("isdebug");

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event)
    {
        if (!Adjuster.ENABLED)
        {
            return;
        }
        
        // set config running variable to false
        if (running)
        	plugin.getConfig().set("isrunning", false);
        
    	// If in debug, log what your doing
    	if (debug)
    		adj.log("[EventHandler] Plugin Disabled");
    }
    
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event)
    {
    	// If in debug, log what your doing
    	if (debug)
    		adj.log("[EventHandler] Plugin Enabled");
    	
    	// set config running variable to true
    	if (!running)
    		plugin.getConfig().set("isrunning", true);
    }

}