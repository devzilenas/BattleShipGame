import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

public class BattleShipNetClient
{
	private Configuration             cfg   ;
	private BattleShipNetClientThread thread;
	private static final BattleShipNetProtocol protocol = new BattleShipNetProtocol();

	public BattleShipNetClient(String hostName, int portNumber)
		throws UnknownHostException, IOException
	{
		this.cfg    = new Configuration(hostName, portNumber);
		this.thread = new BattleShipNetClientThread(
				new Socket(hostName, portNumber));
	}

	public BattleShipNetProtocol getProtocol()
	{
		return protocol;
	}

	public void setThread(BattleShipNetClientThread thread)
	{
		this.thread = thread;
	}

	public BattleShipNetClientThread getThread()
	{
		return thread;
	}

	public void play()
		throws PointConversionException
	{
		BattleShipNetClientThread thread = getThread();
		BattleShipNetClientGame   game   = getThread().getGame();
		thread.start();

		while (!thread.gameOver())
		{
			thread.nextCommand();
			System.out.println("Analyzing command:"+thread.getCommand());
			if (thread.isGetShips())
			{
				thread.tellShips();
			}
			else if (thread.attack())
			{
				Board board = game.getBoard(game.player(0));
				Point point = game.player(1).getStrategy().getNextPoint(board);
				board.attackAt(point);
				thread.attackAt(point);
			}
			else if (thread.attacks())
			{ 
				Player player = game.opponent(
						game.getServerPlayer());
				Point point = new Point(
						thread.getCommand());
				game.getBoard(player).attackAt(point);
			}
			else if (thread.isHit())
			{
				Point point = new Point(thread.getCommand());
				thread.getGame().setHitAt(point);
			}
			else if (thread.isMiss())
			{
				Point point = new Point(
						thread.getCommand());
				thread.getGame().setMissAt(point);
			}
			else if (thread.isSunken())
			{
				Point[] points = PointFactory.pointsFromString(
						thread.getCommand());
				game.getBoard(game.getServerPlayer()).putShipAt(
						ShipFactory.sunken(points.length), points);
			}
			else if (thread.gameOver())
			{
				System.out.println("Game over");
			}
			else 
			{
				System.out.println("uknown command" + getThread().getCommand());
			}
			
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				System.err.println("Client run interrupted"+e);
			}
		}

		if (getThread().gameOver())
		{
			System.out.println("You " + (getThread().getGame().serverWins() ? "LOOSE" : "WIN"));
		}
	}
}
