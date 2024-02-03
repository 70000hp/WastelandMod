

package myid.chiqors.wasteland.ruin;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import myid.chiqors.wasteland.WastelandBiomes;
import myid.chiqors.wasteland.city.MultiVector;
import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.config.RuinConfig;
import myid.chiqors.wasteland.items.LootStack;
import myid.chiqors.wasteland.ruin.code.BuildingCode;
import myid.chiqors.wasteland.utils.CustomItemStack;
import myid.chiqors.wasteland.utils.Vector;
import myid.chiqors.wasteland.world.WastelandWorldData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;

public class RuinVillageGenerator implements IWorldGenerator {
  public static List<Vector> villageLocation;
  
  public static int villageNum;
  
  private boolean generating;
  
  private boolean loadedWorld;
  
  public RuinVillageGenerator() {
    GameRegistry.registerWorldGenerator(toIWorldGenerator(), 8);
    villageLocation = new ArrayList<Vector>();
    villageNum = 0;
    this.generating = false;
    this.loadedWorld = false;
  }
  
  public IWorldGenerator toIWorldGenerator() {
    return this;
  }
  
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    if (world.provider.dimensionId == 0 && this.loadedWorld)
      generateVillage(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider); 
  }
  
  public void generateVillage(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    MultiVector currentLoc = new MultiVector(chunkX * 16, Layout.getWorldHeight(world, chunkX * 16, chunkZ * 16), chunkZ * 16);
    if (checkDist(currentLoc, (ModConfig.minVillageDistance * 16))
            && !this.generating && !world.isRemote
            && !BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16), BiomeDictionary.Type.WATER)) {
      this.generating = true;
      int villageSize = random.nextInt(3);
      int villageDim = (villageSize + 8) * 16;
      int[][] heightMap = getTerrainLevelMap(world, currentLoc, villageDim);
      if (true) {
        System.out.println("Generating Village at X:" + chunkX * 16 + " Z:" + chunkZ * 16);
        villageLocation.add(currentLoc);
        villageNum++;
        RuinedVillage village = new RuinedVillage(world, chunkX * 16, chunkZ * 16, villageDim, villageSize, random);
        village.generate(world, random);
      } 
      this.generating = false;
    } 
  }
  
  public static void spawnBunker(Vector pos, World world) {
    byte[] blocks = BuildingCode.Bunker.BLOCKS;
    byte[] data = BuildingCode.Bunker.DATA;
    int count = 0;
    int worldHeight = getWorldHeight(world, pos.X - 1, pos.Z - 5);
    Random random = new Random();
    int cX = 3;
    int cZ = 3;
    System.out.println(pos.toCustomString());
    for (int k = 0; k < 5; k++) {
      for (int j = 0; j < 7; j++) {
        for (int m = 0; m < 7; m++) {
          if (blocks[count] == 54) {
            world.setBlock(pos.X - cX + m, pos.Y + k, pos.Z - cZ + j, Block.getBlockById(blocks[count]), data[count], 0);
            TileEntityChest chest = (TileEntityChest)world.getTileEntity(pos.X - cX + m, pos.Y + k, pos.Z - cZ + j);
            LootStack loot = new LootStack(RuinConfig.getLoot(RuinConfig.startLoot), RuinConfig.startLootMax, RuinConfig.startLootMin, RuinConfig.startLootRepeat);
            CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
          } else if (blocks[count] == 98) {
            int meta = random.nextInt(10);
            meta = (meta > 6) ? random.nextInt(5) : 0;
            meta = (meta > 2) ? (random.nextInt(2) + 1) : meta;
            world.setBlock(pos.X - cX + m, pos.Y + k, pos.Z - cZ + j, Block.getBlockById(blocks[count]), meta, 0);
          } else {
            world.setBlock(pos.X - cX + m, pos.Y + k, pos.Z - cZ + j, Block.getBlockById(blocks[count]), data[count], 0);
          } 
          count++;
        } 
      } 
    } 
    world.setBlock(pos.X - 1, pos.Y + 1, pos.Z - 4, Blocks.air, 0, 2);
    world.setBlock(pos.X - 1, pos.Y + 2, pos.Z - 4, Blocks.air, 0, 2);
    for (int i = pos.Y + 1; i < worldHeight; i++)
      world.setBlock(pos.X - 1, i, pos.Z - 5, Blocks.ladder, 3, 2); 
    world.setBlock(pos.X - 1, worldHeight, pos.Z - 5, Blocks.air, 3, 2);
    world.setBlock(pos.X - 1, worldHeight + 1, pos.Z - 5, Blocks.air, 3, 2);
    world.setBlock(pos.X - 1, worldHeight + 2, pos.Z - 5, Blocks.air, 3, 2);
  }
  
  private boolean checkDist(Vector current, double distance) {
      for (Vector vector : villageLocation) {
          if (Vector.VtoVlength(current, vector) < distance)
              return false;
      }
    return true;
  }
  
  public void resetData() {
    this.generating = false;
    villageNum = 0;
    villageLocation.clear();
    this.loadedWorld = true;
  }
  
  private boolean checkLevelVariation(int[] heightMap, int maxBlockVariation) {
    int maxHeight = heightMap[0];
    int minHeight = maxHeight;
    for (int i = 1; i < heightMap.length; i++) {
      if (heightMap[i] > maxHeight)
        maxHeight = heightMap[i]; 
      else if (heightMap[i] < minHeight)
        minHeight = heightMap[i]; 
    } 
    return (maxHeight - minHeight <= maxBlockVariation);
  }
  
  private int[][] getTerrainLevelMap(World world, Vector position, int dim) {
    int villageDim = dim / 2;
    int[][] heightMap = new int[2][villageDim * villageDim];
    for (int i = 0; i < villageDim; i++) {
      for (int j = 0; j < villageDim; j++) {
        boolean flag = true;
        byte tries;
        for (tries = 0; tries < 3 && flag; tries = (byte)(tries + 1)) {
          int height = getWorldHeight(world, position.X + j * 2 - villageDim, position.Z + i * 2 - villageDim);
          if (height != 0) {
            heightMap[0][i * villageDim + j] = height;
            heightMap[1][i * villageDim + j] = Block.getIdFromBlock(world.getBlock(position.X + j * 2 - villageDim, height, position.Z + i * 2 - villageDim));
            flag = false;
          } 
        } 
      } 
    } 
    return heightMap;
  }
  
  public static int getWorldHeight(World world, int x, int z) {
    int worldHeight = world.getHeightValue(x, z);
    if (worldHeight == 0) {
      world.getChunkProvider().loadChunk(x >> 4, z >> 4);
      worldHeight = world.getHeightValue(x, z);
    } 
    if (worldHeight == 0)
      System.out.println("World height still 0"); 
    return worldHeight;
  }
  
  public void loadData(List<Vector> villageLoc, int size) {
    villageLocation = villageLoc;
    villageNum = size;
    this.loadedWorld = true;
  }
  
  public void saveData(WastelandWorldData data) {
    data.saveVillageData(villageLocation);
  }
}
