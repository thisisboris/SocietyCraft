package be.thisisboris.SocietyCraft.utils.Databases;

import java.io.File;

import be.thisisboris.SocietyCraft.SocietyCraft;
import be.thisisboris.SocietyCraft.includes.SCLogger;

public class DatabaseManager {

	// Properties
	
	private SocietyCraft plugin;
	private String name;
	
	enum Supported {MySQL, SQLite, Flatfile, None}
	
	public String DBtype = "";
	public String propFile = "";
	
	private static final String settingsFile = "Config.properties";		
	// Methods
	public DatabaseManager(SocietyCraft instance) {
		plugin = instance;	
		name = plugin.getDescription().getName();
	}
	

	public boolean initialize() {
		boolean succeeded = false;
			
			File configFile = new File(plugin.getDataFolder(), settingsFile);
		    PropertiesFile file = new PropertiesFile(configFile);
			switch (Supported.valueOf(null)) {
			
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
				SCLogger.warning("[" + name + "] - " +"Using Flatfile as Standard");
				
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
