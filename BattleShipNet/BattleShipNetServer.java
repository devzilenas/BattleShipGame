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

	public void setServerSocket(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}

	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public void setGame(BattleShipNetGame game)
	{
		this.game = game;
	}

	public BattleShipNetGame getGame()
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
	{
		String resp = null;
		while (!getGame().playersConnected())
		{
			sleep();
		}

		if (getGame().playersConnected())
		{
			getGame().start();
		}

		while (!getGame().isOver())
		{
			getGame().getTurn().say(
					getProtocol().attack());
			getGame().getTurn().nextCommand();
			if (getProtocol().attacks(
						getGame().getTurn().getCommand()))
			{
				getGame().attack(
						new Point(
							getGame().getTurn().getCommand()));
				if (getGame().opponent(
							(getGame().getTurn().getPlayer())).getBoard().isHitAt(point))
				{
					getGame().getTurn().say(
							getProtocol().hit());
				}
				else
				{
				}
			}
			else if ()
			{
			}
			else if ()
			{
			}
		}
	}

	public void run()
	{
		createGame();
		startGame();
		play();
	}

	public void sleep()
	{
		Thread.sleep(getServerTick());
	}
}
