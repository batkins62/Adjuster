package com.creepercountry.listeners.Handlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.creepercountry.main.Adjuster;

public class ADJCmdExecutor implements CommandExecutor
{	 
    /**
     * The plugin instance
     */
	private Adjuster plugin;
	 
	public ADJCmdExecutor(Adjuster plugin)
	{
		this.plugin = plugin;
	}
	 
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// ... implementation exactly as before ...
	}
}
