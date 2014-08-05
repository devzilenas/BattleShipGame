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
		getThread().start();

		getThread().say(getProtocol().ready());

		while (!getThread().gameOver())
		{
			getThread().nextCommand();
			if (getThread().attack())
			{
				BattleShipNetClientGame game = getThread().getGame();
				Board board = game.player(0).getBoard();
				Point point = game.player(1).getStrategy().getNextPoint(board);
				board.attackAt(point);
			}
			else if (getThread().attacks())
			{ 
				BattleShipNetClientGame game = getThread().getGame();
				Player player = game.opponent(
						game.getServerPlayer());
				Point point = new Point(
						getThread().getCommand());
				game.getBoard(player).attackAt(point);
			}
			else if (getThread().isHit())
			{
				Point point = new Point(getThread().getCommand());
				getThread().getGame().setHitAt(point);
			}
			else if (getThread().isMiss())
			{
				Point point = new Point(
						getThread().getCommand());
				getThread().getGame().setMissAt(point);
			}
			else if (getThread().isSunken())
			{
				BattleShipNetClientGame game = getThread().getGame();
				Point[] points = PointFactory.pointsFromString(
						getThread().getCommand());
				game.getBoard(game.getServerPlayer()).putShipAt(
						ShipFactory.sunken(points.length), points);
			}
			else if (getThread().gameOver())
			{
				System.out.println("Game over");
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
