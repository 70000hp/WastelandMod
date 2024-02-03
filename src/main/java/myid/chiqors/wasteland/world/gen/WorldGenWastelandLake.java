

package myid.chiqors.wasteland.world.gen;

import myid.chiqors.wasteland.config.ModConfig;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWastelandLake extends WorldGenerator {
  private Block field_150556_a;
  
  private static final String __OBFID = "CL_00000418";
  
  public WorldGenWastelandLake(Block p_i45455_1_) {
    this.field_150556_a = p_i45455_1_;
  }
  
  public boolean generate(World p_generate_1_, Random p_generate_2_, int p_generate_3_, int p_generate_4_, int p_generate_5_) {
    p_generate_3_ -= 8;

    if (p_generate_4_ <= 4) {
      return false;
    } else {
      p_generate_4_ -= 4;
      boolean[] var6 = new boolean[2048];
      int var7 = p_generate_2_.nextInt(40) + 40;

      int var8;
      for (var8 = 0; var8 < var7; ++var8) {
        double var9 = p_generate_2_.nextDouble() * 6.0 + 3.0;
        double var11 = p_generate_2_.nextDouble() * 4.0 + 2.0;
        double var13 = p_generate_2_.nextDouble() * 6.0 + 3.0;
        double var15 = p_generate_2_.nextDouble() * (16.0 - var9 - 2.0) + 1.0 + var9 / 2.0;
        double var17 = p_generate_2_.nextDouble() * (8.0 - var11 - 4.0) + 2.0 + var11 / 2.0;
        double var19 = p_generate_2_.nextDouble() * (16.0 - var13 - 2.0) + 1.0 + var13 / 2.0;

        for (int var21 = 1; var21 < 15; ++var21) {
          for (int var22 = 1; var22 < 15; ++var22) {
            for (int var23 = 1; var23 < 7; ++var23) {
              double var24 = ((double) var21 - var15) / (var9 / 2.0);
              double var26 = ((double) var23 - var17) / (var11 / 2.0);
              double var28 = ((double) var22 - var19) / (var13 / 2.0);
              double var30 = var24 * var24 + var26 * var26 + var28 * var28;
              if (var30 < 1.0) {
                var6[(var21 * 16 + var22) * 8 + var23] = true;
              }
            }
          }
        }
      }

      int var10;
      int var32;
      boolean var33;
      for (var8 = 0; var8 < 16; ++var8) {
        for (var32 = 0; var32 < 16; ++var32) {
          for (var10 = 0; var10 < 8; ++var10) {
            var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);
            if (var33) {
              Material var12 = p_generate_1_.getBlock(p_generate_3_ + var8, p_generate_4_ + var10, p_generate_5_ + var32).getMaterial();
              if (var10 >= 4 && var12.isLiquid()) {
                return false;
              }

              if (var10 < 4 && !var12.isSolid() && p_generate_1_.getBlock(p_generate_3_ + var8, p_generate_4_ + var10, p_generate_5_ + var32) != this.field_150556_a) {
                return false;
              }
            }
          }
        }
      }

      for (var8 = 0; var8 < 16; ++var8) {
        for (var32 = 0; var32 < 16; ++var32) {
          for (var10 = 0; var10 < 8; ++var10) {
            if (var6[(var8 * 16 + var32) * 8 + var10]) {
              p_generate_1_.setBlock(p_generate_3_ + var8, p_generate_4_ + var10, p_generate_5_ + var32, var10 >= 4 ? Blocks.air : this.field_150556_a, 0, 2);
            }
          }
        }
      }

      if (this.field_150556_a.getMaterial() == Material.lava) {
        for (var8 = 0; var8 < 16; ++var8) {
          for (var32 = 0; var32 < 16; ++var32) {
            for (var10 = 0; var10 < 8; ++var10) {
              var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);
              if (var33 && (var10 < 4 || p_generate_2_.nextInt(2) != 0) && p_generate_1_.getBlock(p_generate_3_ + var8, p_generate_4_ + var10, p_generate_5_ + var32).getMaterial().isSolid()) {
                p_generate_1_.setBlock(p_generate_3_ + var8, p_generate_4_ + var10, p_generate_5_ + var32, Blocks.stone, 0, 2);
              }
            }
          }
        }
      }

      if (this.field_150556_a.getMaterial() == Material.water) {
        for (var8 = 0; var8 < 16; ++var8) {
          for (var32 = 0; var32 < 16; ++var32) {
            byte var35 = 4;
            if (p_generate_1_.isBlockFreezable(p_generate_3_ + var8, p_generate_4_ + var35, p_generate_5_ + var32)) {
              p_generate_1_.setBlock(p_generate_3_ + var8, p_generate_4_ + var35, p_generate_5_ + var32, Blocks.ice, 0, 2);
            }
          }
        }
      }
      return true;
    }
  }
}

