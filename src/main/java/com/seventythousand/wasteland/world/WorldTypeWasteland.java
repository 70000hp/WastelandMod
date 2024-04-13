

package com.seventythousand.wasteland.world;

import com.seventythousand.wasteland.world.gen.WastelandGeneratorInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class WorldTypeWasteland extends WorldType {
  public static WastelandGeneratorInfo genInfo = new WastelandGeneratorInfo();

  public WorldTypeWasteland(String name) {
    super(name);
  }

  public WorldChunkManager getChunkManager(World world) {
    return new WorldChunkManagerWasteland(world);
  }

  public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
    return new ChunkProviderWasteland(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
  }

  public boolean isCustomizable() {
    return true;
  }

  @SideOnly(Side.CLIENT)
  public void onCustomizeButton(Minecraft instance, GuiCreateWorld guiCreateWorld) {}

  public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer) {
    GenLayer ret = new WastelandGenLayerBiome(200L, parentLayer);
    ret = GenLayerZoom.magnify(1000L, ret, 2);
    return (GenLayer)new GenLayerBiomeEdge(1000L, ret);
  }
}
