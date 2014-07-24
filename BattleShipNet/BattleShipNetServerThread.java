import java.net.*;
import java.io.*;

public class BattleShipNetServerThread
	extends Thread
{
	Socket socket;
	Player player;
	boolean ready = false;

	PrintWriter    out;
	BufferedReader in;

	public BattleShipNetServerThread(Socket socket)
	{
		super("BattleShipNetServerThread");
		this.socket = socket;
	}

	public void setReady(boolean ready)
	{
		this.ready = ready;
	}

	public boolean getReady()
	{
		return ready;
	}

	public boolean isReady()
	{
		return ready;
	}

	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}

	public Socket getSocket()
	{
		return socket;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setOut(PrintWriter out)
	{
		this.out = out;
	}
	
	public PrintWriter getOut()
	{
		return out;
	}

	public void setIn(BufferedReader in)
	{
		this.in = in;
	}

	public BufferedReader getIn()
	{
		return in;
	}

	public void say(String str)
	{
		out.println(str);
	}

	public String readLine()
	{
		in.readLine();
	}

	public void run()
	{
		try (
				setOut(new PrintWriter(socket.getOutputStream(), true));
				setIn(new BufferedReader(
					new InputStreamReader(socket.getInputStream()))); 
		)
		{
			String inputLine, outputLine;
			BattleShipNetProtocol bsnp = new BattleShipNetProtocol();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
