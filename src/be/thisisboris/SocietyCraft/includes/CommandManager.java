package be.thisisboris.SocietyCraft.includes;

import java.util.Hashtable;
import java.util.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import be.thisisboris.SocietyCraft.SocietyCraft;

/**
 *
 * @author brenda
 */
public class CommandManager {

    private SocietyCraft plugin;
    private Map<String, CommandExecutor> commands = new Hashtable<String, CommandExecutor>();

    public CommandManager(SocietyCraft instance) {
        this.plugin = instance;
    }

    public void addCommand(String label, CommandExecutor executor) {
        commands.put(label, executor);
    }

    public boolean dispatch(CommandSender sender, Command command, String label, String[] args) {
        if (!commands.containsKey(label)) {
            return false;
        }

        boolean handled = true;

        CommandExecutor ce = commands.get(label);
        handled = ce.onCommand(sender, command, label, args);

        return handled;
    }
}