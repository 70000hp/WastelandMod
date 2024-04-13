

package com.seventythousand.wasteland.world.biome;

import com.seventythousand.wasteland.config.EntitySpawnConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BiomeGenWastelandDesert extends BiomeGenWastelandBase {

  public BiomeGenWastelandDesert(int par1ID, String par2Name, Height par3BiomeHeight) {
    super(par1ID, par2Name, par3BiomeHeight);
    this.theBiomeDecorator.deadBushPerChunk = 10;
    this.theBiomeDecorator.cactiPerChunk = 5;
    wasteTerrain = false;
    setCreatureSpawns(EntitySpawnConfig.wastelandCreatures.get(0), this.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
    setCreatureSpawns(EntitySpawnConfig.wastelandCreatures.get(1), this.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
    setCreatureSpawns(EntitySpawnConfig.wastelandCreatures.get(2), this.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
  }
  public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
  {
    this.topBlock = Blocks.sand;
    this.field_150604_aj = 0;
    this.fillerBlock = Blocks.sandstone;

    if ((p_150573_7_ < -1.0D || p_150573_7_ > 2.0D)) {
      this.field_150604_aj = 1;
      this.fillerBlock = Blocks.hardened_clay;
    }
    this.genBiomeWastelandTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
  }
}
