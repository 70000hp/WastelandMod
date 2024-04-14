

package com.seventythousand.wasteland.world.gen;

import com.seventythousand.wasteland.config.ModConfig;
import java.util.Random;

import com.seventythousand.wasteland.items.BlockRadFluid;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWastelandClay extends WorldGenerator {

  private int numberOfBlocks;


  public WorldGenWastelandClay(int p_i2011_1_) {
    this.numberOfBlocks = p_i2011_1_;
  }

  public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
    boolean generateClay = false;
    for (int i = 0; i < 10; i++) {
      if (p_76484_1_.getBlock(p_76484_3_, p_76484_4_ - i, p_76484_5_) instanceof BlockRadFluid)
        if (!(p_76484_1_.getBlock(p_76484_3_, p_76484_4_ - i - 1, p_76484_5_) instanceof BlockRadFluid)) {
          generateClay = true;
          p_76484_4_ -= i;
        }
    }
    if (!generateClay)
      return false;
    Block field_150546_a = (p_76484_2_.nextInt(ModConfig.clayRarity) == 0) ? Blocks.clay : (Block)Blocks.sand;
    int l = p_76484_2_.nextInt(this.numberOfBlocks - 2) + 2;
    byte b0 = 1;
    for (int i1 = p_76484_3_ - l; i1 <= p_76484_3_ + l; i1++) {
      for (int j1 = p_76484_5_ - l; j1 <= p_76484_5_ + l; j1++) {
        int k1 = i1 - p_76484_3_;
        int l1 = j1 - p_76484_5_;
        if (k1 * k1 + l1 * l1 <= l * l)
          for (int i2 = p_76484_4_ - b0; i2 <= p_76484_4_ + b0; i2++) {
            Block block = p_76484_1_.getBlock(i1, i2, j1);
            Block topBlock = p_76484_1_.getBlock(i1, i2 + 1, j1);
            if (block != Blocks.air && !(block instanceof BlockRadFluid) && topBlock != Blocks.air)
              p_76484_1_.setBlock(i1, i2, j1, field_150546_a, 0, 2);
          }
      }
    }
    return true;
  }
}
