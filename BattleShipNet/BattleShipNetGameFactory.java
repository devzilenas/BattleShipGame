import java.net.ServerSocket;
import java.io.IOException;

public class BattleShipNetGameFactory
	extends GameFactory
{
	public static BattleShipNetGame getMiltonBradleyGame(ServerSocket serverSocket)
		throws IOException
	{
		return new BattleShipNetGame(

				PlayerFactory.human(),
				BoardFactory.getMiltonBradley(),

				PlayerFactory.human(),
				BoardFactory.getMiltonBradley(),

				serverSocket);
	}
}
