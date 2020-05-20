package myid.chiqors.wastelands;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

public class ModConfig
{
	
	public static int wastelandTreeSpawnRate;
	public static int randomFirePerChunk;
	public static int minVillageDistance;
	public static int minCityDistance;
	public static int wastelandRuinRarirty;
	public static int forestRuinRarity;
	public static int mountainRuinRarity;
	
	public static String surfaceBlock;
	
	public static int apocalypseBiomeID;
	public static int mountainBiomeID;
	public static int forestBiomeID;
	public static int cityBiomeID;
	
	public static boolean spawnBunker;
	public static boolean disableSleep;
	public static boolean dayZombies;
	public static boolean spawnCities;
	
	public static void load(Configuration config)
	{
		config.load();
		apocalypseBiomeID = config.get("IDs", "Wasteland Biome ID", 43).getInt(43);
		mountainBiomeID = config.get("IDs", "Wasteland Mountains Biome ID", 44).getInt(44);
		forestBiomeID = config.get("IDs", "Wasteland Forest Biome ID", 45).getInt(45);
		cityBiomeID = config.get("IDs", "Wasteland City Biome ID", 46).getInt(46);
		
		wastelandTreeSpawnRate = config.get("Worldgen", "Dead Tree Rarity", 2).getInt(2);
		randomFirePerChunk = config.get("Worldgen", "Wasteland fires per chunk", 1).getInt(1);
		minVillageDistance = config.get("Worldgen", "Min chunks between abandoned towns", 64).getInt(64);
		minCityDistance = config.get("Worldgen", "Min chunks between abandoned cities", 128).getInt(128);
		wastelandRuinRarirty = config.get("Worldgen", "Wasteland ruins rarity", 50).getInt(50);
		forestRuinRarity = config.get("Worldgen", "Forest tent/treehouse/ruins rarity", 50).getInt(50);
		mountainRuinRarity = config.get("Worldgen", "Mountain ruins rarity", 50).getInt(50);
		spawnBunker = config.get("Worldgen", "Spawn in underground bunker", true).getBoolean(true);
		surfaceBlock = config.get("Worldgen", "The top block layer of the wasteland biome", "minecraft:dirt").getString();
		spawnCities = false;//config.get("Worldgen", "Enable cities", true).getBoolean(true);
		
		disableSleep = config.get("Misc", "Disable sleeping in bed", true).getBoolean(true);
		dayZombies = config.get("Misc", "Allow zombies to spawn in daylight", true).getBoolean(true);
		config.save();
	}
	
	public static Block getSurfaceBlock()
	{
		String[] modidAndName = ModConfig.surfaceBlock.split(":");
		return GameRegistry.findBlock(modidAndName[0], modidAndName[1]);
	}
}
