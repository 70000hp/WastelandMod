

package myid.chiqors.wasteland.world.gen;

import myid.chiqors.wasteland.config.ModConfig;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class WorldGenWastelandBigTree extends WorldGenBigTree {
  static final byte[] otherCoordPairs = new byte[] { 2, 0, 0, 1, 2, 1 };
  
  Random rand = new Random();
  
  World worldObj;
  
  int[] basePos = new int[] { 0, 0, 0 };
  
  int heightLimit = 0;
  
  int height;
  
  double heightAttenuation = 0.45D;
  
  double branchDensity = 0.25D;
  
  double branchSlope = 0.2D;
  
  double scaleWidth = 1.0D;
  
  double leafDensity = 1.0D;
  
  int trunkSize = 1;
  
  int heightLimitLimit = 12;
  
  int leafDistanceLimit = 4;
  
  int[][] leafNodes;
  
  Block block;
  
  int meta;
  
  public WorldGenWastelandBigTree(boolean par1) {
    super(par1);
    this.block = Blocks.log;
    this.meta = 0;
  }
  
  private void generateLeafNodeList() {
    this.height = (int)(this.heightLimit * this.heightAttenuation);
    if (this.height >= this.heightLimit)
      this.height = this.heightLimit - 1; 
    int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
    if (var1 < 1)
      var1 = 1; 
    int[][] var2 = new int[var1 * this.heightLimit][4];
    int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
    int var4 = 1;
    int var5 = this.basePos[1] + this.height;
    int var6 = var3 - this.basePos[1];
    var2[0][0] = this.basePos[0];
    var2[0][1] = var3;
    var2[0][2] = this.basePos[2];
    var2[0][3] = var5;
    var3--;
    while (var6 >= 0) {
      int var7 = 0;
      float var8 = layerSize(var6);
      if (var8 < 0.0F) {
        var3--;
        var6--;
        continue;
      } 
      for (double var9 = 0.5D; var7 < var1; var7++) {
        double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
        double var13 = this.rand.nextFloat() * 2.0D * Math.PI;
        int var15 = MathHelper.floor_double(var11 * Math.sin(var13) + this.basePos[0] + var9);
        int var16 = MathHelper.floor_double(var11 * Math.cos(var13) + this.basePos[2] + var9);
        int[] var17 = { var15, var3, var16 };
        int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };
        if (checkBlockLine(var17, var18) == -1) {
          int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
          double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
          double var22 = var20 * this.branchSlope;
          if (var17[1] - var22 > var5) {
            var19[1] = var5;
          } else {
            var19[1] = (int)(var17[1] - var22);
          } 
          if (checkBlockLine(var19, var17) == -1) {
            var2[var4][0] = var15;
            var2[var4][1] = var3;
            var2[var4][2] = var16;
            var2[var4][3] = var19[1];
            var4++;
          } 
        } 
      } 
      var3--;
      var6--;
    } 
    this.leafNodes = new int[var4][4];
    System.arraycopy(var2, 0, this.leafNodes, 0, var4);
  }
  
  void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block par6) {
    int var7 = (int)(par4 + 0.618D);
    byte var8 = otherCoordPairs[par5];
    byte var9 = otherCoordPairs[par5 + 3];
    int[] var10 = { par1, par2, par3 };
    int[] var11 = { 0, 0, 0 };
    int var12 = -var7;
    int var13 = -var7;
    for (var11[par5] = var10[par5]; var12 <= var7; var12++) {
      var11[var8] = var10[var8] + var12;
      var13 = -var7;
      while (var13 <= var7) {
        double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
        if (var15 > (par4 * par4)) {
          var13++;
          continue;
        } 
        var11[var9] = var10[var9] + var13;
        Block var14 = this.worldObj.getBlock(var11[0], var11[1], var11[2]);
        if (var14 != Blocks.air && var14 != Blocks.leaves) {
          var13++;
          continue;
        } 
        setBlockAndNotifyAdequately(this.worldObj, var11[0], var11[1], var11[2], par6, 0);
        var13++;
      } 
    } 
  }
  
  float layerSize(int par1) {
    float var4;
    if (par1 < this.heightLimit * 0.3D)
      return -1.618F; 
    float var2 = this.heightLimit / 2.0F;
    float var3 = this.heightLimit / 2.0F - par1;
    if (var3 == 0.0F) {
      var4 = var2;
    } else if (Math.abs(var3) >= var2) {
      var4 = 0.0F;
    } else {
      var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
    } 
    var4 *= 0.5F;
    return var4;
  }
  
  float leafSize(int par1) {
    return (par1 >= 0 && par1 < this.leafDistanceLimit) ? ((par1 != 0 && par1 != this.leafDistanceLimit - 1) ? 3.0F : 2.0F) : -1.0F;
  }
  
  void generateLeafNode(int par1, int par2, int par3) {
    int var4 = par2;
    float var6;
    for (int var5 = par2 + this.leafDistanceLimit; var4 < var5; var4++)
       var6 = leafSize(var4 - par2);
  }
  
  void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3, int meta) {
    int[] var4 = { 0, 0, 0 };
    byte var5 = 0;
    byte var6;
    for (var6 = 0; var5 < 3; var5 = (byte)(var5 + 1)) {
      var4[var5] = par2ArrayOfInteger[var5] - par1ArrayOfInteger[var5];
      if (Math.abs(var4[var5]) > Math.abs(var4[var6]))
        var6 = var5; 
    } 
    if (var4[var6] != 0) {
      byte var9, var7 = otherCoordPairs[var6];
      byte var8 = otherCoordPairs[var6 + 3];
      if (var4[var6] > 0) {
        var9 = 1;
      } else {
        var9 = -1;
      } 
      double var10 = var4[var7] / var4[var6];
      double var12 = var4[var8] / var4[var6];
      int[] var14 = { 0, 0, 0 };
      int var15 = 0;
      for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9) {
        var14[var6] = MathHelper.floor_double((par1ArrayOfInteger[var6] + var15) + 0.5D);
        var14[var7] = MathHelper.floor_double(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
        var14[var8] = MathHelper.floor_double(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
        byte var17 = 0;
        int var18 = Math.abs(var14[0] - par1ArrayOfInteger[0]);
        int var19 = Math.abs(var14[2] - par1ArrayOfInteger[2]);
        int var20 = Math.max(var18, var19);
        if (var20 > 0)
          if (var18 == var20) {
            var17 = 4;
          } else if (var19 == var20) {
            var17 = 8;
          }  
        setBlockAndNotifyAdequately(this.worldObj, var14[0], var14[1], var14[2], par3, var17 + meta);
      } 
    } 
  }
  
  void generateLeaves() {
    int var1 = 0;
    for (int var2 = this.leafNodes.length; var1 < var2; var1++) {
      int var3 = this.leafNodes[var1][0];
      int var4 = this.leafNodes[var1][1];
      int var5 = this.leafNodes[var1][2];
      generateLeafNode(var3, var4, var5);
    } 
  }
  
  boolean leafNodeNeedsBase(int par1) {
    return (par1 >= this.heightLimit * 0.2D);
  }
  
  void generateTrunk() {
    int var1 = this.basePos[0];
    int var2 = this.basePos[1];
    int var3 = this.basePos[1] + this.height;
    int var4 = this.basePos[2];
    int[] var5 = { var1, var2, var4 };
    int[] var6 = { var1, var3, var4 };
    placeBlockLine(var5, var6, this.block, this.meta);
  }
  
  void generateLeafNodeBases() {
    int var1 = 0;
    int var2 = this.leafNodes.length;
    for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++) {
      int[] var4 = this.leafNodes[var1];
      int[] var5 = { var4[0], var4[1], var4[2] };
      var3[1] = var4[3];
      int var6 = var3[1] - this.basePos[1];
      if (leafNodeNeedsBase(var6))
        placeBlockLine(var3, var5, this.block, this.meta); 
    } 
  }
  
  int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
    byte var8;
    int[] var3 = { 0, 0, 0 };
    byte var4 = 0;
    byte var5;
    for (var5 = 0; var4 < 3; var4 = (byte)(var4 + 1)) {
      var3[var4] = par2ArrayOfInteger[var4] - par1ArrayOfInteger[var4];
      if (Math.abs(var3[var4]) > Math.abs(var3[var5]))
        var5 = var4; 
    } 
    if (var3[var5] == 0)
      return -1; 
    byte var6 = otherCoordPairs[var5];
    byte var7 = otherCoordPairs[var5 + 3];
    if (var3[var5] > 0) {
      var8 = 1;
    } else {
      var8 = -1;
    } 
    double var9 = var3[var6] / var3[var5];
    double var11 = var3[var7] / var3[var5];
    int[] var13 = { 0, 0, 0 };
    int var14 = 0;
    int var15;
    for (var15 = var3[var5] + var8; var14 != var15; var14 += var8) {
      var13[var5] = par1ArrayOfInteger[var5] + var14;
      var13[var6] = MathHelper.floor_double(par1ArrayOfInteger[var6] + var14 * var9);
      var13[var7] = MathHelper.floor_double(par1ArrayOfInteger[var7] + var14 * var11);
      Block var16 = this.worldObj.getBlock(var13[0], var13[1], var13[2]);
      if (var16 != Blocks.air && var16 != Blocks.leaves)
        break; 
    } 
    return (var14 == var15) ? -1 : Math.abs(var14);
  }
  
  boolean validBigTreeLocation() {
    Block var3 = this.worldObj.getBlock(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
    if (var3.equals(ModConfig.getSurfaceBlock()))
      return true; 
    return false;
  }
  
  public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
    this.worldObj = par1World;
    long var6 = par2Random.nextLong();
    this.rand.setSeed(var6);
    this.basePos[0] = par3;
    this.basePos[1] = par4;
    this.basePos[2] = par5;
    if (this.heightLimit == 0)
      this.heightLimit = 12 + par2Random.nextInt(5); 
    if (!validBigTreeLocation())
      return false; 
    generateLeafNodeList();
    generateLeaves();
    generateTrunk();
    generateLeafNodeBases();
    return true;
  }
  
  public void setTreeType(Block block, int meta) {
    this.block = block;
    this.meta = meta;
  }
  
  public void setTreeType(int[] blockData) {
    setTreeType(Block.getBlockById(blockData[0]), blockData[1]);
  }
}
