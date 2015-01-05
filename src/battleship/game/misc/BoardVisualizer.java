package battleship.core.misc;

import java.util.Map;
public class BoardVisualizer
{
	public static void showBoard(Board board)
	{
		String s     = "0";
		Point  point = null;
		Tile   tile  = null;
		for (Map.Entry<Point, Tile> entry : board.getTiles().entrySet())
		{
			point = entry.getKey()  ; 
			tile  = entry.getValue();
			if (board.isAttacked(point))
			{
				if (board.hasShipAt(point) && board.isHitAt(point))
				{
					s = "x";
				}
				else
				{
					s = "h";
				}
			}
			else
			{
				if (board.hasShipAt(point))
				{
					s = "" + board.getShipAt(point).getSize();
				}
				else 
				{
					s = "0";
				}
			}
			System.out.print(" | " + s );
			if (board.isEastMost(point))
			{
				System.out.println(" |");
			} 
		}
	}
}
