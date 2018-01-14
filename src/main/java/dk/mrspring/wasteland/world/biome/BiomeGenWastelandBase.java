package dk.mrspring.wasteland.world.biome;

import dk.mrspring.wasteland.GameRegisterer;
import dk.mrspring.wasteland.ModConfig;
import dk.mrspring.wasteland.Wasteland;
import dk.mrspring.wasteland.entity.EntityDayZombie;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class BiomeGenWastelandBase extends BiomeGenBase
{
	public static final BiomeGenBase.Height height_Wasteland = new BiomeGenBase.Height(0.1F, 0.05F);
	public static final BiomeGenBase.Height height_WastelandCity = new BiomeGenBase.Height(0.09F, 0.01F);
	public static final BiomeGenBase.Height height_WastelandMountains = new BiomeGenBase.Height(1.0F, 0.5F);
	
	private static int lastID = 0;
	//public static BiomeGenBase[] wastelandBiomes = new BiomeGenBase[16];
	
	public BiomeGenWastelandBase(int par1ID, String par2Name, BiomeGenBase.Height biomeHeight)
	{
		super(par1ID);
		
		this.enableRain = true;
		this.biomeName = par2Name;
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.setHeight(biomeHeight);
		this.theBiomeDecorator = new BiomeDecoratorWasteland();
		
		this.waterColorMultiplier = 0x338533;
		//wastelandBiomes[lastID] = this;
		lastID++;
		
		this.loadBiome();
	}
	
	public static void load()
	{
		BiomeGenBase apocalypse = new BiomeGenApocalypse(ModConfig.apocalypseBiomeID, "Wasteland", height_Wasteland).setColor(0x00E0BD69);
		BiomeGenBase apocMountains = new BiomeGenMountains(ModConfig.mountainBiomeID, "Wasteland Mountains", height_WastelandMountains).setColor(0x009C7C13);
		BiomeGenBase apocForest = new BiomeGenForest(ModConfig.forestBiomeID, "Wasteland Forest", height_Wasteland).setColor(0x00A4B34F);
		BiomeGenBase apocCity = new BiomeGenForest(ModConfig.cityBiomeID, "Wasteland City", height_WastelandCity).setColor(0x008F98B3);
		
		BiomeDictionary.registerBiomeType(apocalypse, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(apocMountains, Type.WASTELAND, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(apocForest, Type.WASTELAND, Type.FOREST);
		BiomeDictionary.registerBiomeType(apocCity, Type.WASTELAND, Type.DEAD);
		BiomeManager.addSpawnBiome(apocalypse);
		BiomeManager.addSpawnBiome(apocMountains);
		BiomeManager.addSpawnBiome(apocForest);
		BiomeManager.addSpawnBiome(apocCity);
	}
	
	public BiomeGenWastelandBase setTopBlock(Block block)
	{
		this.topBlock = block;
		return this;
	}
	
	public BiomeGenWastelandBase setFillerBlock(Block block)
	{
		this.fillerBlock = block;
		return this;
	}
	
	public void loadBiome()
	{
		this.theBiomeDecorator.deadBushPerChunk = 5;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.generateLakes = false;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.theBiomeDecorator.treesPerChunk = -999;
		
		this.setTopBlock(ModConfig.getSurfaceBlock());
		this.setFillerBlock(Blocks.stone);
	}
}
