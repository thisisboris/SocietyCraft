package be.thisisboris.SocietyCraft;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import be.thisisboris.SocietyCraft.commands.SocietyCraftcmd;
import be.thisisboris.SocietyCraft.includes.CommandManager;
import be.thisisboris.SocietyCraft.includes.SCLogger;
import be.thisisboris.SocietyCraft.utils.Databases.DatabaseManager;



/**
 * SocietyCraft - A world enhancing plugin for Bukkit
 *
 * @authors Thisisboris and cskiwi
 */

public class SocietyCraft extends JavaPlugin {
	private final SCPlayerListener playerListener = new SCPlayerListener(this);
    private final SCBlockListener blockListener = new SCBlockListener(this);
    private final SCPluginListener pluginListener = new SCPluginListener(this);
    private final CommandManager commandManager = new CommandManager(this);
    private final DatabaseManager dbMan = new DatabaseManager(this);
    private final List<Player> debugees = new ArrayList<Player>();
	public static String name;
    public static String version;
    private static boolean debugging;
    
    // public commands
    public static List<Player> Onlineplayerlist = new ArrayList<Player>();
    public boolean RequestingEditSign = false;
    public Player PlayerAction;
    // Methods
    
    public void onEnable() {	
    	name = this.getDescription().getName();
        version = this.getDescription().getVersion();
    	
        // Logger
    	SCLogger.initialize(Logger.getLogger("Minecraft"));  	
    	
    	/*
         * Events
         */
        PluginManager pm = getServer().getPluginManager();
        // Plugin Events
        pm.registerEvent(Event.Type.PLUGIN_ENABLE, pluginListener, Priority.Monitor, this);
        // Block Events
        pm.registerEvent(Event.Type.BLOCK_CANBUILD, blockListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Priority.Normal, this);
        // Player Events
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
        
        /*
         * I'll be working on a way give an AI to certain NPC's and NPE's. Listening to the chat
         * will be part of this. Example: If a player approaches a NPC, the NOC speaks to him in 
         * a way the player can answer. A guide would react like this:
         * 
         * Hello can I help you?
         * Player: Yes
         * Tell me the topic!
         * Player: Housing.
         * [HOUSING HELP]
         * 
         * or
         * 
         * Hello can I help you?
         * Player: No.
         * Okay, have a nice day!
         * 
         */

        // Register our commands
        setupCommands();
        
        // Check for database

        if (dbMan.initialize()) {
        	
        	String dbtype = dbMan.DBtype;
        	SCLogger.info(name + " has succesfully connected to " + dbtype);
        	
        } else {
        	
        	SCLogger.warning("could not connect to any Database!");
        	SCLogger.warning("functionality will be limited!");
        	
        }
        
        SCLogger.info(name + " version " + version + " is enabled!");
    }
    
    /*
     * Sets up the core commands of the plugin.
     */
    private void setupCommands() {

        addCommand("SocietyCraft", new SocietyCraftcmd(this));
        addCommand("SC", new SocietyCraftcmd(this));
        addCommand("SocietyAdmin", new SocietyCraftcmd(this));
        addCommand("SA", new SocietyCraftcmd(this));
        
    }

    /*
     * Executes a command when a command event is received.
     * 
     * @param sender    The thing that sent the command.
     * @param cmd       The complete command object.
     * @param label     The label of the command.
     * @param args      The arguments of the command.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return commandManager.dispatch(sender, cmd, label, args);
    }

    /*
     * Adds the specified command to the command manager and server.
     * 
     * @param command   The label of the command.
     * @param executor  The command class that excecutes the command.
     */
    private void addCommand(String command, CommandExecutor executor) {
        getCommand(command).setExecutor(executor);
        commandManager.addCommand(command, executor);
    }

    /*
     * This method runs when the plugin is disabling.
     */
    @Override
    public void onDisable() {
        //TDatabase.disable();
    	
    	/* 
    	 * TODO: Save everything! 
    	 */
    	SCLogger.info(name + "has succesfully been disabled!");
    	
    }
    
    /*
     * Checks is the plugin is in debug mode.
     */
    public boolean inDebugMode(){
        return !debugees.isEmpty() || debugging;
    }

    /*
     * Checks if a player is in debug mode.
     * 
     * @param player    The player to check.
     */
    public boolean isDebugging(final Player player) {
        return debugees.contains(player);
    }

    /*
     * Sets a players debug mode.
     * 
     * @param player    The player to set the debug mode of.
     */
    public void startDebugging(final Player player) {
        debugees.add(player);
    }
    
    public void startDebugging() {
        debugging = true;
    }
    
    public void stopDebugging(final Player player) {
        debugees.remove(player);
    }
    
    public void stopDebugging() {
        for(Player player : debugees) {
            player.sendMessage("You are no longer in debug mode.");
        }
        debugees.clear();
        debugging = false;
    }
    public String SignTextFull = "";
   // For changing a sign with the program
    public void ChangeSign(Block block, String Text){
    	SignTextFull = Text;
    	ChangeSign(block);
    }
    // Settiing them both.
    public void SetSignText(String string){
    	SignTextFull = string;
    }
    public void ChangeSign(Block block){
    	if (block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)
		{
    		if (RequestingEditSign){
    			
    			// Just making a string and clearing the 4 lines
    			String[] signText = {"","","",""};
    			// Setting TempString, for easy change name of SignTextFull if nessecairy
    			String[] TempText = {"","","","","","", ""};
    			
				String FindText = SignTextFull;
				String Char = "\"";
				int beginIndex = 0;
				int endIndex = 0;
				int StringPlace = 0;
				int lastIndex = 0;
				boolean Succeded = false;
			
				while(beginIndex + lastIndex < FindText.length() && StringPlace < 7){
					beginIndex = FindText.indexOf("\"") + lastIndex;					
					if (beginIndex != -1){						
						endIndex = FindText.indexOf(Char, beginIndex +1);
						if (endIndex != -1){							
							TempText[StringPlace] = FindText.substring(beginIndex+1, endIndex);
							lastIndex = endIndex;
							StringPlace++;
							Succeded = true;
						} else{
							// 2e " forgot ==> error, unless someone wants to write a char detecting system :P
							break;
						}
						
					} else {
						if (FindText.length() <= 15 && StringPlace == 0)TempText[2] = FindText;
						Succeded = true;
						break;
					}
				}
				
				for (int i = 0; i < TempText.length; i++){
					if (TempText[i].length() >= 15){
						switch (i){
						case 0:
							PlayerAction.sendMessage("Your first line is to long");
							break;
						case 2:
							PlayerAction.sendMessage("Your second line is to long");
							break;
						case 4:
							PlayerAction.sendMessage("Your thirth line is to long");
							break;
						case 6:
							PlayerAction.sendMessage("Your fourth line is to long");
							break;
						}
						i++;
						Succeded = false;
					}
				}
				
				if (Succeded){
					// setting the lines
					signText[0] = TempText[0];
					signText[1] = TempText[2];
					signText[2] = TempText[4];
					signText[3] = TempText[6];
					
					// creating a sing variable to set the sign
					BlockState state = block.getState();
	    			Sign sign = (Sign) state;
	    				
	    			// Setting the lines
	    			sign.setLine(0, signText[0]);
	    			sign.setLine(1, signText[1]);
	    			sign.setLine(2, signText[2]);
	    			sign.setLine(3, signText[3]);
					
					
	    			// nessecairy, if not sign text won't change
	    			sign.update();
				} else {
					PlayerAction.sendMessage("Something went rong, plzz try again");
				}
        	}
		}
    }
}