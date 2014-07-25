import java.net.*;
import java.io.*;

public class BattleShipNetServer
	extends Thread
{
	private int               portNumber  ;
	private ServerSocket      serverSocket;
	private BattleShipNetGame game        ;

	public BattleShipNetServer(int portNumber)
	{
		this.portNumber = portNumber;
	}

	public void setPortNumber(int portNumber)
	{
		this.portNumber = portNumber;
	}

	public int getPortNumber()
	{
		return portNumber;
	}

	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
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
					new ServerSocket(getPortNumber()));
			setGame(
					BattleShipNetGameFactory.getMiltonBradleyGame(
						serverSocket));
		}
		catch (IOException e)
		{
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}

	public void startGame()
	{
		getGame().startThreads();
	}

	public void play()
	{
		while (!getGame().playersConnected())
		{
		}

		if (getGame().playersConnected())
		{
			System.out.println("saying "+BattleShipNetProtocol.yourName());
			getGame().say(BattleShipNetProtocol.yourName());
		}
	}

	public void run()
	{
		createGame();
		startGame();
		play();
	}
}
