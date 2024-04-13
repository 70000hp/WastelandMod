

package com.seventythousand.wasteland.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;

public class CustomItemStack {
  public ItemStack items;

  public int maxItems;

  public int minItems;

  public CustomItemStack(ItemStack items, int max, int min) {
    this.items = items;
    this.maxItems = max;
    this.minItems = min;
  }

  public static ItemStack[] getLootItems(Random rand, CustomItemStack[] items, int minItems, int maxItems, boolean repeat) {
    int numofItems = minItems + rand.nextInt(maxItems - minItems + 1);
    numofItems = (numofItems < 0) ? 0 : ((numofItems > items.length) ? items.length : numofItems);
    ItemStack[] returned_itemStack = new ItemStack[numofItems];
    List<CustomItemStack> temp_items = new ArrayList<CustomItemStack>();
    int i;
    for (i = 0; i < items.length; i++) {
      CustomItemStack newItem = new CustomItemStack(ItemStack.copyItemStack((items[i]).items), (items[i]).maxItems, (items[i]).minItems);
      copyDamage(newItem.items, (items[i]).items);
      temp_items.add(newItem);
    }
    for (i = 0; i < numofItems && !temp_items.isEmpty(); i++) {
      int itemIndex = rand.nextInt(temp_items.size());
      CustomItemStack item = temp_items.get(itemIndex);
      item.items.stackSize = Math.min(item.items.getItem().getItemStackLimit(item.items), rand.nextInt(item.maxItems - item.minItems + 1) + item.minItems);
      returned_itemStack[i] = item.items;
      if (!repeat)
        temp_items.remove(itemIndex);
    }
    return returned_itemStack;
  }

  public static void placeLoot(Random rand, TileEntityChest chest, ItemStack[] loot) {
      for (ItemStack itemStack : loot) {
          if(chest != null) {
            int slot = rand.nextInt(chest.getSizeInventory());
            while (chest.getStackInSlot(slot) != null)
              slot = (slot >= chest.getSizeInventory() - 1) ? 0 : (slot + 1);
            if (itemStack != null)
              chest.setInventorySlotContents(slot, itemStack);
          }
      }
  }

  private static void copyDamage(ItemStack newItem, ItemStack oldItem) {
    newItem.setItemDamage(oldItem.getItemDamage());
  }
}
