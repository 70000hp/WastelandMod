package myid.chiqors.wastelands.ruin;

import java.util.Random;

import myid.chiqors.wastelands.ModConfig;
import myid.chiqors.wastelands.ModHelper;
import myid.chiqors.wastelands.ruin.Ruin.LootStack;
import myid.chiqors.wastelands.utils.CustomItemStack;
import myid.chiqors.wastelands.utils.Rectangle;
import myid.chiqors.wastelands.utils.Vector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import cpw.mods.fml.common.IWorldGenerator;

public class RuinRuined extends Ruin implements IWorldGenerator
{
	private RuinGenHelper genHelper = new RuinGenHelper();
	
	public RuinRuined(String par1Name)
	{
		super(par1Name);
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		this.genHelper.setWorld(world);
		
		Rectangle pos = new Rectangle(new Vector(x - 5, y, z - 6), 10, 12);
		int[] lay = Layout.getLevels(world, pos);
		Block surfaceBlock = ModConfig.getSurfaceBlock();
		
		if(Layout.checkLevel(lay, 3))
		{
			int xCoord = x;
			int yCoord = Layout.getMinLevel(lay);
			int zCoord = z;
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 1 generation
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, surfaceBlock);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, surfaceBlock);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, surfaceBlock);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, surfaceBlock);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, surfaceBlock);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, surfaceBlock);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 6, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, surfaceBlock);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 2, surfaceBlock);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 1, surfaceBlock);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, surfaceBlock);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, surfaceBlock);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, surfaceBlock);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 4, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, surfaceBlock);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 4, surfaceBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, surfaceBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, surfaceBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, surfaceBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, surfaceBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, surfaceBlock);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, surfaceBlock);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 4, surfaceBlock);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, surfaceBlock);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, surfaceBlock);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, surfaceBlock);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, surfaceBlock);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, surfaceBlock);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, surfaceBlock);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, surfaceBlock);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 4, surfaceBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, surfaceBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, surfaceBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, surfaceBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, surfaceBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, surfaceBlock);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, surfaceBlock);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 7, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 4, surfaceBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, surfaceBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, surfaceBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, surfaceBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 3, surfaceBlock);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 4, Blocks.netherrack);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, surfaceBlock);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 4, surfaceBlock);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 3, surfaceBlock);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 2, surfaceBlock);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 1, surfaceBlock);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 4, surfaceBlock);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, surfaceBlock);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 4, surfaceBlock);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, surfaceBlock);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 2, surfaceBlock);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 1, surfaceBlock);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 1, surfaceBlock);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 2, surfaceBlock);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 3, surfaceBlock);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, surfaceBlock);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 6, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, surfaceBlock);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 0, surfaceBlock);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, surfaceBlock);
			
			genHelper.setBlock(xCoord - 6, yCoord, zCoord - 6, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 7, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 2 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 7, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 5, yCoord, zCoord + 7, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.stone_stairs);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 6, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.cobblestone);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 4, Blocks.fire);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 2, Blocks.stone_stairs);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 3 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.cobblestone);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 4 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stone_stairs);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 5 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 6 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, xCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.cobblestone);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.log);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.log);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.log);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.log);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 7 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
			
			
			genHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, xCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 8 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 2, yCoord, zCoord - 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord + 1, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 4, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 3, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 1, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 9 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 2, Blocks.chest);
			
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(xCoord - 4, yCoord, zCoord - 2);
			LootStack loot = this.setItems(rand);
			CustomItemStack.placeLoot(rand, chest, CustomItemStack.getLootItems(rand, loot.items, loot.minNum, loot.maxNum, loot.repeat));
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord + 4, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord + 0, Blocks.stonebrick);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 10 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 3, yCoord, zCoord - 4, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 11 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick, 2);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
			
			/*	  	  A X+
			 *		  |
			 *  Z- <-- --> Z+
			 *	  	  |
			 *	  	  V X-
			 */
			
			// Layer 12 generation
			
			yCoord++;
			
			genHelper.setBlock(xCoord - 4, yCoord, zCoord - 3, Blocks.stonebrick);
			
			genHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
			
			return true;
		}
		
		return false;
	}
}
