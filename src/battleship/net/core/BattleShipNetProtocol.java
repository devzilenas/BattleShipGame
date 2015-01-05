package battleship.net.core;

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

	public static String hitAt(Point point)
	{
		return hitAt()+ " " + point.toString();
	}

	public static String missAt()
	{
		return "MISS AT";
	}

	public static String missAt(Point point)
	{
		return missAt() + " " + point.toString();
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

	public static boolean isMissAt(String command)
	{
		return contains(missAt(), command);
	}

	public static boolean isSunken(String command)
	{
		return contains(sunken(), command);
	}

	public static String yourName()
	{
		return "YOUR NAME";
	}

	public static String getShips()
	{
		return "GET SHIPS";
	}

	public static String shipsEnd()
	{
		return "SHIPS END";
	}

	public static boolean isShipsEnd(String str)
	{
		return contains(shipsEnd(), str);
	}

	public static boolean isGetShips(String str)
	{
		return contains(getShips(), str);
	}

	public static String getReady()
	{
		return "GET READY";
	}

	public static String attackAt()
	{
		return "ATTACK AT";
	}

	public static String attackAt(Point point)
	{
		return attackAt() + " " + point.toString();
	}

	public static String gameOver()
	{
		return "GAME OVER";
	}

	public static boolean isGameOver(String str)
	{
		return contains(gameOver(), str);
	}

	public static String winnerIs(Player player)
	{
		return "WINNER IS "+player;
	}

	public static String winningStatus(boolean win)
	{
		return "YOU " + (win ? "WIN" : "LOOSE");
	}

	public static String ready()
	{
		return "READY";
	}

	public static String shipAt()
	{
		return "SHIP AT";
	}

	public static String shipAt(Point[] points)
	{ 
		return shipAt() + " " + Point.pointsAsString(points);
	}

	public static Point[] points(String str)
		throws PointConversionException
	{
		Ship ship      = null;
		Point[] points = new Point[0];
		if (contains(shipAt(), str))
		{
			points = PointFactory.pointsFromString(str);
		}
		return points;
	}

	public static boolean attacks(String str)
	{
		return contains(attackAt(), str);
	}

	public static Point extractPoint(String command)
		throws PointConversionException
	{
		return PointFactory.fromString(command);
	}

	public static boolean contains(String str, String command)
	{
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(command);
		return m.lookingAt();
	}
}
