public class BattleShipNetThread
	extends Thread
{
	private CommunicationModule cm;
	private String command;

	public BattleShipNetThread()
	{
		this.cm = new CommunicationModule(socket);
	}

	public String setCommand(String command)
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
