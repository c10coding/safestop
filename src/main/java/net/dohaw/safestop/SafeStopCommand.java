package net.dohaw.safestop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SafeStopCommand implements CommandExecutor {

    private SafeStop plugin;

    public SafeStopCommand(SafeStop plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equalsIgnoreCase("reload") && args.length == 1){
            plugin.getBConfig().reloadConfig();
            plugin.loadValues();
        }else{
            sender.sendMessage("The only command this plugin has is /safestop reload!");
        }
        return false;
    }
}
