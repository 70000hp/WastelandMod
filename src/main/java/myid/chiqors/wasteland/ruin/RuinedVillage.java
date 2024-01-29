

package myid.chiqors.wasteland.ruin;

import myid.chiqors.wasteland.utils.Rectangle;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class RuinedVillage {
  private Building[] structures;
  
  private Building center;
  
  private Layout lay;
  
  private int locX;
  
  private int locZ;
  
  private int dimension;
  
  public RuinedVillage(World world, int posX, int posZ, int dim, int size, Random rand) {
    rand.nextInt(100);
    System.out.println("Size: " + String.valueOf(size));
    this.locX = posX;
    this.locZ = posZ;
    this.dimension = dim;
    int numStructures = 1;
    int centerStruct = 0;
    int smallStruct = 1;
    int midStruct = 0;
    int largeStruct = 0;
    if (size == 0) {
      numStructures = rand.nextInt(4) + 3;
      centerStruct = rand.nextInt(2);
      smallStruct = 3;
      midStruct = numStructures - smallStruct;
    } else if (size == 1) {
      numStructures = rand.nextInt(5) + 5;
      centerStruct = rand.nextInt(2) + 1;
      smallStruct = 2;
      largeStruct = rand.nextInt(2) + 2;
      midStruct = numStructures - smallStruct - largeStruct;
    } else {
      numStructures = rand.nextInt(6) + 6;
      centerStruct = 3;
      largeStruct = rand.nextInt(3) + 3;
      midStruct = rand.nextInt(3) + 2;
      if (midStruct + largeStruct > numStructures) {
        midStruct = numStructures - largeStruct;
        smallStruct = 0;
      } else {
        smallStruct = numStructures - largeStruct - midStruct;
      } 
    } 
    System.out.println("Buildings: " + String.valueOf(numStructures) + " - S:" + String.valueOf(smallStruct) + " M:" + String.valueOf(midStruct) + " L:" + String.valueOf(largeStruct) + " - C:" + String.valueOf(centerStruct));
    if (centerStruct == 0) {
      this.center = null;
    } else if (centerStruct == 1) {
      this.center = Building.create(3);
    } else if (centerStruct == 2) {
      this.center = Building.create(14);
    } else {
      this.center = Building.create(0);
    } 
    this.structures = new Building[numStructures];
    for (int i = 0; i < numStructures; i++) {
      int build;
      if (i < smallStruct) {
        build = 0;
      } else if (i >= smallStruct && i < midStruct + smallStruct) {
        build = 1;
      } else {
        build = 2;
      } 
      this.structures[i] = Building.create(pickStruct(rand, build));
      while (checkDuplicates(this.center, this.structures, i))
        this.structures[i] = Building.create(pickStruct(rand, build)); 
    } 
    this.lay = new Layout(world, rand, this.center, this.structures, posX, posZ, dim, size);
  }
  
  private boolean checkDuplicates(Building c, Building[] b, int num) {
    if (c != null)
      if ((b[num]).name.equals(c.name))
        return true;  
    for (int i = 0; i < num; i++) {
      if ((b[num]).name.equals((b[i]).name) && !(b[num]).duplicate)
        return true; 
    } 
    return false;
  }
  
  private int pickStruct(Random rand, int i) {
    switch (i) {
      case 0:
        switch (rand.nextInt(5)) {
          case 0:
            return 12;
          case 1:
            return 13;
          case 2:
            return 14;
          case 3:
            return 1;
          case 4:
            return 3;
        } 
        break;
      case 1:
        switch (rand.nextInt(3)) {
          case 0:
            return 9;
          case 1:
            return 10;
          case 2:
            return 11;
        } 
        break;
      case 2:
        switch (rand.nextInt(6)) {
          case 0:
            return 2;
          case 1:
            return 4;
          case 2:
            return 5;
          case 3:
            return 6;
          case 4:
            return 7;
          case 5:
            return 8;
        } 
        break;
    } 
    return 0;
  }
  
  public void generate(World world, Random random) {
    if (this.center != null)
      this.center.generate(world, random, this.lay.cPos.position, this.lay.cPos.rotation); 
    for (int i = 0; i < this.lay.bPos.length; i++) {
      if (this.lay.bPos[i] != null && this.structures[i] != null)
        this.structures[i].generate(world, random, (this.lay.bPos[i]).position, (this.lay.bPos[i]).rotation); 
    } 
  }
  
  private void generateRect(World world, Rectangle r, int h) {
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r.width; j++) {
        if (i == 0 || j == 0 || i == r.length - 1 || j == r.width - 1)
          world.setBlock(r.position.X + j, r.position.Y + h, r.position.Z + i, Blocks.sponge); 
      } 
    } 
  }
}
