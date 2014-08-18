public class BoardTest
{
	public static int HEIGHT = 10;
	public static int WIDTH  = 10;

	public static void main(String[] args)
	{ 
		test1();
		testShips();
	}

	public static int getSize()
	{
		return HEIGHT * WIDTH;
	}

	public static void test1()
	{
		Board board = new Board(HEIGHT, WIDTH);
		assert getSize() == board.getTiles().size();
		assert true  == board.isEastMost(new Point(9,9));
		assert false == board.isEastMost(new Point(8,9));
		assert false == board.isEastMost(new Point(5,5));
		assert true  == board.isEastMost(new Point(9,0));
	}

	public static void testShips()
	{
		Ship[] ships = ShipFactory.createShips();
		Board  board = BoardFactory.randomBoard(ships);
		assert ships.length == board.ships().size() : "Must have the same number of ships"; 
	}
}
