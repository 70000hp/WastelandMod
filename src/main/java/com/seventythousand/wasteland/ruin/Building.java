

package com.seventythousand.wasteland.ruin;

import com.hbm.blocks.ModBlocks;
import com.hbm.itempool.ItemPool;
import com.hbm.tileentity.machine.storage.TileEntityCrateIron;
import com.hbm.tileentity.machine.storage.TileEntityCrateSteel;
import com.hbm.tileentity.machine.storage.TileEntitySafe;
import com.seventythousand.wasteland.config.CityLootConfig;
import com.seventythousand.wasteland.config.ModConfig;
import com.seventythousand.wasteland.config.RuinConfig;
import com.seventythousand.wasteland.items.LootStack;
import com.seventythousand.wasteland.ruin.code.BuildingCode;
import com.seventythousand.wasteland.utils.Sphere;
import com.seventythousand.wasteland.utils.Vector;
import com.seventythousand.wasteland.world.WorldChunkManagerWasteland;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import org.jnbt.Tag;

public class Building {
  public int width;

  public int height;

  public int length;

  public String name;
    private Vector[] damageLoc;

    private int[] damageSize;
  public boolean duplicate;

  private byte[] blocks;

  private byte[] data;

  private RuinGenHelper genHelper = new RuinGenHelper();

  public static LootStack easyLoot;

  public static LootStack midLoot;

  public static LootStack hardLoot;

  public Building(String name, int w, int h, int l, byte[] b, byte[] d, boolean multiple) {
    this.width = w;
    this.height = h;
    this.length = l;
    this.blocks = (byte[])b.clone();
    this.data = d;
    this.name = name;
    this.duplicate = multiple;
    easyLoot = new LootStack(RuinConfig.getLoot(RuinConfig.easyLoot), RuinConfig.easyLootMax, RuinConfig.easyLootMin, RuinConfig.easyLootRepeat);
    midLoot = new LootStack(RuinConfig.getLoot(RuinConfig.midLoot), RuinConfig.midLootMax, RuinConfig.midLootMin, RuinConfig.midLootRepeat);
    hardLoot = new LootStack(RuinConfig.getLoot(RuinConfig.hardLoot), RuinConfig.hardLootMax, RuinConfig.hardLootMin, RuinConfig.hardLootRepeat);
  }

  public static Building create(int building) {
    switch (building) {
      case 0:
      case 1:
        return new Building("church", 9, 12, 5, BuildingCode.Church.BLOCKS, BuildingCode.Church.DATA, false);
      case 2:
        return new Building("diner", 10, 11, 16, BuildingCode.Diner.BLOCKS, BuildingCode.Diner.DATA, true);
      case 3:
        return new Building("stand", 5, 5, 3, BuildingCode.GroceryStand.BLOCKS, BuildingCode.GroceryStand.DATA, true);
      case 4:
        return new Building("hospital", 24, 13, 17, BuildingCode.Hospital.BLOCKS, BuildingCode.Hospital.DATA, false);
      case 5:
        return new Building("largeFarm", 14, 10, 27, BuildingCode.LargeFarm.BLOCKS, BuildingCode.LargeFarm.DATA, false);
      case 6:
        return new Building("largeHouse1", 11, 9, 9, BuildingCode.LargeHouse1.BLOCKS, BuildingCode.LargeHouse1.DATA, true);
      case 7:
        return new Building("largeHouse2", 12, 12, 11, BuildingCode.LargeHouse2.BLOCKS, BuildingCode.LargeHouse2.DATA, true);
      case 8:
        return new Building("library", 14, 10, 14, BuildingCode.Library.BLOCKS, BuildingCode.Library.DATA, false);
      case 9:
        return new Building("midHouse1", 9, 7, 12, BuildingCode.MidHouse1.BLOCKS, BuildingCode.MidHouse1.DATA, true);
      case 10:
        return new Building("midHouse2", 9, 9, 8, BuildingCode.MidHouse2.BLOCKS, BuildingCode.MidHouse2.DATA, true);
      case 11:
        return new Building("smallFarm", 12, 7, 9, BuildingCode.SmallFarm.BLOCKS, BuildingCode.SmallFarm.DATA, true);
      case 12:
        return new Building("smallHouse1", 5, 5, 4, BuildingCode.SmallHouse1.BLOCKS, BuildingCode.SmallHouse1.DATA, true);
      case 13:
        return new Building("smallHouse2", 5, 6, 5, BuildingCode.SmallHouse2.BLOCKS, BuildingCode.SmallHouse2.DATA, true);
      case 14:
        return new Building("well", 4, 5, 4, BuildingCode.Well.BLOCKS, BuildingCode.Well.DATA, false);
    }
    return null;
  }

