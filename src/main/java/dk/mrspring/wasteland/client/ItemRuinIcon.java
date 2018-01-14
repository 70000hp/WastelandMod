package dk.mrspring.wasteland.client;

import dk.mrspring.wasteland.ModHelper;
import net.minecraft.item.Item;

public class ItemRuinIcon extends Item
{
	public ItemRuinIcon(String textureName)
	{
		this.setTextureName(ModHelper.ModInfo.modid + ":" + textureName);
	}
}
