package be.thisisboris.SocietyCraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;
/**
 * SocietyCraft block listener
 * @authors Thisisboris and cskiwi
 */


public class SCBlockListener extends BlockListener {
	private final SocietyCraft plugin;
	
	public SCBlockListener(final SocietyCraft plugin) {
        this.plugin = plugin;
    }

	@Override
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
    } 
	public void onBlockBreak (BlockBreakEvent event) {
		if (event.isCancelled()) return;
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if (block.getType() == Material.LOG) {
			player.sendMessage("You are not allowed to have that wood block!");
			event.setCancelled(true);
		}
		if (block.getType() == Material.GRASS){
			player.sendMessage("Here you go a dirt block");
			event.setCancelled(false);
		}
	}
//
//    public void onBlockDamage(BlockDamageEvent event) {
//    }
//
//    public void onBlockCanBuild(BlockCanBuildEvent event) {
//    }
//
//    public void onBlockFromTo(BlockFromToEvent event) {
//    }
//    
//    public void onBlockFlow(BlockFromToEvent event) {
//    }
//
//    public void onBlockIgnite(BlockIgniteEvent event) {
//    }
//
//    public void onBlockPhysics(BlockPhysicsEvent event) {
//    }
//
//    
//
//    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
//    }
//
//    public void onLeavesDecay(LeavesDecayEvent event) {
//    }
//
//    public void onSignChange(SignChangeEvent event) {
//    }
//
//    public void onBlockBurn(BlockBurnEvent event) {
//    }
//
//    public void onBlockBreak(BlockBreakEvent event) {
//    }
//
//    public void onSnowForm(SnowFormEvent event) {
//    }
//
//    public void onBlockDispense(BlockDispenseEvent event) {
//    }
	
}
