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
    	String SignTextFull = string;
    }
    public void ChangeSign(Block block){
    	if (block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)
		{
    		if (RequestingEditSign){
    			
    			// deviding text in to 4 rules;
    			 String[] signText = {"","","",""};
    			 // Setting TempString, for easy change name of SignTextFull if nessecairy
    			 String TempText = SignTextFull;
    			
    			// Editing Text
    			int Lenght = TempText.length();
				int EndIndex = TempText.length();
				String TempLine1 = "";
				String TempLine2 = "";
				String TempLine3 = "";
				String TempLine4 = "";
				
				if (Lenght < 60){
					if (Lenght <= 15){
						TempLine1 = TempText;
					} else if (Lenght <= 30){
						TempLine1 = TempText.substring(0, 15);
						TempLine2 = TempText.substring(15,EndIndex);
					} else if (Lenght <= 45){
						TempLine1 = TempText.substring(0, 15);
						TempLine2 = TempText.substring(15,30);
						TempLine3 = TempText.substring(30,EndIndex);
					} else {
						TempLine1 = TempText.substring(0, 15);
						TempLine2 = TempText.substring(15,30);
						TempLine3 = TempText.substring(30,45);
						TempLine4 = TempText.substring(45,EndIndex);
					}
				}
				// setting the lines
				signText[0] = TempLine1;
				signText[1] = TempLine2;
				signText[2] = TempLine3;
				signText[3] = TempLine4;
				
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
    			
        	}
		}
    }
}