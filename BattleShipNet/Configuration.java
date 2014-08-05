public class Configuration
{
	private String hostName  ;
	private int    portNumber;

	public Configuration(int portNumber)
	{
		this.portNumber = portNumber;
	}

	public Configuration(String hostName, int portNumber)
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
