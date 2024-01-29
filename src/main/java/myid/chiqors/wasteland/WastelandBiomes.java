package myid.chiqors.wasteland;

import myid.chiqors.wasteland.config.ModConfig;
import net.minecraft.world.biome.BiomeGenBase;

public class WastelandBiomes {
  public static BiomeGenBase apocalypse = BiomeGenBase.getBiome(ModConfig.apocalypseBiomeID);
  
  public static BiomeGenBase mountains = BiomeGenBase.getBiome(ModConfig.mountainBiomeID);
  
  public static BiomeGenBase forest = BiomeGenBase.getBiome(ModConfig.forestBiomeID);
  
  public static BiomeGenBase city = BiomeGenBase.getBiome(ModConfig.cityBiomeID);
  
  public static BiomeGenBase radioactive = BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID);
}
