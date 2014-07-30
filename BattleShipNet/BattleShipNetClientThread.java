public class BattleShipNetClientThread 
	extends Thread
{
	private CommunicationModule     cm     ;
	private String                  command;
	private BattleShipNetClientGame game   ;

	public BattleShipNetClientThread(Socket socket)
	{
		this.cm   = new CommunicationModule(socket);
		this.game = BattleShipNetGameFactory.getClientGame();
	}

	public String setCommand(String command)
	{
		this.command = command;
	}

	public void getCommand()
	{
		return command; 
	}

	public String nextCommand()
	{
		String str = null;
		if ( null != (str = getCommunicationModule().getLineBlocking()) )
		{
			setCommand(str);
		}
		return str;
	}

	public void setGame(BattleShipNetClientGame game)
	{
		this.game = game;
	}

	public BattleShipNetClientGame getGame()
	{
		return game;
	}

	public void run()
	{
		getCommunicationModule().init();
	}

	public void say(String str)
	{
		getCommunicationModule().say(str);
	}

	public String getLineBlocking()
	{
		String str = null;
		while (null != (str = getCommunicationsModule().readLine()))
		{
		}
		return str;
	}

	public boolean gameOver()
	{
		return getGame().isOver();
	}

	public boolean myMove()
	{
		return BattleShipNetProtocol.yourMove().equals(
				getCommand());
	}
}
