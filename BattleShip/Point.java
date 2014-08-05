import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;

public class Point
{
	int x;
	int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Point(Point point)
	{
		this(point.getX(), point.getY());
	}

	public Point(String string)
		throws PointConversionException
	{
		this(
				PointFactory.fromString(string).getX(),
				PointFactory.fromString(string).getY());
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getX()
	{
		return x;
	}

	public void setY(int y)
	{
		this.y = y;
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

	public static String pointsAsString(Point[] points)
	{
		StringBuilder sb = new StringBuilder();
		Point point = null;
		for (int i = 0 ; i < points.length; i++)
		{
			point = points[i];
			sb.append(point.toString());
			if (0 != i && points.length - 1 != i)
			{
				sb.append(";");
			}
		}
		return sb.toString();
	}
}
