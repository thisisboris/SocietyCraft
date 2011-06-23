package be.thisisboris.SocietyCraft.utils.Databases;

import java.io.File;

import be.thisisboris.SocietyCraft.SocietyCraft;
import be.thisisboris.SocietyCraft.includes.SCLogger;

public class DatabaseManager {

	// Properties
	
	private SocietyCraft plugin;
	private String name = "[SOCIETYCRAFT]";
	
	enum Supported {MySQL, SQLite, Flatfile, None}
	
	public String DBtype = "";
	public String propFile = "";
	
	private static final String settingsFile = "db.properties";		
	// Methods
	public DatabaseManager(SocietyCraft instance) {
		plugin = instance;	
		
		File configFile = new File(plugin.getDataFolder(), settingsFile);
	    PropertiesFile file = new PropertiesFile(configFile);
	}
	

	public boolean initialize() {
		
		name = plugin.getDescription().getName();
		
		boolean succeeded = false;

			switch (Supported.valueOf("None")) {
			
			case MySQL:
				
				DBtype = "MySQL";
				succeeded = true;
				
			break;
			
			case Flatfile:
				
				DBtype = "Flatfile";
				succeeded = true;
			
			break;
			
			case SQLite:
				
				DBtype = "SQLite";
				succeeded = true;
				
			break;
			
			case None:
				
				DBtype = "None";
				succeeded = false;
				
			break;
			
			default:
				
				SCLogger.warning("[" + name + "] - " + "No database found in SocietyCraft.dbprop");
				SCLogger.warning("[" + name + "] - " + "Using Flatfile as Standard");
				
				DBtype = "Flatfile";
				succeeded = true;
				
			break;
			}
		// Check db.properties file for what type to use. If connection failed, return that it failed.
		
				
		return succeeded;
	}
	
	public boolean connect() {
		boolean succeeded = false;
		
		
		return succeeded;
	}	
	
	public boolean storeThatInfo(String q) {
		boolean succeeded = false;
		String query = q;
		
		
		return succeeded;
	}
	
	public boolean retrieveThisInfo (String q) {
		boolean succeeded = false;
		String query = q;
		
		
		return succeeded;
	}	
}
