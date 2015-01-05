package battleship.net.core;

import java.net.Socket;

public class BattleShipNetThread
	extends Thread
{
	private CommunicationModule cm     ;
	private String              command;

	public BattleShipNetThread(String string)
	{
		super(string);
	}

	public BattleShipNetThread(Socket socket)
	{
		this.cm = new CommunicationModule(socket);
	}

	public void setCommunicationModule(CommunicationModule cm)
	{
		this.cm = cm;
	}

	public CommunicationModule getCommunicationModule()
	{
		return cm;
	}

	public void setCommand(String command)
	{
		this.command = command;
	}

	public String getCommand()
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
}
