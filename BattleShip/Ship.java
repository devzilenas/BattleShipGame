public class Ship
{
	private int size  ;
	private int health;

	public Ship(int size)
	{
		this.size   = size;
		this.health = size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getSize()
	{
		return size;
	} 

	public void setHealth(int health)
	{
		this.health = health; 
	}

	public int getHealth()
	{
		return health;
	}

	public int getLength()
	{
		return getSize();
	}

	public void hit()
	{
		if (!isSunken())
		{
			setHealth(getHealth() - 1);
		}
	}

	public boolean isSunken()
	{
		return 0 == getHealth();
	}

	public boolean isDamaged()
	{
		return getHealth() < getSize();
	}

	public String toString()
	{
		return "Ship size:" + getSize() + ", health:"+getHealth();
	}
}

