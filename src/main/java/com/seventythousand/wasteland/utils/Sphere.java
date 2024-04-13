package com.seventythousand.wasteland.utils;

import java.util.List;

public class Sphere {
  public Vector location;

  public int radius;

  public List<Vector> grid;

  public Sphere(Vector loc, int rad) {
    this.location = loc;
    this.radius = rad;
  }

  public byte[] makeSphereOf(byte[] array, int w, int l, int h, byte replace) {
    int count = 0;
    for (int k = 0; k < h; k++) {
      for (int j = 0; j < l; j++) {
        for (int i = 0; i < w; i++) {
          Vector pos = new Vector(i, j, k);
          if (Vector.VtoVlength(pos, this.location) <= this.radius)
            array[count] = replace;
          count++;
        }
      }
    }
    return array;
  }
}
