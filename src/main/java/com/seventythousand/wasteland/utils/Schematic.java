

package com.seventythousand.wasteland.utils;

import com.seventythousand.wasteland.world.WastelandWorldData;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Schematic {
  public int width;

  public int length;

  public int height;

  public byte[] blocks;

  public byte[] data;

  public int chestNum;

  public Schematic(String fileName) {
    try {
      WastelandWorldData.loadSchematic(fileName + ".schematic", this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void load(int w, int l, int h, byte[] b, byte[] d) {
    this.width = w;
    this.length = l;
    this.height = h;
    this.blocks = b;
    this.data = d;
    this.chestNum = 0;
    int chestID = Block.getIdFromBlock((Block)Blocks.chest);
      for (byte block : this.blocks) {
          if (block == chestID)
              this.chestNum++;
      }
  }
}
