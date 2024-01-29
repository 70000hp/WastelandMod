package myid.chiqors.wasteland.city;

import myid.chiqors.wasteland.utils.Rectangle;
import myid.chiqors.wasteland.utils.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.world.World;

public class CityBlockLayout {
  List<CityBlock> block;
  
  public CityBlockLayout(List<MultiVector> chunkLocation, MultiVector center, Random random, World world) {
    this.block = new ArrayList<CityBlock>();
    connectChunks(chunkLocation);
    List<MultiVector> chunks = reduceHeightVariation(chunkLocation, center);
    createLayout(chunks, random, world);
    setBuildings(random);
  }
  
  private void setBuildings(Random random) {
    if (!this.block.isEmpty())
      for (CityBlock current : this.block) {
        if (current.area.width == 32 && current.area.length == 32) {
          int r = random.nextInt(6);
          switch (r) {
            case 0:
              current.buildingName = "LTA";
              break;
            case 1:
              current.buildingName = "LTB";
              break;
            case 2:
              current.buildingName = "LTC";
              break;
            case 3:
              current.buildingName = "LTD";
              break;
            case 4:
              current.buildingName = "LTE";
              break;
            case 5:
              current.buildingName = "LTF";
              break;
          }
        } else if (current.area.width == 16 && current.area.length == 16) {
          int r = random.nextInt(5);
          switch (r) {
            case 0:
              current.buildingName = "STA";
              break;
            case 1:
              current.buildingName = "STB";
              break;
            case 2:
              current.buildingName = "STC";
              break;
            case 3:
              current.buildingName = "SSA";
              break;
            case 4:
              current.buildingName = "SSB";
              break;
          }
        } else {
          int r = random.nextInt(6);
          switch (r) {
            case 0:
              current.buildingName = "MAA";
              break;
            case 1:
              current.buildingName = "MAB";
              break;
            case 2:
              current.buildingName = "MAC";
              break;
            case 3:
              current.buildingName = "MAD";
              break;
            case 4:
              current.buildingName = "MAE";
              break;
            case 5:
              current.buildingName = "MCA";
              break;
          }
        }
      }  
  }
  
  private void connectChunks(List<MultiVector> chunks) {
    for (int i = 0; i < chunks.size(); i++) {
      MultiVector current = chunks.get(i);
      for (MultiVector compare : chunks) {
        if (current.copy().add(new Vector(0, 0, 16)).equalsXZ(compare)) {
          current.connectedChunk[0] = compare;
        } else if (current.copy().add(new Vector(0, 0, -16)).equalsXZ(compare)) {
          current.connectedChunk[2] = compare;
        } else if (current.copy().add(new Vector(16, 0, 0)).equalsXZ(compare)) {
          current.connectedChunk[1] = compare;
        } else if (current.copy().add(new Vector(-16, 0, 0)).equalsXZ(compare)) {
          current.connectedChunk[3] = compare;
        }
      } 
    } 
  }
  
