public class BattleShipNetGameFactory
	extends GameFactory
{
	public static Game getMiltonBradleyGame()
	{
		return new Game(
				PlayerFactry.human(),
				BoardFactory.getMiltonBradley(),
				PlayerFactory.human(),
				BoardFactory.getMiltonBradley());
	}
}
