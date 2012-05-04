package com.creepercountry.main;

import java.io.File;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.creepercountry.listeners.ADJPlayerListener;
import com.creepercountry.listeners.ADJServerListener;
import com.creepercountry.listeners.Commands.ADJCommandExecutor;
import com.creepercountry.util.Version;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class ADJPlugin extends JavaPlugin
{
    /**
     * The Adjuster instance
     */
    private Adjuster adj;
    
    /**
     * Our Plugin instance
     */
    private static ADJPlugin instance;
    
    /**
     * The listeners
     */
    private ADJPlayerListener playerListener;
    private ADJBlockListener blockListener;
    private ADJServerListener serverListener;
    
    /**
     * the command executor instances
     */
    private ADJCommandExecutor cmdExecutor;
    
    /**
     * our vault instances and variables
     */
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;

    @Override
    public void onEnable()
    {
    	// create the plugin object
    	adj = new Adjuster(this);
    	Adjuster.ENABLED = true;
    	
    	// hook into depends, then load our plugin.
    	pluginHooks();
       	load();
    	
       	// register commands & listeners
        registerCommands();
        registerEvents();
        
    	// set version, get version, and display
    	ADJInfo.setVersion(getDescription().getVersion());
        Version version = ADJInfo.FULL_VERSION;
        adj.log("At version: " + version.toString());
    }
    
    @Override
    public void onDisable()
    {
    	Adjuster.ENABLED = false;

        // cancel ALL tasks we created
        getServer().getScheduler().cancelTasks(this);
        getServer().getServicesManager().unregisterAll(this);

        // disable checks, or fail recovery
        if (isEnabled())
        	adj.destruct();
    }
    
    /**
     * Register all of the events used by Adjuster
     */
    private void registerEvents()
    {
        // Shared Objects
        playerListener = new ADJPlayerListener(this);
        blockListener = new ADJBlockListener(this);
        serverListener = new ADJServerListener(this);
        
        // register event listeners
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(playerListener, this);
        pluginManager.registerEvents(blockListener, this);
        pluginManager.registerEvents(serverListener, this);
        
        // log your success 
        // TODO: **future:display what has been loaded, rather just "im done"**
        // TODO: use bukkitutils debug instead of auto log to logger
        adj.log("PluginManager has registered our listeners.");
    }
    
    /**
     * register commands to executors
     */
    private void registerCommands()
    {
    	cmdExecutor = new ADJCommandExecutor();
    	getCommand("quest").setExecutor(cmdExecutor);
    	
    	// TODO: add debug message to console
    }

    /**
     * Check for required plugins to be loaded
     */
    // TODO: vault status: 100%
    private void pluginHooks()
    {
        if (!setupEconomy() )
        {
            adj.severe(String.format("[%s] - Disabled due to no Vault dependency found!"));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
    }
    
    /**
     * Depend on Vault.jar
     * @return
     */
    private boolean setupEconomy()
    {
    	if (getServer().getPluginManager().getPlugin("Vault") == null) {
    		return false;
    	}
    	RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
        	return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    /**
     * Setup chat (part of vault) dependancy
     * @return
     */
    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    /**
     * Setup permissions (part of vault) dependancy
     * @return
     */
    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    
    /**
     *  load the plugin for full use
     */
    public void load()
    {    
        // if the config isnt there, create a new one
        if(!(new File(getDataFolder(),"config.yml").exists()))
        {
        	adj.warn("No config.yml found... creating blank file.");
        	saveDefaultConfig();
        }
        
    	// if config is there we load the config.yml, so we can append data
        else if(new File(getDataFolder(),"config.yml").exists())
        {
            getConfig().options().copyDefaults(true);
            adj.log("Config file found! Loading data...");
        }
    }
    
    /**
     * @return the Adjuster instance
     */
    public Adjuster getADJ()
    {
    	return adj;
    }
    
    /**
     * @return the main command executor
     */
    public ADJCommandExecutor getCommandExecutor()
    {
        return cmdExecutor;
    }
    
    /**
     * @return the current plugin instance
     */
    public static ADJPlugin getInstance()
    {
        return instance;
    }
}