  private List<MultiVector> reduceHeightVariation(List<MultiVector> chunkLocationIn, MultiVector center) {
    List<MultiVector> chunkLocation = orderChunks(chunkLocationIn, center);
    int p1 = 0;
    int p2 = 0;
    for (int i = 1; i < chunkLocation.size(); i++) {
      MultiVector v = chunkLocation.get(i);
      if (v.X > center.X) {
        p1 = (v.connectedChunk[3] != null) ? (v.connectedChunk[3]).Y : 0;
        if (v.Z > center.Z) {
          p2 = (v.connectedChunk[2] != null) ? (v.connectedChunk[2]).Y : 0;
        } else if (v.Z < center.Z) {
          p2 = (v.connectedChunk[0] != null) ? (v.connectedChunk[0]).Y : 0;
        } 
      } else if (v.X < center.X) {
        p1 = (v.connectedChunk[1] != null) ? (v.connectedChunk[1]).Y : 0;
        if (v.Z > center.Z) {
          p2 = (v.connectedChunk[2] != null) ? (v.connectedChunk[2]).Y : 0;
        } else if (v.Z < center.Z) {
          p2 = (v.connectedChunk[0] != null) ? (v.connectedChunk[0]).Y : 0;
        } 
      } else if (v.Z > center.Z) {
        p1 = (v.connectedChunk[2] != null) ? (v.connectedChunk[2]).Y : 0;
      } else {
        p1 = (v.connectedChunk[0] != null) ? (v.connectedChunk[0]).Y : 0;
      } 
      if (p2 == 0 && p1 > 0) {
        if (v.Y > p1) {
          v.Y = p1 + 1;
        } else if (v.Y < p1) {
          v.Y = p1 - 1;
        } 
      } else if (p2 > 0 && p1 > 0) {
        if (Math.abs(p2 - p1) == 2) {
          v.Y = (p1 < p2) ? (p1 + 1) : (p2 + 1);
        } else if (Math.abs(p2 - p1) == 1) {
          int max;
          int min;
          if (p2 > p1) {
            max = p2;
            min = p1;
          } else {
            max = p1;
            min = p2;
          } 
          if (v.Y > max) {
            v.Y = max;
          } else if (v.Y < min) {
            v.Y = min;
          } 
        } else if (p2 == p1) {
          if (v.Y > p2) {
            v.Y = p2 + 1;
          } else if (v.Y < p2) {
            v.Y = p2 - 1;
          } 
        } else {
          System.out.println("Difference is too high: " + Math.abs(p2 - p1));
          v.Y = (int)((p2 + p1) / 2.0D);
        } 
      } else if (p2 == 0 && p1 == 0) {
        System.out.println("no near chunks found: " + v.toCustomString());
        double min = Vector.VtoVlengthXZ(v, center);
        double diff = min;
        int index = 0;
        for (int k = i - 1; k >= 0; k--) {
          diff = Vector.VtoVlengthXZ(v, chunkLocation.get(k));
          if (diff < min) {
            index = k;
            min = diff;
          } 
        } 
        v.Y = ((MultiVector)chunkLocation.get(index)).Y;
      } 
    } 
    return chunkLocation;
  }
  
  private List<MultiVector> orderChunks(List<MultiVector> chunkLocation, MultiVector center) {
    List<MultiVector> chunkLocationOut = new ArrayList<MultiVector>();
    chunkLocationOut.add(center);
    for (int i = chunkLocation.size() - 1; i >= 0; i--) {
      if (center.equalsXZ(chunkLocation.get(i)))
        chunkLocation.remove(i); 
    } 
    int radius = 1;
    while (!chunkLocation.isEmpty()) {
      for (int j = chunkLocation.size() - 1; j >= 0; j--) {
        MultiVector v = chunkLocation.get(j);
        int diff = Math.abs((center.X - v.X) / 16) + Math.abs((center.Z - v.Z) / 16);
        if (diff == radius) {
          chunkLocationOut.add(v);
          chunkLocation.remove(j);
        } 
      } 
      radius++;
    } 
    return chunkLocationOut;
  }
  
  private void createLayout(List<MultiVector> chunkLocation, Random random, World world) {
    Vector[] vectors = new Vector[chunkLocation.size()];
    for (int j = 0; j < vectors.length; j++)
      vectors[j] = ((MultiVector)chunkLocation.get(j)).copy(); 
    while (!chunkLocation.isEmpty()) {
      int k = chunkLocation.size() - 1;
      if (random.nextInt(4) == 0) {
        CityBlock block = largeBlock(chunkLocation.get(k), chunkLocation, k);
        this.block.add((block != null) ? block : smallBlock(chunkLocation.get(k), chunkLocation, k));
        continue;
      } 
      if (random.nextInt(2) == 0) {
        CityBlock block = midBlock(chunkLocation.get(k), chunkLocation, k, random);
        this.block.add((block != null) ? block : smallBlock(chunkLocation.get(k), chunkLocation, k));
        continue;
      } 
      this.block.add(smallBlock(chunkLocation.get(k), chunkLocation, k));
    } 
    for (int i = 0; i < this.block.size(); i++) {
      CityBlock block = this.block.get(i);
      setConnections(block, this.block);
      setCornerHeights(block, vectors);
      int maxD = 0;
      for (int k = 0; k < block.connectedBlocks.length; k++) {
        if (block.connectedBlocks[k] != null) {
          int diff = Math.abs(block.area.position.Y - (block.connectedBlocks[k]).area.position.Y);
          maxD = (diff > maxD) ? diff : maxD;
        } 
      } 
      block.doGenerate = (maxD < 3);
    } 
  }
  
