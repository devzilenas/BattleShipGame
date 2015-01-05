package battleship.core.strategy;

public class BattleShipStrategyNull
	extends BattleShipStrategy
{
	public Point getNextPoint(Board board)
	{
		return null;
	}
}
