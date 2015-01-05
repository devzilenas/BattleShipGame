package battleship.core.factory;

public class PlayerFactory
{
	public static Player withStrategy(BattleShipStrategy strategy)
	{
		return new Player(strategy);
	}

	public static Player human()
	{
		return withStrategy(new BattleShipStrategyNull());
	}

	public static Player random()
	{
		return withStrategy(new BattleShipStrategyRandom());
	}

	public static Player server()
	{
		return withStrategy(new BattleShipStrategyNull());
	}
}
