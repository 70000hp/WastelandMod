package dk.mrspring.wasteland.utils;

public class Rectangle 
{
	public Vector position;
	public int width;
	public int length;
	public int rotation;

	public Rectangle(Vector pos, int w, int l)
	{
		this.position = pos;
		this.width = w;
		this.length = l;
	}
	
	public static boolean checkConflict(Rectangle rectA, Rectangle rectB, int minDist)
	{
		//return false;
		if ((rectA.position.X>(rectB.position.X + rectB.width + minDist)) || ((rectA.position.X+rectA.width)<rectB.position.X - minDist) || 
				((rectA.position.Z+rectA.length)<rectB.position.Z - minDist) || (rectA.position.Z>(rectB.position.Z + rectB.length + minDist)))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public Rectangle rotate(int dir)
	{
		this.rotation = dir;
		if (dir == 1 || dir == 3)
		{
			int w = this.width;
			this.width = this.length;
			this.length = w;
		}
		
		return this;
	}
}
