package thisisboris.SocietyCraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import thisisboris.SocietyCraft.SocietyCraft;
import thisisboris.SocietyCraft.includes.SCLogger;


//import thisisboris.SocietyCraft.includes.DataManager;

/**
 * @description Handles a command.
 * @authors Thisisboris and cskiwi
 */
public class SocietyCraftcmd implements CommandExecutor {
	
    private final SocietyCraft plugin;
    
    public SocietyCraftcmd(SocietyCraft instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean handled = false;
        if (is(label, "SocietyCraft") || is(label, "SC") || is(label, "sc")) {
        	// if there are no arguments
        	if (args == null || args.length == 0){
        		handled = true;
        		String message = "[SocietyCraft] - Command by Player";
            	if (sendMessage(sender, message)) {
            		// Function that should be executed upon command.
            	} else {
            		message = "Command by Server";
            		// Not a player, not sent to player
            		sendLog(sender, message);    		        		
            	}
        	}
        	// if there are arguments after /sc , ...
        	else {
        		if (is(args[0],"Help")){
        			handled = true;
        			String message = colorizeText("[SocietyCraft]", ChatColor.YELLOW)+ "Help pages:";
        			if (sendMessage(sender, message))
        			{
        				// Function that should be executed upon command.
        			}
        		}
        	}
        }
        return handled;
    }

    // Simplifies and shortens the if statements for commands.
    private boolean is(String entered, String label) {
        return entered.equalsIgnoreCase(label);
    }

    // Checks if the current user is actually a player.
    private boolean isPlayer(CommandSender sender) {
        return sender != null && sender instanceof Player;
    }

    // Checks if the current user is actually a player and sends a message to that player.
    private boolean sendMessage(CommandSender sender, String message) {
        boolean sent = false;
        if (isPlayer(sender)) {
            Player player = (Player) sender;
            player.sendMessage(message);
            sent = true;
        }
        return sent;
    }

    private boolean sendLog(CommandSender sender, String message) {
        boolean sent = false;
        if (!isPlayer(sender)) {
            SCLogger.info(message);
            sent = true;
        }
        return sent;
    }
    
    /*
     * Setted to Commend because no use of it

    // Checks if the current user is actually a player and returns the name of that player.
    private String getName(CommandSender sender) {
        String name = "";
        if (isPlayer(sender)) {
            Player player = (Player) sender;
            name = player.getName();
        }
        return name;
    }

    // Gets the player if the current user is actually a player.
    private Player getPlayer(CommandSender sender) {
        Player player = null;
        if (isPlayer(sender)) {
            player = (Player) sender;
        }
        return player;
    }
     */
    private String colorizeText(String text, ChatColor color) {
        return color + text + ChatColor.WHITE;
    }
}
