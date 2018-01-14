package dk.mrspring.wasteland.ruin;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import cpw.mods.fml.common.IWorldGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.jnbt.ByteArrayTag;
import org.jnbt.ByteTag;
import org.jnbt.CompoundTag;
import org.jnbt.ListTag;
import org.jnbt.NBTInputStream;
import org.jnbt.ShortTag;
import org.jnbt.StringTag;
import org.jnbt.Tag;
import org.jnbt.IntTag;

import dk.mrspring.wasteland.ModConfig;
import dk.mrspring.wasteland.RuinConfig;
import dk.mrspring.wasteland.ruin.code.BuildingCode;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.utils.Sphere;
import dk.mrspring.wasteland.utils.Vector;

public class Building {

	public int width;
	public int height;
	public int length;
	public int rotation;
	
	public String name;
	public boolean duplicate;
	
	private byte[] blocks;
	private byte[] data;
	
	private List entities;
	private List tileentities;
	
	private RuinGenHelper genHelper = new RuinGenHelper();
	
	public static LootStack easyLoot;
	public static LootStack midLoot;
	public static LootStack hardLoot;
	
	public Building(String name, int w, int h, int l, byte[] b, byte[] d, boolean multiple) 
	{
		//super(name);
		//loadSchematic(filename);
		// TODO Auto-generated constructor stub
		this.width = w;
		this.height = h;
		this.length = l;
		
		this.blocks = b.clone();
		this.data = d;
		
		this.name = name;
		this.duplicate = multiple;
		
		easyLoot = new LootStack(RuinConfig.getLoot(RuinConfig.easyLoot), RuinConfig.easyLootMax, RuinConfig.easyLootMin, RuinConfig.easyLootRepeat);
		midLoot = new LootStack(RuinConfig.getLoot(RuinConfig.midLoot), RuinConfig.midLootMax, RuinConfig.midLootMin, RuinConfig.midLootRepeat);
		hardLoot = new LootStack(RuinConfig.getLoot(RuinConfig.hardLoot), RuinConfig.hardLootMax, RuinConfig.hardLootMin, RuinConfig.hardLootRepeat);
	}
	
