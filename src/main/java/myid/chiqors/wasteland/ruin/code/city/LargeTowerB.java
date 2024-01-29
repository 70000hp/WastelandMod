package myid.chiqors.wasteland.ruin.code.city;

import myid.chiqors.wasteland.ruin.code.city.LTB.LargeTowerBBot;
import myid.chiqors.wasteland.ruin.code.city.LTB.LargeTowerBMid;
import myid.chiqors.wasteland.ruin.code.city.LTB.LargeTowerBTop;

public class LargeTowerB {
  public static final int WIDTH = 15;
  
  public static final int LENGTH = 15;
  
  public static byte[][] getBlocks(int floors, int roofType) {
    byte[] topB, topD;
    int topH;
    if (roofType == 0) {
      topB = LargeTowerBTop.TopA.DATA;
      topD = LargeTowerBTop.TopA.BLOCKS;
      topH = 12;
    } else if (roofType == 1) {
      topB = LargeTowerBTop.TopB.DATA;
      topD = LargeTowerBTop.TopB.BLOCKS;
      topH = 12;
    } else {
      topB = LargeTowerBTop.TopC.DATA;
      topD = LargeTowerBTop.TopC.BLOCKS;
      topH = 4;
    } 
    int a = 225;
    int o = 0;
    byte[][] out = new byte[2][a * (8 * floors + topH + 8)];
    for (int k = 0; k < a * 8; k++) {
      out[0][k] = LargeTowerBBot.BLOCKS[k];
      out[1][k] = LargeTowerBBot.DATA[k];
    } 
    for (int j = 0; j < floors; j++) {
      o = a * (8 + 8 * j);
      for (int m = 0; m < a * 8; m++) {
        out[0][m + o] = LargeTowerBMid.BLOCKS[m];
        out[1][m + o] = LargeTowerBMid.DATA[m];
      } 
    } 
    o = a * (8 * floors + 8);
    for (int i = 0; i < a * topH; i++) {
      out[0][i + o] = topB[i];
      out[1][i + o] = topD[i];
    } 
    return out;
  }
  
  public static int getTotalHeight(int floors, int roofType) {
    int topH;
    if (roofType == 0) {
      topH = 12;
    } else if (roofType == 1) {
      topH = 12;
    } else {
      topH = 4;
    } 
    return 8 * floors + topH + 8;
  }
}
