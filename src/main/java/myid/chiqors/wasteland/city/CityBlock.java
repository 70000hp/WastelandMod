

package myid.chiqors.wasteland.city;

import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.items.LootStack;
import myid.chiqors.wasteland.ruin.RuinGenHelper;
import myid.chiqors.wasteland.utils.Rectangle;
import myid.chiqors.wasteland.utils.Vector;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class CityBlock {
  Rectangle area;
  
  int connections;
  
  boolean[] connectedFaces;
  
  boolean doGenerate;
  
  public String buildingName;
  
  public int[] cornerHeight;
  
  public int buildingHeight;
  
  CityBlock[] connectedBlocks;
  
  private RuinGenHelper genHelper = new RuinGenHelper();
  
  public CityBlock(Rectangle area) {
    this.area = area;
    int perimiter = (area.width / 16 + area.length / 16) * 2;
    this.connectedBlocks = new CityBlock[perimiter];
    this.cornerHeight = new int[perimiter];
    for (int i = 0; i < perimiter; i++) {
      this.connectedBlocks[i] = null;
      this.cornerHeight[i] = 0;
    } 
    this.doGenerate = true;
  }
  
  public void setConnectedCityBlock(CityBlock block, int index) {
    if (index < this.connectedBlocks.length) {
      this.connectedBlocks[index] = block;
    } else {
      System.out.println("City block does not have side: " + String.valueOf(index));
    } 
  }
  
  public void setCorners(MultiVector position) {}
  
  public int[] heightVariation() {
    int diff = 0;
    int[] out = { 0, 0, 0 };
    int max = this.cornerHeight[0];
    int min = max;
    for (int i = 0; i < this.cornerHeight.length; i++) {
      max = (this.cornerHeight[i] > max) ? this.cornerHeight[i] : max;
      min = (this.cornerHeight[i] < min) ? this.cornerHeight[i] : min;
      if (i == this.cornerHeight.length - 1) {
        diff = this.cornerHeight[0] - this.cornerHeight[i];
      } else {
        diff = this.cornerHeight[i + 1] - this.cornerHeight[i];
      } 
      out[0] = (diff > out[0]) ? diff : out[0];
    } 
    out[1] = max - min;
    out[2] = (int)((max + min) / 2.0F);
    return out;
  }
  
  public void reduceVariation(int average, int p, boolean[] forcedHeight) {
    for (int i = 0; i < this.cornerHeight.length; i++) {
      if (!forcedHeight[i])
        if (this.cornerHeight[i] > average) {
          this.cornerHeight[i] = this.cornerHeight[i] - p;
        } else if (this.cornerHeight[i] < average) {
          this.cornerHeight[i] = this.cornerHeight[i] + p;
        }  
    } 
  }
  
  public Vector getPositionFromCorner(int index) {
    int x, z, w = this.area.width / 16;
    int l = this.area.length / 16;
    if (index >= 2 * (w + l)) {
      System.out.println("Index excedes number of corners");
      return null;
    } 
    if (index <= w) {
      x = index * 16;
      z = this.area.length;
    } else if (index < w + l && index > w) {
      x = this.area.width;
      z = this.area.length - (index - w) * 16;
    } else if (index >= w + l && index <= 2 * w + l) {
      x = this.area.width - (index - w - l) * 16;
      z = 0;
    } else {
      x = 0;
      z = (index - w - w - l) * 16;
    } 
    return new Vector(this.area.position.X + x, this.area.position.Y, this.area.position.Z + z);
  }
  
  public int getCornerFromPosition(Vector pos) {
    int dX = pos.X - this.area.position.X;
    int dZ = pos.Z - this.area.position.Z;
    if (dX >= 0 && dZ >= 0 && dX <= this.area.width && dZ <= this.area.length) {
      int w = this.area.width / 16;
      int l = this.area.length / 16;
      dX /= 16;
      dZ /= 16;
      if (w == 2 && l == 2) {
        if (dZ == 2)
          return dX; 
        if (dZ == 1)
          return (dX == 2) ? 3 : 7; 
        return 6 - dX;
      } 
      if (w == 2 && l == 1) {
        if (dZ == 1)
          return dX; 
        return 5 - dX;
      } 
      if (w == 1 && l == 2) {
        if (dZ == 2)
          return dX; 
        if (dZ == 1)
          return (dX == 1) ? 2 : 5; 
        return 4 - dX;
      } 
      return (dZ == 1) ? dX : (3 - dX);
    } 
    System.out.println("Corner is outside the block");
    return -1;
  }
  
  public void generate(World world, Random random, List<SchematicBuilding> buildingSchematics, LootStack[] loot, int cityColour) {
    RuinGenHelper.setWorld(world);
    generateBase(world, random);
    CityBuilding building = CityBuilding.create(this.buildingName, random.nextInt(4) + 2, random, buildingSchematics, loot);
    if (building != null) {
      int offsetX, offsetZ, damageNodes = building.height / 15;
      damageNodes = (damageNodes > 0) ? (random.nextInt(damageNodes) + 1) : 1;
      int damageMaxRad = (building.width + building.length) / 6;
      int damageMinRad = (building.width + building.length) / 8;
      building.damageBuilding(damageNodes, damageMaxRad, damageMinRad, random);
      int rot = 0;
      if (this.area.width == 32) {
        if (this.area.length == 32) {
          rot = random.nextInt(4);
        } else {
          rot = random.nextInt(2) * 2;
          rot = (building.width > building.length) ? rot : (rot + 1);
        } 
      } else if (this.area.length == 32) {
        rot = random.nextInt(2) * 2;
        rot = (building.length > building.width) ? rot : (rot + 1);
      } else {
        rot = random.nextInt(4);
      } 
      if (rot == 0 || rot == 2) {
        offsetX = (this.area.width - building.width) / 2;
        offsetZ = (this.area.length - building.length) / 2;
      } else {
        offsetX = (this.area.width - building.length) / 2;
        offsetZ = (this.area.length - building.width) / 2;
      } 
      building.generate(world, random, this.area.position.copy().add(new Vector(offsetX, 0, offsetZ)), rot, cityColour);
    } 
  }
  
  private void generateBase(World world, Random random) {
    int roadWidth = 2;
    Block surfaceBlock = ModConfig.getSurfaceBlock();
    for (int j = roadWidth; j < this.area.length - roadWidth; j++) {
      for (int k = roadWidth; k < this.area.width - roadWidth; k++) {
        RuinGenHelper.setBlock(this.area.position.X + k, this.area.position.Y, this.area.position.Z + j, surfaceBlock);
        RuinedCity.clearAbove(this.area.position.X + k, this.area.position.Y + 1, this.area.position.Z + j, 5, world);
        RuinedCity.fillBelow(this.area.position.X + k, this.area.position.Y - 1, this.area.position.Z + j, 3, world);
      } 
    } 
    int rubble = this.area.length * this.area.width / 10;
    for (int i = 0; i < rubble; i++) {
      int x = random.nextInt(this.area.width - 2 * roadWidth) + roadWidth;
      int z = random.nextInt(this.area.length - 2 * roadWidth) + roadWidth;
      if (random.nextInt(10) > 0) {
        int type = random.nextInt(10);
        Block b = (type == 0) ? Blocks.cobblestone : ((type == 1) ? Blocks.stonebrick : ((type == 2) ? Blocks.mossy_cobblestone : ((type == 3) ? Blocks.glass : ((type == 4) ? Blocks.gravel : surfaceBlock))));
        int m = (type == 1) ? random.nextInt(3) : 0;
        world.setBlock(this.area.position.X + x, this.area.position.Y + 1, this.area.position.Z + z, b, m, 2);
      } else {
        world.setBlock(this.area.position.X + x, this.area.position.Y, this.area.position.Z + z, Blocks.air, 0, 2);
      } 
    } 
  }
}