  private CityBlock largeBlock(MultiVector p, List<MultiVector> c, int id) {
    int[] chunkID = getAdjChunksInd(p, c);
    if (chunkID[0] >= 0) {
      int[] northChunkIDs = getAdjChunksInd(new MultiVector(p.X, p.Y, p.Z + 16), c);
      if (chunkID[1] >= 0 && northChunkIDs[1] >= 0) {
        removeIDs(c, new int[] { id, chunkID[0], chunkID[1], northChunkIDs[1] });
        return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 32, 32));
      } 
      if (chunkID[3] >= 0 && northChunkIDs[3] >= 0) {
        removeIDs(c, new int[] { id, chunkID[0], chunkID[3], northChunkIDs[3] });
        return new CityBlock(new Rectangle(new Vector(p.X - 16, p.Y, p.Z), 32, 32));
      } 
    } else if (chunkID[2] >= 0) {
      int[] northChunkIDs = getAdjChunksInd(new MultiVector(p.X, p.Y, p.Z - 16), c);
      if (chunkID[1] >= 0 && northChunkIDs[1] >= 0) {
        removeIDs(c, new int[] { id, chunkID[2], chunkID[1], northChunkIDs[1] });
        return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z - 16), 32, 32));
      } 
      if (chunkID[3] >= 0 && northChunkIDs[3] >= 0) {
        removeIDs(c, new int[] { id, chunkID[2], chunkID[3], northChunkIDs[3] });
        return new CityBlock(new Rectangle(new Vector(p.X - 16, p.Y, p.Z - 16), 32, 32));
      } 
    } 
    return null;
  }
  
  private CityBlock midBlock(MultiVector p, List<MultiVector> c, int id, Random rand) {
    int[] chunkID = getAdjChunksInd(p, c);
    if (rand.nextInt(2) == 0) {
      if (chunkID[0] >= 0) {
        removeIDs(c, new int[] { id, chunkID[0] });
        return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 16, 32));
      } 
      if (chunkID[2] >= 0) {
        removeIDs(c, new int[] { id, chunkID[2] });
        return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z - 16), 16, 32));
      } 
      if (chunkID[1] >= 0) {
        removeIDs(c, new int[] { id, chunkID[1] });
        return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 32, 16));
      } 
      if (chunkID[3] >= 0) {
        removeIDs(c, new int[] { id, chunkID[3] });
        return new CityBlock(new Rectangle(new Vector(p.X - 16, p.Y, p.Z), 32, 16));
      } 
    } else {
      if (chunkID[1] >= 0) {
        removeIDs(c, new int[] { id, chunkID[1] });
        return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 32, 16));
      } 
      if (chunkID[3] >= 0) {
        removeIDs(c, new int[] { id, chunkID[3] });
        return new CityBlock(new Rectangle(new Vector(p.X - 16, p.Y, p.Z), 32, 16));
      } 
      if (chunkID[0] >= 0) {
        removeIDs(c, new int[] { id, chunkID[0] });
        return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 16, 32));
      } 
      if (chunkID[2] >= 0) {
        removeIDs(c, new int[] { id, chunkID[2] });
        return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z - 16), 16, 32));
      } 
    } 
    return null;
  }
  
  private CityBlock smallBlock(MultiVector p, List<MultiVector> c, int id) {
    removeIDs(c, new int[] { id });
    return new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 16, 16));
  }
  
  private void setConnections(CityBlock block, List<CityBlock> c) {
    int w = block.area.width / 16;
    int l = block.area.length / 16;
    block.connectedFaces = new boolean[2 * w + 2 * l];
    int i;
    for (i = 0; i < w; i++) {
      Vector chunk = block.area.position.copy();
      chunk.X += 16 * i;
      int[] adj = getAdjBlocksInd(chunk, c);
      if (adj[2] >= 0) {
        block.connectedFaces[2 * w + l - i - 1] = (adj[2] >= 0);
        block.setConnectedCityBlock(c.get(adj[2]), 2 * w + l - i - 1);
      } 
      chunk.Z = chunk.Z + block.area.length - 16;
      adj = getAdjBlocksInd(chunk, c);
      if (adj[0] >= 0) {
        block.connectedFaces[i] = (adj[0] >= 0);
        block.setConnectedCityBlock(c.get(adj[0]), i);
      } 
    } 
    for (i = 0; i < l; i++) {
      Vector chunk = block.area.position.copy();
      chunk.Z += 16 * i;
      int[] adj = getAdjBlocksInd(chunk, c);
      if (adj[3] >= 0) {
        block.connectedFaces[2 * w + l + i] = (adj[3] >= 0);
        block.setConnectedCityBlock(c.get(adj[3]), 2 * w + l + i);
      } 
      chunk.X = chunk.X + block.area.width - 16;
      adj = getAdjBlocksInd(chunk, c);
      if (adj[1] >= 0) {
        block.connectedFaces[w + l - i - 1] = (adj[1] >= 0);
        block.setConnectedCityBlock(c.get(adj[1]), w + l - i - 1);
      } 
    } 
  }
  
  private void setCornerHeights(CityBlock block, Vector[] vectors) {
    for (int i = 0; i < block.cornerHeight.length; i++) {
      Vector corner = block.getPositionFromCorner(i);
      for (Vector vector : vectors) {
        if (corner.equalsXZ(vector))
          block.cornerHeight[i] = vector.Y;
      } 
      if (block.cornerHeight[i] == 0) {
        boolean flag = true;
        for (int k = 0; k < vectors.length && flag; k++) {
          if (corner.copy().add(new Vector(0, 0, -16)).equalsXZ(vectors[k])) {
            block.cornerHeight[i] = (vectors[k]).Y;
          } else if (corner.copy().add(new Vector(-16, 0, 0)).equalsXZ(vectors[k])) {
            block.cornerHeight[i] = (vectors[k]).Y;
          } else if (corner.copy().add(new Vector(0, 0, 16)).equalsXZ(vectors[k])) {
            block.cornerHeight[i] = (vectors[k]).Y;
          } else if (corner.copy().add(new Vector(16, 0, 0)).equalsXZ(vectors[k])) {
            block.cornerHeight[i] = (vectors[k]).Y;
          } 
        } 
      } 
    } 
  }
  
  private int[] getAdjChunksInd(MultiVector p, List<MultiVector> c) {
    int[] ind = { -1, -1, -1, -1 };
    for (int i = 0; i < c.size(); i++) {
      int xD = p.X - ((MultiVector)c.get(i)).X;
      int zD = p.Z - ((MultiVector)c.get(i)).Z;
      if (xD == 0 && zD == -16) {
        ind[0] = i;
      } else if (xD == -16 && zD == 0) {
        ind[1] = i;
      } else if (xD == 0 && zD == 16) {
        ind[2] = i;
      } else if (xD == 16 && zD == 0) {
        ind[3] = i;
      } 
    } 
    return ind;
  }
  
  private int[] getAdjBlocksInd(Vector block, List<CityBlock> cityBlocks) {
    int[] ind = { -1, -1, -1, -1 };
    Rectangle north = new Rectangle(block.copy().add(new Vector(4, 0, 20)), 8, 8);
    Rectangle east = new Rectangle(block.copy().add(new Vector(20, 0, 4)), 8, 8);
    Rectangle south = new Rectangle(block.copy().add(new Vector(4, 0, -12)), 8, 8);
    Rectangle west = new Rectangle(block.copy().add(new Vector(-12, 0, 4)), 8, 8);
    for (int i = 0; i < cityBlocks.size(); i++) {
      if (Rectangle.checkConflict(north, ((CityBlock)cityBlocks.get(i)).area, 0)) {
        ind[0] = i;
      } else if (Rectangle.checkConflict(east, ((CityBlock)cityBlocks.get(i)).area, 0)) {
        ind[1] = i;
      } else if (Rectangle.checkConflict(south, ((CityBlock)cityBlocks.get(i)).area, 0)) {
        ind[2] = i;
      } else if (Rectangle.checkConflict(west, ((CityBlock)cityBlocks.get(i)).area, 0)) {
        ind[3] = i;
      } 
    } 
    return ind;
  }
  
  private void removeIDs(List<MultiVector> c, int[] id) {
    if (id.length > 1) {
      Arrays.sort(id);
      for (int i = id.length - 1; i >= 0; i--)
        c.remove(id[i]); 
    } else {
      c.remove(id[0]);
    } 
  }
}
