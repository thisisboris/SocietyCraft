package be.thisisboris.SocietyCraft;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

import be.thisisboris.SocietyCraft.includes.SCLogger;

/**
 * Handle events for all Player related events
 * @authors Thisisboris and cskiwi
 * 
 * 
 */

public class SCPlayerListener extends PlayerListener {
	
	private final SocietyCraft plugin;
	
	public SCPlayerListener(SocietyCraft instance) {
		
		plugin = instance;
		
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		int index = plugin.Onlineplayerlist.size();
		plugin.Onlineplayerlist.add(event.getPlayer());
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		int lengthList = plugin.Onlineplayerlist.size();
		Player QuitingPlayer = event.getPlayer();
		for (int i = 0; i < lengthList; i++){
			Player TempPlayer = plugin.Onlineplayerlist.get(i);
			if (TempPlayer.getDisplayName() == QuitingPlayer.getDisplayName()){
				plugin.Onlineplayerlist.remove(i);
				break;
			}
		}
	
	}
	public void onPlayerInteract(PlayerInteractEvent event) {
		SCLogger.info("Event Happening");
		 if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			 SCLogger.info("Rightclicking");
				 if (plugin.RequestingEditSign){
					 SCLogger.info("Rightclicking And it's true");
					 plugin.ChangeSign(event.getClickedBlock());
					 plugin.RequestingEditSign = false;
				 }
		    }
	 }
    public void onPlayerKick(PlayerKickEvent event) {
    	
    }
}
