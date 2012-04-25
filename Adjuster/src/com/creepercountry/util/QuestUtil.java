package com.creepercountry.util;

import org.bukkit.entity.Player;

import com.creepercountry.main.ADJPlugin;

public class QuestUtil
{    
    /**
     * The plugin instance
     */
	private ADJPlugin plugin;
	
	/**
	 * Check to see if a player has a quest active.
	 * @param player
	 * @return 
	 */
	@SuppressWarnings("unused")
	public boolean isQuesting(Player p)
	{
		// assign variables, to inprove code readability
		String goldpath = p.toString() + ".gold.isActive";
		String diamondpath = p.toString() + ".diamond.isActive"; 
		String redstonepath = p.toString() + ".redstone.isActive";
		String lapispath = p.toString() + ".lapis.isActive";
		
		// grab options and variables from the config
		boolean goldQuesting = plugin.getConfig().getBoolean(goldpath);
		boolean diamondQuesting = plugin.getConfig().getBoolean(diamondpath);
		boolean redstoneQuesting = plugin.getConfig().getBoolean(redstonepath);
		boolean lapisQuesting = plugin.getConfig().getBoolean(lapispath);
		
		if (goldQuesting = true)
			return true;
		else if (diamondQuesting = true)
			return true;
		else if (redstoneQuesting = true)
			return true;
		else if (lapisQuesting = true)
			return true;
		else
			return false;
	}
	
	/**
	 * Returns what quest the player has active
	 * @param player
	 * @return gold, diamond, redstone, lapis
	 */
	@SuppressWarnings("unused")
	public String activeQuest(Player p)
	{
		// assign variables, to inprove code readability
		String goldpath = p.toString() + ".gold.isActive";
		String diamondpath = p.toString() + ".diamond.isActive"; 
		String redstonepath = p.toString() + ".redstone.isActive";
		String lapispath = p.toString() + ".lapis.isActive";
		
		// grab options and variables from the config
		boolean goldQuesting = plugin.getConfig().getBoolean(goldpath);
		boolean diamondQuesting = plugin.getConfig().getBoolean(diamondpath);
		boolean redstoneQuesting = plugin.getConfig().getBoolean(redstonepath);
		boolean lapisQuesting = plugin.getConfig().getBoolean(lapispath);
		
		if (goldQuesting = true)
			return "gold";
		else if (diamondQuesting = true)
			return "diamond";
		else if (redstoneQuesting = true)
			return "redstone";
		else if (lapisQuesting = true)
			return "lapis";
		else
			return "none";
	}
	
	/**
	 * returns true if player is active with the specified quest
	 * @param player
	 * @param quest: gold, diamond, restone, lapis
	 * @return
	 */
	@SuppressWarnings("unused")
	public boolean isQuesting(Player p, String q)
	{
		// assign variables, to inprove code readability
		String goldpath = p.toString() + ".gold.isActive";
		String diamondpath = p.toString() + ".diamond.isActive"; 
		String redstonepath = p.toString() + ".redstone.isActive";
		String lapispath = p.toString() + ".lapis.isActive";
		
		// grab options and variables from the config
		boolean goldQuesting = plugin.getConfig().getBoolean(goldpath);
		boolean diamondQuesting = plugin.getConfig().getBoolean(diamondpath);
		boolean redstoneQuesting = plugin.getConfig().getBoolean(redstonepath);
		boolean lapisQuesting = plugin.getConfig().getBoolean(lapispath);
		
		if (goldQuesting = true)
			if (q == "gold")
				return true;
		else if (diamondQuesting = true)
			if (q == "diamond")
				return true;
		else if (redstoneQuesting = true)
			if (q == "redstone")
				return true;
		else if (lapisQuesting = true)
			if (q == "lapis")
				return true;
		
		// TODO: this may not work, if not change to else statement
		return false;
	}
}
