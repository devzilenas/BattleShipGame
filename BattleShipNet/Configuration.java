public class Configuration
{
	private String hostName;
	private int    portName;

	public Configuration(int portNumber)
	{
		this.portNumber;
	}

	public Configuration(int portNumber, String hostName)
	{
		this.portNumber = portNumber;
		this.hostName   = hostName  ;
	}

	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}

	public String getHostName()
	{
		return hostName;
	}

	public void setPortNumber(int portNumber)
	{
		this.portNumber = portNumber;
	}

	public int getPortNumber()
	{
		return portNumber;
	} 
}
