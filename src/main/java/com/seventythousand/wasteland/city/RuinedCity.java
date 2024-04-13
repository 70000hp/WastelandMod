

package com.seventythousand.wasteland.city;

import com.seventythousand.wasteland.config.ModConfig;
import com.seventythousand.wasteland.items.LootStack;
import com.seventythousand.wasteland.utils.Message;
import com.seventythousand.wasteland.world.gen.WorldGenWastelandBigTree;
import com.seventythousand.wasteland.world.gen.WorldGenWastelandClay;
import com.seventythousand.wasteland.world.gen.WorldGenWastelandLake;
import com.seventythousand.wasteland.Wasteland;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class RuinedCity {
  CityBlockLayout layout;

  public RuinedCity(World world, MultiVector center, List<MultiVector> chunks, Random random) {
    this.layout = new CityBlockLayout(chunks, center, random, world);
  }

  public void generate(World world, Random random, List<SchematicBuilding> buildingSchematics, LootStack[] loot) {
    this.generateCityRoads(world, random);
    int cityColour = random.nextInt(16);
    int citySize = this.layout.block.size();

    for(int i = 0; i < citySize; ++i) {
      CityBlock generatingBlock = (CityBlock)this.layout.block.get(i);
      if (generatingBlock.doGenerate) {
        generatingBlock.generate(world, random, buildingSchematics, loot, cityColour);
      } else {
        boolean large = generatingBlock.area.length == 32 && generatingBlock.area.width == 32;
        boolean genLake = large ? random.nextInt(4) > 0 : random.nextInt(4) == 0;
        int trees = large ? random.nextInt(2) + 1 : 1;
        int w = 10;

        int totalTrees;
        int t;
        int x;
        int z;
        for(totalTrees = 0; totalTrees < 50 && genLake; ++totalTrees) {
          t = random.nextInt(2) == 0 ? generatingBlock.area.width - 8 : 8;
          x = random.nextInt(2) == 0 ? generatingBlock.area.length - 8 : 8;
          t = random.nextInt(w + 1) - w / 2 + t + generatingBlock.area.position.X;
          x = random.nextInt(w + 1) - w / 2 + x + generatingBlock.area.position.Z;
          z = CityGenerator.getWorldHeight(world, t, x);
          WorldGenWastelandLake lake = new WorldGenWastelandLake(random.nextInt(5) == 0 ? Blocks.water : ModConfig.getlakeLiquid());
          WorldGenWastelandClay clay = new WorldGenWastelandClay(3);
          genLake = !lake.generate(world, random, t, z, x);
          if (!genLake && random.nextInt(6) < 5) {
            clay.generate(world, random, t, z, x);
          }
        }

        totalTrees = 0;

        for(t = 0; t < 50 && totalTrees < trees; ++t) {
          x = random.nextInt(2) == 0 ? generatingBlock.area.width - 8 : 8;
          z = random.nextInt(2) == 0 ? generatingBlock.area.length - 8 : 8;
          x = random.nextInt(w + 1) - w / 2 + x + generatingBlock.area.position.X;
          z = random.nextInt(w + 1) - w / 2 + z + generatingBlock.area.position.Z;
          int y = CityGenerator.getWorldHeight(world, x, z);
          WorldGenWastelandBigTree tree = new WorldGenWastelandBigTree(true);
          totalTrees = tree.generate(world, random, x, y, z) ? totalTrees + 1 : totalTrees;
        }
      }

      if (i != citySize) {
        Wasteland.NETWORK.sendToAll(Message.createProgressMessage(i + 1, citySize + 1));
      }
    }
  }

  private void generateCityRoads(World world, Random r) {
    int roadWidth = 3;
    int hOffset = 0;
    for (int b = 0; b < this.layout.block.size(); b++) {
      CityBlock block = this.layout.block.get(b);
      int w = block.area.width / 16;
      int l = block.area.length / 16;
      int x = block.area.position.X;
      int z = block.area.position.Z;
      int i;
      for (i = 0; i < w; i++) {
        if (block.connectedFaces[i])
          for (int j = 0; j < roadWidth; j++) {
            int y1 = block.cornerHeight[i];
            int y2 = block.cornerHeight[i + 1];
            generateRoad(world, x + i * 16 - roadWidth, z + block.area.length - 1 - j, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, true, r);
          }
        if (block.connectedFaces[2 * w + l - i - 1])
          for (int j = 0; j < roadWidth; j++) {
            int y1 = block.cornerHeight[2 * w + l - i];
            int y2 = block.cornerHeight[2 * w + l - i - 1];
            generateRoad(world, x + i * 16 - roadWidth, z + j, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, true, r);
          }
      }
      for (i = 0; i < l; i++) {
        if (block.connectedFaces[w + l - i - 1])
          for (int j = 0; j < roadWidth; j++) {
            int y1 = block.cornerHeight[w + l - i];
            int y2 = block.cornerHeight[w + l - i - 1];
            generateRoad(world, x + block.area.width - 1 - j, z + i * 16 - roadWidth, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, false, r);
          }
        if (block.connectedFaces[2 * w + l + i])
          for (int j = 0; j < roadWidth; j++) {
            int y2, y1 = block.cornerHeight[2 * w + l + i];
            if (2 * w + l + i + 1 >= block.cornerHeight.length) {
              y2 = block.cornerHeight[0];
            } else {
              y2 = block.cornerHeight[2 * w + l + i + 1];
            }
            generateRoad(world, x + j, z + i * 16 - roadWidth, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, false, r);
          }
      }
    }
  }

  private void generateRoad(World world, int x, int z, int y1, int y2, int length, boolean dir, Random r) {
    int odds = 10;
    Block roadBlock = Blocks.stained_hardened_clay;
    Block surfaceBlock = world.getBiomeGenForCoords(x,z).topBlock;
    if (dir) {
      for (int i = 0; i < length; i++) {
        boolean f = (r.nextInt(odds) == 0);
        if (i < length / 2) {
          world.setBlock(x + i, y1, z, f ? surfaceBlock : roadBlock, f ? 0 : 15, 2);
          clearAbove(x + i, y1 + 1, z, 90, world);
          fillBelow(x + i, y1 - 1, z, 3, world);
        } else {
          world.setBlock(x + i, y2, z, f ? surfaceBlock : roadBlock, f ? 0 : 15, 2);
          clearAbove(x + i, y2 + 1, z, 90, world);
          fillBelow(x + i, y2 - 1, z, 3, world);
        }
        int var3 = world.getPrecipitationHeight(x + i, z);
        if (world.func_147478_e(x + i, var3, z, true))
          world.setBlock(x + i, var3, z, Blocks.snow_layer, 0, 2);
      }
    } else {
      for (int i = 0; i < length; i++) {
        boolean f = (r.nextInt(odds) == 0);
        if (i < length / 2) {
          world.setBlock(x, y1, z + i, f ? surfaceBlock : roadBlock, f ? 0 : 15, 2);
          clearAbove(x, y1 + 1, z + i, 90, world);
          fillBelow(x, y1 - 1, z + i, 3, world);
        } else {
          world.setBlock(x, y2, z + i, f ? surfaceBlock : roadBlock, f ? 0 : 15, 2);
          clearAbove(x, y2 + 1, z + i, 90, world);
          fillBelow(x, y2 - 1, z + i, 3, world);
        }
        int var3 = world.getPrecipitationHeight(x, z + i);
        if (world.func_147478_e(x, var3, z + i, true))
          world.setBlock(x, var3, z + i, Blocks.snow_layer, 0, 2);
      }
    }
  }

  public static void clearAbove(int x, int y, int z, int d, World world) {
    for (int i = 0; i < d; i++) {
      if (!world.getBlock(x, y + i, z).equals(Blocks.air))
        world.setBlock(x, y + i, z, Blocks.air, 0, 2);
    }
  }

  public static void fillBelow(int x, int y, int z, int d, World world) {
    for (int i = 0; i < d; i++) {
      Block b = world.getBlock(x, y - i, z);
      if (!b.hasTileEntity(0) && b != Blocks.bedrock)
        world.setBlock(x, y - i, z, Blocks.stonebrick, 0, 2);
    }
  }
}
