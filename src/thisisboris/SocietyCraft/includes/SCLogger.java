package thisisboris.SocietyCraft.includes;

	import java.util.logging.Level;
	import java.util.logging.Logger;

public class SCLogger {

	private static Logger log;
	private static String prefix;
	
	public static void initialize(Logger newLog) {
		
		SCLogger.log = newLog;
		prefix = "[SocietyCraft]";
		
	}
	
	public static void info(String message) {
		
		log.info(prefix + message);
		
	}
	
}
