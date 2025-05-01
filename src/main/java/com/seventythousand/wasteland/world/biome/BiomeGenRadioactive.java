

package com.seventythousand.wasteland.world.biome;

import com.hbm.blocks.ModBlocks;
import com.seventythousand.wasteland.config.EntitySpawnConfig;
import com.seventythousand.wasteland.config.ModConfig;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import java.util.Arrays;
import java.util.Random;

public class BiomeGenRadioactive extends BiomeGenWastelandBase {

    private byte[] field_150621_aC;
    private long field_150622_aD;
    private NoiseGeneratorPerlin field_150623_aE;
    private NoiseGeneratorPerlin field_150624_aF;
    private NoiseGeneratorPerlin field_150625_aG;

  public BiomeGenRadioactive(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight) {
    super(par1ID, par2Name, par3BiomeHeight);
    this.smallLakeSpawnRate = 1000;
    this.ruinSpawnRate /= 4;
    this.treesPerChunk = 6;
    this.treeSpawnRate /= 5;
    flowers.clear();
    addFlower(ModBlocks.mush, 0, 1000);
    setTopBlock(ModBlocks.sellafield_slaked);
    wFlowers.clear();
    wFlowers.put(ModBlocks.mush, 0);
    this.wasteTerrain = true;
    setCreatureSpawns(EntitySpawnConfig.cityCreatures.get(0), this.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
    setCreatureSpawns(EntitySpawnConfig.cityCreatures.get(1), this.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
    setCreatureSpawns(EntitySpawnConfig.cityCreatures.get(2), this.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
  }
    @Override @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) { return 0x424A42; }

    public BiomeGenBase.TempCategory getTempCategory()
    {
        return BiomeGenBase.TempCategory.OCEAN;
    }
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        if (this.field_150621_aC == null || this.field_150622_aD != p_150573_1_.getSeed()) {
            this.func_150619_a(p_150573_1_.getSeed());
        }

        if (this.field_150623_aE == null || this.field_150624_aF == null || this.field_150622_aD != p_150573_1_.getSeed()) {
            Random random1 = new Random(this.field_150622_aD);
            this.field_150623_aE = new NoiseGeneratorPerlin(random1, 6);
            this.field_150624_aF = new NoiseGeneratorPerlin(random1, 3);
        }

        this.field_150622_aD = p_150573_1_.getSeed();
        double d5 = 0.0D;
        int k;
        int l;
        k = (p_150573_5_ & -16) + (p_150573_6_ & 15);
        l = (p_150573_6_ & -16) + (p_150573_5_ & 15);
        double d1 = Math.min(Math.abs(p_150573_7_), this.field_150623_aE.func_151601_a((double) k * 0.25D, (double) l * 0.25D));

        if (d1 > 0.0D) {
            double d2 = 0.001953125D;
            double d3 = Math.abs(this.field_150624_aF.func_151601_a((double) k * d2, (double) l * d2));
            d5 = d1 * d1 * 2.5D;
            double d4 = Math.ceil(d3 * 50.0D) + 14.0D;

            if (d5 > d4) {
                d5 = d4;
            }

            d5 += 64.0D;
        }

        k = p_150573_5_ & 15;
        l = p_150573_6_ & 15;

        Block block = ModBlocks.sellafield_slaked;
        Block block2 = this.fillerBlock;
        int i1 = (int) (p_150573_7_ / 3.0D + 3.0D + p_150573_2_.nextDouble() * 0.2D);
        boolean flag1 = Math.cos(p_150573_7_ / 3.0D * Math.PI) > 0.0D;
        int j1 = -1;
        boolean flag2 = false;
        int k1 = p_150573_3_.length / 256;

        for (int l1 = 255; l1 >= 0; --l1) {
            int i2 = (l * 16 + k) * k1 + l1;

            if ((p_150573_3_[i2] == null || p_150573_3_[i2].getMaterial() == Material.air) && l1 < (int) d5) {
                p_150573_3_[i2] = Blocks.stone;
            }

            if (l1 <= p_150573_2_.nextInt(5)) {
                p_150573_3_[i2] = Blocks.bedrock;
            } else {
                Block block1 = p_150573_3_[i2];

                if (block1 != null && block1.getMaterial() != Material.air) {
                    if (block1 == Blocks.stone) {
                        byte b0;

                        if (j1 == -1) {
                            flag2 = false;

                            if (i1 <= 0) {
                                block = null;
                                block2 = Blocks.stone;
                            } else if (l1 >= 59 && l1 <= 64) {
                                block = ModBlocks.sellafield_slaked;
                                block2 = this.fillerBlock;
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air)) {
                                block = ModConfig.getlakeLiquid();
                            }

                            j1 = i1 + Math.max(0, l1 - 63);

                            if (l1 >= 62) {
                                if (l1 > 66 + i1) {
                                    b0 = 0;

                                    if (l1 >= 64 && l1 <= 127) {
                                        if (!flag1) {
                                            b0 = this.func_150618_d(p_150573_5_, l1, p_150573_6_);
                                        }
                                    } else {
                                        b0 = 1;
                                    }

                                    p_150573_3_[i2] = ModBlocks.sellafield_slaked;
                                    if (b0 < 4) {
                                        p_150573_4_[i2] = (byte) b0;
                                    }
                                } else {
                                    p_150573_3_[i2] = this.topBlock;
                                    p_150573_4_[i2] = (byte) this.field_150604_aj;
                                    flag2 = true;
                                }
                            } else {
                                p_150573_3_[i2] = block2;

                                if (block2 == ModBlocks.sellafield_slaked) {
                                    p_150573_4_[i2] = 2;
                                }
                            }
                        } else if (j1 > 0) {
                            --j1;

                            if (flag2) {
                                p_150573_3_[i2] = ModBlocks.sellafield_slaked;
                                p_150573_4_[i2] = 3;
                            } else {
                                b0 = this.func_150618_d(p_150573_5_, l1, p_150573_6_);

                                p_150573_3_[i2] = ModBlocks.sellafield_slaked;
                                if (b0 < 4) {
                                    p_150573_4_[i2] = b0;
                                }
                            }
                        }
                    }
                } else {
                    j1 = -1;
                }
            }
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

    public void func_150619_a(long p_150619_1_) {
        this.field_150621_aC = new byte[64];
        Arrays.fill(this.field_150621_aC, (byte) 0);
        Random random = new Random(p_150619_1_);
        this.field_150625_aG = new NoiseGeneratorPerlin(random, 1);
        int j;

        for (j = 0; j < 64; ++j) {
            j += random.nextInt(5) + 1;

            if (j < 64) {
                this.field_150621_aC[j] = 1;
            }
        }

        j = random.nextInt(4) + 2;
        int k;
        int l;
        int i1;
        int j1;

        for (k = 0; k < j; ++k) {
            l = random.nextInt(3) + 1;
            i1 = random.nextInt(64);

            for (j1 = 0; i1 + j1 < 64 && j1 < l; ++j1) {
                this.field_150621_aC[i1 + j1] = 4;
            }
        }

        k = random.nextInt(4) + 2;
        int k1;

        for (l = 0; l < k; ++l) {
            i1 = random.nextInt(3) + 2;
            j1 = random.nextInt(64);

            for (k1 = 0; j1 + k1 < 64 && k1 < i1; ++k1) {
                this.field_150621_aC[j1 + k1] = 3;
            }
        }

        l = random.nextInt(4) + 2;

        for (i1 = 0; i1 < l; ++i1) {
            j1 = random.nextInt(3) + 1;
            k1 = random.nextInt(64);

            for (int l1 = 0; k1 + l1 < 64 && l1 < j1; ++l1) {
                this.field_150621_aC[k1 + l1] = 2;
            }
        }

        i1 = random.nextInt(3) + 3;
        j1 = 0;

        for (k1 = 0; k1 < i1; ++k1) {
            byte b0 = 1;
            j1 += random.nextInt(16) + 4;

            for (int i2 = 0; j1 + i2 < 64 && i2 < b0; ++i2) {
                this.field_150621_aC[j1 + i2] = 0;

                if (j1 + i2 > 1 && random.nextBoolean()) {
                    this.field_150621_aC[j1 + i2 - 1] = 1;
                }

                if (j1 + i2 < 63 && random.nextBoolean()) {
                    this.field_150621_aC[j1 + i2 + 1] = 1;
                }
            }
        }
    }
    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
        int k = 6 + p_76728_2_.nextInt(12);
        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l)
        {
            i1 = p_76728_3_ + p_76728_2_.nextInt(16);
            j1 = p_76728_2_.nextInt(68) + 40;
            int k1 = p_76728_4_ + p_76728_2_.nextInt(16);

            if (p_76728_1_.getBlock(i1, j1, k1).isReplaceableOreGen(p_76728_1_, i1, j1, k1, ModBlocks.sellafield_slaked))
            {
                if(k < 8)
                    p_76728_1_.setBlock(i1, j1, k1, ModBlocks.ore_sellafield_diamond, 0, 2);
                else
                    p_76728_1_.setBlock(i1, j1, k1, ModBlocks.ore_sellafield_emerald, 0, 2);
            }
        }
    }
    public byte func_150618_d(int p_150618_1_, int p_150618_2_, int p_150618_3_) {
        int l = (int) Math.round(this.field_150625_aG.func_151601_a((double) p_150618_1_ / 512.0D, (double) p_150618_3_ / 512.0D) * 4.0D);
        return this.field_150621_aC[(p_150618_2_ + l + 64) % 64];
    }
}

