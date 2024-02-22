

package myid.chiqors.wasteland.config;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;

public class ModConfig {
  public static int wastelandTreeSpawnRate;
  
  public static int lakeSpawnRate;
  
  public static int clayRarity;
  
  public static int minVillageDistance;
  
  public static int wastelandRuinRarirty;
  
  public static String surfaceBlockString;
  
  public static String lakeLiquidString;
  
  public static String[] woodBlockStringList;
  
  public static int apocalypseBiomeID;
  
  public static int mountainBiomeID;
  
  public static int forestBiomeID;

  
  public static int radioactiveBiomeID;
  
  public static boolean spawnBunker;
  
  public static boolean disableSleep;
  
  public static boolean forceDisableGrass;
  
  public static int forceDisableGrassRadius;
  
  public static int minCityDistance;

  public static int cityChance;
  
  public static boolean spawnCities;
  
  public static int maxCitySize;
  
  public static String[] citySpawnerList;
  
  public static int[] spawnChance;
  
  public static String[] mobName;
  
  public static int[][] woodTypes;
  
  public static void load(Configuration config) {
    config.load();
    config.setCategoryComment("IDs", "Biome IDs config");
    apocalypseBiomeID = ConfigHelper.createConfigInt(config,"IDs", "Wasteland Biome ID", 43);
    mountainBiomeID = ConfigHelper.createConfigInt(config,"IDs", "Wasteland Mountains Biome ID", 44);
    forestBiomeID = ConfigHelper.createConfigInt(config,"IDs", "Wasteland Forest Biome ID", 45);
    radioactiveBiomeID = ConfigHelper.createConfigInt(config,"IDs", "Radioactive Wasteland Biome ID", 46);
    config.setCategoryComment("Worldgen", "General world generation config");
    wastelandTreeSpawnRate = ConfigHelper.createConfigInt(config,"Worldgen", "Dead Tree Rarity", 10);
    minVillageDistance = ConfigHelper.createConfigInt(config,"Worldgen", "Min chunks between abandoned towns", 32);
    wastelandRuinRarirty = ConfigHelper.createConfigInt(config,"Worldgen", "Wasteland ruins rarity", 20);
    lakeSpawnRate = ConfigHelper.createConfigInt(config,"Worldgen", "Radioactive waste lake rarity", 20);
    clayRarity = ConfigHelper.createConfigInt(config,"Worldgen", "Clay rarity", 3);
    forceDisableGrass = config.getBoolean("Force disable grass generation", "Worldgen", false, "Set to true to disable grass blocks generation added by other mods");
    forceDisableGrassRadius = config.getInt("Grass replace radius", "Worldgen", 2, 0, 16, "Radius (in chunks) to check to replace generated grass blocks\nIf you're not sure what this does, dont change it");
    woodBlockStringList = config.getStringList("Forest tree type rarity", "Worldgen", new String[] { "minecraft:log:0,70", "minecraft:log:1,20", "minecraft:log2:1,9", "minecraft:log2:0,1" }, "Wood blocks that are allowed to be generated in the Wasteland Forest biome\nmod_name:block_name:meta_id,weighted_spawn_chance");
    spawnBunker = ConfigHelper.createConfigBool(config,"Worldgen", "Spawn in underground bunker", true);
    surfaceBlockString = ConfigHelper.createConfigString(config,"Worldgen", "The top block layer of the wasteland biome", "minecraft:dirt");
    lakeLiquidString = ConfigHelper.createConfigString(config,"Worldgen", "Generated lake pockets liquid", "WLM:tile.toxicWasteBlock");
    config.setCategoryComment("Misc", "Other config options");
    disableSleep = ConfigHelper.createConfigBool(config,"Misc", "Disable sleeping in bed", true);
    config.setCategoryComment("CityGen", "World generation of cities and their spawners");
    spawnCities = ConfigHelper.createConfigBool(config,"CityGen", "Enable cities", true);
    minCityDistance = ConfigHelper.createConfigInt(config,"CityGen", "Min chunks between abandoned cities", 128);
    cityChance = ConfigHelper.createConfigInt(config,"CityGen", "Chance of a city to spawn where possible, affected by value above, 1 in x format", 3);
    maxCitySize = config.getInt("Max city size", "CityGen", 8, 2, 24, "Max allowed city size (radius in chunks); Set to lower values for slow processors\nThis only changes maximum allowable size, small city biomes will still spawn small cities");
    citySpawnerList = config.getStringList("City spawner rarity", "CityGen", new String[] { "Zombie,25", "Skeleton,25", "Spider,20", "Creeper,15", "CaveSpider,8", "PigZombie,4", "Blaze,2", "Witch,1" }, "Add or remove spawner types\nMob_name,spawn_chance\nCheck CreatureSpawn.cfg to see names of spawnable creatures. Higher spawn_chance means higher probablily of spawner choosing to be that mob/creature");
    config.save();
    setSpawnerCreatures(citySpawnerList);
    convertWoodTypes(woodBlockStringList);
  }
  
