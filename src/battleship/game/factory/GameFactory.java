package battleship.core.factory;

public class GameFactory
{
	public static Game getMiltonBradleyHumanAgainst(BattleShipStrategy strategy)
	{
		return new Game(
				PlayerFactory.withStrategy(strategy),
				BoardFactory.getMiltonBradley(),

				PlayerFactory.human(),
				BoardFactory.getMiltonBradley());
	}
}

