package com.creepercountry.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
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
import com.griefcraft.lwc.LWC;
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
    public void onLoad()
    {
    	// first load data
    	// check for pex, then confirm with log, or destruct
    }
    
    @Override
    public void onEnable()
    {
    	// create the plugin object
    	adj = new Adjuster(this);
    	Adjuster.ENABLED = true;
    	
    	// check for required plugins, then load our plugin.
    	checkForPlugins();
       	adj.load();
    	
       	// register commands & listeners
        registerEvents();
        registerCommands();

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
    }
    
    /**
     * register commands to executors
     */
    private void registerCommands()
    {
    	//executors for spy
    	SpyExecutor = new SpyCmdExecutor(this);
    	//executors for fun
    	FunExecutor = new FunCmdExecutor(this);
    	//executors for banks
    	BankExecutor = new BankCmdExecutor(this);
    	//executors for ranking
    	RankExecutor = new RankCmdExecutor(this);
    	//executors for quests
    	QuestExecutor = new QuestCmdExecutor(this);
    	getCommand("quest start").setExecutor(QuestExecutor);
    	getCommand("quest quit").setExecutor(QuestExecutor);
    	getCommand("quest finish").setExecutor(QuestExecutor);
    	getCommand("quest verify").setExecutor(QuestExecutor);
    }

    /**
     * Check for required plugins to be loaded
     */
	//lwc: soft
	//logblock: soft
	//towny: soft
    //worldguard: soft
	//essentials, chat, spawn: depend
    private void checkForPlugins()
    {
    	//lwc
    	LWC lwc = null;
    	Plugin lwcPlugin = getServer().getPluginManager().getPlugin("LWC");
    	if(lwcPlugin != null) {
    	    lwc = ((LWCPlugin) lwcPlugin).getLWC();
    	}
    	//worldguard
    	Plugin wgPlugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (wgPlugin == null || !(wgPlugin instanceof WorldGuardPlugin)) {
            return null;
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