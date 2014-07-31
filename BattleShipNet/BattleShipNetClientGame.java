public class BattleShipNetClientGame
	extends Game
{
	public boolean serverWins()
	{
		return hasWinner()
			&& getWinner().getStrategy() instanceof BattleShipStrategyServer;
	}

	public Player getServerPlayer()
	{
		return getPlayer(0).getStrategy() instanceof BattleShipStrategyServer ? getPlayer(0) : getPlayer(1);
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
