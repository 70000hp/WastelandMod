package com.seventythousand.wasteland;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class GameRegisterer {
  public static void registerBiome(BiomeGenBase biome, BiomeDictionary.Type... types) {
    BiomeDictionary.registerBiomeType(biome, types);
  }
}
