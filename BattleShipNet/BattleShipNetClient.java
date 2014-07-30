public class BattleShipNetClient
{
	private ClientConfiguration       cfg   ;
	private BattleShipNetClientThread thread;
	private static final BattleShipNetProtocol protocol = new BattleShipNetProtocol();

	public BattleShipNetClient(String hostName, int portNumber)
	{
		this.cfg    = new ClientConfiguration(hostName, portNumber);
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
	{
		getThread().start();

		say(getProtocol.ready());

		while (!getThread().gameOver())
		{
			getThread().nextCommand();
			if (getThread().myMove())
			{
			} 
			else if (getThread().attack())
			{
			}
			else if (getThread().attacks())
			{
			}
			else if (getThread().gameOver())
			{
			}
			Thread.sleep(100);
		}

		if (getThread().gameOver())
		{
			System.out.println("You " getThread().getGame().serverWins() ? "LOOSE" : "WIN");
		}
	}
}
