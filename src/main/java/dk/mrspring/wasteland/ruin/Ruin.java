package dk.mrspring.wasteland.ruin;

import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.mrspring.wasteland.ModConfig;
import dk.mrspring.wasteland.RuinConfig;
import dk.mrspring.wasteland.Wasteland;
import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.ModHelper.ModInfo;
import dk.mrspring.wasteland.client.ItemRuinIcon;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.world.WorldTypeWasteland;

public class Ruin
{
	protected String name;
	protected int id;
	protected int rarity = 1;
	protected int weight = 10;
	@SideOnly(Side.CLIENT)
	protected Item icon;
	protected ItemStack[] loot;
	
	public static LootStack normalLoot;
	public static LootStack rareLoot;
	public static LootStack seedLoot;
	
	public Ruin(String par1Name)
	{
		this.name = par1Name;
		
		normalLoot = new LootStack(RuinConfig.getLoot(RuinConfig.ruinEasyLoot), RuinConfig.ruinEasyLootMax, RuinConfig.ruinEasyLootMin, RuinConfig.ruinEasyLootRepeat);
		rareLoot = new LootStack(RuinConfig.getLoot(RuinConfig.ruinRareLoot), RuinConfig.ruinRareLootMax, RuinConfig.ruinRareLootMin, RuinConfig.ruinRareLootRepeat);
		seedLoot = new LootStack(RuinConfig.getLoot(RuinConfig.seedLoot), RuinConfig.seedLootMax, RuinConfig.seedLootMin, RuinConfig.seedLootRepeat);
		//System.out.println(rareLoot.items[0].items.toString());
		//GameRegistry.registerWorldGenerator(this.toIWorldGenerator(), this.weight);
	}
	
	public String getLocalizedName()
	{
		 return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
	}
	
	public String getUnlocalizedName()
	{
		return "ruin." + this.name;
	}
	
	protected LootStack setItems(Random random) 
	{
		if (random.nextInt(RuinConfig.rareRuinLootChance) == 0)
		{
			return this.rareLoot;
		}
		else
		{
			return this.normalLoot;
		}
	}
	
	public Ruin setWeight(int par1Weight)
	{
		this.weight = par1Weight;
		return this;
	}
	
	protected boolean generate(World world, Random random, int x, int y, int z)
	{
		return false;
	}
	
	public LootStack setSeedItems()
	{
		return this.seedLoot;
	}
	
	// IWorldGenerator functions:
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.dimensionId == 0)
		{
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
	}
	
	protected void generateSurface(World world, Random random, int i, int j)
	{
		int xCoord = i + random.nextInt(16);
		int yCoord = world.getHeightValue(i, j);
		int zCoord = j + random.nextInt(16);

		if (!world.isRemote)
		{
			this.generate(world, random, xCoord, yCoord, zCoord);
		}
		
	}
	
	protected class LootStack
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
	
}
