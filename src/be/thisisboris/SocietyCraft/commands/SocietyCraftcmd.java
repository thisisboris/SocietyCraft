package be.thisisboris.SocietyCraft.commands;

import org.bukkit.ChatColor;
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
	    			sendMessage(sender, colorizeText("/SC chat: ", ChatColor.DARK_AQUA) + "Information about different Chatchannels");

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
	    		        }
	    				
    				break;
	    			
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
	            		} else {
	            			SCLogger.warning("not a registerd command.");
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
    		} else if (is(args[0], "SetSignText")){
	        	if (isPlayer(sender)){
		        	if (args.length >= 2){
		        		if (getPlayer(sender).isOp()){
		        			String SignText[] = plugin.signText;
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
		    				
	    					int Lenght = TotalText.length();
	    					int EndIndex = TotalText.length();
    						String TempLine1 = "";
    						String TempLine2 = "";
    						String TempLine3 = "";
    						String TempLine4 = "";
    						
		    				if (Lenght < 60){
		    					SCLogger.info("Total string length = " + Lenght);
		    					if (Lenght <= 15){
		    						TempLine1 = TotalText;
		    					} else if (Lenght <= 30){
		    						TempLine1 = TotalText.substring(0, 15);
		    						TempLine2 = TotalText.substring(15,EndIndex);
		    					} else if (Lenght <= 45){
		    						TempLine1 = TotalText.substring(0, 15);
		    						TempLine2 = TotalText.substring(15,30);
		    						TempLine3 = TotalText.substring(30,EndIndex);
		    					} else {
		    						TempLine1 = TotalText.substring(0, 15);
		    						TempLine2 = TotalText.substring(15,30);
		    						TempLine3 = TotalText.substring(30,45);
		    						TempLine4 = TotalText.substring(30,EndIndex);
		    					}
		    				} else {
		    					sendMessage(sender, "The text is to long for the sign, make it shorter");
		    				}
    						// setting the lines
    						SignText[0] = TempLine1;
    						SignText[1] = TempLine2;
    						SignText[2] = TempLine3;
    						SignText[3] = TempLine4;
		    				
    						// sending to logger for testing purposes
		    				SCLogger.info("line 1 = " + SignText[0]);
    						SCLogger.info("line 2 = " + SignText[1]);
    						SCLogger.info("line 3 = " + SignText[2]);
    						SCLogger.info("line 4 = " + SignText[3]);
		    				
		    				// Setting the public value 
		    				plugin.signText[0] = SignText[0];
		    				plugin.signText[1] = SignText[1];
		    				plugin.signText[2] = SignText[2];
		    				plugin.signText[3] = SignText[3];
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
