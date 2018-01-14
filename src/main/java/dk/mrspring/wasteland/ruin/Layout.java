package dk.mrspring.wasteland.ruin;


import java.util.Random;

import dk.mrspring.wasteland.utils.Rectangle;
import dk.mrspring.wasteland.utils.Vector;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class Layout 
{

	public Rectangle cPos;
	public Rectangle[] bPos;
	public int[] bRot;
	public int centerX;
	public int centerZ;
	public int villageDim;
	public int villageSize;
	
	public Layout(World world, Random rand, Building center, Building[] structures, int vPosX, int vPosZ, int dim, int size)
	{
		this.centerX = vPosX;
		this.centerZ = vPosZ;
		this.villageDim = dim;
		this.villageSize = size;
		if (center != null)
		{
			int offsetX = (int)(center.width/2.0f);
			int offsetZ = (int)(center.length/2.0f);
			this.cPos = new Rectangle(new Vector(vPosX-offsetX, world.getHeightValue(vPosX-offsetX, vPosZ-offsetZ) - 1, 
					vPosZ-offsetZ), center.width, center.length);
		}
		else
		{
			this.cPos = null;
		}
		
		bPos = new Rectangle[structures.length];
		for (int i = 0; i < bPos.length; i++)
		{
			bPos[i] = pickBestPos(world, rand, structures[i]);
		}
	}
	
	private Rectangle pickBestPos(World world, Random rand, Building b)
	{
		
		Vector cent = new Vector(this.centerX, getWorldHeight(world, this.centerX, this.centerZ), this.centerZ);
		boolean flag;
		Rectangle pos;
		int rot;
		int[] levels;
		int maxTries = 1000;
		
		for (int i = 0; i < maxTries; i++)
		{
			flag = true;
			rot = 0; //rand.nextInt(4);
			pos = new Rectangle(Vector.randomVector2D(rand, (villageDim/2), ((villageSize < 2) ? 4 : 6)).add(cent), b.width, b.length).rotate(rot);
			if (this.cPos != null)
			{
				if (Rectangle.checkConflict(pos, this.cPos, 2))
				{
					flag = false;
				}
			}
			for (int j = 0; j < this.bPos.length; j++)
			{
				if (bPos[j] != null)
				{
					if (Rectangle.checkConflict(pos, this.bPos[j], 2))
					{
						flag = false;
					}
				}
			}
			levels = getLevels(world, pos);
			if (flag && checkLevel(levels, ((b.width < 8) && (b.length < 8)) ? 0 : 1))
			{
				pos.position.Y = getAverageLevel(levels) - 1;//getWorldHeight(world, pos.position.X, pos.position.Z) - 1;
				return pos;
			}
			
		}
		System.out.println("Skipping building");
		return null;
	}
	
	public static int[] getLevels(World world, Rectangle rect) 
	{
		int[] height = new int[rect.length*rect.width];
		for (int j = 0; j < rect.length; j++)
		{
			for (int i = 0; i < rect.width; i++)
			{
				height[j*rect.width + i] = getWorldHeight(world, rect.position.X+i, rect.position.Z+j);
			}
		}
		return height;
	}

	public static boolean checkLevel(int[] levels, int maxVar) 
	{
		int maxHeight = levels[0];
		int minHeight = maxHeight;
		int height;
		
		for (int j = 0; j < levels.length; j++)
		{
			maxHeight = (levels[j] > maxHeight) ? levels[j] : maxHeight;
			minHeight = (levels[j] < minHeight) ? levels[j] : minHeight;	
		}
		return ((maxHeight - minHeight) <= maxVar);
	}
	
	public static int getAverageLevel(int[] levels) 
	{
		float level = 0;
		
		for (int j = 0; j < levels.length; j++)
		{
			level = level + (float)levels[j];
		}
		return (int)Math.round((level/(float)(levels.length)));
	}
	
	public static int getMinLevel(int[] levels)
	{
		int min;
		if (levels != null && levels.length > 0)
		{
			min = levels[0];
			for (int i = 0; i < levels.length; i++)
			{
				min = levels[i] < min ? levels[i] : min;
			}
			return min;
		}
		return -1;
	}
	
	public static int getWorldHeight(World world, int x, int z)
	{
		int worldHeight = world.getHeightValue(x, z);
		if (worldHeight == 0)
		{
			Chunk chunk = world.getChunkFromBlockCoords(x, z);
			world.getChunkProvider().loadChunk(chunk.xPosition, chunk.zPosition);
			worldHeight = world.getHeightValue(x, z);
		}
		
		Block block = world.getBlock(x, worldHeight, z);
		if (worldHeight == 0) 
		{
			System.out.println("World height still 0");
		}
		else if (block.equals(Blocks.deadbush))
		{
			worldHeight = worldHeight - 1;
			//System.out.println("Its a bush");
		}
		
		return worldHeight;
	}

}
