package com.seventythousand.wasteland.items;

import com.hbm.tileentity.machine.storage.TileEntityCrateBase;
import com.seventythousand.wasteland.config.CityLootConfig;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootStack {
  public WeightedRandomChestContent[] items;

  public int maxNum;

  public int minNum;

  public boolean repeat;

  public LootStack(WeightedRandomChestContent[] items, int max, int min, boolean repeat) {
    this.items = items;
    this.maxNum = max;
    this.minNum = min;
    this.repeat = repeat;
  }

  public static void placeLoot(Random rand, IInventory inventory, WeightedRandomChestContent[] contents, int minItems, int maxItems) {
    int amount = rand.nextInt(maxItems - minItems) + minItems;
    WeightedRandomChestContent.generateChestContents(rand, contents, inventory, amount);
  }
}
