package myid.chiqors.wastelands.world;

import myid.chiqors.wastelands.WastelandBiomes;
import myid.chiqors.wastelands.client.GuiCreateWastelandWorld;
import myid.chiqors.wastelands.world.gen.WastelandGeneratorInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldTypeWasteland extends WorldType
{
	public static WastelandGeneratorInfo genInfo = new WastelandGeneratorInfo();
	
	public WorldTypeWasteland(String name)
	{
		super(name);
	}
	
	public WorldChunkManager getChunkManager(World world)
		{ return new WorldChunkManagerWasteland(world); }
	
	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions)
		{ return new ChunkProviderWasteland(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled()); }
	
	@Override
	public boolean isCustomizable()
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void onCustomizeButton(Minecraft instance, GuiCreateWorld guiCreateWorld)
	{
		//instance.displayGuiScreen(new GuiCreateWastelandWorld(guiCreateWorld, guiCreateWorld.field_146334_a, genInfo));
	}
	
	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new WastelandGenLayerBiome(200L, parentLayer, this);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
}
