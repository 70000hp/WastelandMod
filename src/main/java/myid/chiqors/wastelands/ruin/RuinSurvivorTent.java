package myid.chiqors.wastelands.ruin;

import java.util.Random;

import myid.chiqors.wastelands.ModConfig;
import myid.chiqors.wastelands.ruin.Ruin.LootStack;
import myid.chiqors.wastelands.utils.CustomItemStack;
import myid.chiqors.wastelands.utils.Rectangle;
import myid.chiqors.wastelands.utils.Vector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import cpw.mods.fml.common.IWorldGenerator;

public class RuinSurvivorTent extends Ruin implements IWorldGenerator
{
	private RuinGenHelper genHelper = new RuinGenHelper();
	
	public RuinSurvivorTent(String par1Name)
	{
		super(par1Name);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		this.genHelper.setWorld(world);
		
		Rectangle pos = new Rectangle(new Vector(x - 2, y, z - 3), 5, 6);
		Block biomeBlock = ModConfig.getSurfaceBlock();
		int[] levels = Layout.getLevels(world, pos);
		
		if (Layout.checkLevel(levels, 0))
		{
			int xCoord = x;
			int yCoord = Layout.getAverageLevel(levels) - 1;
			int zCoord = z;
			
			// Layer 1 generation
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, biomeBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, biomeBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, biomeBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, biomeBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, biomeBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 2, biomeBlock);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, biomeBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, biomeBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, biomeBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.chest);
			
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(xCoord - 1, yCoord, zCoord);
			LootStack loot = this.setItems(random);
			CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, biomeBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, biomeBlock);
			
			genHelper.setBlock(xCoord, yCoord, zCoord - 3, biomeBlock);
			genHelper.setBlock(xCoord, yCoord, zCoord - 2, biomeBlock);
			genHelper.setBlock(xCoord, yCoord, zCoord - 1, Blocks.planks);
			genHelper.setBlock(xCoord, yCoord, zCoord + 0, Blocks.planks);
			genHelper.setBlock(xCoord, yCoord - 1, zCoord + 0, Blocks.tnt);
			genHelper.setBlock(xCoord, yCoord, zCoord + 1, Blocks.planks);
			genHelper.setBlock(xCoord, yCoord, zCoord + 2, biomeBlock);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, biomeBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, biomeBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, biomeBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, biomeBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, biomeBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, biomeBlock);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, biomeBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, biomeBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, biomeBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, biomeBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, biomeBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, biomeBlock);
			
			// Layer 2 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.wool);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.wool);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, Blocks.wool);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.wool);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.wool);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 2, Blocks.wool);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.air);
			
			genHelper.setBlock(xCoord, yCoord, zCoord - 3, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord - 2, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord + 0, Blocks.wooden_pressure_plate);
			genHelper.setBlock(xCoord, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord + 2, Blocks.air);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.air);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.wool);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.wool);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.wool);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.wool);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.wool);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.wool);
			
			// Layer 3 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.air);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.air);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 2, Blocks.air);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.wool);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.wool);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.wool);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.wool);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.wool);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.wool);
			
			genHelper.setBlock(xCoord, yCoord, zCoord - 3, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord - 2, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord, yCoord, zCoord + 2, Blocks.air);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.wool);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.wool);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.wool);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.wool);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.wool);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.wool);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.air);
			
			// Layer 4 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.air);
			
			genHelper.setBlock(xCoord, yCoord, zCoord - 3, Blocks.wool);
			genHelper.setBlock(xCoord, yCoord, zCoord - 2, Blocks.wool);
			genHelper.setBlock(xCoord, yCoord, zCoord - 1, Blocks.wool);
			genHelper.setBlock(xCoord, yCoord, zCoord + 0, Blocks.wool);
			genHelper.setBlock(xCoord, yCoord, zCoord + 1, Blocks.wool);
			genHelper.setBlock(xCoord, yCoord, zCoord + 2, Blocks.wool);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.air);
			
			return true;
		}
		return false;
	}
	
	
}
