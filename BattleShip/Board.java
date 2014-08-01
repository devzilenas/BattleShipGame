import java.util.List		  ;
import java.util.ArrayList    ;
import java.util.Iterator     ;
import java.util.Map          ;
import java.util.LinkedHashMap;

public class Board
{
	Dimension dim              ;
	Map<Point,Ship> ships      ;
	Map<Point,Tile> tiles      ;

	public Board(int width, int height)
	{
		dim   = new Dimension(width, height);
		tiles = new LinkedHashMap<Point, Tile>();
		ships = new LinkedHashMap<Point, Ship>();

		for (int x = 0; x < getHeight(); x++)
		{
			for (int y = 0; y < getWidth(); y++)
			{
				setTileAt(new Tile(), new Point(x,y));
			}
		}
	}

	public void setDimension(Dimension dim)
	{ 
		this.dim = dim;
	}

	public Dimension getDimension()
	{
		return dim;
	}

	public void setTiles(Map<Point, Tile> tiles)
	{
		this.tiles = tiles;
	}

	public Map<Point, Tile> getTiles()
	{
		return tiles;
	}

	public void setShips(Map<Point,Ship> ships)
	{
		this.ships = ships;
	}

	public Map<Point,Ship> getShips()
	{
		return ships;
	}

	public List<Point> getPoints()
	{
		return new ArrayList<Point>(getTiles().keySet());
	}

	public List<Ship> ships()
	{
		return new ArrayList<Ship>(getShips().values());
	}

	public void setTileAt(Tile tile, Point point)
	{
		getTiles().put(point, tile);
	}

	public Tile getTileAt(Point point)
	{
		return getTiles().get(point);
	}

	public Point getPointAt(int index)
	{
		return getPoints().get(index);
	}
	
	public boolean isEastMost(Point point)
	{
		int index = getPoints().indexOf(point);
		return 0 == (index + 1) % getWidth();
	}

	public boolean hasShipAt(Point point)
	{
		return getTileAt(point).hasShip();
	}

	public Ship getShipAt(Point point)
	{
		return getTileAt(point).getShip();
	}

	public void attackAt(Point point)
	{
		Tile tile = getTileAt(point);
		if (!tile.isAttacked())
		{
			tile.attack();
		}
	}

	public boolean allSunk()
	{
		boolean allSunk = true;
		for (Ship ship : ships())
		{
			if (!ship.isSunken())
			{
				allSunk = false;
				break;
			}
		}
		return allSunk;
	}

	public boolean fitsSouth(Ship ship, Point from)
	{
		List<Point> points = pointsSouth(from, ship.getLength());
		boolean fits = ship.getLength() <= points.size();
		boolean free = noShipAt(points);
		return fits && free;
	}

	public boolean fitsEast(Ship ship, Point from)
	{
		List<Point> points = pointsEast(from, ship.getLength());
		boolean fits = ship.getLength() <= points.size();
		boolean free = noShipAt(points);
		return fits && free;
	}

	//#TODO rename to "getPointsSouth" for clarity
	public List<Point> pointsSouth(Point from, int length)
	{
		List<Point> points = new ArrayList<Point>();
		int x = from.getX();
		int y = from.getY();

		if (y + length >= getDimension().getHeight())
		{
		}
		else
		{
			for (int i = 0 ; i < length; i++)
			{
				points.add(new Point(x, y + i));
			}
		}

		return points;
	}

	//#TODO rename to "getPointsEast" for clarity
	public List<Point> pointsEast(Point from, int length)
	{
		List<Point> points = new ArrayList<Point>();
		int x = from.getX();
		int y = from.getY();

		if (x + length >= getDimension().getWidth())
		{
		}
		else
		{
			for (int i = 0; i < length; i++)
			{
				points.add(new Point(x + i, y));
			}
		}
		return points;
	}

	public boolean noShipAt(List<Point> points)
	{
		boolean ret = true;
		for (Point p : points)
		{
			if (hasShipAt(p))
			{
				ret = false;
				break;
			}
		}
		return ret;
	}

	//#TODO rename to getRandomPoint
	public Point getRandom()
	{
		int   index = (int) (Math.random() * getSize());
		Point point = getPointAt(index);
		return point;
	}

	//#TODO rename to getRandomEmptyPoint
	public Point getRandomEmpty()
	{
		Point point = getRandom();
		return hasShipAt(point) ? getNextEmptyPoint(point) : point;
	}

	//#TODO rename to getRandomNotAttackedPoint
	public Point getRandomNotAttacked()
	{
		Point point = getRandom();
		Tile tile = null;
		do 
		{
			point = getNextPoint(point);
			tile = getTileAt(point);
		}
		while (tile.isAttacked());
		return point;
	}

	public Point getNextEmptyPoint(Point point)
	{
		Point np = getNextPoint(point);
		while (hasShipAt(np))
		{
			np = getNextPoint(np);
		}
		return np;
	}

	public Point getNextPoint(Point point)
	{
		int index = getPoints().indexOf(point) + 1;
		if (index >= getPoints().size() - 1)
		{
			index = 0;
		}
		else
		{
			index++;
		}
		return getPoints().get(index);
	}

	public void placeRandom(Ship ship)
	{
		Point point = getRandomEmpty(); 
		while (!fitsSouth(ship, point) && !fitsEast(ship, point))
		{
			point = getNextEmptyPoint(point);
		}
		if (fitsSouth(ship, point))
		{
			putSouth(ship, point);
		}
		else
		{
			putEast(ship, point);
		}
	}

	public void putSouth(Ship ship, Point point)
	{
		for (Point p : pointsSouth(point, ship.getLength()))
		{
			putShipAt(ship, p);
		}
	}

	public void putEast(Ship ship, Point point)
	{
		for (Point p : pointsEast(point, ship.getLength()))
		{
			putShipAt(ship, p);
		}
	}

	public void putShipAt(Ship ship, Point point)
	{
		getTileAt(point).setShip(ship);
		ships.put(point, ship);
	}

	public void putShipAt(Ship ship, Point[] points)
	{
		for (Point point : points)
		{
			putShipAt(ship, point);
		}
	}

	public int getWidth()
	{
		return getDimension().getWidth();
	}

	public int getHeight()
	{
		return getDimension().getHeight();
	}

	public int getSize()
	{
		return getDimension().getSize();
	}

	public boolean isAttacked(Point point)
	{
		return getTileAt(point).isAttacked();
	} 

	public boolean isHitAt(Point point)
	{
		return getTileAt(point).isHit();
	}

	public boolean isMissAt(Point point)
	{
		return getTileAt(point).isMiss();
	}

	public void setHitAt(Point point)
	{
		getTileAt(point).setHit(true);
	}
}
