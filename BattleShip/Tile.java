public class Tile
{
	private Ship    ship    ;
	private boolean attacked;
	private boolean hit     ;

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

	public void setHit(boolean hit)
	{
		this.hit = hit;
	}

	public void setHit()
	{
		setHit(true);
	}

	public boolean getHit()
	{
		return hit;
	}

	public boolean isHit()
	{
		return getAttacked() && (getHit() || hasShip());
	}

	public boolean isMiss()
	{
		return getAttacked() && !isHit();
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
			setHit(true);
		}
	}

}