	public static Building create(int building)
	{
		switch (building)
		{
			case 0:
				return new Building("cTower", BuildingCode.ClockTower.WIDTH,
						BuildingCode.ClockTower.HEIGHT, BuildingCode.ClockTower.LENGTH, 
						BuildingCode.ClockTower.BLOCKS, BuildingCode.ClockTower.DATA,
						BuildingCode.ClockTower.MULTIPLE);
			case 1:
				return new Building("church", BuildingCode.Church.WIDTH,
						BuildingCode.Church.HEIGHT, BuildingCode.Church.LENGTH, 
						BuildingCode.Church.BLOCKS, BuildingCode.Church.DATA,
						BuildingCode.Church.MULTIPLE);
			case 2:
				return new Building("diner", BuildingCode.Diner.WIDTH,
						BuildingCode.Diner.HEIGHT, BuildingCode.Diner.LENGTH, 
						BuildingCode.Diner.BLOCKS, BuildingCode.Diner.DATA,
						BuildingCode.Diner.MULTIPLE);
			case 3:
				return new Building("stand", BuildingCode.GroceryStand.WIDTH,
						BuildingCode.GroceryStand.HEIGHT, BuildingCode.GroceryStand.LENGTH, 
						BuildingCode.GroceryStand.BLOCKS, BuildingCode.GroceryStand.DATA,
						BuildingCode.GroceryStand.MULTIPLE);
			case 4:
				return new Building("hospital", BuildingCode.Hospital.WIDTH,
						BuildingCode.Hospital.HEIGHT, BuildingCode.Hospital.LENGTH, 
						BuildingCode.Hospital.BLOCKS, BuildingCode.Hospital.DATA,
						BuildingCode.Hospital.MULTIPLE);
			case 5:
				return new Building("largeFarm", BuildingCode.LargeFarm.WIDTH,
						BuildingCode.LargeFarm.HEIGHT, BuildingCode.LargeFarm.LENGTH, 
						BuildingCode.LargeFarm.BLOCKS, BuildingCode.LargeFarm.DATA,
						BuildingCode.LargeFarm.MULTIPLE);
			case 6:
				return new Building("largeHouse1", BuildingCode.LargeHouse1.WIDTH,
						BuildingCode.LargeHouse1.HEIGHT, BuildingCode.LargeHouse1.LENGTH, 
						BuildingCode.LargeHouse1.BLOCKS, BuildingCode.LargeHouse1.DATA,
						BuildingCode.LargeHouse1.MULTIPLE);
			case 7:
				return new Building("largeHouse2", BuildingCode.LargeHouse2.WIDTH,
						BuildingCode.LargeHouse2.HEIGHT, BuildingCode.LargeHouse2.LENGTH, 
						BuildingCode.LargeHouse2.BLOCKS, BuildingCode.LargeHouse2.DATA,
						BuildingCode.LargeHouse2.MULTIPLE);
			case 8:
				return new Building("library", BuildingCode.Library.WIDTH,
						BuildingCode.Library.HEIGHT, BuildingCode.Library.LENGTH, 
						BuildingCode.Library.BLOCKS, BuildingCode.Library.DATA,
						BuildingCode.Library.MULTIPLE);
			case 9:
				return new Building("midHouse1", BuildingCode.MidHouse1.WIDTH,
						BuildingCode.MidHouse1.HEIGHT, BuildingCode.MidHouse1.LENGTH, 
						BuildingCode.MidHouse1.BLOCKS, BuildingCode.MidHouse1.DATA,
						BuildingCode.MidHouse1.MULTIPLE);
			case 10:
				return new Building("midHouse2", BuildingCode.MidHouse2.WIDTH,
						BuildingCode.MidHouse2.HEIGHT, BuildingCode.MidHouse2.LENGTH, 
						BuildingCode.MidHouse2.BLOCKS, BuildingCode.MidHouse2.DATA,
						BuildingCode.MidHouse2.MULTIPLE);
			case 11:
				return new Building("smallFarm", BuildingCode.SmallFarm.WIDTH,
						BuildingCode.SmallFarm.HEIGHT, BuildingCode.SmallFarm.LENGTH, 
						BuildingCode.SmallFarm.BLOCKS, BuildingCode.SmallFarm.DATA,
						BuildingCode.SmallFarm.MULTIPLE);
			case 12:
				return new Building("smallHouse1", BuildingCode.SmallHouse1.WIDTH,
						BuildingCode.SmallHouse1.HEIGHT, BuildingCode.SmallHouse1.LENGTH, 
						BuildingCode.SmallHouse1.BLOCKS, BuildingCode.SmallHouse1.DATA,
						BuildingCode.SmallHouse1.MULTIPLE);
			case 13:
				return new Building("smallHouse2", BuildingCode.SmallHouse2.WIDTH,
						BuildingCode.SmallHouse2.HEIGHT, BuildingCode.SmallHouse2.LENGTH, 
						BuildingCode.SmallHouse2.BLOCKS, BuildingCode.SmallHouse2.DATA,
						BuildingCode.SmallHouse2.MULTIPLE);
			case 14:
				return new Building("well", BuildingCode.Well.WIDTH,
						BuildingCode.Well.HEIGHT, BuildingCode.Well.LENGTH, 
						BuildingCode.Well.BLOCKS, BuildingCode.Well.DATA,
						BuildingCode.Well.MULTIPLE);
			default:
				return null;
		}
	}
	
