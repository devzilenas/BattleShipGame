import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BattleShipNetServerThread
	extends Thread
{
	private Socket  socket;
	private Player  player;
	private boolean ready = false;

	private PrintWriter    out;
	private BufferedReader in ;

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
		getOut().println(str);
	}

	public String readLine()
	{
		String ret = null;
		try 
		{
			ret = in.readLine();
		}
		catch (IOException e)
		{ 
			System.err.println("Could not read from socket."+e);
		} 
		return ret;
	}

	public void run()
	{
		try
		{
			setOut(new PrintWriter(
						socket.getOutputStream(), true));
			setIn(new BufferedReader(
						new InputStreamReader(socket.getInputStream()))); 
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public boolean isConnected()
	{
		return getSocket().isConnected();
	}
}
