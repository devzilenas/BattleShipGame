import java.net.ServerSocket;
import java.io.IOException;

public class BattleShipNetGameFactory
	extends GameFactory
{
	public static BattleShipNetServerGame getMiltonBradleyGame(ServerSocket serverSocket)
		throws IOException
	{
		return new BattleShipNetServerGame(

				PlayerFactory.human(),
				BoardFactory.getMiltonBradley(),

				PlayerFactory.human(),
				BoardFactory.getMiltonBradley(),

				serverSocket);
	}

	public static BattleShipNetClientGame getClientGame()
	{
		return new BattleShipNetClientGame(
				PlayerFactory.server(),
				BoardFactory.getMiltonBradley(),

				PlayerFactory.random(),
				BoardFactory.getMiltonBradley());
	}
}
