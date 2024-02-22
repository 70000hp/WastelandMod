

package myid.chiqors.wasteland.city;

import myid.chiqors.wasteland.config.CityLootConfig;
import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.items.LootStack;
import myid.chiqors.wasteland.ruin.RuinGenHelper;
import myid.chiqors.wasteland.utils.CustomItemStack;
import myid.chiqors.wasteland.utils.Schematic;
import myid.chiqors.wasteland.utils.Vector;
import java.util.List;
import java.util.Random;

import myid.chiqors.wasteland.world.WorldChunkManagerWasteland;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;

public class CityBuilding {
  public int width;
  
  public int height;
  
  public int length;
  
  public int rotation;
  
  public String name;
  
  public boolean duplicate;
  
  private byte[] blocks;
  
  private byte[] data;
  
  private List entities;
  
  private List tileentities;
  
  private RuinGenHelper genHelper = new RuinGenHelper();
  
  public static LootStack easyLoot;
  
  public static LootStack midLoot;
  
  public static LootStack hardLoot;
  
  public static LootStack ultraLoot;
  
  private Vector[] damageLoc;
  
  private int[] damageSize;
  
  private int roofHeight;
  
  public CityBuilding(String name, int w, int h, int l, byte[] b, byte[] d, int roofHeight, LootStack[] loot) {
    this.width = w;
    this.height = h;
    this.length = l;
    this.roofHeight = roofHeight;
    this.blocks = b;
    this.data = d;
    this.name = name;
    easyLoot = loot[0];
    midLoot = loot[1];
    hardLoot = loot[2];
    ultraLoot = loot[3];
  }
  
