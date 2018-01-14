package dk.mrspring.wasteland.ruin;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import cpw.mods.fml.common.IWorldGenerator;
import dk.mrspring.wasteland.ModHelper;
import dk.mrspring.wasteland.ruin.Ruin.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;

public class RuinSpawner extends Ruin implements IWorldGenerator
{
	private RuinGenHelper genHelper = new RuinGenHelper();
	
	public RuinSpawner(String name)
	{
		super(name);
	}
	
	protected boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		/*	  	  A X+
		 *		  |
		 *  Z- <-- --> Z+
		 *	  	  |
		 *	  	  V X-
		 */
		
		if(world.getHeightValue(xCoord + 4, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord + 4, zCoord - 1) == yCoord &&
		   world.getHeightValue(xCoord + 4, zCoord + 0) == yCoord &&
		   world.getHeightValue(xCoord + 4, zCoord + 1) == yCoord &&
		   world.getHeightValue(xCoord + 4, zCoord + 2) == yCoord &&
		   world.getHeightValue(xCoord + 4, zCoord + 3) == yCoord &&
		   world.getHeightValue(xCoord + 4, zCoord + 4) == yCoord &&
		   
		   world.getHeightValue(xCoord + 3, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord + 3, zCoord + 5) == yCoord &&
		   
		   world.getHeightValue(xCoord + 2, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord + 2, zCoord + 5) == yCoord &&
		   
		   world.getHeightValue(xCoord + 1, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord + 1, zCoord + 5) == yCoord &&
		   
		   world.getHeightValue(xCoord + 0, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord + 0, zCoord + 5) == yCoord &&
		   
		   world.getHeightValue(xCoord - 1, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord - 1, zCoord + 5) == yCoord &&
		   
		   world.getHeightValue(xCoord - 2, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord - 2, zCoord + 5) == yCoord &&
		  
		   world.getHeightValue(xCoord - 3, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord - 3, zCoord + 5) == yCoord &&
		   
		   world.getHeightValue(xCoord - 4, zCoord - 2) == yCoord &&
		   world.getHeightValue(xCoord - 4, zCoord + 5) == yCoord)
		{
			System.out.println("Generating Succesfully at: " + ModHelper.getCoordAsString(xCoord, yCoord, zCoord));
			
			genHelper.setWorld(world);
			
			// Layer 2 Generation
			
			yCoord--;
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.chest);
			
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(xCoord + 4, yCoord, zCoord + 4);
			LootStack loot = this.setItems(random);
			CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.planks);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.planks);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.air);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.air);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, Blocks.stone_stairs);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.air);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.air);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.planks);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.planks);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.air);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.planks);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 4, Blocks.netherrack);
			genHelper.setBlock(xCoord - 2, yCoord + 1, zCoord + 4, Blocks.fire);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 1, Blocks.planks);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 4, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 2, Blocks.planks);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 0, Blocks.planks);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 0, Blocks.cobblestone);
			
			// Layer 3 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 3, world.getHeightValue(xCoord + 3, zCoord - 4), zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 2, world.getHeightValue(xCoord - 2, zCoord - 4), zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, world.getHeightValue(xCoord - 5, zCoord - 5), zCoord - 5, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			// Layer 4 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.stone_stairs);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 5, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			
			// Layer 5 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			
			// Layer 6 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.torch);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick);
			
			// Layer 7 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			// Layer 8 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);			
			
			// Layer 8 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			
			// Layer 9 Generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			
			return true;
		}
		
		return false;
	}
	
	private static ItemStack getLootItem(Random rand, ItemStack[] items)
	{
		int i = rand.nextInt(items.length);
		
		ItemStack itemStack = new ItemStack(items[i].getItem(), 1);
		
		if(itemStack.getItem() == Items.rotten_flesh) return new ItemStack(itemStack.getItem(), rand.nextInt(4) + 2);
		else if(itemStack.getItem() == Items.bucket) return new ItemStack(itemStack.getItem(), rand.nextInt(3) + 1);
		else if(itemStack.getItem() == Items.spider_eye) return new ItemStack(itemStack.getItem(), rand.nextInt(5) + 1);
		else if(itemStack.getItem() == Items.record_chirp) return new ItemStack(itemStack.getItem(), 1);
		else if(itemStack.getItem() == Items.name_tag) return new ItemStack(itemStack.getItem(), rand.nextInt(1) + 1);
		else if(itemStack.getItem() == Items.potato) return new ItemStack(itemStack.getItem(), rand.nextInt(2) + 1);
		else if(itemStack.getItem() == Items.carrot) return new ItemStack(itemStack.getItem(), rand.nextInt(2) + 1);
		else if(itemStack.getItem() == Items.feather) return new ItemStack(itemStack.getItem(), rand.nextInt(9) + 4);
		else if(itemStack.getItem() == Items.leather_chestplate) return new ItemStack(itemStack.getItem(), 1, itemStack.getMaxDamage() - rand.nextInt(itemStack.getMaxDamage() / 2));
		else return itemStack;
	}
}
