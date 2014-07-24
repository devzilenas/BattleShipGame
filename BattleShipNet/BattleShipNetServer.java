import java.net.*;
import java.io.*;

public class BattleShipNetServer
{
	int               portNumber  ;
	ServerSocket      serverSocket;
	BattleShipNetGame game        ;

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

	public void setGame(Game game)
	{
		this.game = game;
	}

	public BattleShipNetGame getGame()
	{
		return game;
	}

	public void createSocket()
	{
		try 
		{
			setServerSocket(new ServerSocket(getPortNumber()));
			setGame(new BattleShipNetGame(serverSocket));
			getGame().init();
		}
		catch (IOException e)
		{
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}

	public static String readLine()
	{
		t1.readLine();
	}

	public static Game play()
	{
		while (getGame().!isReady())
		{
		}

		if (getGame().isReady())
		{
			say(BattleShipNetProtocol.yourName());
			t1.readLine();
			t2.readLine();
		}
	}

	public static void say(Threads[] ts, String msg)
	{
		for (int i = 0; i < ts.length; i++)
		{
			ts[i].say(msg);
		} 
	}

}
