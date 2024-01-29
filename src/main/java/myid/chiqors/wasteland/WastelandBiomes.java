package myid.chiqors.wasteland;

import myid.chiqors.wasteland.config.ModConfig;
import net.minecraft.world.biome.BiomeGenBase;

public class WastelandBiomes {
  public static BiomeGenBase apocalypse = BiomeGenBase.func_150568_d(ModConfig.apocalypseBiomeID);
  
  public static BiomeGenBase mountains = BiomeGenBase.func_150568_d(ModConfig.mountainBiomeID);
  
  public static BiomeGenBase forest = BiomeGenBase.func_150568_d(ModConfig.forestBiomeID);
  
  public static BiomeGenBase city = BiomeGenBase.func_150568_d(ModConfig.cityBiomeID);
  
  public static BiomeGenBase radioactive = BiomeGenBase.func_150568_d(ModConfig.radioactiveBiomeID);
}
