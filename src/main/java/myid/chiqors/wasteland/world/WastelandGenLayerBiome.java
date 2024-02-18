

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
  private List<BiomeManager.BiomeEntry> biomes = new ArrayList<BiomeManager.BiomeEntry>();

  public WastelandGenLayerBiome(long p_i2122_1_, GenLayer p_i2122_3_) {
    super(p_i2122_1_);
    this.parent = p_i2122_3_;
    int i;
    for (i = 0; i < 10; i++) {
      this.biomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID), 10));
      this.biomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.forestBiomeID), 12));
      this.biomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.mountainBiomeID), 12));
      this.biomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 2), 10));
      this.biomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 3), 10));
    }
    for (i = 0; i < 20; i++){
      this.biomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.apocalypseBiomeID), 10));
      this.biomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID + 1), 10));
    }
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
    for (int i1 = 0; i1 < p_75904_4_; i1++) {
      for (int j1 = 0; j1 < p_75904_3_; j1++) {
        initChunkSeed((j1 + p_75904_1_), (i1 + p_75904_2_));
        int k1 = aint[j1 + i1 * p_75904_3_];
        int l1 = (k1 & 0xF00) >> 8;
        k1 &= 0xFFFFF0FF;
        aint1[j1 + i1 * p_75904_3_] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.biomes, (int)(nextLong((WeightedRandom.getTotalWeight(this.biomes) / 10)) * 10L))).biome.biomeID;
      } 
    } 
    return aint1;
  }
}
