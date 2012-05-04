package com.creepercountry.listeners.Commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ADJCommandExecutor implements CommandExecutor
{
	private List<BaseCommand> commands = new ArrayList<BaseCommand>();

	public ADJCommandExecutor() 
	{		
		// Register commands
		commands.add(new HelpCommand());
	}

	/**
	 * Command manager
	 *
	 * @param sender - {@link CommandSender}
	 * @param command - {@link Command}
	 * @param label command name
	 * @param args arguments
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		// If no arg provided for command, set to help by default
		if (args.length == 0) {
			args = new String[]{"help"};
		}

		// Loop through commands to find match. Supports sub-commands
		BaseCommand onCmd;
		BaseCommand[] onCmdArray = commands.toArray(new BaseCommand[0]);
		int index = 0;
		String[] tempArgs = args;
    
		while (index < onCmdArray.length && tempArgs.length > 0)
		{
			onCmd = onCmdArray[index];
			if(tempArgs[0].equalsIgnoreCase(onCmd.name))
			{
				if(onCmd.subCommands.size() > 0 && tempArgs.length > 1)
				{
					onCmdArray = onCmd.subCommands.toArray(new BaseCommand[0]);
					index = 0;
					tempArgs = (String[]) ArrayUtils.remove(tempArgs, 0);
				} else {
					tempArgs = (String[]) ArrayUtils.remove(tempArgs, 0);
					return onCmd.newInstance().run(sender, tempArgs, label);
				}
			} else {
				index++;
			}
		}
        
		new HelpCommand().run(sender, args, label);
		return true;
	}

	/**
	 * @return the commands
	 */
	public List<BaseCommand> getCommands()
	{
		return commands;
	}
}