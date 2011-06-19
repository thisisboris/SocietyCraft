package be.thisisboris.SocietyCraft;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

/**
 * @author Thisisboris, cskiwi
 */
public class SCPluginListener extends ServerListener {

    private final SocietyCraft plugin;

    public SCPluginListener(SocietyCraft instance) {
        plugin = instance;
        // TEconomy.initialize();
    }

    @Override
    public void onPluginEnable(PluginEnableEvent event) {
        if (event.getPlugin() != plugin) {
            /* Try to load again!
            TPermissions.onEnable(event.getPlugin());
            TEconomy.onEnable(event.getPlugin());
            TProtection.onEnable(event.getPlugin());
            
            */
        }
    }

    @Override
    public void onPluginDisable(PluginDisableEvent event) {
    }
}
