import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BattleShipNetProtocol
{
	public static String start()
	{
		return "START";
	}

	public static String attack()
	{
		return "ATTACK";
	}

	public static String miss()
	{
		return "MISS";
	}

	public static String hit()
	{
		return "HIT";
	}

	public static String hitAt()
	{
		return "HIT AT";
	}

	public static String sunken()
	{
		return "SUNKEN";
	}

	public static String sunken(Point[] points)
	{
		return sunken() + " " + Point.pointsAsString(points);
	}

	public static boolean isHitAt(String command)
	{
		return contains(hitAt(), command);
	}

	public static boolean isSunken(String command)
	{
		return contains(sunken(), command);
	}

	public static String yourName()
	{
		return "YOUR NAME";
	}

	public static String getReady()
	{
		return "GET READY";
	}

	public static String attackAt(Point point)
	{
		return "ATTACK AT " + point.toString();
	}

	public static String gameOver()
	{
		return "GAME OVER";
	}

	public static String winnerIs(Player player)
	{
		return "WINNER IS "+player;
	}

	public static String ready()
	{
		return "READY";
	}

	public static boolean attacks(String attacks)
	{
		return contains("ATTACK AT", attacks);
	}

	public static Point extractPoint(String command)
	{
		return PointFactory.fromString(command);
	}

	public static boolean contains(String str, String command)
	{
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(command);
		return m.matches();
	}
}
