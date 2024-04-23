package com.seventythousand.wasteland.world.gen;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockMush;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class WorldGenWastelandTrees extends WorldGenAbstractTree
{
    private Block log;
    private final int minTreeHeight;
    private int metaWood;
    public boolean leaves;


    public WorldGenWastelandTrees(boolean p_i2027_1_)
    {
        this(p_i2027_1_, 4, 0);
    }

    public WorldGenWastelandTrees(boolean p_i2028_1_, int p_i2028_2_, int p_i2028_3_)
    {
        super(p_i2028_1_);
        this.minTreeHeight = p_i2028_2_;
        this.metaWood = p_i2028_3_;
        log = Blocks.log2;
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        int l = rand.nextInt(3) + this.minTreeHeight;
        boolean flag = true;
        leaves = rand.nextInt(20) == 0;
        if (y >= 1 && y + l + 1 <= 256) {
            byte b0;
            int k1;
            Block block;

            for (int i1 = y; i1 <= y + 1 + l; ++i1) {
                b0 = 1;

                if (i1 == y) {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2) {
                    b0 = 2;
                }

                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {

                            if (!this.isReplaceable(world, j1, i1, k1)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                Block block2 = world.getBlock(x, y - 1, z);

                boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling) Blocks.sapling)
                    || (log == ModBlocks.waste_log  &&  block2 != ModBlocks.waste_log && block2 != ModBlocks.mush_block);
                if (isSoil && y < 256 - l - 1) {
                    block2.onPlantGrow(world, x, y - 1, z, x, y, z);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;
                    if (leaves && !(log == ModBlocks.waste_log)) {
                        for (k1 = y - b0 + l; k1 <= y + l; ++k1) {
                            i3 = k1 - (y + l);
                            l1 = b1 + 1 - i3 / 2;

                            for (i2 = x - l1; i2 <= x + l1; ++i2) {
                                j2 = i2 - x;

                                for (int k2 = z - l1; k2 <= z + l1; ++k2) {
                                    int l2 = k2 - z;

                                    if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0 && i3 != 0) {
                                        Block block1 = world.getBlock(i2, k1, k2);

                                        if (block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2)) {
                                            this.setBlockAndNotifyAdequately(world, i2, k1, k2, Blocks.leaves, metaWood);
                                        }
                                    }
                                }
                            }
                        }

                    }
                    if (block2 != ModBlocks.waste_mycelium)
                        for (k1 = 0;k1 < l; ++k1) {
                            block = world.getBlock(x, y + k1, z);

                            if (block.isAir(world, x, y + k1, z) || block.isLeaves(world, x, y + k1, z)) {
                                this.setBlockAndNotifyAdequately(world, x, y + k1, z, log, this.metaWood);
                            }
                        }
                    else {
                        BlockMush mushroom = (BlockMush) ModBlocks.mush;
                        this.setBlockAndNotifyAdequately(world, x, y, z, mushroom, this.metaWood);
                        mushroom.growHuge(world, x, y, z, rand);
                    }

                    return true;
                }

            }

        }
        return false;
    }
    public void setTreeType(Block block, int meta) {
        this.metaWood = meta;
        this.log = block;
    }

}
