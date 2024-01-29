package myid.chiqors.wasteland.ruin.code.city;

import myid.chiqors.wasteland.ruin.code.city.LTF.LargeTowerFBot;
import myid.chiqors.wasteland.ruin.code.city.LTF.LargeTowerFMid;
import myid.chiqors.wasteland.ruin.code.city.LTF.LargeTowerFTop;

public class LargeTowerF {
  public static final int WIDTH = 17;
  
  public static final int LENGTH = 17;
  
  public static byte[][] getBlocks(int floors) {
    int a = 289;
    int o = 0;
    byte[][] out = new byte[2][a * (8 * floors + 4 + 4)];
    for (int k = 0; k < a * 4; k++) {
      out[0][k] = LargeTowerFBot.BLOCKS[k];
      out[1][k] = LargeTowerFBot.DATA[k];
    } 
    for (int j = 0; j < floors; j++) {
      o = a * (4 + 8 * j);
      for (int m = 0; m < a * 8; m++) {
        out[0][m + o] = LargeTowerFMid.BLOCKS[m];
        out[1][m + o] = LargeTowerFMid.DATA[m];
      } 
    } 
    o = a * (8 * floors + 4);
    for (int i = 0; i < a * 4; i++) {
      out[0][i + o] = LargeTowerFTop.BLOCKS[i];
      out[1][i + o] = LargeTowerFTop.DATA[i];
    } 
    return out;
  }
  
  public static int getTotalHeight(int floors) {
    return 8 * floors + 4 + 4;
  }
}
