import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

public class BattleShipNetClient
{
	private Configuration             cfg     ;
	private BattleShipNetProtocol     protocol;
	private BattleShipNetClientThread thread  ;

	public BattleShipNetClient(String hostName, int portNumber)
		throws UnknownHostException, IOException
	{
		this.cfg      = new Configuration(hostName, portNumber);
		this.protocol = new BattleShipNetProtocol();
		this.thread   = new BattleShipNetClientThread(
				new Socket(hostName, portNumber));
	}

	public void setConfiguration(Configuration cfg)
	{
		this.cfg = cfg;
	}

	public Configuration getConfiguration()
	{
		return cfg;
	}
	
	public void setProtocol(BattleShipNetProtocol protocol)
	{
		this.protocol = protocol;
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

		do
		{
			thread.nextCommand();
			System.out.println("Analyzing command:"+thread.getCommand());
			if (thread.isGetShips())
			{
				thread.tellShips();
			}
			else if (thread.attack())
			{
				Board board = game.getBoard(
						game.player(0));
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
			else if (thread.isGameOver())
			{
				System.out.println("Game over");
			}
			else 
			{
				System.out.println("uknown command" + getThread().getCommand());
			}
			
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				System.err.println("Client run interrupted"+e);
			}
		} while (!thread.isGameOver());

		if (getThread().isGameOver())
		{
			System.out.println(
					getThread().nextCommand());
		}
	}
}
