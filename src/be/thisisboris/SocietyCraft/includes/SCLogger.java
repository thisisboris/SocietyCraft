package be.thisisboris.SocietyCraft.includes;

	import java.util.logging.Level;
	import java.util.logging.Logger;

public class SCLogger {

	private static Logger log;
	private static String prefix;
	
	public static void initialize(Logger newLog) {
		
		SCLogger.log = newLog;
		prefix = "[SocietyCraft] - ";
		
	}
	
	public static void info(String message) {
		log.info(prefix + message);
	}
	
	public static void error(String message) {
        log.severe(prefix + message);
    }
	
    public static void warning(String message) {
        log.warning(prefix + message);
    }

    public static void config(String message) {
        log.config(prefix + message);
    }
}
