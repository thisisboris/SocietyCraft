package be.thisisboris.SocietyCraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;;
/**
 * SocietyCraft block listener
 * @authors Thisisboris and cskiwi
 */


public class SCBlockListener extends BlockListener {
	private final SocietyCraft plugin;
	
	public SCBlockListener(final SocietyCraft plugin) {
        this.plugin = plugin;
	}
	public void onBlockBreak (BlockBreakEvent event) {
		if (event.isCancelled()) return;
		Block block = event.getBlock();
		Player player = event.getPlayer();
		event.setCancelled(CancelBreakBlock(player, block));
		// Location pos = player.getLocation();
	}
	private boolean CancelBreakBlock(Player player, Block block){
		boolean ReturnValue = false;
		
		if (block.getType() == Material.LOG) {
			player.sendMessage(ChatColor.RED + "You are not allowed to have that wood block!" + ChatColor.WHITE);
			ReturnValue = true;
		}
		if (block.getType() == Material.GRASS || block.getType() == Material.DIRT){
			player.sendMessage("Here you go a dirt block");
			ReturnValue = false;
		}
		return ReturnValue;
	}
//
//    public void onBlockDamage(BlockDamageEvent event) {
//    }
//	
//	  public void onSignChange(SignChangeEvent event) {
//	      Player player = event.getPlayer();
//	  } 
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
