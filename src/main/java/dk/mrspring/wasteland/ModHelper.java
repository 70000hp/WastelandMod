package dk.mrspring.wasteland;

import net.minecraft.util.MathHelper;

public class ModHelper
{
	public class Directions
	{
		public static final int SOUTH = 0;
		public static final int WEST = 1;
		public static final int NORTH = 2;
		public static final int EAST = 3;
	}
	
	public static class ModInfo
	{
		public static final String modid = "WLM";
		public static final String name = "The Wasteland Mod";
		public static final String version = "1.3.0";
		public static final boolean isDev = version.contains("DEV");
	}
	
	public static final float pixel = 0.0625F;
	
	/**
	 * Returns a string formatted: "X, Y, Z"
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static String getCoordAsString(int x, int y, int z)
	{
		return String.valueOf(x + ", " + y + ", " + z);
	}
	
	/**
	 * Returns a string formatted: "X, Z"
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public static String getCoordAsString(int x, int z)
	{
		return String.valueOf(x + ", " + z);
	}
	
	/**
	 * Returns which direction an entity is facing.
	 * 
	 * 0: SOUTH
	 * 1: WEST
	 * 2: NORTH
	 * 3: EAST
	 * 
	 * Used when making blocks face the player placing it.
	 * 
	 * @param rotationYaw
	 * @return
	 */
	public static int getEntityRotationAsDirection(float rotationYaw)
	{
		return MathHelper.floor_double((double)(rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	}
}