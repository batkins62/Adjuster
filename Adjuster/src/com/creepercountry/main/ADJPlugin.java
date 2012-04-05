package com.creepercountry.main;

import java.util.logging.Logger;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.creepercountry.listeners.ADJPlayerListener;
import com.creepercountry.listeners.ADJServerListener;
import com.creepercountry.listeners.Executor.QuestCmdExecutor;
import com.creepercountry.listeners.Executor.SpyCmdExecutor;
import com.creepercountry.util.Version;
import com.griefcraft.lwc.LWCPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class ADJPlugin extends JavaPlugin
{
    /**
     * The Adjuster instance
     */
    private Adjuster adj;
    
    /**
     * The listeners
     */
    private ADJPlayerListener playerListener;
    private ADJServerListener serverListener;
    
    /**
     * the command executor instances
     */
    private SpyCmdExecutor SpyExecutor;
    private BankCmdExecutor BankExecutor;
    private FunCmdExecutor FunExecutor;
    private RankCmdExecutor RankExecutor;
    private QuestCmdExecutor QuestExecutor;
    
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
        registerEvents();
        registerCommands();
        
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
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(playerListener, this);
        pluginManager.registerEvents(serverListener, this);
        
        // Shared Objects
        playerListener = new ADJPlayerListener(this);
        serverListener = new ADJServerListener(this);
        
        // log your success **future:display what has been loaded, rather just "im done"**
        adj.log("PluginManager has registered our listeners.");
    }
    
    /**
     * register commands to executors
     */
    private void registerCommands()
    {
    	// executors for spy
    	SpyExecutor = new SpyCmdExecutor(this);
    	getCommand("spy").setExecutor(SpyExecutor)
    	// executors for fun
    	FunExecutor = new FunCmdExecutor(this);
    	// executors for banks
    	BankExecutor = new BankCmdExecutor(this);
    	getCommand("bank").setExecutor(BankExecutor);
    	// executors for ranking
    	RankExecutor = new RankCmdExecutor(this);
    	getCommand("ranking").setExecutor(RankExecutor);
    	// executors for quests
    	QuestExecutor = new QuestCmdExecutor(this);
    	getCommand("quest").setExecutor(QuestExecutor);
    	
    	// log our success
    	adj.log("commands loaded to their executors");
    }

    /**
     * Check for required plugins to be loaded
     * @return
     */
	//lwc: soft
	//logblock: soft
	//towny: soft
    //worldguard: soft
	//essentials, chat, spawn: depend
    //pex: depend
    private Object pluginHooks()
    {
    	//lwc
    	Plugin lwcPlugin = getServer().getPluginManager().getPlugin("LWC");
    	if(lwcPlugin == null || !(lwcPlugin instanceof LWCPlugin))
    	{
    		return null;
    	}
    	//worldguard
    	Plugin wgPlugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (wgPlugin == null || !(wgPlugin instanceof WorldGuardPlugin))
        {
            return null;
        }
    }
    
    /**
     *  load the plugin for full use
     */
    public void load()
    {
    	// load the config.yml, so we can append data
        getConfig().options().copyDefaults(true);
        adj.log("Config file found! Loading data...");
        
        //load the questdata.yml @deprecated
        //DELETED: public class QuestConfiguration implements ConfigurationSerializable
        //YamlConfiguration questdata = new YamlConfiguration();
        //questdata.load(getResource("questdata.yml"));
        //questdata.setDefaults(questdata);
        //Adjuster.quest = QuestConfiguration.valueOf(questdata.getConfigurationSection("questdata").getValues(false));
        
        // if the config isnt there, create a new one
        if(!(new File(getDataFolder(),"config.yml").exists()))
        {
        	adj.warn("No config.yml found... creating blank file.");
        	saveDefaultConfig();
        }
    }
    
    /**
     * @return the Adjuster instance
     */
    public Adjuster getADJ()
    {
    	return adj;
    }
}