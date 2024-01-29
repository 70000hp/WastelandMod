

package myid.chiqors.wasteland.world.biome;

import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.world.gen.BiomeDecoratorWasteland;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomeGenWastelandBase extends BiomeGenBase {
  public static final BiomeGenBase.Height height_Wasteland = new BiomeGenBase.Height(0.1F, 0.05F);
  
  public static final BiomeGenBase.Height height_WastelandCity = new BiomeGenBase.Height(0.09F, 0.01F);
  
  public static final BiomeGenBase.Height height_WastelandMountains = new BiomeGenBase.Height(1.0F, 0.5F);
  
  private static int lastID = 0;
  
  public BiomeGenWastelandBase(int par1ID, String par2Name, BiomeGenBase.Height biomeHeight) {
    super(par1ID);
    this.enableRain = true;
    this.biomeName = par2Name;
    func_150570_a(biomeHeight);
    this.theBiomeDecorator = (BiomeDecorator)new BiomeDecoratorWasteland();
    this.waterColorMultiplier = 3376435;
    lastID++;
    loadBiome();
  }
  
  public static void load() {
    BiomeGenBase apocalypse = (new BiomeGenApocalypse(ModConfig.apocalypseBiomeID, "Wasteland", height_Wasteland)).setColor(14728553);
    BiomeGenBase apocMountains = (new BiomeGenMountains(ModConfig.mountainBiomeID, "Wasteland Mountains", height_WastelandMountains)).setColor(10255379);
    BiomeGenBase apocForest = (new BiomeGenForest(ModConfig.forestBiomeID, "Wasteland Forest", height_Wasteland)).setColor(10793807);
    BiomeGenBase apocCity = (new BiomeGenCity(ModConfig.cityBiomeID, "Wasteland City", height_WastelandCity)).setColor(9410739);
    BiomeGenBase radioactive = (new BiomeGenRadioactive(ModConfig.radioactiveBiomeID, "Radioactive Wasteland", height_Wasteland)).setColor(6088238);
    BiomeDictionary.registerBiomeType(apocalypse, new BiomeDictionary.Type[] { BiomeDictionary.Type.WASTELAND });
    BiomeDictionary.registerBiomeType(apocMountains, new BiomeDictionary.Type[] { BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.MOUNTAIN });
    BiomeDictionary.registerBiomeType(apocForest, new BiomeDictionary.Type[] { BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.FOREST });
    BiomeDictionary.registerBiomeType(apocCity, new BiomeDictionary.Type[] { BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DEAD });
    BiomeDictionary.registerBiomeType(radioactive, new BiomeDictionary.Type[] { BiomeDictionary.Type.WASTELAND });
    BiomeManager.addSpawnBiome(apocalypse);
    BiomeManager.addSpawnBiome(apocMountains);
    BiomeManager.addSpawnBiome(apocForest);
    BiomeManager.addSpawnBiome(apocCity);
    BiomeManager.addSpawnBiome(radioactive);
  }
  
  public BiomeGenWastelandBase setTopBlock(Block block) {
    this.topBlock = block;
    return this;
  }
  
  public BiomeGenWastelandBase setFillerBlock(Block block) {
    this.fillerBlock = block;
    return this;
  }
  
  public void loadBiome() {
    this.theBiomeDecorator.deadBushPerChunk = 5;
    this.theBiomeDecorator.flowersPerChunk = -999;
    this.theBiomeDecorator.generateLakes = false;
    this.theBiomeDecorator.grassPerChunk = -999;
    this.theBiomeDecorator.treesPerChunk = -999;
    setTopBlock(ModConfig.getSurfaceBlock());
    setFillerBlock(Blocks.stone);
  }
  
  public void setCreatureSpawns(List<BiomeGenBase.SpawnListEntry> entries, List<BiomeGenBase.SpawnListEntry> biomeEntities, boolean spawn) {
    biomeEntities.clear();
    if (spawn)
      for (int i = 0; i < entries.size(); i++) {
        BiomeGenBase.SpawnListEntry entry = entries.get(i);
        if (entry.itemWeight > 0 && entry.maxGroupCount > 0)
          biomeEntities.add(entries.get(i)); 
      }  
  }
}
