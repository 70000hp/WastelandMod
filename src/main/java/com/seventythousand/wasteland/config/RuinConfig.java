

package com.seventythousand.wasteland.config;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.config.Configuration;

public class RuinConfig {
    public static String[] easyLoot;

    public static String[] midLoot;

    public static String[] hardLoot;

    public static String[] ruinEasyLoot;

    public static String[] ruinRareLoot;

    public static String[] seedLoot;

    public static String[] startLoot;

    public static boolean easyLootRepeat;

    public static boolean midLootRepeat;

    public static boolean hardLootRepeat;

    public static boolean ruinEasyLootRepeat;

    public static boolean ruinRareLootRepeat;

    public static boolean seedLootRepeat;

    public static boolean startLootRepeat;

    public static int easyLootMax;

    public static int easyLootMin;

    public static int midLootMax;

    public static int midLootMin;

    public static int hardLootMax;

    public static int hardLootMin;

    public static int ruinEasyLootMax;

    public static int ruinEasyLootMin;

    public static int ruinRareLootMax;

    public static int ruinRareLootMin;

    public static int seedLootMax;

    public static int seedLootMin;

    public static int startLootMax;

    public static int startLootMin;

    public static int midLootChance;

    public static int hardLootChance;

    public static int rareRuinLootChance;

