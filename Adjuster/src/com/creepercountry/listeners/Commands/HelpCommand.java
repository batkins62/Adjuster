package com.creepercountry.listeners.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import com.creepercountry.main.ADJInfo;
import com.creepercountry.util.BukkitUtils;

public class HelpCommand extends BaseCommand
{
    public HelpCommand() 
    {
        name = "help";
        maxArgs = 1;
        minArgs = 0;
        usage = "<- lists all Adjuster commands";
    }


    @Override
    public boolean execute() {
        // General help
        if (args.isEmpty()) {
            BukkitUtils.sendMessage(sender, ChatColor.AQUA + cleanTitle("Adjuster v" + ADJInfo.FULL_VERSION, "="));
            BukkitUtils.sendMessage(sender, "Type /adjuster help <command> for more info on that command");
            for (BaseCommand cmd : plugin.getCommandExecutor().getCommands().toArray(new BaseCommand[0])) {
                if (cmd.permission(sender)) {
                    BukkitUtils.sendMessage(sender, "- " + ChatColor.GREEN + "/" + usedCommand + " " + cmd.name + ChatColor.GREEN + " " + cmd.usage);
                }
            }
        } // Command-specific help
        else {
            for (BaseCommand cmd : plugin.getCommandExecutor().getCommands().toArray(new BaseCommand[0])) {
                if (cmd.permission(sender) && cmd.name.equalsIgnoreCase(args.get(0))) {
                    BukkitUtils.sendMessage(sender, "---------------------- Adjuster - " + cmd.name);
                    BukkitUtils.sendMessage(sender, "- " + ChatColor.GREEN + "/" + usedCommand + " " + cmd.name + ChatColor.GREEN + " " + cmd.usage);
                    cmd.sender = sender;
                    cmd.moreHelp();
                    return true;
                }
            }
            BukkitUtils.sendMessage(sender, "No command found by that name");
        }
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, ChatColor.AQUA + "Shows all adjuster commands");
        BukkitUtils.sendMessage(sender, ChatColor.RED + "Type " + ChatColor.GRAY + "/adjuster help <command>" + ChatColor.RED + " for help on that command");
    }

    @Override
    public boolean permission(CommandSender csender) {
        return true;
    }

    @Override
    public BaseCommand newInstance() {
        return new HelpCommand();
    }
    
    public String cleanTitle(String title, String fill) { // Formats a string with a provided title and padding and centers title
        int chatWidthMax = 53; // Vanilla client line character max
        int titleWidth = title.length() + 2; // Title's character width with 2 spaces padding
        int fillWidth = (int) ((chatWidthMax - titleWidth) / 2D); // Fill string calculation for padding either side
        String cleanTitle = "";
        
        for(int i = 0; i < fillWidth; i++)
            cleanTitle += fill;
        cleanTitle += " " + title + " ";
        for(int i = 0; i < fillWidth; i++)
            cleanTitle += fill;
        
        return cleanTitle;
    }
}
