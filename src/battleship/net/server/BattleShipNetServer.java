package battleship.net.server;

import java.net.*;
import java.io.*;

public class BattleShipNetServer
	extends Thread
{
	public static final int   SERVER_TICK = 10;
	public BattleShipNetProtocol protocol ;
	private Configuration     cfg         ;
	private ServerSocket      serverSocket;
	private BattleShipNetServerGame game  ;

	public BattleShipNetServer(int portNumber)
	{
		this.cfg      = new Configuration(portNumber);
		this.protocol = new BattleShipNetProtocol();
	}

	public static int getServerTick()
	{
		return SERVER_TICK;
	}

	public void setProtocol(BattleShipNetProtocol protocol)
	{
		this.protocol = protocol;
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

		while (!getGame().playersConnected())
		{
			sleep();
		}

		game.say(BattleShipNetProtocol.getShips());

		game.acquireShips();

		game.start();

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
				Player p1 = game.getPlayer(0);
				Player p2 = game.getPlayer(1);

				System.out.println("Winner is: " + game.getWinner());
				game.say(getProtocol().gameOver());

				game.getThreadFor(p1).say(
						getProtocol().winningStatus(game.getWinner() == p1));

				game.getThreadFor(p2).say(
						getProtocol().winningStatus(game.getWinner() == p2));
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
