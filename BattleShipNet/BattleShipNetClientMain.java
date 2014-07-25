import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BattleShipNetClientMain
{
	public static void main(String[] args)
	{
		if (2 != args.length)
		{
			System.out.println("Usage: BattleShipNetClient <host name> <port number>");
			System.exit(1);
		}

		String hostName   = args[0];
		int    portNumber = 0;
		try 
		{ 
			portNumber = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException e)
		{
			System.out.println("Wrong value for <port number> given.");
		}

		try
		(
		    Socket socket = new Socket(hostName, portNumber);
			PrintWriter out = new PrintWriter(
				socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		)
		{
			String fromServer = null;
			for (;;)
			{
				Thread.sleep(100);
				while (null != (fromServer = in.readLine()))
				{
					System.out.println("Got from server:"+fromServer);
				}
			}
		}
		catch (InterruptedException e)
		{
			System.err.println("Client run interrupted"+e);
		}
		catch (UnknownHostException e)
		{
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to " +hostName);
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
}
