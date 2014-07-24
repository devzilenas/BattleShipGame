public class BattleShipNetGame
{
	Game         game        ;
	Thread[]     threads     ;
	ServerSocket serverSocket;
	
	public BattleShipNetGame(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}

	public BattleShipNetGame(Game game)
	{
		this.game = game;
	}

	public void setGame(BattleShipGame game)
	{
		this.game = game;
	}

	public Game getGame()
	{
		return game;
	}

	public Thread[] getThreads()
	{
		return threads;
	}

	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public Thread getThread(Player player)
	{
		Thread ret = null;
		for (Thread thread : getThreads())
		{
			if (player == thread.getPlayer())
			{
				ret = thread;
				break;
			}
		}
		return ret;
	}

	public Thread getThreadFor(Player player)
	{
		return getThread(player);
	}

	public void init()
	{
		this.threads = new Thread[] {
			new BattleShipNetServerThread(getServerSocket().accept()),
			new BattleShipNetServerThread(getServerSocket().accept())
		};
	}

	public boolean isReady()
	{
		boolean ready = true;
		for (Thread thread : getThreads())
		{
			if (!thread.isReady())
			{
				ready = false;
				break;
			}
		}
		return ready;
	}

	public void prepareGame()
	{
		setGame(BattleShipNetGameFactory.getMiltonBradleyGame());
	}
}
