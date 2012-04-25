package com.creepercountry.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.Vector;

import com.creepercountry.main.ADJPlugin;

public final class Config
{    
    /**
     * The plugin instance
     */
	private ADJPlugin plugin;
	
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
		isreloading = config.getBoolean("system.isreloading");
		isshutdown = config.getBoolean("system.isshutdown");
		isrunning = config.getBoolean("system.isrunning");
		isdebug = config.getBoolean("system.isdebug");
		configversion = config.getInt("system.configversion");
		//// spy variables
		blockversion = config.getBoolean("blockcommands.blockversion");
		blockgc = config.getBoolean("blockcommands.blockgc");
		blockreload = config.getBoolean("blockcommands.blockreload");
		blockplugin = config.getBoolean("blockcommands.blockplugin");
		blockbaltop = config.getBoolean("blockcommands.blockbaltop");
		blockping = config.getBoolean("blockcommands.blockping");
		spyexecutors = config.getBoolean("blockcommands.spyexecutors");
		//// quest variables
		gold = config.getVector("quest.gold");
		diamond = config.getVector("quest.diamond");
		redstone = config.getVector("quest.redstone");
		lapis = config.getVector("quest.lapis");
	}
}
