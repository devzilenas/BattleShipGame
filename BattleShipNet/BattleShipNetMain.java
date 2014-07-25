import java.io.IOException;

public class BattleShipNetMain
{
	public static void main(String[] args)
	{
		if (1 != args.length)
		{
			System.out.println("Usage: java BattleShipNetMain <port number>");
			System.exit(1);
		}

		int portNumber = 0;
		try 
		{
			portNumber = Integer.parseInt(args[0]);
		}
		catch (NumberFormatException e)
		{
			System.err.println("Wrong value for <port number> given.");
		} 
		BattleShipNetServer bsns = new BattleShipNetServer(portNumber);
		bsns.start();
		for (;;);
	}
}
