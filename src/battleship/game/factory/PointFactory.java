package battleship.core.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

class PointFactory
{
	/**
	 * @see Point#toString()
	 */
	public static Point fromString(String str)
		throws PointConversionException
	{
		Point   point = null;
		Pattern p = Pattern.compile("x:(\\d),y:(\\d)");
		Matcher m = p.matcher(str);
		m.find();
		try
		{
			point = new Point(
					Integer.valueOf(m.group(1)), 
					Integer.valueOf(m.group(2)));
		}
		catch (IndexOutOfBoundsException|NumberFormatException e)
		{
			e.printStackTrace(System.err);
			System.err.println("Conversion Str->Point failed for string:"+str);
			throw new PointConversionException();
		}

		return point;
	}

	public static Point[] pointsFromString(String str)
		throws PointConversionException
	{
		Point   point = null;
		List<Point> points = new ArrayList<Point>();
		Pattern p = Pattern.compile("(x:\\d,y:\\d)");
		Matcher m = p.matcher(str);
		try
		{
			while (m.find())
			{
				points.add(fromString(m.group(1)));
			}
		}
		catch (IndexOutOfBoundsException|NumberFormatException e)
		{
			e.printStackTrace(System.err);
			System.err.println("Conversion Str->Points failed for string:"+str);
			throw new PointConversionException();
		}

		return points.toArray(new Point[0]);
	}
}
