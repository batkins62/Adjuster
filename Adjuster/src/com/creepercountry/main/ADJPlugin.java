package com.creepercountry.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.creepercountry.util.Version;

public class ADJPlugin extends JavaPlugin
{
    /**
     * The Adjuster instance
     */
    private Adjuster adj;
    
    /**
     * The player listener
     */
    private ADJPlayerListener playerListener;
    
    /**
     * The server listener
     */
    private ADJServerListener serverListener;
    
    /**
     * The logging object
     */
    private Logger logger = Logger.getLogger("Adjuster");
    
    /**
     * the command executor instance
     */
    private CommandExecutor ADJExecutor;
    
    @Override
    public void onEnable()
    {
    	preload();
    	// create the plugin object
    	adj = new Adjuster(this);
    	Adjuster.ENABLED = true;
    	
    	//check for essentials
    	
    	// Register Listeners
        try {
            registerEvents();
        } catch (NoSuchFieldError e) {
        }
        // Register Command Handlers
        try {
        	registerCommands();
        } catch (NoSuchFieldError e) {
        }
        
        // load the rest of the plugin
        load();

    	// get version, set version, and display
    	ADJInfo.setVersion(getDescription().getVersion());
        Version version = ADJInfo.FULL_VERSION;
        log("At version: " + version.toString());
    }
    
    @Override
    public void onDisable()
    {
    	Adjuster.ENABLED = false;

        // cancel all tasks we created
        getServer().getScheduler().cancelTasks(this);

        // disable checks, or fail recovery
        if (isEnabled())
        	adj.destruct();
    }
    
    /**
     * Register all of the events used by Adjuster
     */
    private void registerEvents()
    {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(playerListener, this);
        pluginManager.registerEvents(serverListener, this);
    }
    
    /**
     * register commands to executors
     */
    private void registerCommands()
    {
    	ADJExecutor = new CommandExecutor(this);
    	getCommand("reload confirm").setExecutor(ADJExecutor);
    	getCommand("adjuster report").setExecutor(ADJExecutor);
    	getCommand("cuff").setExecutor(ADJExecutor);
    	getCommand("adjuster stop").setExecutor(ADJExecutor);
    	getCommand("adjuster start").setExecutor(ADJExecutor);
    	getCommand("troll").setExecutor(ADJExecutor);
    }
    
    private void preload()
    {
    	log("Loading shared objects");

        playerListener = new ADJPlayerListener(this);
        serverListener = new ADJServerListener(this);
    }
    
    /**
     * @return the Adjuster instance
     */
    public Adjuster getADJ()
    {
    	return adj;
    }
}