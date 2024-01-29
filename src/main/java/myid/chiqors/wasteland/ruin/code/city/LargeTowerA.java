package myid.chiqors.wasteland.ruin.code.city;

import myid.chiqors.wasteland.ruin.code.city.LTA.LargeTowerABot;
import myid.chiqors.wasteland.ruin.code.city.LTA.LargeTowerAMid;
import myid.chiqors.wasteland.ruin.code.city.LTA.LargeTowerATop;

public class LargeTowerA {
  public static final int WIDTH = 15;
  
  public static final int LENGTH = 15;
  
  public static byte[][] getBlocks(int floors) {
    int a = 225;
    int o = 0;
    byte[][] out = new byte[2][a * (5 * floors + 39 + 6)];
    for (int k = 0; k < a * 6; k++) {
      out[0][k] = LargeTowerABot.BLOCKS[k];
      out[1][k] = LargeTowerABot.DATA[k];
    } 
    for (int j = 0; j < floors; j++) {
      o = a * (6 + 5 * j);
      for (int m = 0; m < a * 5; m++) {
        out[0][m + o] = LargeTowerAMid.BLOCKS[m];
        out[1][m + o] = LargeTowerAMid.DATA[m];
      } 
    } 
    o = a * (5 * floors + 6);
    for (int i = 0; i < a * 39; i++) {
      out[0][i + o] = LargeTowerATop.BLOCKS[i];
      out[1][i + o] = LargeTowerATop.DATA[i];
    } 
    return out;
  }
  
  public static int getTotalHeight(int floors) {
    return 5 * floors + 39 + 6;
  }
}
