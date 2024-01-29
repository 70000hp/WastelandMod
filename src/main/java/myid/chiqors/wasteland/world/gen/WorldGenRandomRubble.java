

package myid.chiqors.wasteland.world.gen;

import myid.chiqors.wasteland.config.ModConfig;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRandomRubble extends WorldGenerator {
  public WorldGenRandomRubble() {
    super(true);
  }
  
  public boolean generate(World world, Random random, int x, int y, int z) {
    byte byte0 = 3;
    int l = random.nextInt(2) + 2;
    int i1 = random.nextInt(2) + 2;
    boolean flag = false;
    boolean flag1 = false;
    boolean flag2 = false;
    byte byte1 = 75;
    Material material = world.getBlock(x, y + 1, z).getMaterial();
    Material material1 = world.getBlock(x, y, z).getMaterial();
    Material material2 = world.getBlock(x + 1, y, z).getMaterial();
    Material material3 = world.getBlock(x - 1, y, z).getMaterial();
    Material material4 = world.getBlock(x, y, z + 1).getMaterial();
    Material material5 = world.getBlock(x, y, z - 1).getMaterial();
    if (world.getBlock(x, y - 1, z).equals(ModConfig.getSurfaceBlock()) && !material.isSolid() && !material1.isSolid() && !material4.isSolid() && !material2.isSolid() && !material3.isSolid() && !material5.isSolid() && world.getBlock(x, y, z) == Blocks.air && world.getBlock(x, y + 1, z) == Blocks.air) {
      for (int j1 = 0; j1 < byte1; j1++) {
        int k1 = x + random.nextInt(8);
        int l1 = y - 1 + random.nextInt(4);
        int i2 = z + random.nextInt(8);
        Material material6 = world.getBlock(k1, l1 - 1, i2).getMaterial();
        if (world.getBlock(k1, l1, i2) == Blocks.air && material6.isSolid()) {
          Block j2 = Blocks.cobblestone;
          int k2 = random.nextInt(31);
          if (k2 < 10) {
            j2 = Blocks.cobblestone;
          } else if (k2 >= 10 && k2 < 20) {
            j2 = Blocks.mossy_cobblestone;
          } else if (k2 >= 20 && k2 < 30) {
            j2 = Blocks.planks;
          } else {
            j2 = Blocks.brick_block;
          } 
          world.setBlock(k1, l1, i2, j2);
        } 
      } 
      return true;
    } 
    return false;
  }
}
