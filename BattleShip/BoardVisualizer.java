public class BoardVisualizer
{
	public static void showBoard(Board board)
	{ 
		for (Point point : board.getPoints())
		{
			System.out.print(" | " + (board.hasShipAt(point) ? board.getShipAt(point).getSize() : 0) );
			if (board.isEastMost(point))
			{
				System.out.println(" |");
			} 
		}
	}
}
