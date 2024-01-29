package myid.chiqors.wasteland.ruin.code.city;

import myid.chiqors.wasteland.ruin.code.city.LTC.LargeTowerCBot;
import myid.chiqors.wasteland.ruin.code.city.LTC.LargeTowerCMid;
import myid.chiqors.wasteland.ruin.code.city.LTC.LargeTowerCTop;

public class LargeTowerC {
  public static final int WIDTH = 19;
  
  public static final int LENGTH = 19;
  
  public static byte[][] getBlocks(int floors) {
    int a = 361;
    int o = 0;
    byte[][] out = new byte[2][a * (12 * floors + 7 + 12)];
    for (int k = 0; k < a * 12; k++) {
      out[0][k] = LargeTowerCBot.BLOCKS[k];
      out[1][k] = LargeTowerCBot.DATA[k];
    } 
    for (int j = 0; j < floors; j++) {
      o = a * (12 + 12 * j);
      for (int m = 0; m < a * 12; m++) {
        out[0][m + o] = LargeTowerCMid.BLOCKS[m];
        out[1][m + o] = LargeTowerCMid.DATA[m];
      } 
    } 
    o = a * (12 * floors + 12);
    for (int i = 0; i < a * 7; i++) {
      out[0][i + o] = LargeTowerCTop.BLOCKS[i];
      out[1][i + o] = LargeTowerCTop.DATA[i];
    } 
    return out;
  }
  
  public static int getTotalHeight(int floors) {
    return 12 * floors + 7 + 12;
  }
}
