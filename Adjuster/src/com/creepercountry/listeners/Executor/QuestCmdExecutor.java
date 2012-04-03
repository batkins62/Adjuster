package com.creepercountry.listeners.Executor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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
        String commandName = args[1].toLowerCase();				// what command was given
        String questname = args[0].toLowerCase();				// what argument was given
        Player player = (Player) sender;						// cast sender as a player
        boolean isPlayer = (sender instanceof Player);			// check if they're a player
        
        if (isPlayer)
        {
    		Vector vector = player.getLocation().toVector();
    		int foodlvl = player.getFoodLevel();
    		int health = player.getHealth();
    		int level = player.getLevel();
    		String gamemode = player.getGameMode().toString();
    		String ign = player.getName().toString();
    		
        	if (commandName.equals("start"))
            {
                if (sender.hasPermission("adjuster.quest.start") || sender.isOp())
                {
                	//start code
                }
            }
                
            else if (commandName.equals("finish"))
            {
            	if (sender.hasPermission("adjuster.quest.finish") || sender.isOp())
            	{	
            		plugin.getConfig().set(ign + "." + questname + "." + "vector", vector);
            		plugin.getConfig().set(ign + "." + questname + "." + "foodlvl", foodlvl);
            		plugin.getConfig().set(ign + "." + questname + "." + "health", health);
            		plugin.getConfig().set(ign + "." + questname + "." + "level", level);
            		plugin.getConfig().set(ign + "." + questname + "." + "gamemode", gamemode);
            		plugin.saveConfig();
            		
            		sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            		sender.sendMessage(ChatColor.GREEN + "You have finished " + questname);
            		sender.sendMessage(ChatColor.GREEN + "Your data has been saved! ");
            		sender.sendMessage(ChatColor.GREEN + "use: /quest verify " + questname + " " + ign);
            		sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            	}
            }
                
            else if (commandName.equals("quit"))
            {
                // quit code
            }
                
            else if (commandName.equals("verify"))
            {
        		plugin.getConfig().getVector(ign + "." + questname + "." + "vector");
        		plugin.getConfig().getInt(ign + "." + questname + "." + "foodlvl");
        		plugin.getConfig().getInt(ign + "." + questname + "." + "health");
        		plugin.getConfig().getInt(ign + "." + questname + "." + "level");
        		plugin.getConfig().getString(ign + "." + questname + "." + "gamemode");
            }
            return true;
        }
        else if (!isPlayer)
        	sender.sendMessage("soz bro, only players can do this command.");
        
        return false;
	}
}
