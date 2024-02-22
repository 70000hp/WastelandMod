

package myid.chiqors.wasteland.world;

import myid.chiqors.wasteland.config.ModConfig;
import java.util.ArrayList;
import java.util.List;

import myid.chiqors.wasteland.world.gen.layer.GenLayerWastelandRiverMix;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.*;
import net.minecraftforge.common.BiomeManager;


public class WastelandGenLayerBiome extends GenLayer {
  private ArrayList<BiomeManager.BiomeEntry>[] biomes = new ArrayList[BiomeManager.BiomeType.values().length];

  public WastelandGenLayerBiome(long p_i2122_1_, GenLayer p_i2122_3_) {
    super(p_i2122_1_);
    this.parent = p_i2122_3_;
    for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values())
    {
      int idx = type.ordinal();
      if (biomes[idx] == null) biomes[idx] = new ArrayList<BiomeManager.BiomeEntry>();
    }

    //no cool and icy biomes plox
    this.biomes[BiomeManager.BiomeType.WARM.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID), 7));
    this.biomes[BiomeManager.BiomeType.WARM.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.forestBiomeID), 10));
    this.biomes[BiomeManager.BiomeType.WARM.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.apocalypseBiomeID), 10));
    this.biomes[BiomeManager.BiomeType.WARM.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.mountainBiomeID), 8));

    this.biomes[BiomeManager.BiomeType.DESERT.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 6), 9));
    this.biomes[BiomeManager.BiomeType.DESERT.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 5), 9));
    this.biomes[BiomeManager.BiomeType.DESERT.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 5 + 128), 8));
    this.biomes[BiomeManager.BiomeType.DESERT.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 4), 10));

    this.biomes[BiomeManager.BiomeType.COOL.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 1), 7));
    this.biomes[BiomeManager.BiomeType.COOL.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.forestBiomeID), 7));
    this.biomes[BiomeManager.BiomeType.COOL.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.mountainBiomeID), 8));
    this.biomes[BiomeManager.BiomeType.COOL.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.forestBiomeID + 128), 9));

    this.biomes[BiomeManager.BiomeType.ICY.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.forestBiomeID + 128), 7));
    this.biomes[BiomeManager.BiomeType.ICY.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 2), 8));
    this.biomes[BiomeManager.BiomeType.ICY.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 3), 9));
    this.biomes[BiomeManager.BiomeType.ICY.ordinal()].add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 1), 10));


  }
  public static GenLayer[] initializeAllBiomeGenerators(long p_75901_0_, WorldType p_75901_2_)
  {
    boolean flag = false;
    GenLayerIsland genlayerisland = new GenLayerIsland(1L);
    GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
    GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
    GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
    genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
    genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
    genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
    GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
    GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
    genlayeraddisland = new GenLayerAddIsland(3L, genlayeraddsnow);
    GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, GenLayerEdge.Mode.COOL_WARM);
    genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
    genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
    genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
    genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
    genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
    GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
    GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
    GenLayer genlayer2 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
    byte b0 = 4;

    b0 = getModdedBiomeSize(p_75901_2_, b0);

    GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer2, 0);
    GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
    GenLayer object = p_75901_2_.getBiomeLayer(p_75901_0_, genlayer2);

    GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
    GenLayerHills genlayerhills = new GenLayerHills(1000L, object, genlayer1);
    genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
    genlayer = GenLayerZoom.magnify(1000L, genlayer, b0);
    GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
    GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
    object = new GenLayerRareBiome(1001L, genlayerhills);

    for (int j = 0; j < b0; ++j)
    {
      object = new GenLayerZoom((long)(1000 + j), object);

      if (j == 0)
      {
        object = new GenLayerAddIsland(3L, object);
      }

      if (j == 1)
      {
        object = new GenLayerShore(1000L, object);
      }
    }

    GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, object);
    GenLayerWastelandRiverMix genlayerrivermix = new GenLayerWastelandRiverMix(100L, genlayersmooth1, genlayersmooth);
    GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
    genlayerrivermix.initWorldGenSeed(p_75901_0_);
    genlayervoronoizoom.initWorldGenSeed(p_75901_0_);
    return new GenLayer[] {genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
  }
  public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
    int[] aint = this.parent.getInts(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
    int[] aint1 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);
    for (int i1 = 0; i1 < p_75904_4_; ++i1)
    {
      for (int j1 = 0; j1 < p_75904_3_; ++j1)
      {
        this.initChunkSeed((long)(j1 + p_75904_1_), (long)(i1 + p_75904_2_));
        int k1 = aint[j1 + i1 * p_75904_3_];
        k1 &= -3841;

       if (k1 == 1)
        {
          aint1[j1 + i1 * p_75904_3_] = getWeightedBiomeEntry(BiomeManager.BiomeType.DESERT).biome.biomeID;
        }
        else if (k1 == 2)
        {
          aint1[j1 + i1 * p_75904_3_] = getWeightedBiomeEntry(BiomeManager.BiomeType.WARM).biome.biomeID;
        }
        else if (k1 == 3)
        {
          aint1[j1 + i1 * p_75904_3_] = getWeightedBiomeEntry(BiomeManager.BiomeType.COOL).biome.biomeID;
        }
        else
        {
          aint1[j1 + i1 * p_75904_3_] = getWeightedBiomeEntry(BiomeManager.BiomeType.ICY).biome.biomeID;
        }
      }
    }
    return aint1;
  }
  protected BiomeManager.BiomeEntry getWeightedBiomeEntry(BiomeManager.BiomeType type)
  {
    List<BiomeManager.BiomeEntry> biomeList = biomes[type.ordinal()];
    int totalWeight = WeightedRandom.getTotalWeight(biomeList);
    int weight = BiomeManager.isTypeListModded(type)?nextInt(totalWeight):nextInt(totalWeight / 10) * 10;
    return (BiomeManager.BiomeEntry)WeightedRandom.getItem(biomeList, weight);
  }
}