  public boolean generate(World world, Random random, Vector pos, int rot) {
    int maxSize, minSize, numHoles;
    Block top = world.getWorldChunkManager() instanceof WorldChunkManagerWasteland ?  world.getBiomeGenForCoords(pos.X, pos.Z).topBlock : Blocks.grass;
    RuinGenHelper.setWorld(world);
    byte[] blockArray = blocks.clone();
      int damageNodes = height / 15;
      damageNodes = (damageNodes > 0) ? (random.nextInt(damageNodes) + 1) : 1;
      int damageMaxRad = (width + length) / 16;
      int damageMinRad = (width + length) / 24;
    if (this.name.equals((create(14)).name)) {
      int waterHeight = random.nextInt(7) + 10;
      for (int i = 0; i < waterHeight; i++) {
        world.setBlock(pos.X + 1, pos.Y - 1 - i, pos.Z + 1, Blocks.water);
        world.setBlock(pos.X + 2, pos.Y - 1 - i, pos.Z + 1, Blocks.water);
        world.setBlock(pos.X + 2, pos.Y - 1 - i, pos.Z + 2, Blocks.water);
        world.setBlock(pos.X + 1, pos.Y - 1 - i, pos.Z + 2, Blocks.water);
      }
    } else {
      ruinBuilding(damageNodes, damageMaxRad, damageMinRad, random);
    }
    int count = 0;
    short j;
    boolean doGen = true;
    Vector p = new Vector(0, 0, 0);
    for (j = 0; j < this.height; j = (short)(j + 1)) {
      p.Y = j;
      short k;
      for (k = 0; k < this.length; k = (short)(k + 1)) {
        short i;
        for (i = 0; i < this.width; i = (short)(i + 1)) {
          rotateVector(p, rot, i, k, this.width, this.length);

          for (int c = 0; damageLoc != null && c < this.damageLoc.length; c++)
                doGen = (doGen && Vector.VtoVlength(p, this.damageLoc[c]) > this.damageSize[c]);
          if(doGen) {
              if (blockArray[count] == 7) {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + j, pos.Z + p.Z, top);
              } else if (blockArray[count] == 54) {
                  handleLoot(world, random, pos.X + p.X, pos.Y + j, pos.Z + p.Z);
              } else if (blockArray[count] != 2) {
                  if (world.getBiomeGenForCoords(pos.X + p.X, pos.Z + p.Z).biomeID != ModConfig.radioactiveBiomeID) {
                      RuinGenHelper.setBlock(pos.X + p.X, pos.Y + j, pos.Z + p.Z, Block.getBlockById(blockArray[count]), this.data[count]);
                  } else {
                      Block block;
                      switch (blockArray[count]) {
                          case 5 -> block = ModBlocks.waste_planks;
                          case 17 -> block = ModBlocks.waste_log;
                          case 53, 47, 65, 72, 109, 85, 96, 106 -> block = Blocks.air;
                          default -> block = Block.getBlockById(this.blocks[count]);

                      }
                      RuinGenHelper.setBlock(pos.X + p.X, pos.Y + j, pos.Z + p.Z, block, this.data[count]);
                  }
              }
          }
          int var3 = world.getPrecipitationHeight(p.X + pos.X, p.Z + pos.Z );
          if (world.func_147478_e(p.X + pos.X, var3, p.Z + pos.Z , true))
            world.setBlock(p.X + pos.X, var3, p.Z + pos.Z , Blocks.snow_layer, 0, 2);
          count++;
        }
      }
    }
    return true;
  }

    private void handleLoot(World world, Random random, int x, int y, int z) {

        if (random.nextInt(CityLootConfig.hardLootChance) == 0) {
            RuinGenHelper.setBlock(x, y, z, ModBlocks.safe, 0);
            TileEntitySafe safe = (TileEntitySafe) world.getTileEntity(x, y, z);
            safe.setMod(1);
            safe.setPins(random.nextInt(999) + 1);
            safe.lock();
            LootStack.placeLoot(random, safe,
                hardLoot.items,
                CityLootConfig.hardLootMin,
                CityLootConfig.hardLootMax, CityLootConfig.hardLootRepeat);

        } else if (random.nextInt(CityLootConfig.midLootChance) == 0) {
            RuinGenHelper.setBlock(x, y, z, ModBlocks.crate_steel, 0);
            TileEntityCrateSteel chest = (TileEntityCrateSteel) world.getTileEntity(x, y, z);
            LootStack.placeLoot(random, chest,
                midLoot.items,
                CityLootConfig.midLootMin,
                CityLootConfig.midLootMax, CityLootConfig.midLootRepeat);
        } else {
            RuinGenHelper.setBlock(x, y, z, Blocks.chest, 0);
            TileEntityChest chest = (TileEntityChest)world.getTileEntity(x, y, z);
            LootStack.placeLoot(random, chest,
                easyLoot.items,
                CityLootConfig.easyLootMin,
                CityLootConfig.easyLootMax, CityLootConfig.easyLootRepeat);
        }
    }

  private void ruinBuilding(int num, int maxSize, int minSize, Random rand) {
      int inc = this.height / num;
      int maxR = this.width / 2 + 1;
      int minR = this.width / 2 - 1;
      this.damageLoc = new Vector[num];
      this.damageSize = new int[num];
      for (int i = 0; i < num; i++) {
          this.damageLoc[i] = Vector.randomVector2D(rand, maxR, minR).add(new Vector(this.width / 2, 0, this.length / 2));
          (this.damageLoc[i]).Y = rand.nextInt(inc) + inc * i;
          this.damageSize[i] = rand.nextInt(maxSize - minSize + 1) + minSize;
      }
  }
    private void rotateVector(Vector p, int rot, short i, short k, int width, int length) {
        if (rot == 1) {
            p.X = k;
            p.Z = width - i - 1;
        } else if (rot == 2) {
            p.X = width - i - 1;
            p.Z = length - k - 1;
        } else if (rot == 3) {
            p.X = length - k - 1;
            p.Z = i;
        } else {
            p.X = i;
            p.Z = k;
        }
    }

  private static Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
      return items.get(key);
  }
}
