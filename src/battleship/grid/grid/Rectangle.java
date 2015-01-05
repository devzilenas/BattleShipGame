package battleship.grid.grid;

public class Rectangle
	extends java.awt.Rectangle
{
	private boolean active;

	public Rectangle(int x, int y)
	{
		super(x,y);
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public boolean getActive()
	{
		return active;
	}

	public boolean isActive()
	{
		return active;
	}

	public void toggleActive()
	{
		setActive(!getActive());
	}
}
