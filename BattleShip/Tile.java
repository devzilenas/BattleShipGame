public class Tile
{
	private Ship ship;
	boolean attacked;

	public Tile()
	{
	}

	public Tile(Ship ship)
	{
		this.ship = ship;
	}

	public void setShip(Ship ship)
	{
		this.ship = ship;
	}
	
	public Ship getShip()
	{
		return ship;
	}

	public void setAttacked(boolean attacked)
	{
		this.attacked = attacked;
	}

	public boolean getAttacked()
	{
		return attacked;
	}

	public boolean isAttacked()
	{
		return getAttacked();
	}

	public boolean isHit()
	{
		return isAttacked() && hasShip();
	}

	public boolean isMiss()
	{
		return isAttacked() && !hasShip();
	}

	public boolean hasShip()
	{
		return null != getShip();
	}

	public void attack()
	{ 
		setAttacked(true);
		if (hasShip())
		{
			getShip().hit();
		}
	}

}

