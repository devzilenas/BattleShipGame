import java.net.ServerSocket;
import java.io.IOException;

public class BattleShipNetServerGame
	extends Game
{
	BattleShipNetServerThread[] threads;
	ServerSocket                serverSocket;
	
	public BattleShipNetServerGame(Player p1, Board b1, Player p2, Board b2, ServerSocket serverSocket)
		throws IOException
	{
		super(p1,b1,p2,b2);
		this.serverSocket = serverSocket;

		this.threads = new BattleShipNetServerThread[] {
			new BattleShipNetServerThread(
					getServerSocket().accept(), p1),
			new BattleShipNetServerThread(
					getServerSocket().accept(), p2)
		};
	}

	public void setThreads(BattleShipNetServerThread[] threads)
	{
		this.threads = threads;
	}

	public BattleShipNetServerThread[] getThreads()
	{
		return threads;
	}

	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public BattleShipNetServerThread getThread(Player player)
	{
		BattleShipNetServerThread ret = null;
		for (BattleShipNetServerThread thread : getThreads())
		{
			if (thread.getPlayer() == player)
			{
				ret = thread;
				break;
			}
		}
		return ret;
	}

	public BattleShipNetServerThread getThreadFor(Player player)
	{
		return getThread(player);
	}

	public boolean isReady()
	{
		boolean ready = true;
		for (BattleShipNetServerThread thread : getThreads())
		{
			if (!thread.isReady())
			{
				ready = false;
				break;
			}
		}
		return ready;
	}

	public void startThreads()
	{
		for (Thread thread : getThreads())
		{
			thread.start();
		}
	}

	public boolean playersConnected()
	{
		boolean connected = true;
		for (BattleShipNetServerThread thread : getThreads())
		{
			if (!thread.isConnected())
			{
				connected = false;
				break;
			}
		}
		return connected;
	}

	public void say(String msg)
	{
		if (playersConnected())
		{
			for (BattleShipNetServerThread thread : getThreads())
			{
				thread.say(msg);
			}
		}
	}

	public BattleShipNetServerThread getTurnThread()
	{
		return getThreadFor(super.getTurn());
	}
}
