

package com.seventythousand.wasteland.client;

import cpw.mods.fml.client.FMLClientHandler;
import com.seventythousand.wasteland.gui.BiomesGui;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.biome.BiomeGenBase;

public class ClientProxy {
  public static void displayBiomeMap(int[][] biomeMap, int i, List<BiomeGenBase> biomes) {
    FMLClientHandler.instance().getClient().displayGuiScreen((GuiScreen)new BiomesGui(biomeMap, 200, biomes));
  }
}
