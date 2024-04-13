

package com.seventythousand.wasteland.world.biome;

import com.seventythousand.wasteland.config.EntitySpawnConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class BiomeGenMountains extends BiomeGenWastelandBase {
  public BiomeGenMountains(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight) {
    super(par1ID, par2Name, par3BiomeHeight);
    treeSpawnRate /= 2;
    temples = true;
    smallLakeSpawnRate *= 3;
    wasteTerrain = true;
    setCreatureSpawns(EntitySpawnConfig.mountainsCreatures.get(0), this.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
    setCreatureSpawns(EntitySpawnConfig.mountainsCreatures.get(1), this.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
    setCreatureSpawns(EntitySpawnConfig.mountainsCreatures.get(2), this.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
  }
  public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
  {
    super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    int k = 3 + p_76728_2_.nextInt(6);
    int l;
    int i1;
    int j1;

    for (l = 0; l < k; ++l)
    {
      i1 = p_76728_3_ + p_76728_2_.nextInt(16);
      j1 = p_76728_2_.nextInt(28) + 4;
      int k1 = p_76728_4_ + p_76728_2_.nextInt(16);

      if (p_76728_1_.getBlock(i1, j1, k1).isReplaceableOreGen(p_76728_1_, i1, j1, k1, Blocks.stone))
      {
        p_76728_1_.setBlock(i1, j1, k1, Blocks.emerald_ore, 0, 2);
      }
    }
  }
  public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
  {
    this.topBlock = Blocks.dirt;
    this.field_150604_aj = 0;
    this.fillerBlock = Blocks.dirt;

    if ((p_150573_7_ < -1.0D || p_150573_7_ > 2.0D))
    {
      this.topBlock = Blocks.gravel;
      this.fillerBlock = Blocks.gravel;
    }
    else if (p_150573_7_ > 1.0D)
    {
      this.topBlock = Blocks.stone;
      this.fillerBlock = Blocks.stone;
    }

    this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
  }
}
