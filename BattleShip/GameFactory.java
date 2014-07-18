public class GameFactory
{
	public static Game getMiltonBradleyHumanAgainst(BattleShipStrategy strategy)
	{
		return new Game(
				PlayerFactory.withStrategy(strategy),
				BoardFactory.getMiltonBradley(),

				PlayerFactory.withStrategy(new BattleShipStrategyNull()),
				BoardFactory.getMiltonBradley());
	}
}

