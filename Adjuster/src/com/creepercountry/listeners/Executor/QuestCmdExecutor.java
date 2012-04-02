package com.creepercountry.listeners.Executor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.creepercountry.main.ADJPlugin;

public class QuestCmdExecutor implements CommandExecutor
{
    /**
     * The plugin instance
     */
	private ADJPlugin plugin;
	 
	public QuestCmdExecutor(ADJPlugin Adjuster)
	{
		this.plugin = Adjuster;
	}
	 
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
    {
        String commandName = command.getName().toLowerCase();	// what command was given
        String argString = args.toString();						// what argument was given
        boolean isPlayer = (sender instanceof Player);			// check if they're a player
        
        if (isPlayer)
        {
        	if (commandName.equals("quest start"))
            {
                // start code
            }
                
            else if (commandName.equals("quest finish"))
            {
            	// finish code
            }
                
            else if (commandName.equals("quest quit"))
            {
                // quit code
            }
                
            else if (commandName.equals("quest verify"))
            {
                // verify code
            }
            return true;
        }
        else if (!isPlayer)
        	sender.sendMessage(ChatColor.RED + "soz bro, only players can do this command.");
        
        return false;
	}
}