  public static Block getBlockFromString(String string) {
    String[] modidAndName = string.split(":");
    return GameRegistry.findBlock(modidAndName[0], modidAndName[1]);
  }
  
  public static Block getSurfaceBlock() {
    return getBlockFromString(surfaceBlockString);
  }
  
  public static Block getlakeLiquid() {
    return getBlockFromString(lakeLiquidString);
  }
  
  public static void setSpawnerCreatures(String[] rawStringArray) {
    int num = rawStringArray.length;
    mobName = new String[num];
    spawnChance = new int[num];
    for (int i = 0; i < num; i++) {
      if (rawStringArray[i].length() > 0) {
        String[] split = rawStringArray[i].split(",");
        if (split.length == 2) {
          mobName[i] = split[0];
          spawnChance[i] = Integer.parseInt(split[1]);
        } 
      } 
    } 
  }
  
  public static String getSpawnerCreature(Random random) {
    int num = spawnChance.length;
    int totalChance = 0;
      for (int k : spawnChance) totalChance += k;
    int creatureType = random.nextInt(totalChance);
    totalChance = 0;
    for (int j = 0; j < num; j++) {
      if (creatureType < totalChance + spawnChance[j])
        return mobName[j]; 
      totalChance += spawnChance[j];
    } 
    System.out.println("Creature spawner not found");
    return null;
  }
  
  public static void convertWoodTypes(String[] rawStringArray) {
    int num = rawStringArray.length;
    woodTypes = new int[3][num];
    for (int i = 0; i < num; i++) {
      if (rawStringArray[i].length() > 0) {
        String[] split = rawStringArray[i].split(",");
        woodTypes[2][i] = (split.length == 2) ? Integer.parseInt(split[1]) : 0;
        split = split[0].split(":");
        if (split.length == 3) {
          woodTypes[1][i] = Integer.parseInt(split[2]);
          woodTypes[0][i] = Block.getIdFromBlock(GameRegistry.findBlock(split[0], split[1]));
        } else if (split.length == 2) {
          woodTypes[1][i] = 0;
          woodTypes[0][i] = Block.getIdFromBlock(GameRegistry.findBlock(split[0], split[1]));
        } else {
          woodTypes[1][i] = 0;
          woodTypes[0][i] = Block.getIdFromBlock(Blocks.log);
        } 
      } 
    } 
  }
  
  public static int[] getWoodType(Random random) {
    int[] out = new int[2];
    int num = (woodTypes[0]).length;
    int totalChance = 0;
    for (int i = 0; i < num; i++)
      totalChance += woodTypes[2][i]; 
    int woodType = random.nextInt(totalChance);
    totalChance = 0;
    for (int j = 0; j < num; j++) {
      if (woodType < totalChance + woodTypes[2][j]) {
        out[0] = woodTypes[0][j];
        out[1] = woodTypes[1][j];
        return out;
      } 
      totalChance += woodTypes[2][j];
    } 
    System.out.println("Wood type not found");
    return null;
  }
}
