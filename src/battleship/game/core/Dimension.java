package battleship.core;

public class Dimension
{
	int width ;
	int height;

	public Dimension(int width, int height)
	{
		this.width  = width;
		this.height = height;
	}

	public void setWidth(int width)
	{
		this.width = width;
	} 

	public int getWidth()
	{
		return width;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getHeight()
	{
		return height;
	}

	public int getSize()
	{
		return getWidth() * getHeight();
	}
}
