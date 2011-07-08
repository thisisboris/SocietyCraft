package be.thisisboris.SocietyCraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.thisisboris.SocietyCraft.SocietyCraft;
import be.thisisboris.SocietyCraft.includes.SCLogger;

enum HelpList {list, plot, house, resident, nation;};
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
        String prefix = colorizeText("[SocietyCraft] - ", ChatColor.GOLD);
        String message;
                
        if (is(label, "SocietyCraft") || is(label, "SC")) {

        	if (args == null || args.length == 0){
	        	//------------------
				// Player
				//------------------
				if (isPlayer(sender)){
	    			
	    			message = prefix.concat("[HELP]");
	    			sendMessage(sender, message);
	    			
	    			sendMessage(sender, colorizeText("/SC or SocietyCraft: ", ChatColor.DARK_AQUA) + "Shows this page.");
	    			sendMessage(sender, colorizeText("/SC Nations: ", ChatColor.DARK_AQUA) + "Information about Nations");
	    			sendMessage(sender, colorizeText("/SC NPC's: ", ChatColor.DARK_AQUA) + "Information about NPC's");
	    			sendMessage(sender, colorizeText("/SC Jobs: ", ChatColor.DARK_AQUA) + "Information about jobs");
	    			sendMessage(sender, colorizeText("/SC Chat: ", ChatColor.DARK_AQUA) + "Information about different Chatchels");
	    			sendMessage(sender, colorizeText("/SC Plot: ", ChatColor.DARK_AQUA) + "Information about plot's");
	    			sendMessage(sender, colorizeText("/SC House: ", ChatColor.DARK_AQUA) + "Information about houses");

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
	    			switch (HelpList.valueOf(args[1])){
	    			case list:
	    				
	    				String listmessage;
	    				int length;
	    				
	    				listmessage = "Amount of players: ";
	    				length = plugin.Onlineplayerlist.size();;
	    				listmessage += Integer.toString(length);
	    				sendMessage(sender, listmessage);
	    				
	    				// resetting message to an empty string
	    				listmessage = "";
	    				// adding the players
	    				for (int i = 0; i < length; i++){
	    					listmessage += plugin.Onlineplayerlist.get(i).getDisplayName();
	    				}
	    				
	    				// sending the player list
	    		        if (isPlayer(sender)){
	    		        	sendMessage(sender, listmessage);
	    		        	handled = true;
	    		        } else {
	    		        	SCLogger.info(listmessage);
	    		        	handled = true;
	    		        }
	    				
    				break;
	    			
	    			case plot:
	    				if (isPlayer(sender)){
    						message = prefix.concat("[HELP]");
        	    			sendMessage(sender, message);
        	    			
        	    			sendMessage(sender, colorizeText("/SC help plot: ", ChatColor.DARK_AQUA) + "Shows this page.");
        	    			sendMessage(sender, colorizeText("/SC help plot buy: ", ChatColor.DARK_AQUA) + "Buy's the plot where your standing in");
        	    			sendMessage(sender, colorizeText("/SC help plot sell: ", ChatColor.DARK_AQUA) + "Sells the plot where your standing in");
        	    			sendMessage(sender, colorizeText("/SC help plot Setowner <PlayerName>: ", ChatColor.DARK_AQUA) + "Set the owner of a plot");
        	    			sendMessage(sender, colorizeText("/SC help plot SetPrice <Price>: ", ChatColor.DARK_AQUA) + "Set the price of the plot");
        	    			
        	    			handled = true;
	            		} else {
	            			// terminal info
	            		}
	    				break;
	    			case house:
	    				if (isPlayer(sender)){
    						message = prefix.concat("[HELP]");
        	    			sendMessage(sender, message);
        	    			
        	    			sendMessage(sender, colorizeText("/SC help house: ", ChatColor.DARK_AQUA) + "Shows this page.");
        	    			sendMessage(sender, colorizeText("/SC help house buy: ", ChatColor.DARK_AQUA) + "Buy's the house where your standing in");
        	    			sendMessage(sender, colorizeText("/SC help house sell: ", ChatColor.DARK_AQUA) + "Sells the house where your standing in");
        	    			sendMessage(sender, colorizeText("/SC help house Setowner <PlayerName>: ", ChatColor.DARK_AQUA) + "Set the owner of a house");
        	    			sendMessage(sender, colorizeText("/SC help house SetPrice <Price>: ", ChatColor.DARK_AQUA) + "Set the price of the house");
        	    			sendMessage(sender, colorizeText("/SC help house SetRent <Price>: ", ChatColor.DARK_AQUA) + "Set the rent of the house");
        	    			
        	    			handled = true;
	            		} else {
	            			// terminal info
	            		}
	    				break;	   
	    			default:
	    				// wrong command after help
	    				if (isPlayer(sender)){
	            			message = prefix.concat("[HELP]");
	            			message = "Can't find help page, type /sc help for the availible help pages";
	            			sendMessage(sender, message);
	            			handled = true;
	            		} else {
	            			SCLogger.warning("not a registerd command.");
	            			handled = true;
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
    		} else if (is(args[0], "House")){
	        	if (isPlayer(sender)){
	        		if (args.length >= 2){
	        			if (is(args[1],"buy")){
	        				// player is trying to buy a house
	        				Location loc = getPlayer(sender).getLocation();
	        				/*
	        				 * Player is trying to buy a house,
	        				 * 
	        				 * checklist:
	        				 * 	- He is in a house that's buyable	
	        				 * 	- he has the money for it
	        				 * 	- He has the permissions
	        				 * 		* he isn't an enemy of the town
	        				 * 		* he isn't an enemy of the Nation
	        				 * 		* Player that is selling the house doens't allow for him to buy
	        				 * 	- Set the sign that defines the house with his name
	        				 * 
	        				 */
	        				handled = true;
	        			} else  if (is(args[1], "Sell")){
	        				Location loc = getPlayer(sender).getLocation();
	        				
	        				
	        				/*
	        				 * Player is trying to sell the house
	        				 * 
	        				 * checklist:
	        				 *	- he owes the house he's trying to sell
	        				 */
	        				handled = true;
	        			} else if (is (args[1], "SetPrice")){
	        				/*
	        				 * Checklist:
	        				 * 	- Pretty strait forward
	        				 */
	        				handled = true;
	        			} else if (is (args[1], "SetRent")){
	        				Player player = getPlayer(sender);
	        				/*
	        				 * CheckList:
	        				 * 	- check if he's the owner
	        				 * 
	        				 * 	Notes:
	        				 * 	- setting by ID, or by standing in the house? 
	        				 */
	        				handled = true;
	        			} else if (is (args[1], "SetOwner")){
	        				Player player = getPlayer(sender);
	        				/*
	        				 * Checklist:
	        				 * 	- if player is the owner
	        				 * 	- if the other player is a member
	        				 * 	- if the other player is online otherwise otherwise send message when he come's online
	        				 * 
	        				 */
	        				handled = true;
	        			} else {
	        				sendMessage(sender, "this is not a command, plzz try /sc help house for the availible commands");
	        				handled = true;
	        			}
	        		} else {
	        			sendMessage(sender, "This is not a command, plzz try /sc help house for the availible commands");
	        			handled = true;
	        		}
	        	} else {
	        		// command executed by terminal
	        	}
	        } else if (is(args[0], "Plot")){
	        	if (isPlayer(sender)){
	        		if (args.length >= 2){
	        			if (is(args[1],"buy")){
	        				// player is trying to buy a house
	        				Location loc = getPlayer(sender).getLocation();
	        				/*
	        				 * Player is trying to buy a house,
	        				 * 
	        				 * checklist:
	        				 * 	- He is in a plot that's buyable	
	        				 * 	- he has the money for it
	        				 * 	- He has the permissions
	        				 * 		* he isn't an enemy of the town
	        				 * 		* he isn't an enemy of the Nation
	        				 * 		* Player that is selling the house doens't allow for him to buy
	        				 * 	- Set the sign that defines the house with his name
	        				 * 
	        				 */
	        				handled = true;
	        			} else  if (is(args[1], "Sell")){
	        				Location loc = getPlayer(sender).getLocation();
	        				/*
	        				 * Player is trying to sell the house
	        				 * 
	        				 * checklist:
	        				 *	- he owes the plot he's trying to sell
	        				 */
	        				handled = true;
	        			} else if (is (args[1], "SetOwner")){
	        				Player player = getPlayer(sender);
	        				/*
	        				 * Checklist:
	        				 * 	- if player is the owner
	        				 * 	- if the other player is a member
	        				 * 	- if the other player is online otherwise otherwise send message when he come's online
	        				 * 
	        				 */
	        				handled = true;
	        			} else if (is (args[1], "SetPrice")){
	        				Player player = getPlayer(sender);
	        				/*
	        				 * CheckList:
	        				 * 	- if he's the owner
	        				 * 	- is the plot is for sale
	        				 * 
	        				 * 	Notes:
	        				 * 	- setting by ID, or by standing in the house? 
	        				 */
	        				handled = true;
	        			} else {
	        				sendMessage(sender, "this is not a command, plzz try /sc help house for the availible commands");
	        				handled = true;
	        			}
	        		} else {
	        			sendMessage(sender, "This is not a command, plzz try /sc help house for the availible commands");
	        			handled = true;
	        		}
	        	} else {
	        		// command executed by terminal
	        	}
	        	
        	} else if (is(args[0], "SetSignText")){
	        	if (isPlayer(sender)){
		        	if (args.length >= 2){
		        		if (getPlayer(sender).isOp()){
		        			String TotalText;
		        			plugin.RequestingEditSign = true;
		    				sendMessage(sender, "Right click the sign you want to edit");
		    				
		    				// clear the string for reuse
		    				TotalText = "";
		    				// get every word.
		    				for(int i = 1; i < args.length; i++){
		    					TotalText += args[i].toString();
		    					// if not last word ad space between
		    					if (i != args.length -1){
		    						TotalText += " ";
		    					}
		    				}
		    				plugin.PlayerAction = getPlayer(sender);
		    				plugin.SetSignText(TotalText);
		    				handled = true; 
		    			} else {
		    				sendMessage(sender, "You need to be an op to do this");
		    			}
		        	} else {
		        		sendMessage(sender, "/sc SetSignText <TEXT>");
		        		handled = true;
		        	}
		       	} else {
		       		SCLogger.warning("Not a command for terminal.");
		       		handled = true;
		       	}
	        } 
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
