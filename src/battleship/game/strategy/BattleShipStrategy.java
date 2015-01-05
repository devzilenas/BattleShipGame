package battleship.core.strategy;

public abstract class BattleShipStrategy
	implements Strategy
{
	public abstract Point getNextPoint(Board board);
}

