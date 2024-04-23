

package com.seventythousand.wasteland.world.biome;

import com.hbm.blocks.ModBlocks;
import com.seventythousand.wasteland.config.EntitySpawnConfig;
import com.seventythousand.wasteland.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class BiomeGenRadioactive extends BiomeGenWastelandBase {
  public BiomeGenRadioactive(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight) {
    super(par1ID, par2Name, par3BiomeHeight);
    this.smallLakeSpawnRate = 1000;
    this.ruinSpawnRate /= 4;
    this.treesPerChunk = 6;
    this.treeSpawnRate /= 5;
    flowers.clear();
    addFlower(ModBlocks.mush, 0, 1000);
    setTopBlock(ModBlocks.sellafield);
    wFlowers.clear();
    wFlowers.put(ModBlocks.mush, 0);
    this.wasteTerrain = true;
    setCreatureSpawns(EntitySpawnConfig.cityCreatures.get(0), this.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
    setCreatureSpawns(EntitySpawnConfig.cityCreatures.get(1), this.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
    setCreatureSpawns(EntitySpawnConfig.cityCreatures.get(2), this.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
  }
    public BiomeGenBase.TempCategory getTempCategory()
    {
        return BiomeGenBase.TempCategory.OCEAN;
    }
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.topBlock = ModBlocks.waste_mycelium;
        this.field_150604_aj = 0;
        this.fillerBlock = Blocks.dirt;

        if ((p_150573_7_ < 1.5D || p_150573_7_ > 2.0D)) {
            this.topBlock = ModBlocks.sellafield_slaked;
            this.fillerBlock = ModBlocks.sellafield_slaked;
        }
        this.genBiomeWastelandTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }
    @Override
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
                            else if (l1 >= 59 && l1 <= 70) {
                                block = this.topBlock;
                            }

                            if (l1 < 63)
                            {
                                block = ModConfig.getlakeLiquid();
                                b0 = 0;
                            }

                            k = l;

                            if (l1 >= 56)
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
}

