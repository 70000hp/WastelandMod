package myid.chiqors.wasteland.items;

import myid.chiqors.wasteland.config.CityLootConfig;
import myid.chiqors.wasteland.utils.CustomItemStack;

public class LootStack {
  public CustomItemStack[] items;
  
  public int maxNum;
  
  public int minNum;
  
  public boolean repeat;
  
  public LootStack(CustomItemStack[] items, int max, int min, boolean repeat) {
    this.items = items;
    this.maxNum = max;
    this.minNum = min;
    this.repeat = repeat;
  }
  
  public static LootStack[] loadCityLoot() {
    LootStack[] loot = new LootStack[4];
    loot[0] = new LootStack(CityLootConfig.getLoot(CityLootConfig.easyLoot), CityLootConfig.easyLootMax, CityLootConfig.easyLootMin, CityLootConfig.easyLootRepeat);
    loot[1] = new LootStack(CityLootConfig.getLoot(CityLootConfig.midLoot), CityLootConfig.midLootMax, CityLootConfig.midLootMin, CityLootConfig.midLootRepeat);
    loot[2] = new LootStack(CityLootConfig.getLoot(CityLootConfig.hardLoot), CityLootConfig.hardLootMax, CityLootConfig.hardLootMin, CityLootConfig.hardLootRepeat);
    loot[3] = new LootStack(CityLootConfig.getLoot(CityLootConfig.ultraLoot), CityLootConfig.ultraLootMax, CityLootConfig.ultraLootMin, CityLootConfig.ultraLootRepeat);
    return loot;
  }
}
