import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BattleShipNetServerThread
	extends Thread
{
	private CommunicationModule cm    ;
	private Player              player;
	private boolean             ready = false;

	public BattleShipNetServerThread(Socket socket, Player player)
	{
		super("BattleShipNetServerThread");
		this.player = player;
		cm = new CommunicationModule(socket);
	}

	public void setCommunicationModule(CommunicationModule cm)
	{
		this.cm = cm;
	}

	public CommunicationModule getCommunicationModule()
	{
		return cm;
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

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void say(String str)
	{
		getCommunicationModule().say(str);
	}

	public void run()
	{
		getCommunicationModule().init();
	}

	public boolean isConnected()
	{
		return getCommunicationModule().isConnected();
	}
}
