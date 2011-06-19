package be.thisisboris.SocietyCraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.thisisboris.SocietyCraft.SocietyCraft;
import be.thisisboris.SocietyCraft.includes.SCLogger;



//import be.thisisboris.SocietyCraft.includes.DataManager;

/**
 * @description Handles commands.
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
        String prefix = colorizeText("[SocietyCraft] - ", ChatColor.GOLD);
        String message;
        if (is(label, "SocietyCraft") || is(label, "SC")) {
        	
        		/* 
        		 * When a user with permissions, which we still have to implement, 
        		 * types /sc or /societycraft he gets an overview of all the help
        		 * sections there are in Societycraft.
        		 * 
        		 * If the terminal executes this command, he should get the statistics
				 * of the current Society. 
				 * 
				 * That's logic!
        		 *
        		 */

        	if (args == null || args.length == 0 || is(args[0],"Help")){
        		        		
        		if (isPlayer(sender)){
        			
        			message = prefix.concat("[HELP]");
        			
        			sendMessage(sender, message);
        			handled = true;
        			
        		} else {
        			
        			message = prefix.concat("Prepare for statistics dump");
        			
        			sendLog(sender, message);
        			handled = true;
        			
        		}
        		        		
        	}
        	
        		/*
        		 * Since these basic commands display help pages, and we have a lot
        		 * of help pages, we should check if there are any arguments behind
        		 * the command. Which will tell the plugin what help page the user
        		 * wants to see.
        		 * 
        		 * If the terminal executes this, different statistics will be displayed.
        		 * For example:
        		 * 
        		 * /sc town will display help for a town to a user, but for the terminal this
        		 * will return statistics about all towns there are in the world.
        		 * 
        		 * That's logic!
        		 * 
        		 */
        	
        	else if (is(args[0],"Nation")) {
        		
        		
        
        	} else if (is(args[0],"Town")) {
        		
        		
        		
        	} else if (is(args[0],"Citizen")) {
        		
        		
        		
        	} else if (is(args[0],"Jobs")) {
        		
        		
        		
        	} else if (is(args[0],"")) {
        		
        		
        		
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
    private String colorizeText(String text, ChatColor color) {
        return color + text + ChatColor.WHITE;
    }
}
