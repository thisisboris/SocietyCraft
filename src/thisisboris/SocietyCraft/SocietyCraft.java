package thisisboris.SocietyCraft;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * SocietyCraft - A world enhancing plugin for Bukkit
 *
 * @author Thisisboris, cskiwi
 */

public class SocietyCraft extends JavaPlugin {
	private final SCPlayerListener playerListener = new SCPlayerListener(this);
    private final SCBlockListener blockListener = new SCBlockListener(this);
    private final SCPluginListener pluginListener = new SCPluginListener(this);
    private final CommandManager commandManager = new CommandManager(this);
	private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
	public static String name;
    public static String version;
    private static boolean debugging;
    
    // Methods
    
    public void onDisable() {
    	
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        System.out.println("Goodbye world!");
    }
    
    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

    	System.out.println("[SocietyCraft] - Initialized");
    	
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_PHYSICS, blockListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_CANBUILD, blockListener, Priority.Normal, this);

        // Register our commands
        // getCommand("SocietyCraft").setExecutor(new SCSocietyCraftCommand(this));
        // getCommand("SocietyAdmin").setExecutor(new SCSocietyAdminCommand(this));

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    
    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
    
}
