package com.seventythousand.wasteland.items;

import com.hbm.tileentity.machine.storage.TileEntityCrateBase;
import com.seventythousand.wasteland.config.CityLootConfig;
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


    public static ItemStack[] getLootItems(Random rand, WeightedRandomChestContent[] possibleItems, int minItems, int maxItems, boolean repeat) {
        int numofItems = minItems + rand.nextInt(maxItems - minItems + 1);
        numofItems = (numofItems < 0) ? 0 : (Math.min(numofItems, possibleItems.length));
        ItemStack[] returned_itemStack = new ItemStack[numofItems];

        for (int i = 0; i < numofItems; i++) {
            int itemIndex = rand.nextInt(possibleItems.length);
            WeightedRandomChestContent item = possibleItems[itemIndex];
            if (item.theItemId != null && item.theItemId.getItem() != null) {
                item.theItemId.stackSize = Math.min(item.theItemId.getItem().getItemStackLimit(item.theItemId), rand.nextInt(item.theMaximumChanceToGenerateItem - item.theMinimumChanceToGenerateItem + 1) + item.theMinimumChanceToGenerateItem);
                returned_itemStack[i] = item.theItemId;
            }
        }
        return returned_itemStack;
    }

    public static void placeLoot(Random rand, TileEntityChest chest, ItemStack[] loot) {
        for (ItemStack itemStack : loot) {
            if(chest != null) {
                int slot = rand.nextInt(chest.getSizeInventory());
                for (int i = 0; i < chest.getSizeInventory(); i++)
                    slot = (slot >= chest.getSizeInventory() - 1) ? 0 : (slot + 1);
                if (itemStack != null)
                    chest.setInventorySlotContents(slot, itemStack);
            }
        }
    }
    public static void placeLoot(Random rand, TileEntityCrateBase chest, WeightedRandomChestContent[] contents, int minItems, int maxItems, boolean repeat) {
        ItemStack[] loot = getLootItems(rand, contents, minItems, maxItems, repeat);
        for (ItemStack itemStack : loot) {
            if(chest != null) {
                int slot = rand.nextInt(chest.getSizeInventory());
                for (int i = 0; i < chest.getSizeInventory(); i++)
                    slot = (slot >= chest.getSizeInventory() - 1) ? 0 : (slot + 1);
                if (itemStack != null)
                    chest.setInventorySlotContents(slot, itemStack);
            }
        }
    }
    public static void placeLoot(Random rand, TileEntityChest chest, WeightedRandomChestContent[] contents, int minItems, int maxItems, boolean repeat) {
        ItemStack[] loot = getLootItems(rand, contents, minItems, maxItems, repeat);
        for (ItemStack itemStack : loot) {
            if(chest != null) {
                int slot = rand.nextInt(chest.getSizeInventory());
                for (int i = 0; i < chest.getSizeInventory(); i++)
                    slot = (slot >= chest.getSizeInventory() - 1) ? 0 : (slot + 1);
                if (itemStack != null)
                    chest.setInventorySlotContents(slot, itemStack);
            }
        }
    }
}
