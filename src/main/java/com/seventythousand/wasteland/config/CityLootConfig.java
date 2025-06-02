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

  public static void load(Configuration config) {
    config.load();
    config.setCategoryComment("Loot Amount", "Total itemstacks in chests. Randomized between max and min");
    easyLootMax = config.getInt("Common city chest items MAX", "Loot Amount", 26, 0, 27, "");
    easyLootMin = config.getInt("Common city chest items MIN", "Loot Amount", 20, 0, 27, "");
    midLootMax = config.getInt("Uncommon city chest items MAX", "Loot Amount", 25, 0, 27, "");
    midLootMin = config.getInt("Uncommon city chest items MIN", "Loot Amount", 20, 0, 27, "");
    hardLootMax = config.getInt("Rare city chest items MAX", "Loot Amount", 24, 0, 27, "");
    hardLootMin = config.getInt("Rare city chest items MIN", "Loot Amount", 8, 0, 27, "");
    ultraLootMax = config.getInt("Ultra city chest items MAX", "Loot Amount", 16, 0, 27, "");
    ultraLootMin = config.getInt("Ultra city chest items MIN", "Loot Amount", 12, 0, 27, "");
    config.setCategoryComment("Loot List", "Add item names chest loot. Do NOT skip or add blank lines. Format for items:\nmod_name:item_name:damage_value,max,min\nCheck mod language registry for item names. max = maximum stack size, min = minimum stack size. If max/min stack size > game stack limit, game will chose the stack limit.");
    easyLoot = config.get("Loot List", "Common chest loot items", new String[] { "hbm:item.canned_conserve,2,1,12", "hbm:item.powder_sawdust,12,4,10", "minecraft:potato,3,2,1", "minecraft:string,9,2,9", "minecraft:potion,1,1,12", "hbm:item.canned_conserve:2,2,1,6", "hbm:item.matchstick:16,12,1,5", "hbm:item.canned_conserve:3,2,2,8", "hbm:item.ducttape,6,2,4", "hbm:item.canned_conserve:20,4,3,2", "minecraft:paper,12,5,3", "hbm:item.bottle_nuka,5,2,2", "hbm:item.dust,12,2,8", "hbm:item.can_smart,4,2,5" }).getStringList();
    midLoot = config.get("Loot List", "Uncommon chest loot items", new String[] { "minecraft:bucket,2,1,3", "hbm:item.canister_empty,3,2,5", "minecraft:potion,4,3,4", "hbm:item.horseshoe_magnet,1,1,2", "minecraft:paper,20,10,5", "hbm:tile.pole_satellite_receiver,2,1,3", "hbm:item.can_empty,4,2,5", "hbm:tile.pole_top,2,1,3", "hbm:tile.tape_recorder,6,4,3", "hbm:item.pill_herbal,2,1,4", "hbm:tile.deco_crt,3,2,5", "hbm:item.coffee,3,1,5", "hbm:tile.radiorec,2,1,4", "hbm:item.cigarette,6,2,6", "hbm:item.screwdriver,1,1,5", "hbm:item.rag,6,2,4", "hbm:item.flame_conspiracy,5,2,4", "hbm:item.flame_politics,5,2,4", "hbm:item.plant_item:1,6,2,4", "hbm:item.radx,3,1,4", "hbm:item.lodestone,1,1,3",  "hbm:tile.deco_computer,3,1,3",  "hbm:item.weapon_golf_club,1,1,3" }).getStringList();
    hardLoot = config.get("Loot List", "Rare chest loot items", new String[] { "hbm:tile.radiorec,3,2,6",  "hbm:item.jackt,1,1,2", "hbm:item.syringe_metal_stimpak,6,4,7", "minecraft:diamond,2,1,6", "hbm:item.siphon,1,1,2", "hbm:item.blowtorch,1,1,2", "hbm:item.boltgun,1,1,1", "hbm:item.defuser,1,1,2", "hbm:tile.bobblehead:3,3,1,3", "hbm:item.weapon_saw,1,1,2", "hbm:item.bolt:30,24,12,6", "hbm:item.bolt:33,24,12,6", "hbm:item.canister_full:20,4,1,6", "hbm:item.canister_full:20,4,1,6", "hbm:tile.deco_crt,3,1,4",  "hbm:item.serum,1,1,3",  "hbm:item.spider_milk,1,1,3",  "hbm:item.med_bag,1,1,5",  "hbm:item.siox,1,1,4", "hbm:item.bandaid,1,1,4", "hbm:item.armor_polish,1,1,2"}).getStringList();
    ultraLoot = config.get("Loot List", "Ultra rare chest loot items", new String[] { "hbm:item.cheese,14,12,6", "hbm:item.loops,10,2,6", "hbm:item.scrumpy,1,1,3", "hbm:item.gun_heavy_revolver,1,1,2", "hbm:item.wd40,1,1,6", "hbm:item.gun_uzi,1,1,1", "hbm:item.bathwater,1,1,3", "hbm:item.acetylene_torch,1,1,3",  "hbm:item.ammo_standard:22,16,8,8", "hbm:item.ammo_standard:14,16,8,8", "hbm:tile.deco_toaster,1,1,8", "hbm:item.siox,3,2,6", "hbm:item.radx,12,8,8", "hbm:item.syringe_metal_medx,16,8,8",  "hbm:item.morning_glory,1,1,3", "hbm:item.syringe_metal_stimpak,12,8,6", "hbm:item.syringe_metal_psycho,12,8,6","hbm:tile.deco_crt,4,2,4", "hbm:item.heart_piece,1,1,6", "hbm:item.shimmer_head,1,1,1", "hbm:item.shimmer_axe_head,1,1,1", "hbm:item.shimmer_handle,1,1,3" }).getStringList();
    config.setCategoryComment("Loot Rarity", "Rarity of uncommon and rare chests. Spawn chance 1 in N (higher numbers = lower spawn chance)");
    midLootChance = config.getInt("Uncommon city loot chance", "Loot Rarity", 3, 1, 10000, "If not picked, defaults to common loot");
    hardLootChance = config.getInt("Rare city loot chance", "Loot Rarity", 9, 1, 10000, "If not picked, checks rarity of uncommon loot");
    ultraLootChance = config.getInt("Ultra rare city loot chance ", "Loot Rarity", 12, 1, 10000, "If not picked, checks rarity of rare loot");
    config.save();
  }

  public static WeightedRandomChestContent[] getLoot(String[] rawStringArray) {
    return RuinConfig.getLoot(rawStringArray);
  }
}
