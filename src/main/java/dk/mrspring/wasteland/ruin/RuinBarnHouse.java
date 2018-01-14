package dk.mrspring.wasteland.ruin;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import cpw.mods.fml.common.IWorldGenerator;
import dk.mrspring.wasteland.ModHelper;
import dk.mrspring.wasteland.utils.CustomItemStack;

public class RuinBarnHouse extends Ruin implements IWorldGenerator
{
	private RuinGenHelper genHelper = new RuinGenHelper();
	
	public RuinBarnHouse(String name)
	{
		super(name);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		genHelper.setWorld(world);
		
		/*	  	  A X+
		 *		  |
		 *  Z- <-- --> Z+
		 *	  	  |
		 *	  	  V X-
		 */
		
		int xCoord = x;
		int yCoord = y;
		int zCoord = z;
		
		Material material1 = world.getBlock(xCoord, yCoord, zCoord).getMaterial();
		
		int[] basePos = new int[] { x, y, z };
		
		if(material1.isSolid() && world.getHeightValue(xCoord + 1, zCoord + 1) == yCoord && world.getHeightValue(xCoord, zCoord + 1) == yCoord && world.getHeightValue(xCoord - 1, zCoord + 1) == yCoord && world.getHeightValue(xCoord - 1, zCoord) == yCoord && world.getHeightValue(xCoord - 1, zCoord - 1) == yCoord && world.getHeightValue(xCoord, zCoord - 1) == yCoord && world.getHeightValue(xCoord + 1, zCoord - 1) == yCoord)	// Upper Left Corner
		{
			this.genHelper.setWorld(world);
			
			xCoord -= 6;
			zCoord -= 4;
			
			RuinGenHelper.setCube(xCoord, yCoord + 1, zCoord - 1, 11, 9, 14, Blocks.air);
			
			// Layer 1 Generation
			
			yCoord--;
			
			for(int i = 0; i < 9; i++)
				{ world.setBlock(xCoord, yCoord, zCoord + i, Blocks.cobblestone); }
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, Blocks.dirt);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.dirt);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 6, Blocks.dirt);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 7, Blocks.dirt);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.dirt);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.dirt);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.dirt);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 7, Blocks.dirt);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.dirt);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.planks);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 6, Blocks.dirt);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 7, Blocks.dirt);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.dirt);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.dirt);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 6, Blocks.dirt);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 2, Blocks.dirt);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 4, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 5, Blocks.dirt);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 7, Blocks.planks);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 2, Blocks.dirt);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 4, Blocks.dirt);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 5, Blocks.dirt);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 2, Blocks.planks);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 4, Blocks.dirt);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 5, Blocks.dirt);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 6, Blocks.dirt);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 7, Blocks.dirt);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 4, Blocks.dirt);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 6, Blocks.dirt);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 7, Blocks.dirt);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 4, Blocks.dirt);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 6, Blocks.dirt);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 7, Blocks.dirt);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 1, Blocks.dirt);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 2, Blocks.dirt);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 3, Blocks.dirt);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 4, Blocks.cobblestone);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 5, Blocks.dirt);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 6, Blocks.dirt);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 7, Blocks.dirt);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 8, Blocks.cobblestone);
			
			for(int i = 0; i < 9; i++)
				{ genHelper.setBlock(xCoord + 12, yCoord, zCoord, Blocks.cobblestone); }
			
			// Layer 2 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.log);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.fence);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 6, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 6, Blocks.air);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.fence);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.fence);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 6, Blocks.fence);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 8, Blocks.air);
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.fence);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 6, Blocks.air);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 6, Blocks.air);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 0, Blocks.fence);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 6, Blocks.air);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 3, Blocks.fence);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 6, Blocks.fence);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
			
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 4, Blocks.planks);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 6, Blocks.air);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 0, Blocks.fence);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 6, Blocks.air);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 4, Blocks.air);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 5, Blocks.air);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 7, Blocks.air);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 8, Blocks.air);
			
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 4, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 8, Blocks.log);
			
			// Layer 3 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.log);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.log);
			
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
			
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 3, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 4, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 5, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 8, Blocks.log);
			
			// Layer 4 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 6, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.log);
			
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
			
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 4, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 6, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 7, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 8, Blocks.log);
			
			 // Layer 5 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.log);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.oak_stairs, 5);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.planks);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.planks);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.planks);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 6, Blocks.planks);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 7, Blocks.planks);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 6, Blocks.planks);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.planks);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.oak_stairs, 4);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 7, Blocks.planks);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 7, Blocks.planks);
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.oak_stairs, 5);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
			
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +10, yCoord, zCoord + 5, Blocks.planks);

			genHelper.setBlock(xCoord +11, yCoord, zCoord + 5, Blocks.planks);
			genHelper.setBlock(xCoord +11, yCoord, zCoord + 6, Blocks.planks);
			
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 4, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 6, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 7, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 8, Blocks.log);
			
			// Layer 6 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 7, Blocks.chest);
			
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(xCoord + 1, yCoord, zCoord + 7);
			LootStack loot = this.setItems(random);
			CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.log);
			genHelper.setBlock(xCoord + 9, yCoord, zCoord + 9, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 4, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 5, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 6, Blocks.log);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 7, Blocks.log);
			
			// Layer 7 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 5, Blocks.cobblestone);
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 6, Blocks.cobblestone);
			
			// Layer 8 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 7, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 7, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 5, Blocks.cobblestone);
			
			// Layer 9 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord +12, yCoord, zCoord + 5, Blocks.cobblestone);
			
			return true;
		}
		return false;
	}
	
	private ItemStack getChestLoot(Random rand, ItemStack[] items)
	{
		int i = rand.nextInt(items.length);
		ItemStack item = new ItemStack(items[i].getItem(), 1);
		
		if(item.getItem() == Items.wheat) return new ItemStack(item.getItem(), rand.nextInt(8) + 4);
		else if(item.getItem() == Items.apple) return new ItemStack(item.getItem(), rand.nextInt(2) + 1);
		else if(item.getItem() == Items.bread) return new ItemStack(item.getItem(), rand.nextInt(2) + 2);
		else return new ItemStack(item.getItem(), 1);
	}
}
