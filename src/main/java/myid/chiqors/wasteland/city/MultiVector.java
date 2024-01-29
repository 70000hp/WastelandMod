package myid.chiqors.wasteland.city;

import myid.chiqors.wasteland.utils.Vector;

public class MultiVector extends Vector {
  MultiVector[] connectedChunk;
  
  public MultiVector(int x, int y, int z) {
    super(x, y, z);
    this.connectedChunk = new MultiVector[] { null, null, null, null };
  }
  
  public MultiVector copy() {
    return new MultiVector(this.X, this.Y, this.Z);
  }
}
