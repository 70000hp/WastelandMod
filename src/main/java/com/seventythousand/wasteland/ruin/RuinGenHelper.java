

package com.seventythousand.wasteland.ruin;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class RuinGenHelper {
  protected static World worldObj;

  public static void setCube(int baseX, int baseY, int baseZ, int lengthX, int lengthY, int lengthZ, Block block) {
    for (int y = 0; y < lengthY; y++) {
      for (int z = 0; z < lengthX; z++) {
        for (int x = 0; x < lengthZ; x++)
          setBlock(x + baseX, y + baseY, z + baseZ, block);
      }
    }
  }

  public static void setBlock(int x, int y, int z, Block block) {
    setBlock(x, y, z, block, 0);
  }

  public static void setBlock(int x, int y, int z, Block block, int meta) {
    worldObj.setBlock(x, y, z, block, meta, 2);
  }

  public static void setWorld(World world) {
    worldObj = world;
  }
}
