import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Point
{
	int x;
	int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
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

	/**
	 * @see #toString()
	 */
	public static Point fromString(String str)
		throws PointConversionException
	{
		Point   point = null;
		Pattern p = Pattern.compile("x:(\\d),y:(\\d)");
		Matcher m = p.matcher(str);
		if (m.matches() && 2 == m.groupCount())
		{
			point = new Point(m.group(1), m.group(2));
		}
		else
		{
			throw new PointConversionException();
		}

		return point;
	}
}
