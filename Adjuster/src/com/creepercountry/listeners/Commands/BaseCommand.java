package com.creepercountry.listeners.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.creepercountry.util.BukkitUtils;
import com.creepercountry.main.ADJPlugin;

/**
 * Abstract class representing a command. When run by the command manager (
 * {@link Guardian}), it pre-processes all the data into more useful forms.
 * Extending classes should adjust required fields in their constructor
 *
 */
public abstract class BaseCommand
{

	protected final ADJPlugin plugin = ADJPlugin.getInstance();
	protected CommandSender sender;
	protected List<String> args = new ArrayList<String>();
	protected String usedCommand;
	protected Player player;
	
	// Commands below can be set by each individual command
	public String name;
	public int minArgs = -1;
	public int maxArgs = -1;
	public boolean allowConsole = true;
	public boolean commandPassThrough = false;
	public String usage;
	public List<SubCommand> subCommands = new ArrayList<SubCommand>();

	/**
	 * Method called by the command manager in {@link onCmd} to run the
	 * command. Arguments are processed into a list for easier manipulating.
	 * Argument lengths, permissions and sender types are all handled.
	 *
	 * @param csender
	 * {@link CommandSender} to send data to
	 * @param preArgs arguments to be processed
	 * @param cmd command being executed
	 * @return true on success, false if there is an error in the checks or if
	 * the extending command returns false
	 */
	public boolean run(CommandSender csender, String[] preArgs, String cmd)
	{
		sender = csender;
		usedCommand = cmd;
		// Sort out arguments
		args.clear();
		args.addAll(Arrays.asList(preArgs));
		// Check arg lengths
		if (minArgs > -1 && args.size() < minArgs || maxArgs > -1 && args.size() > maxArgs)
		{
			BukkitUtils.sendMessage(sender, ChatColor.RED + "Wrong arguments supplied!");
			sendUsage();
			return true;
		}
		// Check if sender should be a player
		if (!allowConsole && !(sender instanceof Player))
		{
			return false;
		}
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
		// Check permission
		if (!permission())
		{
			BukkitUtils.sendMessage(sender, ChatColor.RED + "You do not have permission to do that!");
			return false;
		}
		return execute();

	}

	/**
	 * Runs the extending command. Should only be run by the BaseCommand after
	 * all pre-processing is done
	 *
	 * @return true on success, false otherwise
	 */
	public abstract boolean execute();

	/**
	 * Performs the extending command's permission check.
	 *
	 * @return true if the user has permission, false if not
	 */
	public final boolean permission()
	{
		return permission(sender);
	}

	public abstract boolean permission(CommandSender csender);

	public abstract BaseCommand newInstance();

	/**
	 * Sends advanced help to the sender
	 */
	public void moreHelp()
	{
	}

	/**
	 * Displays the help information for this command
	 */
	public void sendUsage()
	{
		BukkitUtils.sendMessage(sender, ChatColor.RED + "/" + usedCommand + " " + name + " " + usage);
	}

}