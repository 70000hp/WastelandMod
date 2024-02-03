

package myid.chiqors.wasteland.ruin;

import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.config.RuinConfig;
import myid.chiqors.wasteland.items.LootStack;
import myid.chiqors.wasteland.ruin.code.BuildingCode;
import myid.chiqors.wasteland.utils.CustomItemStack;
import myid.chiqors.wasteland.utils.Sphere;
import myid.chiqors.wasteland.utils.Vector;
import java.util.List;
import java.util.Map;
import java.util.Random;

import myid.chiqors.wasteland.world.WorldChunkManagerWasteland;
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
    Block top = world.getWorldChunkManager() instanceof WorldChunkManagerWasteland ? ModConfig.getSurfaceBlock() : Blocks.grass;
    RuinGenHelper.setWorld(world);
    if (this.blocks.length < 100) {
      numHoles = 1;
      maxSize = 5;
      minSize = 4;
    } else if (this.blocks.length >= 100 && this.blocks.length < 500) {
      numHoles = random.nextInt(2) + 2;
      maxSize = 2;
      minSize = 1;
    } else if (this.blocks.length >= 500 && this.blocks.length < 1500) {
      numHoles = random.nextInt(2) + 1;
      maxSize = 3;
      minSize = 2;
    } else {
      numHoles = random.nextInt(4) + 2;
      maxSize = 4;
      minSize = 2;
    } 
    if (this.name.equals((create(14)).name)) {
      int waterHeight = random.nextInt(7) + 10;
      for (int i = 0; i < waterHeight; i++) {
        world.setBlock(pos.X + 1, pos.Y - 1 - i, pos.Z + 1, Blocks.water);
        world.setBlock(pos.X + 2, pos.Y - 1 - i, pos.Z + 1, Blocks.water);
        world.setBlock(pos.X + 2, pos.Y - 1 - i, pos.Z + 2, Blocks.water);
        world.setBlock(pos.X + 1, pos.Y - 1 - i, pos.Z + 2, Blocks.water);
      } 
    } else {
      ruinBuilding(numHoles, maxSize, minSize, random);
    } 
    int count = 0;
    short j;
    for (j = 0; j < this.height; j = (short)(j + 1)) {
      short k;
      for (k = 0; k < this.length; k = (short)(k + 1)) {
        short i;
        for (i = 0; i < this.width; i = (short)(i + 1)) {
          int x, z;
          if (rot == 0) {
            x = i;
            z = k;
          } else if (rot == 1) {
            x = this.width - i - 1;
            z = k;
          } else if (rot == 2) {
            x = this.width - i - 1;
            z = this.width - k - 1;
          } else {
            x = i;
            z = this.width - k - 1;
          } 
          if (this.blocks[count] == 7) {
            RuinGenHelper.setBlock(pos.X + x, pos.Y + j, pos.Z + z, top);
          } else if (this.blocks[count] != 2) {
            if (this.blocks[count] == 54) {
              RuinGenHelper.setBlock(pos.X + x, pos.Y + j, pos.Z + z, (Block)Blocks.chest);
              TileEntityChest chest = (TileEntityChest)world.getTileEntity(pos.X + x, pos.Y + j, pos.Z + z);
              LootStack loot = setItems(random);
              CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
            } else {
              RuinGenHelper.setBlock(pos.X + x, pos.Y + j, pos.Z + z, Block.getBlockById(this.blocks[count]), this.data[count]);
            } 
          } 
          count++;
        } 
      } 
    } 
    return true;
  }
  
  private LootStack setItems(Random random) {
    if (random.nextInt(RuinConfig.hardLootChance) == 0) {
      return hardLoot;
    } 
    if (random.nextInt(RuinConfig.midLootChance) == 0) {
      return midLoot;
    }
    return easyLoot;
  }
  
  private void ruinBuilding(int nodes, int maxRuinRad, int minRuinRad, Random rand) {
    int bottom = (int)(this.blocks.length / 3.0F);
    int rad = rand.nextInt(maxRuinRad - minRuinRad) + minRuinRad;
    for (int i = 0; i < nodes; i++) {
      int node = rand.nextInt(this.blocks.length - bottom) + bottom - 1;
      while (this.blocks[node] == 0 || this.blocks[node] == 2)
        node = rand.nextInt(this.blocks.length - bottom) + bottom - 1; 
      int y = node / this.width * this.length;
      int mod = node - this.width * this.length * y;
      int z = mod / this.width;
      int x = mod - z * this.width;
      Sphere hole = new Sphere(new Vector(x, y, z), rad);
      this.blocks = hole.makeSphereOf(this.blocks, this.width, this.length, this.height, (byte)0);
    } 
  }
  
  private static Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
      return items.get(key);
  }
}
