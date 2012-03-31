package com.creepercountry.main;

import java.util.logging.Logger;

import org.bukkit.ChatColor;

public class Adjuster
{
	
    /**
	* If Adjuster is currently enabled
	*/
	public static boolean ENABLED = false;
	    
    /**
	* The current instance of Adjuster
	*/
	private static Adjuster instance;
	
    /**
	* Plugin instance
	*/
	private ADJPlugin plugin;
	
    /**
	* Logging instance
	*/
	private Logger logger = Logger.getLogger("Adjuster");

    public Adjuster(ADJPlugin plugin)
    {
        this.plugin = plugin;
        Adjuster.instance = this;
        //configuration = Configuration.load("core.yml");
    }
        
    /**
     * Get the currently loaded Adjuster instance
     *
     * @return
     */
    public static Adjuster getInstance()
    {
      	return instance;
    }
    
    /**
     * Log a string at info level
     *
     * @param str
     */
    public void log(String str)
    {
    	logger.info("[Adjuster] " + ChatColor.stripColor(str));
    }
    
    /**
     * Log a string at severe level
     * 
     * @param str
     */
    public void severe(String str)
    {
    	logger.severe("[Adjuster] " + ChatColor.stripColor(str));
    }
    
    /**
     * disable this plugin when onDisable fails first time
     */
    public void destruct()
    {
    	severe("Cant disable plugin, Starting destruct sequence!");
    	// first check to see if our variable is set to enabled
    	if (Adjuster.ENABLED = true)
    		Adjuster.ENABLED = false;
    	// then check if any schedular tasks are running
    	if (!plugin.getServer().getScheduler().getPendingTasks().isEmpty())
    		plugin.getServer().getScheduler().cancelTasks(plugin);
    	// try disabling plugin... again
    	if (plugin.isEnabled() || plugin.isInitialized() || plugin.isNaggable())
    		plugin.getPluginLoader().disablePlugin(plugin);
    	severe("Destructed, No further checks available");
    }
}
