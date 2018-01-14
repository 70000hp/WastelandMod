package dk.mrspring.wasteland.city;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.wasteland.ModConfig;
import dk.mrspring.wasteland.RuinConfig;
import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.ruin.Building.LootStack;
import dk.mrspring.wasteland.ruin.code.BuildingCode;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.utils.Vector;
import dk.mrspring.wasteland.world.WastelandWorldData;

public class CityGenerator implements IWorldGenerator
{
	public static List<Vector> cityLocation;
	public static int cityNum;
	private boolean generating;
	private boolean loadedWorld;
	
	public CityGenerator()
	{
		GameRegistry.registerWorldGenerator(this.toIWorldGenerator(), 10);
		cityLocation = new ArrayList<Vector>();
		cityNum = 0;
		generating = false;
		loadedWorld = false;
	}

	public IWorldGenerator toIWorldGenerator()
	{
		IWorldGenerator generator = (IWorldGenerator) this;
		
		return generator;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.dimensionId == 0 && world.getBiomeGenForCoords(chunkX*16, chunkZ*16) == WastelandBiomes.city && this.loadedWorld)
		{
			generateCity(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}
	
	
	public void generateCity(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		
		Vector currentLoc = new Vector(chunkX * 16, world.getHeightValue((chunkX * 16), (chunkZ * 16)),  (chunkZ * 16));
		
		if (checkDist(currentLoc, ModConfig.minCityDistance*16) && !this.generating && !world.isRemote)
		{
			generating = true;

			List<Vector> chunks = new ArrayList<Vector>();
			chunks.add(currentLoc);
			addConnectedBiomeChunks(chunks, currentLoc, world);
			Vector center = getCenterChunk(chunks, world);
			
			
			if (chunks.size() > 8)
			{
				System.out.println("Generating City at X:" + String.valueOf(center.X) + " Z:" + String.valueOf(center.Z));
				cityLocation.add(center);
				cityNum++;
				
				RuinedCity city = new RuinedCity(world, center, chunks, random);
				city.generate(world, random);
			}
			
			this.generating = false;
		}
		
	}


	private Vector getCenterChunk(List<Vector> chunks, World world) 
	{
		int maxX = chunks.get(0).X;
		int minX = maxX;
		int maxZ = chunks.get(0).Z;
		int minZ = maxZ;
		
		for (int i = 1; i < chunks.size(); i++)
		{
			maxX = (chunks.get(i).X > maxX) ? chunks.get(i).X : maxX;
			minX = (chunks.get(i).X < minX) ? chunks.get(i).X : minX;
			maxZ = (chunks.get(i).Z > maxZ) ? chunks.get(i).Z : maxZ;
			minZ = (chunks.get(i).Z < minZ) ? chunks.get(i).Z : minZ;
		}
		
		int cX = ((int)(((maxX - minX)/2) + minX)) & 0xF0;
		int cZ = ((int)(((maxZ - minZ)/2) + minZ)) & 0xF0;
		int h = getWorldHeight(world, cX, cZ);
		return new Vector(cX, h, cZ);
	}

	private void addConnectedBiomeChunks(List<Vector> chunks, Vector position, World world) 
	{
		int biomeID = world.getBiomeGenForCoords(position.X, position.Z).biomeID;
		
		for (int i = 0; i < 4; i++)
		{
			Vector current = chooseChunk(i, position);
			if (world.getBiomeGenForCoords(current.X, current.Z).biomeID == biomeID)
			{
				for (int j = 0; j < chunks.size(); j++)
				{
					if (!chunks.get(j).equalsXZ(current))
					{
						chunks.add(current);
					}
				}
			}
		}
		for (int i = 0; i < 4; i++)
		{
			Vector current = chooseChunk(i, position);
			if (world.getBiomeGenForCoords(current.X, current.Z).biomeID == biomeID)
			{
				addConnectedBiomeChunks(chunks, current, world);
			}
		}
	}

	private Vector chooseChunk(int i, Vector position)
	{
		Vector pos;
		
		if (i == 0)
		{
			pos = new Vector(position.X + 16, position.Y, position.Z);
		}
		else if (i == 1)
		{
			pos = new Vector(position.X - 16, position.Y, position.Z);
		}
		else if (i == 2)
		{
			pos = new Vector(position.X, position.Y, position.Z + 16);
		}
		else
		{
			pos = new Vector(position.X, position.Y, position.Z - 16);
		}
		
		return pos;
	}
	
	private boolean checkDist(Vector current, double distance)
	{
		for (int i = 0; i < cityLocation.size(); i++)
		{
			if (Vector.VtoVlength(current, cityLocation.get(i)) < distance)
			{
				return false;
			}
		}
		return true;
	}
	
	public void resetData()
	{
		this.generating = false;
		this.cityNum = 0;
		this.cityLocation.clear();
		this.loadedWorld = true;
	}
	
	public static int getWorldHeight(World world, int x, int z)
	{
		int worldHeight = world.getHeightValue(x, z);
		if (worldHeight == 0)
		{
			//Chunk chunk = world.getChunkFromBlockCoords(x, z);
			world.getChunkProvider().loadChunk(x >> 4, z >> 4);
			worldHeight = world.getHeightValue(x, z);
		}
		
		if (worldHeight == 0) 
		{
			System.out.println("World height still 0");
		}
		return worldHeight;
	}

	public void loadData(List<Vector> villageLoc, int size) 
	{
		this.cityLocation = villageLoc;
		this.cityNum = size;
		this.loadedWorld = true;
	}
	
	public void saveData(WastelandWorldData data)
	{
		data.saveCityData(cityLocation);
	}

}
