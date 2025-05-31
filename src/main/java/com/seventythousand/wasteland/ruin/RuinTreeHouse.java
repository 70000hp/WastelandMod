

package com.seventythousand.wasteland.ruin;

import com.seventythousand.wasteland.config.ModConfig;
import com.seventythousand.wasteland.items.LootStack;
import cpw.mods.fml.common.IWorldGenerator;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class RuinTreeHouse extends Ruin implements IWorldGenerator {
  private RuinGenHelper genHelper = new RuinGenHelper();

  public RuinTreeHouse(String par1Name) {
    super(par1Name);
  }

  public boolean generate(World world, Random random, int x, int y, int z) {
    RuinGenHelper.setWorld(world);
    int woodMeta = random.nextInt(3);
    if (world.getBlock(x, y, z).equals(ModConfig.getSurfaceBlock())) {
        int yCoord = world.getHeightValue(x, z) - 1;
        int var1 = world.getHeightValue(x - 1, z - 1);
      RuinGenHelper.setBlock(x - 1, var1, z - 1, Blocks.planks, woodMeta);
      yCoord++;
      RuinGenHelper.setBlock(x, yCoord, z, Blocks.log, woodMeta);
      if (!world.getBlock(x, yCoord, z - 1).getMaterial().isSolid())
        RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.ladder, 2);
      yCoord++;
      RuinGenHelper.setBlock(x, yCoord, z, Blocks.log, woodMeta);
      yCoord++;
      RuinGenHelper.setBlock(x, yCoord, z, Blocks.log, woodMeta);
      yCoord++;
      RuinGenHelper.setBlock(x, yCoord, z, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x - 1, yCoord, z, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.ladder, 2);
      yCoord++;
      RuinGenHelper.setBlock(x + 0, yCoord, z + 0, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x + 0, yCoord, z - 1, Blocks.ladder, 2);
      RuinGenHelper.setBlock(x + 0, yCoord, z + 1, Blocks.planks, woodMeta);
      RuinGenHelper.setBlock(x + 0, yCoord, z + 2, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 2, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.planks, woodMeta);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.planks, woodMeta);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.planks, woodMeta);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 0, Blocks.log, woodMeta);
      yCoord++;
      RuinGenHelper.setBlock(x, yCoord, z, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 2, Blocks.log, woodMeta);
      handleLoot(world, random, x - 1, yCoord, z + 1);

      RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x - 2, yCoord, z - 2, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 1, Blocks.log, woodMeta);
      yCoord++;
      RuinGenHelper.setBlock(x, yCoord, z, Blocks.log, woodMeta);
      RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.log, 8 + woodMeta);
      return true;
    }
    return false;
  }
}
