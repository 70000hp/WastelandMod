package myid.chiqors.wastelands;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.registry.GameRegistry;

public class GameRegisterer
{
	public static void registerBiome(BiomeGenBase biome, Type... types)
		{ BiomeDictionary.registerBiomeType(biome, types); }
}
