package myid.chiqors.wastelands.utils;

import java.util.Random;

public class Vector 
{
	public int X;
	public int Y;
	public int Z;
	
	public Vector(int x, int y, int z)
	{
		this.X = x;
		this.Y = y;
		this.Z = z;
	}
	
	public Vector add(Vector v)
	{
		this.X = this.X + v.X;
		this.Y = this.Y + v.Y;
		this.Z = this.Z + v.Z;
		
		return this;
	}
	
	public Vector minus(Vector v)
	{
		this.X = this.X - v.X;
		this.Y = this.Y - v.Y;
		this.Z = this.Z - v.Z;
		
		return this;
	}
	
	public boolean equalsXZ(Vector v)
	{
		return ((this.X == v.X) && (this.Z == v.Z));
	}
	
	public boolean equals(Vector v)
	{
		return ((this.X == v.X) && (this.Z == v.Z) && (this.Y == v.Y));
	}
	
	public String toCustomString()
	{
		return ("X:" + String.valueOf(this.X) + " Y:" + String.valueOf(this.Y) + " Z:" + String.valueOf(this.Z));
	}
	
	public static double length(Vector v)
	{
		return Math.floor(Math.sqrt((double)(v.X*v.X + v.Y*v.Y + v.Z*v.Z)));
	}
	
	public static double VtoVlength(Vector v1, Vector v2)
	{
		return length(new Vector(v1.X - v2.X, v1.Y - v2.Y, v1.Z - v2.Z));
	}
	
	public static double VtoVlengthXZ(Vector v1, Vector v2) 
	{
		return length(new Vector(v1.X - v2.X, 0, v1.Z - v2.Z));
	}
	
	public static Vector randomVector3D(Random rand, int maxSize, int minSize)
	{
		int v1;
		int v2;
		int v3;
		
		if (rand.nextInt(2) == 0)
		{
			v1 = rand.nextInt(maxSize - minSize) + minSize;
		}
		else
		{
			v1 = 0 - minSize - rand.nextInt(maxSize - minSize);
		}
		
		if (rand.nextInt(2) == 0)
		{
			v2 = rand.nextInt(maxSize - minSize) + minSize;
		}
		else
		{
			v2 = 0 - minSize - rand.nextInt(maxSize - minSize);
		}
		
		if (rand.nextInt(2) == 0)
		{
			v3 = rand.nextInt(maxSize - minSize) + minSize;
		}
		else
		{
			v3 = 0 - minSize - rand.nextInt(maxSize - minSize);
		}
		
		
		return new Vector(v1,v2, v3);
	}
	
	public static Vector randomVector2D(Random rand, int maxSize, int minSize)
	{
		int v1;
		int v2;
		
		if (rand.nextInt(2) == 0)
		{
			v1 = rand.nextInt(maxSize - minSize) + minSize;
		}
		else
		{
			v1 = 0 - minSize - rand.nextInt(maxSize - minSize);
		}
		
		if (rand.nextInt(2) == 0)
		{
			v2 = rand.nextInt(maxSize - minSize) + minSize;
		}
		else
		{
			v2 = 0 - minSize - rand.nextInt(maxSize - minSize);
		}
		
		return new Vector(v1,0,v2);
	}


}
