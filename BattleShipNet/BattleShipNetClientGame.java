public class BattleShipNetClientGame
	extends Game
{
	public BattleShipNetClientGame(Player p1, Board b1, Player p2, Board b2)
	{
		super(p1,b1,p2,b2);
	}

	public boolean serverWins()
	{
		return hasWinner()
			&& getWinner().getStrategy() instanceof BattleShipStrategyServer;
	}

	public Player getServerPlayer()
	{
		return player(0).getStrategy() instanceof BattleShipStrategyServer ? player(0) : player(1);
	}

	public void setHitAt(Point point)
	{
		Tile tile = getServerPlayer().getBoard().getTileAt(point);
		tile.setHit(true);
		tile.setAttacked(true);
	}

	public void setMissAt(Point point)
	{
		Tile tile = getServerPlayer().getBoard().getTileAt(point);
		tile.setAttacked(true);
	}
}
