

package com.seventythousand.wasteland.ruin;

import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.machine.storage.TileEntityCrateBase;
import com.seventythousand.wasteland.items.LootStack;
import com.seventythousand.wasteland.utils.Rectangle;
import com.seventythousand.wasteland.utils.Vector;
import cpw.mods.fml.common.IWorldGenerator;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RuinRuinedCiv1 extends Ruin implements IWorldGenerator {
  private RuinGenHelper genHelper = new RuinGenHelper();

  public RuinRuinedCiv1(String par1Name) {
    super(par1Name);
  }

  public boolean generate(World world, Random random, int x, int y, int z) {
    RuinGenHelper.setWorld(world);

    int floorMeta = 0;
    int wallMeta = 0;
    boolean flag = false;
    int j1 = 0;
    int k1 = 0;
    Rectangle pos = new Rectangle(new Vector(x, y, z), 6, 6);
    int[] lay = Layout.getLevels(world, pos);
    if (Layout.checkLevel(lay, 0)) {
      boolean flag1 = true;
      if (flag1) {
        Block wallBlock, floorBlock;
        int i2 = random.nextInt(4);
        if (i2 == 0) {
          wallBlock = ModBlocks.brick_concrete_cracked;
        } else if (i2 == 1) {
          wallBlock = ModBlocks.brick_concrete_mossy;
        } else if (i2 == 2) {
          wallBlock = ModBlocks.concrete_super;
          wallMeta = random.nextInt(4);
        } else {
          wallBlock = ModBlocks.lightstone;
          wallMeta = 2;

        }
        int j2 = random.nextInt(4);
        if (j2 == 0) {
          floorBlock = ModBlocks.tile_lab;
        } else if (j2 == 1) {
          floorBlock = ModBlocks.vinyl_tile;
        } else if (j2 == 2) {
          floorBlock = ModBlocks.brick_light;
        } else {
          floorBlock = ModBlocks.lightstone;
          floorMeta = 1;
        }
        boolean flag2 = false;
        if (!flag2) {
          for (int l2 = 1; l2 <= 3; l2++) {
            for (int j3 = 0; j3 < 49; j3++) {
              RuinGenHelper.setBlock(x + k1, y + l2, z + j1, Blocks.air);
              k1++;
              if (k1 == 7) {
                j1++;
                k1 = 0;
              }
            }
          }
          int i3 = 0;
          int k3 = 0;
          for (int l3 = 0; l3 < 49 && random.nextInt(6) != 0; l3++) {
            RuinGenHelper.setBlock(x + k3, y, z + i3, floorBlock, floorMeta);
            k3++;
            if (k3 == 7) {
              i3++;
              k3 = 0;
            }
          }
          int i4 = 0;
          int j4 = 0;
          if (random.nextBoolean()) {
            for (int l4 = 1; l4 < 4; l4++) {
              for (int l5 = 0; l5 < 25; l5++) {
                RuinGenHelper.setBlock(x + i4 + 1, y - l4, z + j4 + 1, Blocks.air);
                i4++;
                if (i4 == 5) {
                  j4++;
                  i4 = 0;
                }
              }
              j4 = 0;
              i4 = 0;
            }
            if (random.nextBoolean()) {
                for (int k7 = 0; k7 < 25; k7++) {
                    RuinGenHelper.setBlock(x + i4 + 1, y - 3, z + j4 + 1, Blocks.water);
                    i4++;
                    if (i4 == 5) {
                        j4++;
                        i4 = 0;
                    }
                }
            } else {
                int l7 = random.nextInt(24);
                int k8 = l7 / 5;
                int j9 = l7 % 5;
                RuinGenHelper.setBlock(x + k8, y - 3, z + j9, ModBlocks.crate_iron);
                TileEntityCrateBase chest = (TileEntityCrateBase) world.getTileEntity(x + k8, y - 3, z + j9);
                LootStack lootStack = setItems(random);
                LootStack.placeLoot(random, chest, LootStack.getLootItems(random, lootStack.items, lootStack.minNum, lootStack.maxNum, lootStack.repeat));
            }
          }
          k3 = 0;
          i3 = 0;
          for (int j5 = 0; j5 < 3; j5++) {
            int j6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < 28; i8++) {
              int l8 = random.nextInt(j5 + 1);
              if (j6 == 0) {
                Material material9 = world.getBlock(x, y + j5, z + i7).getMaterial();
                if (material9.isSolid() && l8 == 0)
                  if (j5 == 1) {
                    if (random.nextInt(2) == 0) {
                      RuinGenHelper.setBlock(x, y + 1 + j5, z + i7, Blocks.glass);
                    } else {
                      RuinGenHelper.setBlock(x, y + 1 + j5, z + i7, wallBlock, wallMeta);
                    }
                  } else {
                    RuinGenHelper.setBlock(x, y + 1 + j5, z + i7, wallBlock, wallMeta);
                  }
                i7++;
              }
              if (j6 == 1) {
                Material material10 = world.getBlock(x + 6, y + j5, z + i7).getMaterial();
                if (material10.isSolid() && l8 == 0)
                  if (j5 == 1) {
                    if (random.nextInt(2) == 0) {
                      RuinGenHelper.setBlock(x + 6, y + 1 + j5, z + i7, Blocks.glass);
                    } else {
                      RuinGenHelper.setBlock(x + 6, y + 1 + j5, z + i7, wallBlock);
                    }
                  } else {
                    RuinGenHelper.setBlock(x + 6, y + 1 + j5, z + i7, wallBlock);
                  }
                i7++;
              }
              if (j6 == 2) {
                Material material11 = world.getBlock(x + i7, y + j5, z).getMaterial();
                if (material11.isSolid() && l8 == 0)
                  if (j5 == 1) {
                    if (random.nextInt(2) == 0) {
                      RuinGenHelper.setBlock(x + i7, y + 1 + j5, z, Blocks.glass);
                    } else {
                      RuinGenHelper.setBlock(x + i7, y + 1 + j5, z, wallBlock);
                    }
                  } else {
                    RuinGenHelper.setBlock(x + i7, y + 1 + j5, z, wallBlock);
                  }
                i7++;
              }
              if (j6 == 3) {
                Material material12 = world.getBlock(x + i7, y + j5, z + 6).getMaterial();
                if (material12.isSolid() && l8 == 0)
                  if (j5 == 1) {
                    if (random.nextInt(2) == 0) {
                      RuinGenHelper.setBlock(x + i7, y + 1 + j5, z + 6, Blocks.glass);
                    } else {
                      RuinGenHelper.setBlock(x + i7, y + 1 + j5, z + 6, wallBlock);
                    }
                  } else {
                    RuinGenHelper.setBlock(x + i7, y + 1 + j5, z + 6, wallBlock);
                  }
                i7++;
              }
              if (i7 == 7) {
                j6++;
                i7 = 0;
              }
            }
          }
          int k5 = random.nextInt(4);
          int k6 = random.nextInt(3);
          int j7 = random.nextInt(2);
          if (k5 == 0) {
            RuinGenHelper.setBlock(x, y + 1, z + 2 + k6, Blocks.air);
            RuinGenHelper.setBlock(x, y + 2, z + 2 + k6, Blocks.air);
          }
          if (k5 == 1) {
            RuinGenHelper.setBlock(x + 6, y + 1, z + 2 + k6, Blocks.air);
            RuinGenHelper.setBlock(x + 6, y + 2, z + 2 + k6, Blocks.air);
          }
          if (k5 == 2) {
            RuinGenHelper.setBlock(x + 2 + k6, y + 1, z, Blocks.air);
            RuinGenHelper.setBlock(x + 2 + k6, y + 2, z, Blocks.air);
          }
          if (k5 == 3) {
            RuinGenHelper.setBlock(x + 2 + k6, y + 1, z + 6, Blocks.air);
            RuinGenHelper.setBlock(x + 2 + k6, y + 2, z + 6, Blocks.air);
          }
          int j8 = random.nextInt(3);
          int i9 = random.nextInt(5) + 1;
          int k9 = 0;
          int l9 = 0;
          if (j8 == 0) {
            k9 = x + i9;
            l9 = z + 1;
          }
          if (j8 == 1) {
            k9 = x + 1;
            l9 = z + i9;
          }
          if (j8 == 2) {
            k9 = x + 1;
            l9 = z + i9;
          }
          RuinGenHelper.setBlock(k9, y + 1, l9,  ModBlocks.crate_iron);
          TileEntityCrateBase chest1 = (TileEntityCrateBase)world.getTileEntity(k9, y + 1, l9);
          LootStack loot = setItems(random);
          LootStack.placeLoot(random, chest1, LootStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
          int i10 = random.nextInt(2);
          if (i10 == 0) {
            int j10 = 0;
            int k10 = 0;
            for (int l10 = 0; l10 < 12; l10++) {
              if (k10 == 0)
                RuinGenHelper.setBlock(x + 2, y + 1, z - 1 - j10, ModBlocks.fence_metal);
              if (k10 == 1)
                RuinGenHelper.setBlock(x + 3 + j10, y + 1, z - 4, ModBlocks.fence_metal);
              if (k10 == 2)
                RuinGenHelper.setBlock(x + 6, y + 1, z - 1 - j10, ModBlocks.fence_metal);
              j10++;
              if (j10 == 4) {
                k10++;
                j10 = 0;
              }
            }
            for (int i11 = 0; i11 < 20; i11++) {
              int l11 = i11 / 5;
              int j12 = i11 % 5;
              RuinGenHelper.setBlock(x + j12 + 2, y, z - l11 - 1, Blocks.dirt);
            }
            for (int j11 = 0; j11 < 9; j11++) {
              int i12 = j11 / 3;
              int k12 = j11 % 3;
              RuinGenHelper.setBlock(x + k12 + 3, y + 1, z - i12 - 1, Blocks.air);
            }
            int k11 = random.nextInt(2);
            if (k11 == 0) {
              Building.handleLoot(world, random, x + 3, y + 1, z - 3);
            }
          }
          return true;
        }
      }
    }
    return false;
  }

  private String pickMobSpawner(Random random) {
    int i = random.nextInt(4);
    if (i == 0)
      return "Skeleton";
    if (i == 1)
      return "Zombie";
    if (i == 2)
      return "Zombie";
    if (i == 3)
      return "Spider";
    return "";
  }
}
