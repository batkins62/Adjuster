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
        String commandName = command.getName().toLowerCase();	// what command was given
        String argString = args.toString();						// what argument was given
        Player player = (Player) sender;						// cast sender as a player
        boolean isPlayer = (sender instanceof Player);			// check if they're a player
        
        
        if (isPlayer)
        {
        	if (commandName.equals("quest start"))
            {
                if (sender.hasPermission("adjuster.quest.start") || sender.isOp())
                {
                	// start code
                }
            }
                
            else if (commandName.equals("quest finish"))
            {
            	if (sender.hasPermission("adjuster.quest.finish") || sender.isOp())
            	{
            		Vector vector = player.getLocation().toVector();
            		int foodlvl = player.getFoodLevel();
            		int health = player.getHealth();
            		int level = player.getLevel();
            		String gamemode = player.getGameMode().toString();
            		String ign = player.getName().toString();

            		sender.sendMessage(ChatColor.RED + "DO NOT MOVE! saving your current quest data");
            		
            		plugin.getConfig().set("[ " + ign + ", " + argString + " ] vector: ", vector);
            		plugin.getConfig().set("[ " + ign + ", " + argString + " ] foodlvl: ", foodlvl);
            		plugin.getConfig().set("[ " + ign + ", " + argString + " ] health: ", health);
            		plugin.getConfig().set("[ " + ign + ", " + argString + " ] level: ", level);
            		plugin.getConfig().set("[ " + ign + ", " + argString + " ] gamemode: ", gamemode);
            		plugin.getConfig().set("[ " + ign + ", " + argString + " ] ign: ", ign);
            		plugin.saveConfig();
            		
            		sender.sendMessage(ChatColor.RED + "Your data has been saved");
            	}
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
