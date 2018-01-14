package dk.mrspring.wasteland.utils;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;

public class CustomItemStack 
{
	public ItemStack items;
	public int maxItems;
	public int minItems;
	
	public CustomItemStack(ItemStack items, int max, int min)
	{
		this.items = items;
		this.maxItems = max;
		this.minItems = min;
	}
	
	public static ItemStack[] getLootItems(Random rand, CustomItemStack[] items, int minItems, int maxItems, boolean repeat)
	{
		
		int numofItems = minItems + (rand.nextInt(maxItems - minItems + 1));
		if (numofItems < 0)
		{
			numofItems = 0;
		}
		else if (numofItems > maxItems)
		{
			numofItems = maxItems;
		}
		
		ItemStack[] returned_itemStack = new ItemStack[numofItems];
		CustomItemStack[] temp_items = new CustomItemStack[items.length];
		for (int i = 0; i < items.length; i++)
		{
			temp_items[i] = new CustomItemStack(ItemStack.copyItemStack(items[i].items), items[i].maxItems, items[i].minItems);
		}
		
		if (maxItems >= temp_items.length && !repeat)
		{
			for (int i = 0; i < temp_items.length; i++)
			{
				rand.setSeed(rand.nextLong());
				int stackSize = Math.min(temp_items[i].items.getItem().getItemStackLimit(temp_items[i].items), (rand.nextInt(items[i].maxItems - items[i].minItems + 1) + items[i].minItems));
				returned_itemStack[i] = new ItemStack(temp_items[i].items.getItem(), stackSize);
			}
		}
		else
		{
			//System.out.println(temp_items.toString());
			int tempStackSize = temp_items.length;
			for (int i = 0; i < numofItems; i++)
			{

				int k = rand.nextInt(temp_items.length);
				while (temp_items[k] == null)
				{
					k = k + 1;
					if (k >= temp_items.length)
					{
						k = 0;
					}
				}
				//System.out.println(String.valueOf(i) + " " + String.valueOf(k) + " " + String.valueOf(numofItems) + " " + String.valueOf(temp_items.length));
				//System.out.println(temp_items[k].items.toString());
				int stackSize = Math.min(temp_items[k].items.getItem().getItemStackLimit(temp_items[k].items), (rand.nextInt(items[k].maxItems - items[k].minItems + 1) + items[k].minItems));
				returned_itemStack[i] = new ItemStack(temp_items[k].items.getItem(), stackSize);
				
				if (!repeat)
				{
					temp_items[k] = null;
					tempStackSize --;
					if (tempStackSize == 0)
					{
						return returned_itemStack;
					}
				}
			}
		}
		return returned_itemStack;
	}
	
	public static void placeLoot(Random rand, TileEntityChest chest, ItemStack[] loot)
	{
		for (int i = 0; i < loot.length; i++)
		{
			int slot = rand.nextInt(chest.getSizeInventory());
			//System.out.println("Starting place loop");
			while (chest.getStackInSlot(slot) != null)
			{
				//System.out.println(message + " " + String.valueOf(slot) + " " + String.valueOf(chest.getSizeInventory()));
				slot = slot + 1;
				if (slot > chest.getSizeInventory())
				{
					slot = 0;
				}
			}
			if(loot[i] != null)
			{
				chest.setInventorySlotContents(slot, loot[i]);
			}
		}
		
	}
}
