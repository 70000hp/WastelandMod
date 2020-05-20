package myid.chiqors.wastelands.client;

import myid.chiqors.wastelands.ModHelper;
import net.minecraft.item.Item;

public class ItemRuinIcon extends Item
{
	public ItemRuinIcon(String textureName)
	{
		this.setTextureName(ModHelper.ModInfo.modid + ":" + textureName);
	}
}
