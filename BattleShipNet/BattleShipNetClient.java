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
			if (getThread().attack().equals(
						getThread().getCommand()))
			{
				Point point = getThread().getGame().player(1).getStrategy().getNextPoint(player(0).getBoard());
				getThread().attackAt(point);
			}
			else if (getThread().attacks())
			{ 
				Point point = new Point(
						getThread().getCommand());
				getThread().getGame().attackAt(point);
			}
			else if (getThread().isHit())
			{
				Point point = new Point(getThread().getCommand());
				getThread().getGame().setHitAt(point);
			}
			else if (getThread().isMiss())
			{
				Point point = new Point(getThread().getCommand());
				getThread().getGame().setMissAt(point);
			}
			else if (getThread().isSunken())
			{
				Point[] points = Point.pointsFromString(
						getThread().getCommand());
				getThread().getGame().getBoard().putShipAt(
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
			System.out.println("You " getThread().getGame().serverWins() ? "LOOSE" : "WIN");
		}
	}
}
