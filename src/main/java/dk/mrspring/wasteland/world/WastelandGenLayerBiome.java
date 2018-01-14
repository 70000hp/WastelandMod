package dk.mrspring.wasteland.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dk.mrspring.wasteland.ModConfig;
import dk.mrspring.wasteland.WastelandBiomes;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
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
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;

public class WastelandGenLayerBiome extends GenLayer
{
    private List<BiomeEntry> biomes = new ArrayList<BiomeEntry>();
    
    private static final String __OBFID = "CL_00000555";

    public WastelandGenLayerBiome(long p_i2122_1_, GenLayer p_i2122_3_, WorldType p_i2122_4_)
    {
        super(p_i2122_1_);
        
        this.parent = p_i2122_3_;

        if (ModConfig.spawnCities)
        {
	        for (int i = 0; i < 1; i++)
	        {
	        	 biomes.add(new BiomeEntry(WastelandBiomes.city, 10));
	        }
        }
        
        for (int i = 0; i < 10; i++)
        {
	        biomes.add(new BiomeEntry(WastelandBiomes.forest, 10));
	        biomes.add(new BiomeEntry(WastelandBiomes.mountains, 10));
        }
        
        for (int i = 0; i < 40; i++)
        {
	        biomes.add(new BiomeEntry(WastelandBiomes.apocalypse, 10));
        }
        
        
    }
    
    //@Override
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

        if (p_75901_2_ == WorldType.LARGE_BIOMES)
        {
            b0 = 6;
        }

        b0 = getModdedBiomeSize(p_75901_2_, b0);

        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer2, 0);
        //GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        Object object = p_75901_2_.getBiomeLayer(p_75901_0_, genlayer2);

        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayer, 2);
        GenLayerHills genlayerhills = new GenLayerHills(1000L, (GenLayer)object, genlayer1);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, 2);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, b0);
        //GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayer);
        object = new GenLayerRareBiome(1001L, genlayerhills);

        for (int j = 0; j < b0; ++j)
        {
            object = new GenLayerZoom((long)(1000 + j), (GenLayer)object);

            if (j == 0)
            {
                object = new GenLayerAddIsland(3L, (GenLayer)object);
            }

            if (j == 1)
            {
                object = new GenLayerShore(1000L, (GenLayer)object);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayersmooth1.initWorldGenSeed(p_75901_0_);
        genlayerrivermix.initWorldGenSeed(p_75901_0_);
        genlayervoronoizoom.initWorldGenSeed(p_75901_0_);
        return new GenLayer[] {genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }
    
    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int[] aint = this.parent.getInts(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
        int[] aint1 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

        for (int i1 = 0; i1 < p_75904_4_; ++i1)
        {
            for (int j1 = 0; j1 < p_75904_3_; ++j1)
            {
                this.initChunkSeed((long)(j1 + p_75904_1_), (long)(i1 + p_75904_2_));
                int k1 = aint[j1 + i1 * p_75904_3_];
                int l1 = (k1 & 3840) >> 8;
                k1 &= -3841;

                
                aint1[j1 + i1 * p_75904_3_] = ((BiomeEntry)WeightedRandom.getItem(this.biomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.biomes) / 10) * 10))).biome.biomeID;
            }
        }

        return aint1;
    }
    
    private static int pickBiome(List<BiomeEntry> biomes)
    {
    	Random rand = new Random();
    	int totalWeight = 0;
    	for (int i = 0; i < biomes.size(); i++)
    	{
    		totalWeight = totalWeight + biomes.get(i).itemWeight;
    	}
    	int num = rand.nextInt(totalWeight);
    	int sum = 0;
    	for (int i = 0; i < biomes.size(); i++)
    	{
    		sum = sum + biomes.get(1).itemWeight;
    		if (sum > num)
    		{
    			return biomes.get(i).biome.biomeID;
    		}
    	}
    	return pickBiome(biomes);
    }
}