

package myid.chiqors.wasteland.city;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import myid.chiqors.wasteland.Wasteland;
import myid.chiqors.wasteland.WastelandBiomes;
import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.items.LootStack;
import myid.chiqors.wasteland.ruin.Layout;
import myid.chiqors.wasteland.utils.Message;
import myid.chiqors.wasteland.utils.Vector;
import myid.chiqors.wasteland.world.WastelandWorldData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class CityGenerator implements IWorldGenerator {
  public static List<Vector> cityLocation;
  
  public static int cityNum;
  
  private static boolean generating = false;
  
  private boolean loadedWorld;
  
  public CityGenerator() {
    GameRegistry.registerWorldGenerator(toIWorldGenerator(), 12);
    cityLocation = new ArrayList<Vector>();
    cityNum = 0;
    this.loadedWorld = false;
  }
  
  public IWorldGenerator toIWorldGenerator() {
    return this;
  }
  
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    if (world.provider.dimensionId == 0 && this.loadedWorld && ModConfig.spawnCities)
      generateCity(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider); 
  }
  
  public void generateCity(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    MultiVector currentLoc = new MultiVector(chunkX * 16, Layout.getWorldHeight(world, chunkX * 16, chunkZ * 16), chunkZ * 16);
    if (checkDist(currentLoc, (ModConfig.minCityDistance * 16))) {
      if (!generating && !world.isRemote) {
        Wasteland.NETWORK.sendToAll((IMessage)Message.createChatMessage("Generating world structures (please wait)..."));
        Wasteland.NETWORK.sendToAll((IMessage)Message.createProgressMessage(0, 1));
        generating = true;
        List<MultiVector> chunks = new ArrayList<MultiVector>();
        chunks.add(currentLoc);
        addConnectedBiomeChunks(chunks, currentLoc, world);
        MultiVector center = getCenterChunk(chunks, world);
        limitCitySize(chunks, center, ModConfig.maxCitySize * 16);
        if (chunks.size() > 0) {
          System.out.println("Generating City at X:" + center.X + " Z:" + center.Z + " Size: " + chunks.size());
          List<SchematicBuilding> buildingSchematics = SchematicBuilding.loadAllBuildings();
          LootStack[] loot = LootStack.loadCityLoot();
          RuinedCity city = new RuinedCity(world, center, chunks, random);
          city.generate(world, random, buildingSchematics, loot);
          buildingSchematics.clear();
          cityLocation.add(center);
          cityNum++;
        } 
        Wasteland.NETWORK.sendToAll((IMessage)Message.createProgressMessage(1, 1));
        Wasteland.NETWORK.sendToAll((IMessage)Message.createChatMessage("...done"));
        generating = false;
      } 
    } 
  }
  
  private void limitCitySize(List<MultiVector> allChunks, MultiVector centerChunk, int maxChunkDist) {
    for (int i = allChunks.size() - 1; i >= 0; i--) {
      if (Vector.VtoVlengthXZ(centerChunk, allChunks.get(i)) > maxChunkDist)
        allChunks.remove(i); 
    } 
  }
  
  private MultiVector getCenterChunk(List<MultiVector> chunks, World world) {
    int maxX = ((MultiVector)chunks.get(0)).X;
    int minX = maxX;
    int maxZ = ((MultiVector)chunks.get(0)).Z;
    int minZ = maxZ;
    for (int i = 1; i < chunks.size(); i++) {
      maxX = (((MultiVector)chunks.get(i)).X > maxX) ? ((MultiVector)chunks.get(i)).X : maxX;
      minX = (((MultiVector)chunks.get(i)).X < minX) ? ((MultiVector)chunks.get(i)).X : minX;
      maxZ = (((MultiVector)chunks.get(i)).Z > maxZ) ? ((MultiVector)chunks.get(i)).Z : maxZ;
      minZ = (((MultiVector)chunks.get(i)).Z < minZ) ? ((MultiVector)chunks.get(i)).Z : minZ;
    } 
    int cX = (maxX - minX) / 2 + minX & 0xFFFFFFF0;
    int cZ = (maxZ - minZ) / 2 + minZ & 0xFFFFFFF0;
    for (MultiVector v : chunks) {
      if (v.X == cX && v.Z == cZ)
        return v;
    } 
    System.out.println("Center not found");
    return null;
  }
  
  private void addConnectedBiomeChunks(List<MultiVector> chunks, MultiVector position, World world) {
    int biomeID = (world.getBiomeGenForCoords(position.X, position.Z)).biomeID;
    MultiVector[] newChunks = { null, null, null, null };
    boolean containsChunk = false;
    int i;

    if(chunks.size() > 256) return;

    for (i = 0; i < 4; i++) {
      MultiVector current = chooseChunk(i, position);
      if ((world.getBiomeGenForCoords(current.X, current.Z)).biomeID == biomeID) {
        for (int j = 0; j < chunks.size() && !containsChunk; j++) {
          if (((MultiVector)chunks.get(j)).equalsXZ(current))
            containsChunk = true; 
        } 
        if (!containsChunk) {
          current.Y = Layout.getWorldHeight(world, current.X, current.Z);
          newChunks[i] = current.copy();
          chunks.add(newChunks[i]);
        } else {
          newChunks[i] = null;
        } 
        containsChunk = false;
      } 
    } 
    for (i = 0; i < 4; i++) {
      if (newChunks[i] != null)
        addConnectedBiomeChunks(chunks, newChunks[i], world); 
    } 
  }
  
  private MultiVector chooseChunk(int i, MultiVector position) {
    MultiVector pos;
    if (i == 0) {
      pos = new MultiVector(position.X + 16, position.Y, position.Z);
    } else if (i == 1) {
      pos = new MultiVector(position.X - 16, position.Y, position.Z);
    } else if (i == 2) {
      pos = new MultiVector(position.X, position.Y, position.Z + 16);
    } else {
      pos = new MultiVector(position.X, position.Y, position.Z - 16);
    } 
    return pos;
  }
  
  private boolean checkDist(Vector current, double distance) {
    for (Vector vector : cityLocation) {
      if (Vector.VtoVlength(current, vector) < distance)
        return false;
    } 
    return true;
  }
  
  public void resetData() {
    generating = false;
    cityNum = 0;
    cityLocation.clear();
    this.loadedWorld = true;
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
    cityLocation = villageLoc;
    cityNum = size;
    this.loadedWorld = true;
  }
  
  public void saveData(WastelandWorldData data) {
    data.saveCityData(cityLocation);
  }
}
