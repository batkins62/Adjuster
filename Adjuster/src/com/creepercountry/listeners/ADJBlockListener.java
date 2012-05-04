package com.creepercountry.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.creepercountry.main.ADJPlugin;
import com.creepercountry.main.Adjuster;

public class ADJBlockListener implements Listener
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
	public ADJBlockListener(ADJPlugin plugin)
    {
        this.plugin = plugin;
    }
	
	//@EventHandler
	//public void onSignChangeEvent(SignChangeEvent event)
	//{
		//
	//}
	
}
