package com.creepercountry.listeners.Handlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if ((sender instanceof Player)) {
	           // doSomething
	        } else {
	           sender.sendMessage(ChatColor.RED + "You must be a player!");
	           return false;
	        }
	        Player player = (Player) sender;
	        return false;
	}
}
