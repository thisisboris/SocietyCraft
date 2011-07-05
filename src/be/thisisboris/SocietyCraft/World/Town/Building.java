package be.thisisboris.SocietyCraft.World.Town;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Building {
	public String GetOwner(Location loc){
		String player = "Je Oma";
		// querry getting The info required
		return player;
	}
	public boolean IsOwner(Player player, int houseID){
		boolean IsOwner = false;
		String Name = "";
		// with querry retrieving what ID is 
		if (player.getName() == Name){
			
		}
		return IsOwner;
	}
	public int GetId(Location loc){
		int id = 0;
		double Xpos, Ypos, Zpos;
		Xpos = loc.getX();
		Ypos = loc.getY();
		Zpos = loc.getY();
		
		// with querry getting ID
		return id;
	}
	/*
	 * we can make more functions like IsHouseOwner(Player player, location loc)
	 * but first making these work would be more usefull ;P
	 */
}