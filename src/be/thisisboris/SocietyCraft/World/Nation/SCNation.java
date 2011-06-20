package be.thisisboris.SocietyCraft.World.Nation;

import java.util.List;

public class SCNation {
	
	private String name;
	private int people;
	private String king;
	
	/* 
	 * When a nation is created, e new instance of this class is made.
	 * It contains all the nation-based methods. It gets and stores in a
	 * MySQL database.
	 * 
	 */
	
	public boolean isNeutral() {
		boolean neutral = false;
		
		return neutral;
	}
	
	public String setStance(String stance, SCNation nation) {
					
		if (stance == "ally") {
			
			return this.name + " has forged an alliance with " + nation.name;
			
		} else if (stance == "enemy") {
			
			return this.name + " has declared a war against " + nation.name;
			
		} else if (stance == "neutral") {
			
			return this.name + " is now neutral about " + nation.name + " and it's followers";
			
		} else {
			
			return "An error has occured while setting " + this.name + " to " + stance + " with " + nation.name;
			
		}
		
	}
	
	
}
