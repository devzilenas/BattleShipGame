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

	public boolean getHit()
	{
		return hit;
	}

	public boolean isHit()
	{
		return isAttacked() && (isHit() || hasShip());
	}

	public boolean isMiss()
	{
		return isAttacked() && !isHit();
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

