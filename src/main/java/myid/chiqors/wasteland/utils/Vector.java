package myid.chiqors.wasteland.utils;

import java.util.Random;

public class Vector {
  public int X;
  
  public int Y;
  
  public int Z;
  
  public Vector(int x, int y, int z) {
    this.X = x;
    this.Y = y;
    this.Z = z;
  }
  
  public Vector add(Vector v) {
    this.X += v.X;
    this.Y += v.Y;
    this.Z += v.Z;
    return this;
  }
  
  public Vector minus(Vector v) {
    this.X -= v.X;
    this.Y -= v.Y;
    this.Z -= v.Z;
    return this;
  }
  
  public boolean equalsXZ(Vector v) {
    return (this.X == v.X && this.Z == v.Z);
  }
  
  public boolean equals(Vector v) {
    return (this.X == v.X && this.Z == v.Z && this.Y == v.Y);
  }
  
  public String toCustomString() {
    return "X:" + this.X + " Y:" + this.Y + " Z:" + this.Z;
  }
  
  public static double length(Vector v) {
    return Math.floor(Math.sqrt((v.X * v.X + v.Y * v.Y + v.Z * v.Z)));
  }
  
  public static double VtoVlength(Vector v1, Vector v2) {
    return length(new Vector(v1.X - v2.X, v1.Y - v2.Y, v1.Z - v2.Z));
  }
  
  public static double VtoVlengthXZ(Vector v1, Vector v2) {
    return length(new Vector(v1.X - v2.X, 0, v1.Z - v2.Z));
  }
  
  public static Vector randomVector3D(Random rand, int maxSize, int minSize) {
    double a1 = rand.nextInt(360) * 0.017453292519943295D;
    double a2 = rand.nextInt(180) * 0.017453292519943295D;
    double r = (rand.nextInt(maxSize - minSize + 1) + minSize);
    int x = (int)(Math.cos(a1) * Math.sin(a2) * r);
    int z = (int)(Math.sin(a1) * Math.sin(a2) * r);
    int y = (int)(Math.cos(a2) * r);
    return new Vector(x, y, z);
  }
  
  public static Vector randomVector2D(Random rand, int maxSize, int minSize) {
    double a1 = rand.nextInt(360) * 0.017453292519943295D;
    double r = (rand.nextInt(maxSize - minSize + 1) + minSize);
    int x = (int)(Math.cos(a1) * r);
    int z = (int)(Math.sin(a1) * r);
    return new Vector(x, 0, z);
  }
  
  public Vector copy() {
    return new Vector(this.X, this.Y, this.Z);
  }
}
