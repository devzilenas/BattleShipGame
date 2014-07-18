import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardFactory
{
	public static final int WIDTH  = 10;
	public static final int HEIGHT = 10;

	public static Board getStandard()
	{
		Board board = new Board(WIDTH, HEIGHT);
		return board;
	} 

	public static Board randomBoard(Ship[] ships)
	{
		Board   board = placeOnBoard(ships);
		return board;
	}

	public static Board placeOnBoard(Ship[] ships)
	{
		List<Ship> lships = new ArrayList<Ship>(Arrays.asList(ships));
		Board board = getStandard();

		Ship ship;
		while (!lships.isEmpty())
		{
			ship = lships.remove(0);
			board.placeRandom(ship);
		}
		return board;
	}

	public static Board getMiltonBradley()
	{
		return randomBoard(ShipFactory.createShips());
	}
}
