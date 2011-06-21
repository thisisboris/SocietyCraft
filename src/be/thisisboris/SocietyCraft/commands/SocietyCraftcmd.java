package be.thisisboris.SocietyCraft.commands;

import java.text.Format;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.thisisboris.SocietyCraft.SocietyCraft;
import be.thisisboris.SocietyCraft.includes.SCLogger;

enum HelpList {plot, house, resident, nation;};
enum NationList {add, remove, setking, setcapital };
enum TownList {add, remove, setspawn, setassistent, removeassistent, list };
enum CitizenList {make, setowner, givemoney };
enum JobsList {join, browse, quit };    
enum AdminList {kick, ban}

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
        String prefix = "[SocietyCraft] - ";
        String message;
                
        if (is(label, "SocietyCraft") || is(label, "SC")) {

        	if (args == null || args.length == 0){
	        	//------------------
				// Player
				//------------------
				if (isPlayer(sender)){
	    			
	    			message = prefix.concat("[HELP]");
	    			sendMessage(sender, message);
	    			
	    			sendMessage(sender, colorizeText("/SC or SocietyCraft: ", ChatColor.LIGHT_PURPLE) + "Shows this page.");
	    			sendMessage(sender, colorizeText("/SC Nations: ", ChatColor.LIGHT_PURPLE) + "Information about Nations");
	    			sendMessage(sender, colorizeText("/SC NPC's: ", ChatColor.LIGHT_PURPLE) + "Information about NPC's");
	    			sendMessage(sender, colorizeText("/SC Jobs: ", ChatColor.LIGHT_PURPLE) + "Information about jobs");
	    			sendMessage(sender, colorizeText("/SC chat: ", ChatColor.LIGHT_PURPLE) + "Information about different Chatchannels");

	    			handled = true;
	    			
	    		} else {
	    		//-------------------
				// Terminal
				//-------------------
	    			sendLog(sender, "Prepare for statistics dump!");
	    			sendLog(sender, "Dump!");
	    			sendLog(sender, "Dump!");
	    			sendLog(sender, "Dump!");
	    			sendLog(sender, "Dump!");
	    			sendLog(sender, "Dump!");
	    			sendLog(sender, "Holy shit that's a lot of dumps!");
	    			handled = true;
	    			
	    		}
    		}else if (is(args[0],"Help")){
	    		if (args.length >= 2){
	    			handled = true;
	    			switch (HelpList.valueOf(args[1])){
	    			case plot:
	    				//------------------
	    				// Player
	    				//------------------
	    				if (isPlayer(sender)){
	    					if (args.length == 3){
	    						if (args[2].hashCode() == "kiwi".hashCode()){
	            					message = prefix.concat("[HELP]");
	                    			message = "You must be kiwi to know this command :P";
	                    			sendMessage(sender, message);
	            				}
	            			}else {
		            			message = prefix.concat("[HELP]");
		            			message = "Here comes the helppage for the plots";
		            			sendMessage(sender, message);
	            			}
	            		}
	    				break;
	    			case house:
	    				//------------------
	    				// Player
	    				//------------------
	    				if (isPlayer(sender)){
	            			message = prefix.concat("[HELP]");
	            			message += "Here comes the helppage for the house";
	            			sendMessage(sender, message);
	            		}
	    				break;
	   
	    			default:
	    				// wrong command after help
	    				if (isPlayer(sender)){
	            			message = prefix.concat("[HELP]");
	            			message = "Can't find help page, the following helppages excists: plot, house, resident, nation";
	            			sendMessage(sender, message);
	            		}
	    				break;
	    			}        		        		
	        	}else {
	        		if (isPlayer(sender)){
            			message = prefix.concat("[HELP]");
            			message = "/sc help";
            			sendMessage(sender, message);
            			handled = true;
            		}
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

        	
        } else if (is(label, "SocietyAdmin") || is(label, "SA")) {
    	if (args == null || args.length == 0){
        		//------------------
				// Player
				//------------------
				if (isPlayer(sender)){
        			if (getPlayer(sender).isOp()){
        				message = prefix.concat("[HELP]");
        				message = "Yes yes, your an admin";
        				sendMessage(sender, message);
            			handled = true;
        			}
        		} else {
        		//-------------------
    			// Terminal
    			//-------------------
        			message = prefix.concat("Prepare for statistics dump");
        			message = "Here you go some admin help xD";
        			sendLog(sender, message);
        			handled = true;
        			
        		}
    		}else if (is(args[0],"Help")){
    			if (args.length > 0){
    				handled = true;
    				switch (AdminList.valueOf(args[1])){
	        		case ban:
	        			break;
	        		default:
	        			// wrong command after help
	        			break;
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
