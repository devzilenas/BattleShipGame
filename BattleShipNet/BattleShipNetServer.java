import java.net.*;
import java.io.*;

public class BattleShipNetServer
	extends Thread
{
	public static final int   SERVER_TICK = 100;
	public static final BattleShipNetProtocol protocol = new BattleShipNetProtocol();
	private Configuration     cfg         ;
	private ServerSocket      serverSocket;
	private BattleShipNetServerGame game  ;

	public BattleShipNetServer(int portNumber)
	{
		this.cfg = new Configuration(portNumber);
	}

	public static int getServerTick()
	{
		return SERVER_TICK;
	}

	public BattleShipNetProtocol getProtocol()
	{
		return protocol;
	}

	public void setConfiguration(Configuration cfg)
	{
		this.cfg = cfg;
	}

	public Configuration getConfiguration()
	{
		return cfg;
	}

	public void setServerSocket(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}

	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public void setGame(BattleShipNetServerGame game)
	{
		this.game = game;
	}

	public BattleShipNetServerGame getGame()
	{
		return game;
	}

	public void createGame()
	{
		try 
		{
			setServerSocket(
					new ServerSocket(
						getConfiguration().getPortNumber()));
			setGame(
					BattleShipNetGameFactory.getMiltonBradleyGame(
						serverSocket));
		}
		catch (IOException e)
		{
			System.err.println("Could not listen on port " + getConfiguration().getPortNumber());
			System.exit(-1);
		}
	}

	public void startGame()
	{
		getGame().startThreads();
	}

	public void play()
		throws PointConversionException
	{
		BattleShipNetServerGame game = getGame();

		String resp = null;

		while (!getGame().playersConnected())
		{
			sleep();
		}

		if (game.playersConnected())
		{ 
			game.say(BattleShipNetProtocol.getShips());
		}

		if (game.playersConnected())
		{
			System.out.println("Acquiring ships");
			game.acquireShips();
		}

		if (game.playersConnected())
		{ 
			game.start();
		}

		while (!game.isOver())
		{
			game.getTurnThread().say(
					getProtocol().attack());

			game.getTurnThread().nextCommand();

			if (getProtocol().attacks(
						game.getTurnThread().getCommand()))
			{
				Point point = new Point(
						game.getTurnThread().getCommand());

				game.attack(point);

				if (game.getBoard(
							game.opponent(
								game.getTurnThread().getPlayer())).isHitAt(point))
				{
					game.getTurnThread().say(
							getProtocol().hitAt(point));
				}
				else
				{
					game.getTurnThread().say(
							getProtocol().missAt(point));
				}
				game.endTurn();
			}
			else
			{
				System.out.println("Uknown command " + game.getTurnThread().getCommand());
			}

			if (game.isOver())
			{
				System.out.println("Winner is"+game.getWinner());
			}
		}
	}

	public void run()
	{
		createGame();
		startGame();
		try {
			play();
		}
		catch (PointConversionException e) 
		{
			System.err.println("Point conversion failed!"+e);
		} 
	}

	public void sleep()
	{
		try
		{
			Thread.sleep(getServerTick());
		}
		catch (InterruptedException e)
		{
			System.err.println("Server thread interruption!" + e);
		} 
	}
}
