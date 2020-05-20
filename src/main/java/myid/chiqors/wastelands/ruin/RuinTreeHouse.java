package myid.chiqors.wastelands.ruin;

import java.util.Random;

import myid.chiqors.wastelands.ModConfig;
import myid.chiqors.wastelands.ruin.Ruin.LootStack;
import myid.chiqors.wastelands.utils.CustomItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import cpw.mods.fml.common.IWorldGenerator;

public class RuinTreeHouse extends Ruin implements IWorldGenerator
{
	private RuinGenHelper genHelper = new RuinGenHelper();
	
	public RuinTreeHouse(String par1Name)
	{
		super(par1Name);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		this.genHelper.setWorld(world);
		
		if(world.getBlock(x, y, z).equals(ModConfig.getSurfaceBlock()))
		{
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			int xCoord = x;
			int yCoord = world.getHeightValue(x, z) - 1;
			int zCoord = z;
			
			// Layer 1 generation
			
			int var1 = world.getHeightValue(xCoord - 1, zCoord - 1);
			genHelper.setBlock(xCoord - 1, var1, zCoord - 1, Blocks.planks);
			
			// Layer 2 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
			
			if(!world.getBlock(xCoord, yCoord, zCoord - 1).getMaterial().isSolid())
				genHelper.setBlock(xCoord, yCoord, zCoord - 1, Blocks.ladder, 2);
			
			// Layer 3 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
			
			// Layer 4 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
			
			// Leyer 5 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord, Blocks.log);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.log);
			genHelper.setBlock(xCoord, yCoord, zCoord - 1, Blocks.ladder, 2);
			
			// Layer 6 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.log);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.ladder, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.planks);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.log);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.log);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.planks);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.planks);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.planks);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.log);
			
			// Layer 7 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.log);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.chest);
			
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(xCoord - 1, yCoord, zCoord + 1);
			LootStack loot = this.setItems(random);
			CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.log);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.log);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.log);
			
			// Layer 8 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
			genHelper.setBlock(xCoord, yCoord, zCoord + 1, Blocks.log, 8);
			
			return true;
		}
		return false;
	}
}
