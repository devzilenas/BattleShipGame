public class PlayerFactory
{
	public static BattleShipPlayer withStrategy(BattleShipStrategy strategy)
	{
		return new BattleShipPlayer(strategy);
	}

	public static BattleShipPlayer human()
	{
		return withStrategy(new BattleShipStrategyNull());
	}

	public static BattleShipPlayer random()
	{
		return withStrategy(new BattleShipStrategyRandom());
	}

	public static BattleShipPlayer server()
	{
		return withStrategy(new BattleShipStrategyNull());
	}
}
