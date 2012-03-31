package com.creepercountry.listeners;

import com.creepercountry.main.ADJPlugin;
import com.creepercountry.main.Adjuster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

public class ADJServerListener implements Listener
{
    private ADJPlugin plugin;

    public ADJServerListener(ADJPlugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event)
    {
        if (!Adjuster.ENABLED)
        {
            return;
        }
    }

}