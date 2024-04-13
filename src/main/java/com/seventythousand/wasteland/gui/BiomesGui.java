

package com.seventythousand.wasteland.gui;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.biome.BiomeGenBase;

@SideOnly(Side.CLIENT)
public class BiomesGui extends GuiScreen {
  public static final int GUI_ID = 0;

  private int[][] map;

  private int mapSize = 0;

  private List<BiomeGenBase> biomesList;

  private int shiftLeftPercent;

  public BiomesGui(int[][] map, int size, List<BiomeGenBase> biomes) {
    this.map = map;
    this.mapSize = size;
    this.biomesList = biomes;
    this.shiftLeftPercent = 10;
  }

  public void initGui() {}

  public void drawScreen(int i, int j, float f) {
    if (this.map != null || this.mapSize > 0) {
      int pixelSize = (int)(0.85D * this.height / this.mapSize);
      drawDefaultBackground();
      drawBiomes(pixelSize);
      drawBiomeNames(this.biomesList, pixelSize);
    }
    super.drawScreen(i, j, f);
  }

  private void drawBiomeNames(List<BiomeGenBase> biomes, int pixelWidth) {
    int shiftX = (int)(this.shiftLeftPercent / 100.0D * this.width);
    FontRenderer font = (FMLClientHandler.instance().getClient()).fontRenderer;
    int width = ((pixelWidth & 0x1) == 0) ? pixelWidth : (int)(pixelWidth / 2.0D);
    int x = (int)(0.77D * this.width);
    int y = (int)(0.1D * this.height);
    for (int i = 0; i < biomes.size(); i++)
      drawString(font, ((BiomeGenBase)biomes.get(i)).biomeName, x - shiftX, y + i * (font.FONT_HEIGHT + 2), (((BiomeGenBase)biomes.get(i)).color & 0xFFFFFF) + -16777216);
  }

  private void drawBiomes(int pixelWidth) {
    int centerX, centerY, shiftX = (int)(this.shiftLeftPercent / 100.0D * this.width);
    if ((this.mapSize & 0x1) == 0) {
      centerX = this.width / 2;
      centerY = this.height / 2;
    } else {
      centerX = (this.width - pixelWidth) / 2;
      centerY = (this.height - pixelWidth) / 2;
    }
    for (int j = 0; j < this.mapSize; j++) {
      for (int i = 0; i < this.mapSize; i++)
        drawRect(centerX - this.mapSize / 2 * pixelWidth + i * pixelWidth - shiftX, centerY - this.mapSize / 2 * pixelWidth + j * pixelWidth, centerX - this.mapSize / 2 * pixelWidth + (i + 1) * pixelWidth - shiftX, centerY - this.mapSize / 2 * pixelWidth + (j + 1) * pixelWidth, this.map[j][i]);
    }
  }

  public boolean doesGuiPauseGame() {
    return false;
  }

  public void keyTyped(char c, int i) {
    super.keyTyped(c, i);
  }

  public void mouseClicked(int i, int j, int k) {
    super.mouseClicked(i, j, k);
  }

  public void updateScreen() {
    super.updateScreen();
  }

  public void onGuiClosed() {
    super.onGuiClosed();
  }

  public void actionPerformed(GuiButton b) {
    super.actionPerformed(b);
  }
}