  public void damageBuilding(int num, int maxSize, int minSize, Random rand) {
    int inc = (this.height - this.roofHeight) / num;
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
  
  public void generate(World world, Random random, Vector pos, int rot, int cityColour) {
    RuinGenHelper.setWorld(world);
    Block top = !(world.getWorldChunkManager() instanceof WorldChunkManagerWasteland) ? world.getBiomeGenForCoords(pos.X, pos.Z).topBlock : Blocks.grass;
    int count = 0;
    boolean doGen = true;
    int flavor = random.nextInt(3);
    Vector p = new Vector(0, 0, 0);
    short j;
    for (j = 0; j < this.height; j = (short)(j + 1)) {
      p.Y = j;
      short k;
      for (k = 0; k < this.length; k = (short)(k + 1)) {
        short i;
        for (i = 0; i < this.width; i = (short)(i + 1)) {
          rotateVector(p, rot, i, k, this.width, this.length);
          rotateBlock(this.blocks, this.data, count, rot);
          if (this.damageLoc != null)
            for (int c = 0; c < this.damageLoc.length; c++)
              doGen = (doGen && Vector.VtoVlength(p, this.damageLoc[c]) > this.damageSize[c]);  
          if (doGen)
            if (this.blocks[count] == bedrockID) {
              RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, top);
            } else if (this.blocks[count] == airID) {
              if (p.Y > 0 && p.Y < 3)
                RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.air); 
            } else if (this.blocks[count] == chestID) {
              if (random.nextInt(10) != 0) {
                RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, (Block)Blocks.chest, this.data[count]);
                TileEntityChest chest = (TileEntityChest)world.getTileEntity(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z);
                LootStack loot = setItems(random);
                CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
              } 
            } else if (this.blocks[count] == spawnerID) {
              if (random.nextInt(12) != 0) {
                String mobName = ModConfig.getSpawnerCreature(random);
                RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.mob_spawner);
                TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner)world.getTileEntity(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z);
                if(mobName != null)
                    mobSpawner.func_145881_a().setEntityName(mobName);
              } 
            } else if (this.blocks[count] == cobbleID || this.blocks[count] == mossyCobbleID) {
              if (random.nextInt(50) != 0) {
                int randomNumber = random.nextInt(10);
                if (randomNumber == 0) {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.stone);
                } else if (randomNumber < 4) {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.mossy_cobblestone);
                } else {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.cobblestone);
                } 
              } 
            } else if (this.blocks[count] == stoneID) {
              if (random.nextInt(75) != 0) {
                int randomNumber = random.nextInt(10);
                if (randomNumber == 0) {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.mossy_cobblestone);
                } else if (randomNumber < 3) {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.cobblestone);
                } else {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.stone);
                } 
              } 
            } else if (this.blocks[count] == stoneBrickID) {
              if (random.nextInt(75) != 0) {
                int randomNumber = random.nextInt(10);
                int meta = (randomNumber < 5) ? 0 : ((randomNumber > 6) ? 2 : 1);
                RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.stonebrick, meta);
              } 
            } else if (this.blocks[count] == sandStoneID) {
              if (random.nextInt(65) != 0) {
                int randomNumber = random.nextInt(10);
                int meta = (randomNumber < 4) ? 0 : 2;
                RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.sandstone, meta);
              } 
            } else if (this.blocks[count] == glassID || this.blocks[count] == glassPaneID) {
              if (random.nextInt(20) != 0)
                RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Block.getBlockById(this.blocks[count]), this.data[count]); 
            } else if (this.blocks[count] == woolID || this.blocks[count] == stainedGlassID) {
              if (random.nextInt(40) != 0)
                if (this.data[count] == 0 || this.data[count] == 15 || this.data[count] == 8) {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Block.getBlockById(this.blocks[count]), this.data[count]);
                } else {
                  RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Block.getBlockById(this.blocks[count]), cityColour);
                }  
            } else if (this.blocks[count] == woodPlankID) {
              if (random.nextInt(40) != 0)
                RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.planks, this.data[count]); 
            } else {
              RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Block.getBlockById(this.blocks[count]), this.data[count]);
            }
          doGen = true;
          count++;
         if(j == height - 1) {
          int var3 = world.getPrecipitationHeight(p.X + pos.X, p.Z + pos.Z);
          if (world.func_147478_e(p.X + pos.X, var3, p.Z + pos.Z, true))
             world.setBlock(p.X + pos.X, var3, p.Z + pos.Z, Blocks.snow_layer, 0, 2);
         }
        }
      } 
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
  
  private void rotateBlock(byte[] blocks, byte[] data, int index, int rot) {
    Block block = Block.getBlockById(blocks[index]);
    byte meta = data[index];
    if (block instanceof net.minecraft.block.BlockStairs) {
      byte d = (byte)(meta & 0x3);
      meta = (byte)(meta & 0xFC);
      if (rot == 1) {
        data[index] = (byte)((d == 0) ? (meta + 3) : ((d == 1) ? (meta + 2) : ((d == 2) ? meta : (meta + 1))));
      } else if (rot == 2) {
        data[index] = (byte)((d == 0) ? (meta + 1) : ((d == 1) ? meta : ((d == 2) ? (meta + 3) : (meta + 2))));
      } else if (rot == 3) {
        data[index] = (byte)((d == 0) ? (meta + 2) : ((d == 1) ? (meta + 3) : ((d == 2) ? (meta + 1) : meta)));
      } 
    } else if (block instanceof net.minecraft.block.BlockDoor) {
      if ((meta & 0x80) == 0) {
        byte d = (byte)(meta & 0x3);
        meta = (byte)(meta & 0xFC);
        if (rot == 1) {
          data[index] = (byte)((d == 0) ? (meta + 3) : ((d == 1) ? meta : ((d == 2) ? (meta + 1) : (meta + 2))));
        } else if (rot == 2) {
          data[index] = (byte)((d == 0) ? (meta + 2) : ((d == 1) ? (meta + 3) : ((d == 2) ? meta : (meta + 1))));
        } else if (rot == 3) {
          data[index] = (byte)((d == 0) ? (meta + 1) : ((d == 1) ? (meta + 2) : ((d == 2) ? (meta + 3) : meta)));
        } 
      } 
    } else if (block instanceof net.minecraft.block.BlockTrapDoor) {
      if ((meta & 0x80) == 0) {
        byte d = (byte)(meta & 0x3);
        meta = (byte)(meta & 0xFC);
        if (rot == 1) {
          data[index] = (byte)((d == 0) ? (meta + 1) : ((d == 1) ? (meta + 2) : ((d == 2) ? (meta + 3) : meta)));
        } else if (rot == 2) {
          data[index] = (byte)((d == 0) ? (meta + 2) : ((d == 1) ? (meta + 3) : ((d == 2) ? meta : (meta + 1))));
        } else if (rot == 3) {
          data[index] = (byte)((d == 0) ? (meta + 3) : ((d == 1) ? meta : ((d == 2) ? (meta + 1) : (meta + 2))));
        } 
      } 
    } 
    if (block instanceof net.minecraft.block.BlockSign || block instanceof net.minecraft.block.BlockLadder || block instanceof net.minecraft.block.BlockChest || block instanceof net.minecraft.block.BlockFurnace) {
      byte d = (byte)(meta & 0x7);
      meta = (byte)(meta & 0xF8);
      if (rot == 1) {
        data[index] = (byte)((d == 2) ? (meta + 4) : ((d == 3) ? (meta + 5) : ((d == 4) ? (meta + 3) : (meta + 2))));
      } else if (rot == 2) {
        data[index] = (byte)((d == 2) ? (meta + 3) : ((d == 3) ? (meta + 2) : ((d == 4) ? (meta + 5) : (meta + 4))));
      } else if (rot == 3) {
        data[index] = (byte)((d == 2) ? (meta + 5) : ((d == 3) ? (meta + 4) : ((d == 4) ? (meta + 2) : (meta + 3))));
      } 
    } else if (block instanceof net.minecraft.block.BlockLever) {
      byte d = (byte)(meta & 0x7);
      meta = (byte)(meta & 0xF8);
      if (d > 0 && d < 5)
        if (rot == 1) {
          data[index] = (byte)((d == 1) ? (meta + 4) : ((d == 2) ? (meta + 3) : ((d == 3) ? (meta + 1) : (meta + 2))));
        } else if (rot == 2) {
          data[index] = (byte)((d == 1) ? (meta + 2) : ((d == 2) ? (meta + 1) : ((d == 3) ? (meta + 4) : (meta + 3))));
        } else if (rot == 3) {
          data[index] = (byte)((d == 1) ? (meta + 3) : ((d == 2) ? (meta + 4) : ((d == 3) ? (meta + 2) : (meta + 1))));
        }  
    } else if (block instanceof net.minecraft.block.BlockBed) {
      byte d = (byte)(meta & 0x3);
      meta = (byte)(meta & 0xFC);
      if (rot == 1) {
        data[index] = (byte)((d == 0) ? (meta + 3) : ((d == 1) ? meta : ((d == 2) ? (meta + 1) : (meta + 2))));
      } else if (rot == 2) {
        data[index] = (byte)((d == 0) ? (meta + 2) : ((d == 1) ? (meta + 3) : ((d == 2) ? meta : (meta + 1))));
      } else if (rot == 3) {
        data[index] = (byte)((d == 0) ? (meta + 1) : ((d == 1) ? (meta + 2) : ((d == 2) ? (meta + 3) : meta)));
      } 
    } else if (block instanceof net.minecraft.block.BlockRail) {
      byte d = (byte)(meta & 0xF);
      meta = (byte)(meta & 0xF0);
      if (rot == 1) {
        if (d < 2) {
          data[index] = (byte)((d == 0) ? (meta + 1) : meta);
        } else if (d >= 2 && d < 6) {
          data[index] = (byte)((d == 2) ? (meta + 4) : ((d == 3) ? (meta + 5) : ((d == 4) ? (meta + 3) : (meta + 2))));
        } else {
          data[index] = (byte)((d == 6) ? (meta + 9) : ((d == 7) ? (meta + 6) : ((d == 8) ? (meta + 7) : (meta + 8))));
        } 
      } else if (rot == 2) {
        if (d >= 2 && d < 6) {
          data[index] = (byte)((d == 2) ? (meta + 3) : ((d == 3) ? (meta + 2) : ((d == 4) ? (meta + 5) : (meta + 4))));
        } else {
          data[index] = (byte)((d == 6) ? (meta + 8) : ((d == 7) ? (meta + 9) : ((d == 8) ? (meta + 6) : (meta + 7))));
        } 
      } else if (rot == 3) {
        if (d < 2) {
          data[index] = (byte)((d == 0) ? (meta + 1) : meta);
        } else if (d >= 2 && d < 6) {
          data[index] = (byte)((d == 2) ? (meta + 5) : ((d == 3) ? (meta + 4) : ((d == 4) ? (meta + 2) : (meta + 3))));
        } else {
          data[index] = (byte)((d == 6) ? (meta + 7) : ((d == 7) ? (meta + 8) : ((d == 8) ? (meta + 9) : (meta + 6))));
        } 
      } 
    } 
  }
  
  private LootStack setItems(Random random) {
    if (random.nextInt(CityLootConfig.ultraLootChance) == 0) {
      return ultraLoot;
    } 
    if (random.nextInt(CityLootConfig.hardLootChance) == 0) {
      return midLoot;
    } 
    if (random.nextInt(CityLootConfig.midLootChance) == 0) {
      return midLoot;
    }
    return easyLoot;
  }
  
  public static CityBuilding create(String name, int floors, Random random, List<SchematicBuilding> buildingSchematics, LootStack[] loot) {
    if (name.equalsIgnoreCase("none"))
      return null;
    for (SchematicBuilding current : buildingSchematics) {
          if (current.name.equals(name)) {
              Schematic middle, top;
              int height, topH;
              boolean singleFloor = (current.top == null || current.middle == null);
              byte chestID = (byte) Block.getIdFromBlock((Block) Blocks.chest);
              byte airID = (byte) Block.getIdFromBlock(Blocks.air);
              Schematic bottom = (current.bottom.length == 1) ? current.bottom[0] : current.bottom[random.nextInt(current.bottom.length)];
              int w = bottom.width;
              int l = bottom.length;
              int area = w * l;
              if (!singleFloor) {
                  middle = (current.middle.length == 1) ? current.middle[0] : current.middle[random.nextInt(current.middle.length)];
                  top = (current.top.length == 1) ? current.top[0] : current.top[random.nextInt(current.top.length)];
                  height = bottom.height + top.height + middle.height * floors;
                  topH = top.height;
              } else {
                  middle = null;
                  top = null;
                  height = bottom.height;
                  topH = 0;
              }
              int size = area * height;
              byte[] blocks = new byte[size];
              byte[] meta = new byte[size];
              int h = bottom.blocks.length;
              int chestIndx = (bottom.chestNum == 0) ? -1 : random.nextInt(bottom.chestNum);
              int chestCount = 0;
              for (int j = 0; j < h; j++) {
                  if (bottom.blocks[j] == chestID) {
                      blocks[j] = (chestCount == chestIndx) ? chestID : airID;
                      meta[j] = (chestCount == chestIndx) ? bottom.data[j] : 0;
                      chestCount++;
                  } else {
                      blocks[j] = bottom.blocks[j];
                      meta[j] = bottom.data[j];
                  }
              }
              if (!singleFloor) {
                  int offset = bottom.blocks.length;
                  h = middle.blocks.length;
                  chestCount = 0;
                  for (int k = 0; k < floors; k++) {
                      chestIndx = (middle.chestNum == 0) ? -1 : random.nextInt(middle.chestNum);
                      chestCount = 0;
                      for (int n = 0; n < h; n++) {
                          if (middle.blocks[n] == chestID) {
                              blocks[n + offset] = (chestCount == chestIndx) ? chestID : airID;
                              meta[n + offset] = (chestCount == chestIndx) ? middle.data[n] : 0;
                              chestCount++;
                          } else {
                              blocks[n + offset] = middle.blocks[n];
                              meta[n + offset] = middle.data[n];
                          }
                      }
                      offset += middle.blocks.length;
                  }
                  h = top.blocks.length;
                  chestIndx = (top.chestNum == 0) ? -1 : random.nextInt(top.chestNum);
                  chestCount = 0;
                  for (int m = 0; m < h; m++) {
                      if (top.blocks[m] == chestID) {
                          blocks[m + offset] = (chestCount == chestIndx) ? chestID : airID;
                          meta[m + offset] = (chestCount == chestIndx) ? top.data[m] : 0;
                          chestCount++;
                      } else {
                          blocks[m + offset] = top.blocks[m];
                          meta[m + offset] = top.data[m];
                      }
                  }
              }
              return new CityBuilding(name, w, height, l, blocks, meta, topH, loot);
          }
      }
    System.out.println("Unknown City Building");
    return null;
  }

  
  public static final int cobbleID = Block.getIdFromBlock(Blocks.cobblestone);
  
  public static final int mossyCobbleID = Block.getIdFromBlock(Blocks.mossy_cobblestone);
  
  public static final int stoneID = Block.getIdFromBlock(Blocks.stone);
  
  public static final int sandStoneID = Block.getIdFromBlock(Blocks.sandstone);
  
  public static final int stoneBrickID = Block.getIdFromBlock(Blocks.stonebrick);
  
  public static final int woolID = Block.getIdFromBlock(Blocks.wool);
  
  public static final int glassID = Block.getIdFromBlock(Blocks.glass);
  
  public static final int glassPaneID = Block.getIdFromBlock(Blocks.glass_pane);
  
  public static final int stainedGlassID = Block.getIdFromBlock((Block)Blocks.stained_glass);
  
  public static final int woodPlankID = Block.getIdFromBlock(Blocks.planks);
  
  public static final int chestID = Block.getIdFromBlock((Block)Blocks.chest);
  
  public static final int spawnerID = Block.getIdFromBlock(Blocks.mob_spawner);
  
  public static final int airID = Block.getIdFromBlock(Blocks.air);
  
  public static final int bedrockID = Block.getIdFromBlock(Blocks.bedrock);
}
