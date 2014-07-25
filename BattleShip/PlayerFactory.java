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
}
