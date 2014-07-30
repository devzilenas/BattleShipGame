public class BattleShipNetClientGame
	extends Game
{
	public boolean serverWins()
	{
		return hasWinner()
			&& getWinner().getStrategy() instanceof BattleShipStrategyServer();
	}
}
