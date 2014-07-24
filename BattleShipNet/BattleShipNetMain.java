public class BattleShipNetMain
{
	public static void main(String[] args)
	{
		if (1 != args.length)
		{
			System.out.println("Usage: java BattleShipNetMain <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		BattleShipNetServer bsns = new BattleShipNetServer(portNumber);
		bsns.createSocket();
		bsns.play();
	}
}
