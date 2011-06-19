package be.thisisboris.SocietyCraft;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Handle events for all Player related events
 * @author Thisisboris, cskiwi
 */

public class SCPlayerListener extends PlayerListener {
	private final SocietyCraft plugin;
	
	public SCPlayerListener(SocietyCraft instance) {
		
		plugin = instance;
		
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		
	}
	public void onPlayerQuit(PlayerQuitEvent event) {
		 
	}    	
    public void onPlayerKick(PlayerKickEvent event) {
    	
    }
	
	
}
