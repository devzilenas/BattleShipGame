import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Map;

public class BattleShipGrid
	extends Grid
{
	private Board   board    ;
	private boolean showShips = true;

	public BattleShipGrid(int width, int height, int rows, int cols)
	{
		super(width, height, rows, cols);
	}

	public BattleShipGrid(int width, int height, Board board)
	{
		super(width, height, board.getWidth(), board.getHeight());
		this.board = board;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}

	public Board getBoard()
	{
		return board;
	}

	public void setShowShips(boolean showShips)
	{
		this.showShips = showShips;
	}

	public boolean getShowShips()
	{
		return showShips;
	} 

	public Rectangle getRectangle(Point point)
	{
		return getRectangles().get(point);
	}

	public List<Point> getPoints()
	{
		return new ArrayList<Point>(getRectangles().keySet());
	}

	public Point getPoint(Rectangle rectangle)
	{
		Point ret = null;
		for (Map.Entry<Point, Rectangle> entry : getRectangles().entrySet())
		{
			if (entry.getValue() == rectangle)
			{
				ret = entry.getKey();
				break;
			}
		}
		return ret;
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		for (Point point : getPoints())
		{
			Tile tile   = board.getTileAt(point);
			Color color = null; 
			if (tile.isAttacked())
			{
				if (tile.hasShip())
				{
					Ship ship = tile.getShip();
					if (ship.isSunken())
					{
						color = BattleShipGridVisualizer.getSunkenShipColor();
					}
					else 
					{
						color = BattleShipGridVisualizer.getDamagedShipColor();
					}
				}
				else
				{
					color = BattleShipGridVisualizer.getMissColor();
				}
			}
			else
			{
				if (tile.hasShip() && getShowShips())
				{
					color = BattleShipGridVisualizer.getBattleShipColor();
				}
				else
				{
					color = GridVisualizer.getNormalRectangleColor();
				}
			}

			g2.setPaint(color);

			g2.fill(getRectangle(point));
		}
		
		paintGrid(g2);
	}
}

