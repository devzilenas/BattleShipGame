public class PlayerFactory
{
	public static BattleShipPlayer withStrategy(BattleShipStrategy strategy)
	{
		return new BattleShipPlayer(strategy);
	}
}
