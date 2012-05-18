package com.creepercountry.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.Vector;

import com.creepercountry.main.ADJPlugin;

public final class Config
{    
    /**
     * The plugin instance
     */
	private ADJPlugin plugin = ADJPlugin.getInstance();
	
	/**
	 * config.yml system section variables.
	 * @returns true or false
	 */
	public boolean isreloading, isshutdown, isrunning, isdebug;
	
	/**
	 * config.yml spy section variables.
	 * @returns true or false
	 */
	public boolean blockversion, blockgc, blockreload, blockbaltop, blockping, blockplugin, spyexecutors;
	
	/**
	 * config.yml system section int.
	 * @returns int
	 */
	public int configversion;
	
	/**
	 * config.yml quest section variables.
	 * @returns vector
	 */
	public Vector gold, diamond, redstone, lapis;
	
	/** 
	 * Constructor
	 */
	public Config()
    {
        load();
    }
	
	/**
	 * load the config variables
	 */
	public void load()
	{
		// getConfig, so we can do things with the config
		FileConfiguration config = plugin.getConfig();
		
		// grab data from config, set them to variables
		//// system variables
		isreloading = config.getBoolean("system.isreloading", false);
		isshutdown = config.getBoolean("system.isshutdown", false);
		isrunning = config.getBoolean("system.isrunning", false);
		isdebug = config.getBoolean("system.isdebug", false);
		configversion = config.getInt("system.configversion");
		//// spy variables
		blockversion = config.getBoolean("blockcommands.blockversion", true);
		blockgc = config.getBoolean("blockcommands.blockgc", true);
		blockreload = config.getBoolean("blockcommands.blockreload", true);
		blockplugin = config.getBoolean("blockcommands.blockplugin", true);
		blockbaltop = config.getBoolean("blockcommands.blockbaltop", true);
		blockping = config.getBoolean("blockcommands.blockping", true);
		spyexecutors = config.getBoolean("blockcommands.spyexecutors", false);
		//// quest variables
		gold = config.getVector("quest.gold");
		diamond = config.getVector("quest.diamond");
		redstone = config.getVector("quest.redstone");
		lapis = config.getVector("quest.lapis");
	}
}
