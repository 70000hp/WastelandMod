

package com.seventythousand.wasteland.world.biome;

import com.seventythousand.wasteland.config.EntitySpawnConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class BiomeGenForest extends BiomeGenWastelandBase {
  boolean cold;

  public BiomeGenForest(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight, boolean mountainous) {
    super(par1ID, par2Name, par3BiomeHeight);
    this.treeSpawnRate /= 5;
    this.ruinSpawnRate /= 2;
    this.theBiomeDecorator.mushroomsPerChunk = 2;
    this.theBiomeDecorator.deadBushPerChunk = 2;
    this.treesPerChunk = 6;
    cold =  mountainous;
    wasteTerrain = !cold;
    setCreatureSpawns(EntitySpawnConfig.forestCreatures.get(0), this.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
    setCreatureSpawns(EntitySpawnConfig.forestCreatures.get(1), this.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
    setCreatureSpawns(EntitySpawnConfig.forestCreatures.get(2), this.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
  }
  public BiomeGenBase createMutation()
  {
    BiomeGenForest biomegenforest = new BiomeGenForest(this.biomeID + 128, "Mountainous Forest", new BiomeGenBase.Height(1.2F, 0.02F), true);
    biomegenforest.cold = true;
    biomegenforest.setColor(9286496);
    biomegenforest.setTemperatureRainfall(0.3F, 0.4F);
    biomegenforest.field_150609_ah = 14273354;
    return biomegenforest;
  }
  public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
  {
    if(cold) {
      this.topBlock = Blocks.dirt;
      this.field_150604_aj = 2;
      this.fillerBlock = Blocks.dirt;

      if ((p_150573_7_ < -1.0D || p_150573_7_ > 2.0D)) {
        this.topBlock = Blocks.snow_layer;
      } else if (p_150573_7_ > 1.0D) {
        this.topBlock = Blocks.stone;
        this.fillerBlock = Blocks.stone;
      }
    }
    this.genBiomeWastelandTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
  }
}
