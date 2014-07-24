public class BattleShipNetProtocol
{
	public static String miss()
	{
		return "MISS";
	}

	public static String hit()
	{
		return "HIT";
	}

	public static String yourName()
	{
		return "YOUR NAME";
	}

	public static String yourMove()
	{
		return "YOUR MOVE";
	}

	public static String getReady()
	{
		return "GET READY";
	}

	public static String attackAt(Point point)
	{ 
		return "ATTACK " + point.toString();
	}

	public static String gameOver()
	{
		return "GAME OVER";
	}

	public static String winnerIs(Player player)
	{
		return "WINNER IS "+player;
	}
}