	public boolean generate(World world, Random random, Vector pos, int rot)
	{
			//System.out.print("Generating - X:" + String.valueOf(pos.X) + " Y:" + String.valueOf(pos.Y) + " Z:" + String.valueOf(pos.Z) + " ");
			this.genHelper.setWorld(world);
			int maxSize;
			int minSize;
			int numHoles;
			
			if (this.blocks.length < 100)
			{
				numHoles = random.nextInt(2) + 2;
				maxSize = 2;
				minSize = 0;
			}
			else if (this.blocks.length >= 100 && this.blocks.length < 500)
			{
				numHoles = random.nextInt(2) + 2;
				maxSize = 2;
				minSize = 1;
			}
			else if (this.blocks.length >= 500 && this.blocks.length < 1500)
			{
				numHoles = random.nextInt(2) + 1;
				maxSize = 3;
				minSize = 2;
			}
			else
			{
				numHoles = random.nextInt(4) + 2;
				maxSize = 4;
				minSize = 2;
			}
			
			if (this.name.equals(Building.create(Building.WELL).name))
			{
				int waterHeight = random.nextInt(7) + 10;
				for (int i = 0; i < waterHeight; i++)
				{
					world.setBlock(pos.X + 1, pos.Y - 1 - i, pos.Z + 1, Blocks.water);
					world.setBlock(pos.X + 2, pos.Y - 1 - i, pos.Z + 1, Blocks.water);
					world.setBlock(pos.X + 2, pos.Y - 1 - i, pos.Z + 2, Blocks.water);
					world.setBlock(pos.X + 1, pos.Y - 1 - i, pos.Z + 2, Blocks.water);
				}
			}
			else
			{
				this.ruinBuilding(numHoles, maxSize, minSize, random);
			}
			
			int x;
			int z;
			int count = 0;
			for (short j = 0; j < this.height; j++)
			{
				//System.out.print(".");
				for (short k = 0; k < this.length; k++)
				{
					for (short i = 0; i < this.width; i++)
					{
						if (rot == 0)
						{
							x = i;
							z = k;
						}
						else if (rot == 1)
						{
							x = this.width - i - 1;
							z = k;
						}
						else if (rot == 2)
						{
							x = this.width - i - 1;
							z = this.width - k - 1;
						}
						else
						{
							x = i;
							z = this.width - k - 1;
						}
						if (blocks[count] == 7)
						{
							this.genHelper.setBlock(pos.X+x, pos.Y+j, pos.Z+z, ModConfig.getSurfaceBlock());
						}
						else if(blocks[count] == 2)
						{
							//do nothing
						}
						else if(blocks[count] == 54)
						{
							genHelper.setBlock(pos.X+x, pos.Y+j, pos.Z+z, Blocks.chest);
							TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos.X+x, pos.Y+j, pos.Z+z);
							LootStack loot = this.setItems(random);
							CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
						}
						else
						{
							this.genHelper.setBlock(pos.X+x, pos.Y+j, pos.Z+z, Block.getBlockById(this.blocks[count]), this.data[count]);
						}
						count++;
					}
					
				}
			}
			/*for (int i = 0; i < entities.size(); i++)
			{
				EntityPainting entityMap = null;
				
				try 
				{
					Map<String, Tag> mapTags = ((CompoundTag)entities.get(i)).getValue();
					int xP = (Integer) getChildTag(mapTags, "TileX", IntTag.class).getValue();
					int yP = (Integer) getChildTag(mapTags, "TileY", IntTag.class).getValue();
					int zP = (Integer) getChildTag(mapTags, "TileZ", IntTag.class).getValue();
					byte dP = (Byte) getChildTag(mapTags, "Dir", ByteTag.class).getValue();
					String motive = (String) getChildTag(mapTags, "Motive", StringTag.class).getValue();
		            entityMap = new EntityPainting(world, xP + x, yP + y, zP + z, dP, motive);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				if (entityMap != null)
				{
					//System.out.println("P" + String.valueOf(i) + " X:" + String.valueOf(entityMap.posX) + " Y:" + String.valueOf(entityMap.posY) + " Z:" + String.valueOf(entityMap.posZ));
					world.spawnEntityInWorld(entityMap);
					
				}
				
			}*/
			//System.out.println(" Done!");
			return true;
	}
	
	private LootStack setItems(Random random) 
	{
		if (random.nextInt(RuinConfig.hardLootChance) == 0)
		{
			return this.hardLoot;
		}
		else if (random.nextInt(RuinConfig.midLootChance) == 0)
		{
			return this.midLoot;
		}
		else
		{
			return this.easyLoot;
		}
	}

	private void ruinBuilding(int nodes, int maxRuinRad, int minRuinRad, Random rand)
	{
		int bottom = (int)(this.blocks.length/3.0f);
		int rad = rand.nextInt(maxRuinRad - minRuinRad) + minRuinRad;
		int node;
		int x;
		int y;
		int z;
		int mod;
		for (int i = 0; i < nodes; i++)
		{
			node = rand.nextInt(this.blocks.length - bottom) + bottom - 1;
			while (this.blocks[node] == 0 || this.blocks[node] == 2)
			{
				node = rand.nextInt(this.blocks.length - bottom) + bottom - 1;
			}
			y = (int)(node/(this.width * this.length));
			mod = node - (this.width * this.length * y);
			z = (int)(mod/this.width);
			x = mod - (z*width);
			//System.out.println("Hole at - x:" + String.valueOf(x) + " y:" + String.valueOf(y) + " z:" + String.valueOf(z) + " rad:" + String.valueOf(rad));
			Sphere hole = new Sphere(new Vector(x, y, z), rad);
			this.blocks = hole.makeSphereOf(this.blocks, this.width, this.length, this.height, (byte) 0);
		}
	}
	
	private static Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) 
	{
        Tag tag = items.get(key);
        return tag;
    }
	
	
	public static class LootStack
	{
		CustomItemStack[] items;
		int maxNum;
		int minNum;
		boolean repeat;
		
		public LootStack(CustomItemStack[] items, int max, int min, boolean repeat)
		{
			this.items = items;
			this.maxNum = max;
			this.minNum = min;
			this.repeat = repeat;
		}
	}
	
	
	public static final int CLOCK_TOWER = 0;
	public static final int CHURCH = 1;
	public static final int DINER = 2;
	public static final int STAND = 3;
	public static final int HOSPITAL = 4;
	public static final int L_FARM = 5;
	public static final int L_HOUSE1 = 6;
	public static final int L_HOUSE2 = 7;
	public static final int LIBRARY = 8;
	public static final int M_HOUSE1 = 9;
	public static final int M_HOUSE2 = 10;
	public static final int S_FARM = 11;
	public static final int S_HOUSE1 = 12;
	public static final int S_HOUSE2 = 13;
	public static final int WELL = 14;

}
