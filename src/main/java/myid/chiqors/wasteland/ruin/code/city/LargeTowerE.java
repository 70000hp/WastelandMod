package myid.chiqors.wasteland.ruin.code.city;

import myid.chiqors.wasteland.ruin.code.city.LTE.LargeTowerEBot;
import myid.chiqors.wasteland.ruin.code.city.LTE.LargeTowerEMid;
import myid.chiqors.wasteland.ruin.code.city.LTE.LargeTowerETop;

public class LargeTowerE {
  public static final int WIDTH = 18;
  
  public static final int LENGTH = 18;
  
  public static byte[][] getBlocks(int floors) {
    int a = 324;
    int o = 0;
    byte[][] out = new byte[2][a * (10 * floors + 7 + 11)];
    for (int k = 0; k < a * 11; k++) {
      out[0][k] = LargeTowerEBot.BLOCKS[k];
      out[1][k] = LargeTowerEBot.DATA[k];
    } 
    for (int j = 0; j < floors; j++) {
      o = a * (11 + 10 * j);
      for (int m = 0; m < a * 10; m++) {
        out[0][m + o] = LargeTowerEMid.BLOCKS[m];
        out[1][m + o] = LargeTowerEMid.DATA[m];
      } 
    } 
    o = a * (10 * floors + 11);
    for (int i = 0; i < a * 7; i++) {
      out[0][i + o] = LargeTowerETop.BLOCKS[i];
      out[1][i + o] = LargeTowerETop.DATA[i];
    } 
    return out;
  }
  
  public static int getTotalHeight(int floors) {
    return 10 * floors + 7 + 11;
  }
}
