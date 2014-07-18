import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class Grid
	extends Canvas
{
	private int       rows;
	private int       cols;
	private Dimension dim ;
	private Map<Point, Rectangle> rectangles;

	public Grid(int width, int height, int rows, int cols)
	{
		dim = new Dimension(width, height);
		this.rows = rows;
		this.cols = cols;

		rectangles = new LinkedHashMap<Point, Rectangle>(); 
		for (int row = 0; row < getRows(); row++)
		{
			for (int col = 0; col < getCols(); col++)
			{
			    Rectangle r = makeRectangle(row, col);
				rectangles.put(new Point(row, col), r);
			}
		}

	}

	public Map<Point, Rectangle> getRectangles() 
	{
		return rectangles;
	}

	public Dimension getDimension()
	{
		return dim;
	}

	public void setRows(int rows)
	{
		this.rows = rows; 
	}

	public int getRows()
	{
		return rows;
	}

	public void setCols(int cols)
	{
		this.cols = cols;
	}

	public int getCols()
	{
		return cols;
	}

	public List<Rectangle> rectangles()
	{
		return new ArrayList<Rectangle>(getRectangles().values());
	}

	public Rectangle getRectangle(int x, int y)
	{
		Rectangle rectangle = null;
		for (Rectangle r : rectangles())
		{ 
			if (r.contains(x,y))
			{
				rectangle = r;
			}
		}
		return rectangle;
	}

	public Rectangle makeRectangle(int row, int col)
	{
		Rectangle r = new Rectangle(
				(int) getWidth()/getCols(),
				(int) getHeight()/getRows());
		r.setLocation((int)(col*r.getWidth()), (int)(row*r.getHeight()));
		return r;
	}

	public int getHeight()
	{
		return getDimension().getHeight();
	}

	public int getWidth()
	{
		return getDimension().getWidth();
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		for (Rectangle r : rectangles())
		{
			if (r.isActive())
			{
				g2.setPaint(GridVisualizer.getActiveRectangleColor());
			} 
			else
			{
				g2.setPaint(GridVisualizer.getNormalRectangleColor());
			}
			g2.fill(r);
		}
		paintGrid(g2);
	}

	public void paintGrid(Graphics2D g2)
	{
		g2.setPaint(GridVisualizer.getGridColor());
		for (Rectangle r : rectangles())
		{
			g2.draw(r);
		}
	}

	@Override
	public java.awt.Dimension getPreferredSize()
	{
		return new java.awt.Dimension(getWidth(), getHeight());
	}
}
