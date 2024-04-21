package com.seventythousand.wasteland.config;

import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.config.Configuration;

public class CityLootConfig {
  public static String[] easyLoot;

  public static String[] midLoot;

  public static String[] hardLoot;

  public static String[] ultraLoot;

  public static int easyLootMax;

  public static int easyLootMin;

  public static int midLootMax;

  public static int midLootMin;

  public static int hardLootMax;

  public static int hardLootMin;

  public static int ultraLootMax;

  public static int ultraLootMin;

  public static boolean easyLootRepeat;

  public static boolean midLootRepeat;

  public static boolean hardLootRepeat;

  public static boolean ultraLootRepeat;

  public static int midLootChance;

  public static int hardLootChance;

  public static int ultraLootChance;

  public static void loadConfig(Configuration config) {
    config.load();
    config.setCategoryComment("Loot Amount", "Total itemstacks in chests. Randomized between max and min");
    easyLootMax = config.getInt("Common village chest items MAX", "Loot Amount", 26, 0, 27, "");
    easyLootMin = config.getInt("Common village chest items MIN", "Loot Amount", 20, 0, 27, "");
    easyLootRepeat = config.getBoolean("Common village chest item duplicates", "Loot Amount", true, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
    midLootMax = config.getInt("Uncommon village chest items MAX", "Loot Amount", 25, 0, 27, "");
    midLootMin = config.getInt("Uncommon village chest items MIN", "Loot Amount", 20, 0, 27, "");
    midLootRepeat = config.getBoolean("Uncommon village chest item duplicates", "Loot Amount", true, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
    hardLootMax = config.getInt("Rare village chest items MAX", "Loot Amount", 24, 0, 27, "");
    hardLootMin = config.getInt("Rare village chest items MIN", "Loot Amount", 15, 0, 27, "");
    hardLootRepeat = config.getBoolean("Rare village chest item duplicates", "Loot Amount", false, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
    ultraLootMax = config.getInt("Rare village chest items MAX", "Loot Amount", 20, 0, 27, "");
    ultraLootMin = config.getInt("Rare village chest items MIN", "Loot Amount", 15, 0, 27, "");
    ultraLootRepeat = config.getBoolean("Rare village chest item duplicates", "Loot Amount", false, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
    config.setCategoryComment("Loot List", "Add item names chest loot. Do NOT skip or add blank lines. Format for items:\nmod_name:item_name:damage_value,max,min\nCheck mod language registry for item names. max = maximum stack size, min = minimum stack size. If max/min stack size > game stack limit, game will chose the stack limit.");
    easyLoot = config.get("Loot List", "Common chest loot items", new String[] { "minecraft:mushroom_stew,1,1", "minecraft:bread,2,1", "minecraft:wheat,6,2", "minecraft:apple,3,1", "minecraft:gunpowder,8,2", "minecraft:string,8,2", "minecraft:dye,10,8", "minecraft:rotten_flesh,6,1", "minecraft:clay_ball,16,12" }).getStringList();
    midLoot = config.get("Loot List", "Uncommon chest loot items", new String[] { "minecraft:bucket,1,1", "minecraft:bread,4,2", "minecraft:iron_ingot,2,1", "minecraft:compass,1,1", "minecraft:map,1,1", "minecraft:egg,2,1", "minecraft:leather_chestplate,1,1" }).getStringList();
    hardLoot = config.get("Loot List", "Rare chest loot items", new String[] { "minecraft:potato,4,1", "minecraft:carrot,4,1", "minecraft:water_bucket,1,1", "minecraft:cooked_porkchop,4,1", "minecraft:cooked_beef,4,1", "minecraft:flint_and_steel,1,1", "minecraft:feather,12,2", "minecraft:gold_ingot,3,1", "minecraft:diamond,2,1", "minecraft:milk_bucket,1,1" }).getStringList();
    ultraLoot = config.get("Loot List", "Ultra rare chest loot items", new String[] { "minecraft:water_bucket,1,1", "minecraft:iron_ingot,10,6", "minecraft:gold_ingot,5,3", "minecraft:diamond,2,1", "minecraft:milk_bucket,1,1" }).getStringList();
    config.setCategoryComment("Loot Rarity", "Rarity of uncommon and rare chests. Spawn chance 1 in N (higher numbers = lower spawn chance)");
    midLootChance = config.getInt("Uncommon city loot chance", "Loot Rarity", 3, 1, 10000, "If not picked, defaults to common loot");
    hardLootChance = config.getInt("Rare city loot chance", "Loot Rarity", 10, 1, 10000, "If not picked, checks rarity of uncommon loot");
    ultraLootChance = config.getInt("Ultra rare city loot chance ", "Loot Rarity", 30, 1, 10000, "If not picked, checks rarity of rare loot");
    config.save();
  }

  public static WeightedRandomChestContent[] getLoot(String[] rawStringArray) {
    return RuinConfig.getLoot(rawStringArray);
  }
}
