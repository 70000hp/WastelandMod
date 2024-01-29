package myid.chiqors.wasteland.ruin.code.city;

import myid.chiqors.wasteland.ruin.code.city.LTD.LargeTowerDBot;
import myid.chiqors.wasteland.ruin.code.city.LTD.LargeTowerDMid;
import myid.chiqors.wasteland.ruin.code.city.LTD.LargeTowerDTop;

public class LargeTowerD {
  public static final int WIDTH = 17;
  
  public static final int LENGTH = 17;
  
  public static byte[][] getBlocks(int floors) {
    int a = 289;
    int o = 0;
    byte[][] out = new byte[2][a * (8 * floors + 9 + 8)];
    for (int k = 0; k < a * 8; k++) {
      out[0][k] = LargeTowerDBot.BLOCKS[k];
      out[1][k] = LargeTowerDBot.DATA[k];
    } 
    for (int j = 0; j < floors; j++) {
      o = a * (8 + 8 * j);
      for (int m = 0; m < a * 8; m++) {
        out[0][m + o] = LargeTowerDMid.BLOCKS[m];
        out[1][m + o] = LargeTowerDMid.DATA[m];
      } 
    } 
    o = a * (8 * floors + 8);
    for (int i = 0; i < a * 9; i++) {
      out[0][i + o] = LargeTowerDTop.BLOCKS[i];
      out[1][i + o] = LargeTowerDTop.DATA[i];
    } 
    return out;
  }
  
  public static int getTotalHeight(int floors) {
    return 8 * floors + 9 + 8;
  }
}
