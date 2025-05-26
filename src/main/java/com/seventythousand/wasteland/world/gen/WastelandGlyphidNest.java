package com.seventythousand.wasteland.world.gen;

import com.hbm.blocks.ModBlocks;
import com.hbm.util.LootGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.World;

import java.util.Random;

public class WastelandGlyphidNest {
    public static final int[][][] schematicSmall = new int[][][] {
        {
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
        },
        {
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,1,1,1,1,1,1,1,0,0},
            {0,1,1,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,1,1,0},
            {0,0,1,1,1,1,1,1,1,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
        },
        {
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,1,1,1,1,1,1,1,0,0},
            {0,0,1,1,2,2,2,1,1,1,0},
            {1,1,1,2,2,2,2,2,1,1,1},
            {1,1,1,2,2,2,2,2,1,1,1},
            {1,1,1,2,2,2,2,2,1,1,1},
            {0,1,1,1,2,2,2,1,1,1,0},
            {0,0,1,1,1,1,1,1,1,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
        },
        {
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,1,1,2,2,2,1,1,0,0},
            {0,0,1,2,2,3,2,2,1,1,0},
            {1,1,2,3,3,3,3,3,2,1,1},
            {1,1,2,3,3,3,3,3,2,1,1},
            {1,1,2,3,3,3,3,3,2,1,1},
            {0,1,1,2,2,3,2,2,1,1,0},
            {0,0,1,1,2,2,2,1,1,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
        },
        {
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,1,1,1,2,1,1,1,0,0},
            {0,1,1,1,2,2,2,1,1,1,0},
            {1,1,1,2,2,2,2,2,1,1,1},
            {1,1,2,2,2,2,2,2,2,1,1},
            {1,1,1,2,2,2,2,2,1,1,1},
            {0,1,1,2,2,2,2,2,1,1,0},
            {0,0,1,1,2,2,2,1,1,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
        },
        {
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,1,1,1,2,1,1,1,0,0},
            {0,1,1,1,2,2,2,1,1,1,0},
            {1,1,1,2,2,2,2,2,1,1,1},
            {1,1,2,2,2,2,2,2,2,1,1},
            {1,1,1,2,2,2,2,2,1,1,1},
            {0,1,1,2,2,2,2,2,1,1,0},
            {0,0,1,1,2,2,2,1,1,0,0},
            {0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
        }
    };
    public static void generateSmall(World world, int x, int y, int z, Random rand, int meta, boolean loot) {

        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 6; j++) {
                for(int k = 0; k < 11; k++) {

                    int block = schematicSmall[5 - j][i][k];
                    int iX = x + i - 5;
                    int iY = y + j - 1;
                    int iZ = z + k - 5;
                    int basaltOre = rand.nextInt(15);
                    switch(block) {
                        case 1:
                            if(basaltOre < 12) world.setBlock(iX, iY, iZ, basaltOre < 5 ? ModBlocks.ore_basalt : ModBlocks.basalt, basaltOre != 3 ? basaltOre : 0, 2);
                            else world.setBlock(iX, iY, iZ, rand.nextInt(12) == 0 ? ModBlocks.glyphid_spawner : ModBlocks.glyphid_base, meta, 2);
                            break;
                        case 2: world.setBlock(iX, iY, iZ, rand.nextInt(12) == 0 ? ModBlocks.glyphid_spawner : ModBlocks.glyphid_base, meta, 2); break;
                        case 3:
                            int r = rand.nextInt(4);
                            if(r == 0) {
                                world.setBlock(iX, iY, iZ, Blocks.skull, 1, 3);
                                TileEntitySkull skull = (TileEntitySkull) world.getTileEntity(iX, iY, iZ);
                                if(skull != null) skull.func_145903_a(rand.nextInt(16));
                            } else if(r == 1) {
                                world.setBlock(iX, iY, z + k - 5, ModBlocks.deco_loot, 0, 2);
                                LootGenerator.lootBones(world, iX, iY, iZ);
                            } else if(r == 2) {
                                if(loot) {
                                    world.setBlock(iX, iY, iZ, ModBlocks.deco_loot, 0, 2);
                                    LootGenerator.lootGlyphidHive(world, iX, iY, iZ);
                                } else {
                                    world.setBlock(iX, iY, iZ, ModBlocks.glyphid_base, meta, 2);
                                }
                            }
                            break;
                    }
                }
            }
        }
    }
}
