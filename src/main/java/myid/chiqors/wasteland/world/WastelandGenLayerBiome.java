

package myid.chiqors.wasteland.world;

import myid.chiqors.wasteland.WastelandBiomes;
import myid.chiqors.wasteland.config.ModConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;

public class WastelandGenLayerBiome extends GenLayer {
  private List<BiomeManager.BiomeEntry> biomes = new ArrayList<BiomeManager.BiomeEntry>();
  
  private static final String __OBFID = "CL_00000555";
  
  public WastelandGenLayerBiome(long p_i2122_1_, GenLayer p_i2122_3_, WorldType p_i2122_4_) {
    super(p_i2122_1_);
    this.parent = p_i2122_3_;
    if (ModConfig.spawnCities)
      for (int j = 0; j < 1; j++)
        this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.city, 10));  
    int i;
    for (i = 0; i < 3; i++)
      this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.radioactive, 10)); 
    for (i = 0; i < 10; i++) {
      this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.forest, 10));
      this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.mountains, 10));
    } 
    for (i = 0; i < 40; i++)
      this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.apocalypse, 10)); 
  }
  
  public static GenLayer[] initializeAllBiomeGenerators(long p_75901_0_, WorldType p_75901_2_) {
    boolean flag = false;
    GenLayerIsland genlayerisland = new GenLayerIsland(1L);
    GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, (GenLayer)genlayerisland);
    GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, (GenLayer)genlayerfuzzyzoom);
    GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, (GenLayer)genlayeraddisland);
    genlayeraddisland = new GenLayerAddIsland(2L, (GenLayer)genlayerzoom);
    genlayeraddisland = new GenLayerAddIsland(50L, (GenLayer)genlayeraddisland);
    genlayeraddisland = new GenLayerAddIsland(70L, (GenLayer)genlayeraddisland);
    GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, (GenLayer)genlayeraddisland);
    GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, (GenLayer)genlayerremovetoomuchocean);
    genlayeraddisland = new GenLayerAddIsland(3L, (GenLayer)genlayeraddsnow);
    GenLayerEdge genlayeredge = new GenLayerEdge(2L, (GenLayer)genlayeraddisland, GenLayerEdge.Mode.COOL_WARM);
    genlayeredge = new GenLayerEdge(2L, (GenLayer)genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
    genlayeredge = new GenLayerEdge(3L, (GenLayer)genlayeredge, GenLayerEdge.Mode.SPECIAL);
    genlayerzoom = new GenLayerZoom(2002L, (GenLayer)genlayeredge);
    genlayerzoom = new GenLayerZoom(2003L, (GenLayer)genlayerzoom);
    genlayeraddisland = new GenLayerAddIsland(4L, (GenLayer)genlayerzoom);
    GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, (GenLayer)genlayeraddisland);
    GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, (GenLayer)genlayeraddmushroomisland);
    GenLayer genlayer2 = GenLayerZoom.magnify(1000L, (GenLayer)genlayerdeepocean, 0);
    byte b0 = 4;
    if (p_75901_2_ == WorldType.LARGE_BIOMES)
      b0 = 6; 
    b0 = getModdedBiomeSize(p_75901_2_, b0);
    GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer2, 0);
    Object object = p_75901_2_.getBiomeLayer(p_75901_0_, genlayer2);
    GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayer, 2);
    GenLayerHills genlayerhills = new GenLayerHills(1000L, (GenLayer)object, genlayer1);
    genlayer = GenLayerZoom.magnify(1000L, genlayer, 2);
    genlayer = GenLayerZoom.magnify(1000L, genlayer, b0);
    GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayer);
    object = new GenLayerRareBiome(1001L, (GenLayer)genlayerhills);
    for (int j = 0; j < b0; j++) {
      object = new GenLayerZoom((1000 + j), (GenLayer)object);
      if (j == 0)
        object = new GenLayerAddIsland(3L, (GenLayer)object); 
      if (j == 1)
        object = new GenLayerShore(1000L, (GenLayer)object); 
    } 
    GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object);
    GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, (GenLayer)genlayersmooth1, (GenLayer)genlayersmooth);
    GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, (GenLayer)genlayerrivermix);
    genlayersmooth1.initWorldGenSeed(p_75901_0_);
    genlayerrivermix.initWorldGenSeed(p_75901_0_);
    genlayervoronoizoom.initWorldGenSeed(p_75901_0_);
    return new GenLayer[] { (GenLayer)genlayerrivermix, (GenLayer)genlayervoronoizoom, (GenLayer)genlayerrivermix };
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
  
  private static int pickBiome(List<BiomeManager.BiomeEntry> biomes) {
    Random rand = new Random();
    int totalWeight = 0;
      for (BiomeManager.BiomeEntry biomeEntry : biomes)
          totalWeight += ((BiomeManager.BiomeEntry) biomeEntry).itemWeight;
    int num = rand.nextInt(totalWeight);
    int sum = 0;
      for (BiomeManager.BiomeEntry biome : biomes) {
          sum += ((BiomeManager.BiomeEntry) biomes.get(1)).itemWeight;
          if (sum > num)
              return ((BiomeManager.BiomeEntry) biome).biome.biomeID;
      }
    return pickBiome(biomes);
  }
}
