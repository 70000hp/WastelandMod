package dk.mrspring.wasteland.ruin;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import dk.mrspring.wasteland.ruin.Ruin.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.utils.Rectangle;
import dk.mrspring.wasteland.utils.Vector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class RuinRuinedCiv1 extends Ruin implements IWorldGenerator
{
	private RuinGenHelper genHelper = new RuinGenHelper();
	
	public RuinRuinedCiv1(String par1Name)
	{
		super(par1Name);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		this.genHelper.setWorld(world);
		
		byte byte0 = 3;
		int l = random.nextInt(2) + 2;
		int i1 = random.nextInt(2) + 2;
		boolean flag = false;
		int j1 = 0;
		int k1 = 0;
		Rectangle pos = new Rectangle(new Vector (x, y, z), 6, 6);
		int[] lay = Layout.getLevels(world, pos);

		if (Layout.checkLevel(lay, 0))
		{
			boolean flag1 = true;
			if (flag1)
			{
				Block wallBlock;
				
				int i2 = random.nextInt(3);
				if (i2 == 0)
					wallBlock = Blocks.cobblestone;
				else if (i2 == 1)
					wallBlock = Blocks.mossy_cobblestone;
				else if (i2 == 2)
					wallBlock = Blocks.planks;
				else
					wallBlock = Blocks.mossy_cobblestone;
				
				Block floorBlock;
				
				int j2 = random.nextInt(2);
				if (j2 == 0)
					floorBlock = Blocks.cobblestone;
				else if (j2 == 1)
					floorBlock = Blocks.mossy_cobblestone;
				else if (j2 == 2)
					floorBlock = Blocks.planks;
				else
					floorBlock = Blocks.planks;
				
				boolean flag2 = false;
				if (!flag2)
				{
					for (int l2 = 1; l2 <= 3; l2++)
					{
						for (int j3 = 0; j3 < 49; j3++)
						{
							genHelper.setBlock(x + k1, y + l2, z + j1, Blocks.air);
							
							k1++;
							if (k1 == 7)
							{
								j1++;
								k1 = 0;
							}
						}
					}
					int i3 = 0;
					int k3 = 0;
					for (int l3 = 0; l3 < 49; l3++)
					{
						genHelper.setBlock(x + k3, y, z + i3, floorBlock);
						
						k3++;
						if (k3 == 7)
						{
							i3++;
							k3 = 0;
						}
					}
					int i4 = 0;
					int j4 = 0;
					int k4 = random.nextInt(2);
					if (k4 == 0)
					{
						for (int l4 = 1; l4 < 4; l4++)
						{
							for (int l5 = 0; l5 < 25; l5++)
							{
								genHelper.setBlock(x + i4 + 1, y - l4, z + j4 + 1, Blocks.air);
								
								i4++;
								if (i4 == 5)
								{
									j4++;
									i4 = 0;
								}
							}
							j4 = 0;
							i4 = 0;
						}
						genHelper.setBlock(x + 3, y - 3, z + 3, Blocks.torch);
						genHelper.setBlock(x + 2, y - 3, z + 3, Blocks.stone_pressure_plate);
						genHelper.setBlock(x + 2, y - 4, z + 3, Blocks.stone);
						genHelper.setBlock(x + 2, y - 5, z + 3, Blocks.tnt);
						
						int l6 = random.nextInt(2);
						if (l6 == 0) {
							for (int k7 = 0; k7 < 25; k7++)
							{
								genHelper.setBlock(x + i4 + 1, y - 3, z + j4 + 1, Blocks.water);
								
								i4++;
								if (i4 == 5)
								{
									j4++;
									i4 = 0;
								}
							}
						}
						int l7 = random.nextInt(26);
						int k8 = l7 / 5;
						int j9 = l7 % 5;
						genHelper.setBlock(x + k8, y - 3, z + j9, Blocks.chest);
						TileEntityChest chest = (TileEntityChest) world.getTileEntity(x + k8, y - 3, z + j9);
						LootStack loot = this.setItems(random);
						CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
					}
					k3 = 0;
					i3 = 0;
					for (int j5 = 0; j5 < 3; j5++)
					{
						int j6 = 0;
						int i7 = 0;
						for (int i8 = 0; i8 < 28; i8++)
						{
							int l8 = random.nextInt(j5 + 1);
							if (j6 == 0)
							{
								Material material9 = world.getBlock(x, y + j5, z + i7).getMaterial();
								if ((material9.isSolid()) && (l8 == 0)) {
									if (j5 == 1)
									{
										if (random.nextInt(2) == 0) {
											genHelper.setBlock(x, y + 1 + j5, z + i7, Blocks.glass);
										} else {
											genHelper.setBlock(x, y + 1 + j5, z + i7, wallBlock);
										}
									}
									else {
										genHelper.setBlock(x, y + 1 + j5, z + i7, wallBlock);
									}
								}
								i7++;
							}
							if (j6 == 1)
							{
								Material material10 = world.getBlock(x + 6, y + j5, z + i7).getMaterial();
								if ((material10.isSolid()) && (l8 == 0)) {
									if (j5 == 1)
									{
										if (random.nextInt(2) == 0) {
											genHelper.setBlock(x + 6, y + 1 + j5, z + i7, Blocks.glass);
										} else {
											genHelper.setBlock(x + 6, y + 1 + j5, z + i7, wallBlock);
										}
									}
									else {
										genHelper.setBlock(x + 6, y + 1 + j5, z + i7, wallBlock);
									}
								}
								i7++;
							}
							if (j6 == 2)
							{
								Material material11 = world.getBlock(x + i7, y + j5, z).getMaterial();
								if ((material11.isSolid()) && (l8 == 0)) {
									if (j5 == 1)
									{
										if (random.nextInt(2) == 0) {
											genHelper.setBlock(x + i7, y + 1 + j5, z, Blocks.glass);
										} else {
											genHelper.setBlock(x + i7, y + 1 + j5, z, wallBlock);
										}
									}
									else {
										genHelper.setBlock(x + i7, y + 1 + j5, z, wallBlock);
									}
								}
								i7++;
							}
							if (j6 == 3)
							{
								Material material12 = world.getBlock(x + i7, y + j5, z + 6).getMaterial();
								if ((material12.isSolid()) && (l8 == 0)) {
									if (j5 == 1)
									{
										if (random.nextInt(2) == 0) {
											genHelper.setBlock(x + i7, y + 1 + j5, z + 6, Blocks.glass);
										} else {
											genHelper.setBlock(x + i7, y + 1 + j5, z + 6, wallBlock);
										}
									}
									else {
										genHelper.setBlock(x + i7, y + 1 + j5, z + 6, wallBlock);
									}
								}
								i7++;
							}
							if (i7 == 7)
							{
								j6++;
								i7 = 0;
							}
						}
					}
					int k5 = random.nextInt(3);
					int k6 = random.nextInt(3);
					int j7 = random.nextInt(2);
					if (k5 == 0)
					{
						genHelper.setBlock(x, y + 1, z + 2 + k6, Blocks.air);
						genHelper.setBlock(x, y + 2, z + 2 + k6, Blocks.air);
					}
					if (k5 == 1)
					{
						genHelper.setBlock(x + 6, y + 1, z + 2 + k6, Blocks.air);
						genHelper.setBlock(x + 6, y + 2, z + 2 + k6, Blocks.air);
					}
					if (k5 == 2)
					{
						genHelper.setBlock(x + 2 + k6, y + 1, z, Blocks.air);
						genHelper.setBlock(x + 2 + k6, y + 2, z, Blocks.air);
					}
					if (k5 == 3)
					{
						genHelper.setBlock(x + 2 + k6, y + 1, z + 6, Blocks.air);
						genHelper.setBlock(x + 2 + k6, y + 2, z + 6, Blocks.air);
					}
					int j8 = random.nextInt(2);
					int i9 = random.nextInt(5) + 1;
					int k9 = 0;
					int l9 = 0;
					if (j8 == 0)
					{
						k9 = x + i9;
						l9 = z + 1;
					}
					if (j8 == 1)
					{
						k9 = x + 1;
						l9 = z + i9;
					}
					if (j8 == 2)
					{
						k9 = x + 1;
						l9 = z + i9;
					}
					genHelper.setBlock(k9, y + 1, l9, Blocks.chest);
					TileEntityChest chest1 = (TileEntityChest) world.getTileEntity(k9, y + 1, l9);
					LootStack loot = this.setItems(random);
					CustomItemStack.placeLoot(random, chest1, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
					
					int i10 = random.nextInt(2);
					if (i10 == 0)
					{
						int j10 = 0;
						int k10 = 0;
						for (int l10 = 0; l10 < 12; l10++)
						{
							if (k10 == 0) {
								genHelper.setBlock(x + 2, y + 1, z - 1 - j10, Blocks.fence);
							}
							if (k10 == 1) {
								genHelper.setBlock(x + 3 + j10, y + 1, z - 4, Blocks.fence);
							}
							if (k10 == 2) {
								genHelper.setBlock(x + 6, y + 1, z - 1 - j10, Blocks.fence);
							}
							j10++;
							if (j10 == 4)
							{
								k10++;
								j10 = 0;
							}
						}
						for (int i11 = 0; i11 < 20; i11++)
						{
							int l11 = i11 / 5;
							int j12 = i11 % 5;
							genHelper.setBlock(x + j12 + 2, y, z - l11 - 1, Blocks.dirt);
						}
						for (int j11 = 0; j11 < 9; j11++)
						{
							int i12 = j11 / 3;
							int k12 = j11 % 3;
							genHelper.setBlock(x + k12 + 3, y + 1, z - i12 - 1, Blocks.air);
						}
						int k11 = random.nextInt(2);
						if (k11 == 0)
						{
							genHelper.setBlock(x + 3, y + 1, z - 3, Blocks.chest);
							TileEntityChest chest2 = (TileEntityChest)world.getTileEntity(x + 3, y + 1, z - 3);
							LootStack loot1 = this.setSeedItems();
							CustomItemStack.placeLoot(random, chest2, CustomItemStack.getLootItems(random, loot1.items, loot1.minNum, loot1.maxNum, loot.repeat));
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	private ItemStack pickCheskLootItem(Random random, ItemStack[] itemStacks)
	{
		int i = random.nextInt(itemStacks.length);
		
		ItemStack itemStack = new ItemStack(itemStacks[i].getItem(), 1);
		
		if(itemStack.getItem() == Items.stick) return new ItemStack(itemStack.getItem(), random.nextInt(8) + 4);
		else if(itemStack.getItem() == Items.wheat) return new ItemStack(itemStack.getItem(), random.nextInt(3) + 1);
		else if(itemStack.getItem() == Items.gunpowder) return new ItemStack(itemStack.getItem(), random.nextInt(4) + 1);
		else if(itemStack.getItem() == Items.string) return new ItemStack(itemStack.getItem(), random.nextInt(1) + 3);
		else if(itemStack.getItem() == Item.getItemFromBlock(Blocks.planks) && random.nextInt(100) == 0) return new ItemStack(itemStack.getItem(), random.nextInt(4) + 8);
		else if(itemStack.getItem() == Items.dye) return new ItemStack(itemStack.getItem(), random.nextInt(1) + 1, itemStack.getItemDamage());
		else return new ItemStack(itemStack.getItem(), 1);
		
		/*if (i == 0) {
			return new ItemStack(Item.bowlSoup);
		}
		if (i == 1) {
			return new ItemStack(Item.stick, random.nextInt(8) + 4);
		}
		if (i == 2) {
			return new ItemStack(Item.bread);
		}
		if (i == 3) {
			return new ItemStack(Item.wheat, random.nextInt(3) + 1);
		}
		if (i == 4) {
			return new ItemStack(Item.gunpowder, random.nextInt(4) + 1);
		}
		if (i == 5) {
			return new ItemStack(Item.silk, random.nextInt(1) + 3);
		}
		if (i == 6) {
			return new ItemStack(Item.bucketWater);
		}
		if ((i == 7) && (random.nextInt(100) == 0)) {
			return new ItemStack(Block.planks, random.nextInt(4) + 8);
		}
		if ((i == 8) && (random.nextInt(2) == 0)) {
			return new ItemStack(Item.redstone, random.nextInt(2) + 1);
		}
		if ((i == 9) && (random.nextInt(10) == 0)) {
			return new ItemStack(Item.itemsList[(Item.record13.itemID + random.nextInt(2))]);
		}
		if (i == 10) {
			return new ItemStack(Item.dyePowder, random.nextInt(1) + 1, 15);
		}
		return null;*/
	}
	
	private String pickMobSpawner(Random random)
	{
		int i = random.nextInt(4);
		if (i == 0) {
			return "Skeleton";
		}
		if (i == 1) {
			return "Zombie";
		}
		if (i == 2) {
			return "Zombie";
		}
		if (i == 3) {
			return "Spider";
		}
		return "";
	}
}
