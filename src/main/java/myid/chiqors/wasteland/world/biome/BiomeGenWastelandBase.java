

package myid.chiqors.wasteland.world.biome;

import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.world.gen.BiomeDecoratorWasteland;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenRiver;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomeGenWastelandBase extends BiomeGenBase {
  
  public static final BiomeGenBase.Height height_WastelandMountains = new BiomeGenBase.Height(1.0F, 0.5F);
  public int smallLakeSpawnRate = ModConfig.lakeSpawnRate;
  public int ruinSpawnRate = ModConfig.wastelandRuinRarirty;
  public int treeSpawnRate = ModConfig.wastelandTreeSpawnRate;
  public int treesPerChunk = 1;
  public boolean temples = false;
  public boolean wasteTerrain = false;
  private static int lastID = 0;
  
  public BiomeGenWastelandBase(int par1ID, String par2Name, BiomeGenBase.Height biomeHeight) {
    super(par1ID);
    this.enableRain = true;
    this.biomeName = par2Name;
    setHeight(biomeHeight);
    this.theBiomeDecorator = new BiomeDecoratorWasteland();
    this.waterColorMultiplier = 3376435;
    lastID++;
    loadBiome();
  }
  
  public static void load() {
    BiomeGenBase apocalypse = (new BiomeGenApocalypse(ModConfig.apocalypseBiomeID, "Wasteland", BiomeGenBase.height_LowPlains)).setColor(14728553).setTemperatureRainfall(0.8F, 0.2F);
    BiomeGenBase apocMountains = (new BiomeGenMountains(ModConfig.mountainBiomeID, "Wasteland Mountains", height_WastelandMountains)).setColor(10255379).setTemperatureRainfall(0.7F, 0.3F);
    BiomeGenBase apocForest = (new BiomeGenForest(ModConfig.forestBiomeID, "Wasteland Forest", BiomeGenBase.height_MidPlains, false)).setColor(10793807).setTemperatureRainfall(0.7F, 0.6F);
    BiomeGenBase radioactive = (new BiomeGenRadioactive(ModConfig.radioactiveBiomeID, "Radioactive Wasteland", BiomeGenBase.height_Shores)).setColor(6088238).setTemperatureRainfall(0.5F, 0.5F);
    BiomeGenBase tundra = (new BiomeGenWastelandTundra(ModConfig.radioactiveBiomeID + 1, "Wasteland Tundra", BiomeGenBase.height_LowPlains)).setEnableSnow().setTemperatureRainfall(0.0F, 0.95F).setColor(10526880);
    BiomeGenBase tundraHills = (new BiomeGenWastelandTundra(ModConfig.radioactiveBiomeID + 2, "Wasteland Mountains", BiomeGenBase.height_HighPlateaus)).setEnableSnow().setTemperatureRainfall(0.0F, 0.95F).setColor(10526880);
    BiomeGenBase tundraForest = (new BiomeGenWastelandTaiga(ModConfig.radioactiveBiomeID + 3, "Wasteland Taiga", BiomeGenBase.height_MidPlains)).setEnableSnow().setTemperatureRainfall(0.0F, 0.95F).setColor(747097);
    BiomeGenBase desert = (new BiomeGenWastelandDesert(ModConfig.radioactiveBiomeID + 4, "Wasteland Desert", BiomeGenBase.height_LowHills)).setColor(747097).setTemperatureRainfall(1.5F, 0.0F);
    BiomeGenBase mesa = (new BiomeGenWastelandMesa(ModConfig.radioactiveBiomeID + 5, "Wasteland Mesa", BiomeGenBase.height_Default, false)).setColor(747097).setTemperatureRainfall(2.0F, 0.0F);
    BiomeGenBase bryce = (new BiomeGenWastelandMesa(ModConfig.radioactiveBiomeID + 6, "Wasteland Bryce", BiomeGenBase.height_Default, true)).setColor(747097).setTemperatureRainfall(2.0F, 0.0F);

    BiomeDictionary.registerBiomeType(apocalypse, BiomeDictionary.Type.WASTELAND);
    BiomeDictionary.registerBiomeType(apocMountains, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.MOUNTAIN);
    BiomeDictionary.registerBiomeType(apocForest, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.FOREST);
    BiomeDictionary.registerBiomeType(apocForest.createMutation(), BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.FOREST);
    BiomeDictionary.registerBiomeType(radioactive, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SWAMP);
    BiomeDictionary.registerBiomeType(tundra, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY);
    BiomeDictionary.registerBiomeType(tundraHills, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY);
    BiomeDictionary.registerBiomeType(tundraForest, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY);
    BiomeDictionary.registerBiomeType(desert, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DRY);
    BiomeDictionary.registerBiomeType(mesa, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.MESA, BiomeDictionary.Type.DRY, BiomeDictionary.Type.WASTELAND);
    BiomeDictionary.registerBiomeType(mesa.createMutation(), BiomeDictionary.Type.SANDY, BiomeDictionary.Type.MESA, BiomeDictionary.Type.DRY, BiomeDictionary.Type.WASTELAND);
    BiomeDictionary.registerBiomeType(bryce, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.MESA, BiomeDictionary.Type.DRY, BiomeDictionary.Type.WASTELAND);
    BiomeManager.addSpawnBiome(apocalypse);
    BiomeManager.addSpawnBiome(desert);
    BiomeManager.addSpawnBiome(mesa);
    BiomeManager.addSpawnBiome(tundra);
    BiomeManager.addSpawnBiome(tundraHills);
    BiomeManager.addSpawnBiome(apocMountains);
    BiomeManager.addSpawnBiome(apocForest);
  }

  public void setTopBlock(Block block) {
    this.topBlock = block;
  }
  
  public void setFillerBlock(Block block) {
    this.fillerBlock = block;
  }
  public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
  {
    this.genBiomeWastelandTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
  }

  public void genBiomeWastelandTerrain(World world, Random random, Block[] blocks, byte[] meta, int x, int y, double p_150560_7_)
  {
    Block block = this.topBlock;
    byte b0 = (byte)(this.field_150604_aj & 255);
    Block block1 = this.fillerBlock;
    int k = -1;
    int l = (int)(p_150560_7_ / 3.0D + 3.0D + random.nextDouble() * 0.25D);
    int i1 = x & 15;
    int j1 = y & 15;
    int k1 = blocks.length / 256;

    for (int l1 = 255; l1 >= 0; --l1)
    {
      int i2 = (j1 * 16 + i1) * k1 + l1;

      if (l1 <= random.nextInt(5))
      {
        blocks[i2] = Blocks.bedrock;
      }
      else
      {
        Block block2 = blocks[i2];

        if (block2 != null && block2.getMaterial() != Material.air)
        {
          if (block2 == Blocks.stone)
          {
            if (k == -1)
            {
              if (l <= 0)
              {
                block = null;
                b0 = 0;
                block1 = Blocks.stone;
              }
              else if (l1 >= 59 && l1 <= 70)
              {
                if(wasteTerrain){
                  int type = random.nextInt(3);
                  block = type == 0 ? Blocks.hardened_clay : this.topBlock;
                  if (type == 2){
                    b0 = 2;
                  }
                } else {
                  block = this.topBlock;
                  b0 = (byte) (this.field_150604_aj & 255);
                }
                block1 = this.fillerBlock;
              }

              if (l1 < 65 && (block == null || block.getMaterial() == Material.air))
              {
                if (this.getFloatTemperature(x, l1, y) < 0.15F)
                {
                  block = Blocks.ice;
                }
                else
                {
                  block = ModConfig.getlakeLiquid();
                }
                b0 = 0;
              }

              k = l;

              if (l1 >= 62)
              {
                blocks[i2] = block;
                meta[i2] = b0;
              }
              else if (l1 < 56 - l)
              {
                block = null;
                block1 = Blocks.stone;
                blocks[i2] = Blocks.gravel;
              }
              else
              {
                blocks[i2] = block1;
              }
            }
            else if (k > 0)
            {
              --k;
              blocks[i2] = block1;

              if (k == 0 && block1 == Blocks.sand)
              {
                k = random.nextInt(4) + Math.max(0, l1 - 63);
                block1 = Blocks.sandstone;
              }
            }
          }
        }
        else
        {
          k = -1;
        }
      }
    }
  }
  public void loadBiome() {
    this.theBiomeDecorator.flowersPerChunk = -999;
    this.theBiomeDecorator.grassPerChunk = -999;
    this.theBiomeDecorator.treesPerChunk = -999;
    setTopBlock(ModConfig.getSurfaceBlock());
    setFillerBlock(Blocks.stone);
  }
  
  public void setCreatureSpawns(List<BiomeGenBase.SpawnListEntry> entries, List<BiomeGenBase.SpawnListEntry> biomeEntities, boolean spawn) {
    biomeEntities.clear();
    if (spawn)
        for (SpawnListEntry entry : entries) {
            if (entry.itemWeight > 0 && entry.maxGroupCount > 0)
                biomeEntities.add(entry);
        }
  }
}
