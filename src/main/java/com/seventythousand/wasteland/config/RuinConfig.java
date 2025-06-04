

package com.seventythousand.wasteland.config;

import net.minecraft.item.Item;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.config.Configuration;

public class RuinConfig {
    public static String[] easyLoot;

    public static String[] midLoot;

    public static String[] hardLoot;

    public static String[] ruinEasyLoot;

    public static String[] ruinRareLoot;

    public static String[] ruinHardLoot;

    public static String[] seedLoot;

    public static String[] startLoot;

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

    public static int hardRuinLootChance;

    public static void load(Configuration config) {
        config.load();
        config.setCategoryComment("Loot Amount", "Total itemstacks in chests. Randomized between max and min");
        easyLootMax = config.getInt("Common village chest items MAX", "Loot Amount", 25, 0, 27, "");
        easyLootMin = config.getInt("Common village chest items MIN", "Loot Amount", 15, 0, 27, "");
        midLootMax = config.getInt("Uncommon village chest items MAX", "Loot Amount", 15, 0, 27, "");
        midLootMin = config.getInt("Uncommon village chest items MIN", "Loot Amount", 10, 0, 27, "");
        hardLootMax = config.getInt("Rare village chest items MAX", "Loot Amount", 12, 0, 27, "");
        hardLootMin = config.getInt("Rare village chest items MIN", "Loot Amount", 8, 0, 27, "");
        ruinEasyLootMax = config.getInt("Common ruin chest items MAX", "Loot Amount", 26, 0, 27, "Ruins are random structures spread through the wasteland outside of towns");
        ruinEasyLootMin = config.getInt("Common ruin chest items MIN", "Loot Amount", 20, 0, 27, "");
        ruinRareLootMax = config.getInt("Rare ruin chest items MAX", "Loot Amount", 20, 0, 27, "");
        ruinRareLootMin = config.getInt("Rare ruin chest items MIN", "Loot Amount", 15, 0, 27, "");
        seedLootMax = config.getInt("Seed/farm ruin chest items MAX", "Loot Amount", 27, 0, 27, "Seed chests spawn in ruined structures");
        seedLootMin = config.getInt("Seed/farm ruin chest items MIN", "Loot Amount", 14, 0, 27, "");
        startLootMax = config.getInt("Bunker chest items MAX", "Loot Amount", 28, 0, 54, "Items in spawn bunker chest (if enabled)");
        startLootMin = config.getInt("Bunker chest items MIN", "Loot Amount", 24, 0, 53, "");
        config.setCategoryComment("Loot List", "Add item names chest loot. Do NOT skip or add blank lines. Format for items:\nmod_name:item_name:damage_value,max,min,weight\nCheck mod language registry for item names. max = maximum stack size, min = minimum stack size. If max/min stack size > game stack limit, game will chose the stack limit.");
        easyLoot = config.get("Loot List", "Common village chest loot items", new String[]{
            "hbm:item.canned_conserve,2,1,12", "hbm:item.powder_sawdust,12,4,10",
            "minecraft:potato,3,2,1",
            "minecraft:string,9,2,9",
            "minecraft:potion,3,1,4",
            "hbm:item.canned_conserve:2,2,1,8",
            "hbm:item.canned_conserve:3,2,2,10",
            "hbm:item.canned_conserve:20,4,3,2",
            "minecraft:paper,12,5,8",
            "hbm:item.bottle_nuka,5,2,8",
            "hbm:item.dust,12,2,8",
            "hbm:item.can_smart,4,2,5",
            "hbm:item.plant_item:1,6,2,6",
            "hbm:item.solid_fuel,12,2,2",
            "hbm:item.rag,6,2,6"}).getStringList();
        midLoot = config.get("Loot List", "Uncommon village chest loot items",
            new String[]{
                "minecraft:bucket,1,1,3", "minecraft:carrot,2,2,2",
                "hbm:item.canister_empty,3,2,5",
                "hbm:item.battery_advanced,1,1,4",
                "hbm:item.horseshoe_magnet,1,1,4",
                "hbm:item.weapon_saw,1,1,4",
                "minecraft:book,6,4,7",
                "hbm:item.briquette:1,8,6,6",
                "hbm:item.powder_fertilizer,8,4,6",
                "hbm:item.canister_full:20,6,2,3",
                "hbm:item.canister_full:95,6,2,3",
                "hbm:item.crayon:1,32,16,3",
                "hbm:item.ammo_standard:13,8,6,5",
                "hbm:item.ammo_standard:5,8,6,5",
                "hbm:item.ammo_standard:42,12,5,3",
                "hbm:item.ammo_standard:15,8,4,5",
                "hbm:item.flame_pony,5,2,2", "hbm:item.flame_opinion,5,2,2",
                "hbm:item.pipe:2600,3,2,6", "hbm:item.pipe:2900,3,2,5", "hbm:item.wire_fine:2900,16,8,5", "hbm:item.wire_fine:699,16,8,5",
                "hbm:item.motor,4,2,4", "hbm:item.circuit,4,2,4", "hbm:item.circuit:1,4,2,4",
                "hbm:item.bolt:30,24,12,4", "hbm:item.casing,8,4,5", "hbm:item.casing:1,8,4,5", "hbm:item.casing:5,4,3,2",
                "hbm:item.part_receiver_light:30,2,1,1", "hbm:item.part_receiver_light:49,2,1,1", "hbm:item.part_barrel_light:30,2,1,1",
                "hbm:item.pill_herbal,2,1,4", "hbm:tile.deco_crt,3,1,6", "hbm:item.gun_kit_1,3,1,4",
                "hbm:item.steel_pickaxe:60,1,1,3", "hbm:item.steel_shovel:20,1,1,3", "hbm:item.steel_axe:20,1,1,3", "hbm:item.steel_hoe:20,1,1,4",
                "hbm:tile.radiorec,2,1,3", "hbm:item.cigarette,6,2,6", "hbm:item.screwdriver,1,1,4",
                 }).getStringList();
        hardLoot = config.get("Loot List", "Rare village chest loot items",
            new String[]{
                "hbm:item.cheese,14,12,6", "hbm:item.loops,10,8,8",
                "hbm:item.gun_maresleg,1,1,2", "hbm:item.gun_henry,1,1,2", //hillbilly guns
                "hbm:item.fuel_additive,3,1,4",
                "hbm:item.siphon,1,1,3", "hbm:item.blowtorch,1,1,3", "hbm:item.boltgun,1,1,3", "hbm:item.defuser,1,1,3", //tools
                "hbm:item.heart_piece,1,1,4", "hbm:item.bathwater,1,1,8","hbm:item.bandaid,1,1,3", "hbm:item.armor_polish,1,1,3", //armor mods
                "hbm:item.ammo_standard:23,10,8,6", "hbm:item.ammo_standard:19,16,8,6",
                "hbm:item.part_mechanism:49,2,1,3", "hbm:item.part_receiver_light:30,4,2,3",
                "hbm:item.bolt:33,24,12,6", "hbm:item.serum,1,1,3",
                "hbm:item.spider_milk,1,1,3",
                "hbm:item.bottle_mercury,3,2,4",//gun parts
                "hbm:tile.deco_toaster,1,1,2", "hbm:tile.radiorec,4,2,5", //furniture and stuff
                "hbm:item.battery_advanced_cell_4,1,1,3",
                "hbm:item.siox,3,2,3", "hbm:item.radx,3,2,3", "hbm:item.med_bag,1,1,3",//drugs from ebay.gov
                "hbm:item.syringe_metal_medx,4,2,6", "hbm:item.syringe_metal_stimpak,12,6,6", "hbm:item.syringe_metal_psycho,3,2,6" //fentanyl 2
                }).getStringList();

        ruinEasyLoot = config.get("Loot List", "Common ruins chest loot items",
            new String[]{
            "hbm:item.canned_conserve,3,2,12",
            "hbm:item.powder_sawdust,6,4,10",
            "minecraft:gunpowder,5,2,5",
            "minecraft:string,12,2,9",
            "minecraft:potion,5,2,7",
            "hbm:item.canned_conserve:2,3,1,2",
            "hbm:item.canned_conserve:3,3,2,7", "hbm:item.canned_conserve:14,3,2,12",
            "minecraft:paper,12,5,3",
            "hbm:item.bottle_nuka,5,2,2",
            "hbm:item.cap_nuka,12,6,7",
            "hbm:item.ammo_standard:4,12,5,4",
            "hbm:item.ammo_standard:20,12,5,4",
            "hbm:item.dust,12,2,10",
            "hbm:item.gas_mask_m65:60,1,1,2",
            "hbm:item.gas_mask_filter_piss,1,1,7",
            "hbm:item.crowbar,1,1,1",
            "hbm:item.wrench,1,1,1",
            "hbm:item.gas_mask_olde:90,1,1,3",
            "hbm:item.plant_item:1,6,2,7"}).getStringList();
        ruinRareLoot = config.get("Loot List", "Rare ruins chest loot items",
            new String[]{"hbm:item.plate_copper,4,2,2",
                "hbm:item.part_barrel_light:30,3,2,2",
                "hbm:item.pipe:2600,3,2,6",
                "hbm:item.pipe:2900,3,2,6",
                "hbm:item.definitelyfood,12,3,6",
                "hbm:item.pipe:2600,3,2,6",
                "hbm:item.cordite,4,2,6" ,
                "hbm:item.stopsign,1,1,3",
                "hbm:item.sopsign,1,1,3",
                "hbm:item.ammo_standard:5,16,8,5",
                "hbm:item.ammo_standard:15,16,8,5",
                "hbm:item.ammo_standard:22,32,16,5",
                "hbm:item.canister_full:20,6,2,5",
                "hbm:item.canister_full:95,6,2,5",
                "hbm:item.canister_full:46,4,2,5",
                "hbm:item.weapon_bat,1,1,3",
                "hbm:item.crowbar,1,1,4",
                "hbm:item.wrench,1,1,4",
                "hbm:item.pill_herbal,3,2,3", "hbm:item.radx,3,2,3", "hbm:item.med_bag,1,1,3",//drugs from ebay.gov
                "hbm:item.syringe_metal_medx,4,2,4", "hbm:item.syringe_metal_stimpak,12,6,4", "hbm:item.syringe_metal_psycho,3,2,4", //fentanyl 2
                "hbm:item.casing,8,4,5",
                "hbm:item.casing:1,8,4,5",
                "hbm:item.robes_helmet,1,1,4",
                "hbm:item.robes_plate,1,1,4",
                "hbm:item.robes_legs,1,1,4",
                "hbm:item.robes_boots,1,1,4",
                "hbm:item.jackt,1,1,1",
                "hbm:item.gas_mask_m65,1,1,4",
                "hbm:item.gas_mask_filter,1,1,6",
                "hbm:item.steel_plate,1,1,3",
                "hbm:tile.deco_crt,3,2,6",
                "hbm:tile.filing_cabinet,2,1,5"}).getStringList();
        ruinHardLoot = config.get("Loot List", "Hard ruins chest loot items", new String[]{
            "hbm:item.pipe:30,4,3,4",
            "hbm:item.med_bag,1,1,5",
            "hbm:item.siphon,1,1,4",
            "hbm:item.blowtorch,1,1,2",
            "hbm:item.boltgun,1,1,2" ,
            "hbm:item.defuser,1,1,3",
            "hbm:item.fuel_additive,3,1,4",
            "hbm:item.gun_light_revolver,1,1,2",
            "hbm:item.heart_piece,1,1,3",
            "hbm:tile.mine_ap,3,2,3",
            "hbm:item.ammo_standard:8,16,8,4",
            "hbm:item.ammo_standard:14,16,8,4",
            "hbm:item.ammo_standard:23,32,16,4",
            "hbm:item.ammo_standard:18,64,32,4",
            "hbm:item.ammo_standard:12,16,5,4",
            "hbm:item.part_mechanism:49,2,1,4",
            "hbm:item.part_receiver_light:30,2,1,4",
            "hbm:item.part_receiver_light:49,2,1,4",
            "hbm:item.part_barrel_heavy:33,1,1,1",
            "hbm:item.part_barrel_light:30,2,1,4",
            "hbm:tile.red_barrel,3,2,6",
            "hbm:item.cordite,12,8,6",
            "hbm:item.boltgun:2,1,1,2",
            "hbm:item.sopsign,1,1,1",
            "hbm:item.weapon_golf_club,1,1,3",
            "hbm:item.bolt:30,24,12,1",
            "hbm:item.bolt:33,24,12,1",}).getStringList();
        seedLoot = config.get("Loot List", "Ruins seed chest loot items", new String[]{"minecraft:wheat_seeds,8,5", "minecraft:potato,3,1", "minecraft:wheat,6,3", "minecraft:reeds,8,4,9"}).getStringList();
        startLoot = config.get("Loot List", "Start bunker chest loot items", new String[]{"hbm:item.weapon_bat:20,1,1", "hbm:item.steel_pickaxe:60,1,1,3", "hbm:item.steel_shovel:20,1,1,3", "hbm:item.steel_axe:20,1,1,3", "hbm:item.definitelyfood,18,12,6", "hbm:item.robes_helmet,1,1", "hbm:item.robes_plate,1,1", "hbm:item.robes_legs,1,1", "hbm:item.robes_boots,1,1"}).getStringList();
        config.setCategoryComment("Loot Rarity", "Rarity of uncommon and rare chests. Spawn chance 1 in N (higher numbers = lower spawn chance)");
        midLootChance = config.getInt("Uncommon village loot chance", "Loot Rarity", 18, 1, 10000, "If not picked, defaults to common village chest loot");
        hardLootChance = config.getInt("Rare village loot chance", "Loot Rarity", 30, 1, 10000, "If not picked, checks rarity of uncommon village chest loot");
        rareRuinLootChance = config.getInt("Uncommon ruin loot chance", "Loot Rarity", 3, 1, 10000, "If not picked, defaults to common ruin chest loot");
        hardRuinLootChance = config.getInt("Hard ruin loot chance", "Loot Rarity", 9, 1, 10000, "If not picked, defaults to common ruin chest loot");
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