    public static void loadConfig(Configuration config) {
        config.load();
        config.setCategoryComment("Loot Amount", "Total itemstacks in chests. Randomized between max and min");
        easyLootMax = config.getInt("Common village chest items MAX", "Loot Amount", 25, 0, 27, "");
        easyLootMin = config.getInt("Common village chest items MIN", "Loot Amount", 15, 0, 27, "");
        easyLootRepeat = config.getBoolean("Common village chest item duplicates", "Loot Amount", true, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
        midLootMax = config.getInt("Uncommon village chest items MAX", "Loot Amount", 15, 0, 27, "");
        midLootMin = config.getInt("Uncommon village chest items MIN", "Loot Amount", 10, 0, 27, "");
        midLootRepeat = config.getBoolean("Uncommon village chest item duplicates", "Loot Amount", false, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
        hardLootMax = config.getInt("Rare village chest items MAX", "Loot Amount", 12, 0, 27, "");
        hardLootMin = config.getInt("Rare village chest items MIN", "Loot Amount", 8, 0, 27, "");
        hardLootRepeat = config.getBoolean("Rare village chest item duplicates", "Loot Amount", false, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
        ruinEasyLootMax = config.getInt("Common ruin chest items MAX", "Loot Amount", 26, 0, 27, "Ruins are random structures spread through the wasteland outside of towns");
        ruinEasyLootMin = config.getInt("Common ruin chest items MIN", "Loot Amount", 20, 0, 27, "");
        ruinEasyLootRepeat = config.getBoolean("Common ruin chest item duplicates", "Loot Amount", true, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
        ruinRareLootMax = config.getInt("Rare ruin chest items MAX", "Loot Amount", 20, 0, 27, "");
        ruinRareLootMin = config.getInt("Rare ruin chest items MIN", "Loot Amount", 15, 0, 27, "");
        ruinRareLootRepeat = config.getBoolean("Rare ruin chest item duplicates", "Loot Amount", true, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
        seedLootMax = config.getInt("Seed/farm ruin chest items MAX", "Loot Amount", 12, 0, 27, "Seed chests spawn in ruined structures");
        seedLootMin = config.getInt("Seed/farm ruin chest items MIN", "Loot Amount", 6, 0, 27, "");
        seedLootRepeat = config.getBoolean("Seed/farm ruin chest item duplicates", "Loot Amount", true, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
        startLootMax = config.getInt("Bunker chest items MAX", "Loot Amount", 27, 0, 27, "Items in spawn bunker chest (if enabled)");
        startLootMin = config.getInt("Bunker chest items MIN", "Loot Amount", 25, 0, 27, "");
        startLootRepeat = config.getBoolean("Bunker chest item duplicates", "Loot Amount", false, "Set to false to prevent item duplicates in a spawned chest. If items MAX > total types of items, item MAX = total types");
        config.setCategoryComment("Loot List", "Add item names chest loot. Do NOT skip or add blank lines. Format for items:\nmod_name:item_name:damage_value,max,min,weight\nCheck mod language registry for item names. max = maximum stack size, min = minimum stack size. If max/min stack size > game stack limit, game will chose the stack limit.");
        easyLoot = config.get("Loot List", "Common village chest loot items", new String[]{"hbm:item.canned_conserve,2,1,12", "hbm:item.powder_sawdust,12,4,10", "minecraft:potato,3,2,1", "minecraft:string,9,2,9", "minecraft:potion,1,1,12", "hbm:item.canned_conserve:2,2,1,6", "hbm:item.canned_conserve:3,2,2,10", "hbm:item.canned_conserve:20,4,3,2", "minecraft:paper,12,5,3", "hbm:item.bottle_nuka,5,2,2", "hbm:item.dust,12,2,8", "hbm:item.can_smart,4,2,5"}).getStringList();
        midLoot = config.get("Loot List", "Uncommon village chest loot items", new String[]{"minecraft:bucket,1,1,3", "minecraft:carrot,2,2,2", "hbm:item.canister_empty,3,2,5", "hbm:item.battery_advanced,1,1,4", "minecraft:potion,12,6,7", "hbm:item.horseshoe_magnet,1,1,1", "hbm:item.weapon_saw,1,1,2", "minecraft:paper,20,10,4", "hbm:item.canister_full:20,1,1,2", "hbm:item.ammo_45,8,6,2", "hbm:item.ammo_44,8,6,2", "hbm:item.assembly_762,8,4,1", "hbm:item.pill_herbal,2,1,4", "hbm:tile.deco_crt,1,1,1", "hbm:item.gun_kit_1,3,1,4", "hbm:tile.radiorec,1,1,1", "hbm:item.cigarette,6,2,6", "hbm:item.screwdriver,1,1,2", "hbm:item.rag,6,2,4", "hbm:item.plant_item:1,6,2,4"}).getStringList();
        hardLoot = config.get("Loot List", "Rare village chest loot items", new String[]{"hbm:item.cheese,14,12,6", "hbm:item.loops,10,2,3", "hbm:item.gun_uac_pistol,1,1,3", "hbm:item.gun_revolver_nopip,1,1,4", "hbm:item.gun_bolt_action_green,1,1,4", "hbm:item.bathwater,1,1,3", "hbm:item.horseshoe_magnet,1,1,4", "hbm:tile.deco_toaster,1,1,4", "hbm:item.battery_advanced_cell_4,1,1,1", "minecraft:diamond,2,1", "hbm:item.chocolate,10,4,6", "hbm:item.siox,3,2,6", "hbm:item.radx,3,2,6", "hbm:item.syringe_metal_medx,4,2,6", "hbm:item.syringe_metal_stimpak,4,3,6", "hbm:item.syringe_metal_psycho,3,2,6"}).getStringList();
        ruinEasyLoot = config.get("Loot List", "Common ruins chest loot items", new String[]{"hbm:item.canned_conserve,3,2,12", "hbm:item.powder_sawdust,6,4,10", "minecraft:gunpowder,5,2,5", "minecraft:string,12,2,9", "minecraft:potion,5,2,7", "hbm:item.canned_conserve:2,3,1,2", "hbm:item.canned_conserve:3,3,2,7", "hbm:item.canned_conserve:14,3,2,12", "minecraft:paper,12,5,3", "hbm:item.bottle_nuka,5,2,2", "hbm:item.cap_nuka,12,6,7", "hbm:item.ammo_357,10,5,4", "hbm:item.dust,12,2,10", "hbm:item.gas_mask_m65:60,1,1,2", "hbm:item.gas_mask_filter_piss,1,1,7", "hbm:item.gas_mask_olde:90,1,1,3", "hbm:item.plant_item:1,6,2,7"}).getStringList();
        ruinRareLoot = config.get("Loot List", "Rare ruins chest loot items", new String[]{"hbm:item.plate_copper,4,2,2", "hbm:item.pipe:30,2,1,2", "hbm:item.definitelyfood,12,3,1", "minecraft:iron_ingot,3,1,3", "hbm:item.cordite,3,1,2", "hbm:item.stopsign,1,1,1", "hbm:item.sopsign,1,1,1", "hbm:item.weapon_bat,1,1,3", "hbm:item.crowbar,1,1,4", "hbm:item.assembly_762,1,1,1", "hbm:item.assembly_lead,1,1,3", "hbm:item.assembly_lead,1,1,3", "hbm:item.plate_steel,4,2,3", "hbm:item.robes_helmet,1,1,6", "hbm:item.robes_plate,1,1,6", "hbm:item.robes_legs,1,1,6", "hbm:item.robes_boots,1,1,6", "hbm:item.jackt,1,1,1", "hbm:item.gas_mask_m65,1,1,4", "hbm:item.gas_mask_filter,1,1,6", "hbm:tile.deco_crt,1,1,2", "hbm:tile.filing_cabinet,1,1,5"}).getStringList();
        seedLoot = config.get("Loot List", "Ruins seed chest loot items", new String[]{"minecraft:wheat_seeds,3,1", "minecraft:potato,3,1", "minecraft:wheat,2,1", "minecraft:reeds,8,4,9"}).getStringList();
        startLoot = config.get("Loot List", "Start bunker chest loot items", new String[]{"hbm:item.weapon_bat:20,1,1", "hbm:item.steel_pickaxe:60,1,1", "hbm:item.steel_shovel:20,1,1", "hbm:item.steel_axe:20,1,1", "hbm:item.definitelyfood,18,12", "hbm:item.robes_helmet,1,1", "hbm:item.robes_plate,1,1", "hbm:item.robes_legs,1,1", "hbm:item.robes_boots,1,1", "hbm:item.gun_revolver,1,1"}).getStringList();
        config.setCategoryComment("Loot Rarity", "Rarity of uncommon and rare chests. Spawn chance 1 in N (higher numbers = lower spawn chance)");
        midLootChance = config.getInt("Uncommon village loot chance", "Loot Rarity", 4, 1, 10000, "If not picked, defaults to common village chest loot");
        hardLootChance = config.getInt("Rare village loot chance", "Loot Rarity", 16, 1, 10000, "If not picked, checks rarity of uncommon village chest loot");
        rareRuinLootChance = config.getInt("Uncommon chest village MAX", "Loot Rarity", 4, 1, 10000, "If not picked, defaults to common ruin chest loot");
        config.save();
    }

    public static WeightedRandomChestContent[] getLoot(String[] rawStringArray) {
        WeightedRandomChestContent[] items = new WeightedRandomChestContent[rawStringArray.length];
        for (int i = 0; i < rawStringArray.length; i++) {
            if (rawStringArray[i].length() > 0) {
                int max, min, weight = 4;
                String mod, item;
                int damage;
                String[] split = rawStringArray[i].split(",");
                if (split.length >= 3) {
                    max = Integer.parseInt(split[1]);
                    min = Integer.parseInt(split[2]);
                    if (split.length >= 4) {
                        weight = Integer.parseInt(split[3]);
                    }
                } else {
                    max = 1;
                    min = 1;
                }
                String[] modidAndName = split[0].split(":");
                if (modidAndName.length == 3) {
                    mod = modidAndName[0];
                    item = modidAndName[1];
                    damage = Integer.parseInt(modidAndName[2]);
                } else {
                    mod = modidAndName[0];
                    item = modidAndName[1];
                    damage = 0;
                }
                Item itemObj = (Item) Item.itemRegistry.getObject(mod + ":" + item);
                if (itemObj != null) {
                    items[i] = new WeightedRandomChestContent(itemObj, damage, min, max, weight);
                }
            }
        }
        return items;
    }
}
