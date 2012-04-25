package com.creepercountry.listeners.Executor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.creepercountry.main.ADJPlugin;
import com.creepercountry.main.Adjuster;

public class QuestCmdExecutor implements CommandExecutor
{
    /**
     * The Adjuster instance
     */
    private Adjuster adj;
    
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
        String commandName = args[1].toString().toLowerCase();							// what command was given
        String questname = args[0].toString().toLowerCase();							// what argument was given
        Player player = (Player) sender;												// cast sender as a player
        boolean isPlayer = (sender instanceof Player);									// check if they're a player
        //boolean isSpying = plugin.getConfig().getBoolean("spyexecutors");				// are we spying on commands?
        
        // spy on what people are typing
        // TODO: why isnt this working? throws "java.lang.NullPointerException"
        //if (isSpying && isPlayer)
        //	adj.log(sender.getName().toString() + " used command /quest " + args.toString());
        
        // this is to prevent ArrayIndexOutOfBoundsException
        // TODO: why does this not work?!?!
        if (args.length < 2)
        	return false;
        
        if (isPlayer && args.length == 2)
        {
    		Vector vector = player.getLocation().toVector();
    		int foodlvl = player.getFoodLevel();
    		int health = player.getHealth();
    		int level = player.getLevel();
    		String gamemode = player.getGameMode().toString();
    		String ign = sender.getName().toString();
    		boolean isQuesting = plugin.getConfig().getBoolean(ign + "." + questname + "." + "isActive");

    		// TODO: why is this throwing a "java.lang.NullPointerException"?
    		//String stringvector = plugin.getConfig().getVector(ign + "." + questname + "." + "vector").toString();
    		int displayFood = plugin.getConfig().getInt(ign + "." + questname + "." + "foodlvl");
    		int displayHealth = plugin.getConfig().getInt(ign + "." + questname + "." + "health");
    		int displayLevel = plugin.getConfig().getInt(ign + "." + questname + "." + "level");
    		String stringgamemode = plugin.getConfig().getString(ign + "." + questname + "." + "gamemode");
    		int displayDeath = plugin.getConfig().getInt(ign + "." + questname + "." + "deathcount");
    		int quitcount = plugin.getConfig().getInt(ign + "." + questname + "." + "quits");
    		
        	if (commandName.equals("start") && !isQuesting)
            {
        		
                if (sender.hasPermission("adjuster.quest.start") || sender.isOp())
                {
                	if (questname.equals("gold") 
                			|| questname.equals("diamond") 
                			|| questname.equals("redstone") 
                			|| questname.equals("lapis"))
                	{
                		// set the player active status on the current quest, then  setup profile
                		plugin.getConfig().set(ign + "." + questname + "." + "isActive", true);
                		plugin.getConfig().set(ign + "." + questname + "." + "vector", 0);
                		plugin.getConfig().set(ign + "." + questname + "." + "foodlvl", 0);
                		plugin.getConfig().set(ign + "." + questname + "." + "health", 0);
                		plugin.getConfig().set(ign + "." + questname + "." + "level", 0);
                		plugin.getConfig().set(ign + "." + questname + "." + "gamemode", 0);
                		plugin.getConfig().set(ign + "." + questname + "." + "deathcount", 0);
                		plugin.getConfig().set(ign + "." + questname + "." + "quits", 0);
                		plugin.saveConfig();
                		
                		// welcome the player to the Quest
                		sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                		sender.sendMessage(ChatColor.GREEN + "You have started the " + questname + " level quest!");
                		sender.sendMessage(ChatColor.GREEN + "NOTE: everything you do will be tracked!");
                		sender.sendMessage(ChatColor.GREEN + "If at any point you want to quit the quest, type: /quest quit " + questname);
                		sender.sendMessage(ChatColor.GREEN + "When PROMPTED at the end of your quest type: /quest finish " + questname);
                		sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                		
                		// nag the log
                		// TODO: fix why "java.lang.NullPointerException" happens when this is enabled
                		//adj.log("Player " + ign + " has started quest " + questname + ".");
                	}
                	else
                	{
                		sender.sendMessage(ChatColor.RED + "Soz, only quest options: gold, diamond, redstone, lapis");
                	}
                }
            }
                
            else if (commandName.equals("finish"))
            {
            	if (sender.hasPermission("adjuster.quest.finish") || sender.isOp())
            	{
            		if (player.getLocation().toVector() == plugin.getConfig().getVector(questname))
            		{
            			plugin.getConfig().set(ign + "." + questname + "." + "vector", vector);
            			plugin.getConfig().set(ign + "." + questname + "." + "foodlvl", foodlvl);
            			plugin.getConfig().set(ign + "." + questname + "." + "health", health);
            			plugin.getConfig().set(ign + "." + questname + "." + "level", level);
            			plugin.getConfig().set(ign + "." + questname + "." + "gamemode", gamemode);
            			plugin.getConfig().set(ign + "." + questname + "." + "isActive", false);
            			plugin.saveConfig();
            		
            			sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            			sender.sendMessage(ChatColor.GREEN + "You have finished " + questname);
            			sender.sendMessage(ChatColor.GREEN + "Your data has been saved! ");
            			sender.sendMessage(ChatColor.GREEN + "use: /quest verify " + questname + " " + ign);
            			sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            		}
            		else
            		{
            			sender.sendMessage(ChatColor.RED + "hmm, i show that you havent completed the quest yet...");
            			sender.sendMessage(ChatColor.RED + "if you think you recieved this in error, contact any staff member.");
            			sender.sendMessage(ChatColor.RED + "most of the time, this error means you are not standing on the block required");
            			adj.log(ign + " tried to finish a quest, but was rejected due to incompletion:");
            			adj.log(player.getLocation().toVector().toString());
            		}
            	}
            }
                
            else if (commandName.equals("quit"))
            {
            	
            	
            	if (isQuesting)
            	{
            		// increment the players quit counter
            		plugin.getConfig().set(ign + "." + questname + "." + "quits", quitcount++);
            		
            		// grab players data before we erase
        			plugin.getConfig().set(ign + "." + questname + "." + "vector", vector);
        			plugin.getConfig().set(ign + "." + questname + "." + "foodlvl", foodlvl);
        			plugin.getConfig().set(ign + "." + questname + "." + "health", health);
        			plugin.getConfig().set(ign + "." + questname + "." + "level", level);
        			plugin.getConfig().set(ign + "." + questname + "." + "gamemode", gamemode);
        			plugin.getConfig().set(ign + "." + questname + "." + "isActive", false);
        			
        			// log players data before we erase
        			// TODO: why is this throwing a "java.lang.NullPointerException"?
        			//adj.log(ign + " has QUIT quest " + questname + "...");
        			//adj.log("Logging Stats for: " + ign + " on quest: " + questname);
        			//adj.log("Vector: " + stringvector);
        			//adj.log("Food Level: " + displayFood);
        			//adj.log("Health: " + displayHealth);
        			//adj.log("Level: " + displayLevel);
        			//adj.log("GameMode: " + stringgamemode);
        			//adj.log("Death Count: " + displayDeath);
            		
            		// now erase data
            		plugin.getConfig().set(ign + "." + questname + "." + "isActive", null);
            		plugin.getConfig().set(ign + "." + questname + "." + "vector", null);
            		plugin.getConfig().set(ign + "." + questname + "." + "foodlvl", null);
            		plugin.getConfig().set(ign + "." + questname + "." + "health", null);
            		plugin.getConfig().set(ign + "." + questname + "." + "level", null);
            		plugin.getConfig().set(ign + "." + questname + "." + "gamemode", null);
            		plugin.getConfig().set(ign + "." + questname + "." + "deathcount", null);
            		plugin.saveConfig();
            		
            		// confirm with the player
            		sender.sendMessage(ChatColor.RED + "You have quit " + questname + " level quest.");
            		sender.sendMessage(ChatColor.RED + "You will need to restart the quest, when you decide to continue.");
            		sender.sendMessage(ChatColor.GREEN + "This does NOT give you a new record on this quest...");
            		sender.sendMessage(ChatColor.GREEN + "Data collected in this session was logged to your record.");
            		sender.sendMessage(ChatColor.RED + "You will no longer be tracked in this quest session.");
            	}
            	else
            		sender.sendMessage(ChatColor.RED + "You cant quit a quest you havent started.");
            }
                
            else if (commandName.equals("verify"))
            {
            	if (sender.hasPermission("adjuster.quest.verify") && !isQuesting)
            	{

            		
            		// display the results:
        			sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        			sender.sendMessage(ChatColor.GREEN + "Showing Stats for: " + ign + " on quest: " + questname);
        			//sender.sendMessage(ChatColor.GREEN + "Vector: " + stringvector);
        			sender.sendMessage(ChatColor.GREEN + "Food Level: " + displayFood);
        			sender.sendMessage(ChatColor.GREEN + "Health: " + displayHealth);
        			sender.sendMessage(ChatColor.GREEN + "Level: " + displayLevel);
        			sender.sendMessage(ChatColor.GREEN + "GameMode: " + stringgamemode);
        			sender.sendMessage(ChatColor.GREEN + "Death Count: " + displayDeath);
        			sender.sendMessage(ChatColor.GREEN + "Quit Count: " + quitcount);
        			sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            		
            	}
            	
            	else if (isQuesting)
            	{
            		sender.sendMessage(ChatColor.RED + "Please complete the quest before you verify completion");
            		// TODO: why is this throwing a "java.lang.NullPointerException"?
            		//adj.log(ign + " was denied access to verify because they are actively questing!");
            	}
            }
        	
            else if (commandName.equals("admin"))
            {
            	if (sender.hasPermission("adjuster.quest.admin") || sender.isOp())
            	{
            		if (questname.equals("setgold"))
            		{
            			plugin.getConfig().set(questname, vector);
            			plugin.saveConfig();
            			
            			// TODO: why is this throwing a "java.lang.NullPointerException"?
            			//adj.log(ign + " set the location of the gold level quest");
            			sender.sendMessage(ChatColor.RED + "Vector for GOLD LEVEL QUEST saved!");
            		}
            		if (questname.equals("setdiamond"))
            		{
            			//setdiamondcode
            		}
            		if (questname.equals("setredstone"))
            		{
            			//setredstonecode
            		}
            		if (questname.equals("setlapis"))
            		{
            			//setlapiscode
            		}
            	}
            	else
            	{
            		sender.sendMessage(ChatColor.RED + "DENIED! you do not have the required permission to access this command");
            		adj.log(ign + " was denied access to /quest admin");
            	}
            }
            else if (commandName.equals("start") && isQuesting)
            	sender.sendMessage(ChatColor.RED + "You can't start a quest you already started!");
            	
        	return true;
        }
        // display error messages for offenders of the all mighty syntax
        else if (!isPlayer)
        	sender.sendMessage("soz bro, only players can do this command.");
        else if (!(args.length == 2) && isPlayer)
        	sender.sendMessage(ChatColor.RED + "Invalid syntax, please check what your typing and try again.");
        
        // return false so plugin.yml displays its syntax help message
        return false;
	}
}
