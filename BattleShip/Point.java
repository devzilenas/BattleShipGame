public class Point
{
	private int x;
	private int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public boolean equals(Object o)
	{
		Point p = (Point) o;
		return p.getX() == getX() && p.getY() == getY();
	}

	public int hashCode()
	{
		return getY()*1000+ getX();
	}

	public String toString()
	{
		return "x:"+getX()+",y:"+getY();
	}
}
