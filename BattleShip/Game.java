import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Arrays;

public class Game
{
	private Player   turn   ;
	private Player   winner ;
	private boolean  started;
	private Map<Player, Board> boards;

	public Game()
	{
		boards = new LinkedHashMap<Player, Board>();
	}

	public Game(Player p1, Board b1, Player p2, Board b2)
	{
		this();
		boards.put(p1, b1);
		boards.put(p2, b2);
	}

	public void setTurn(Player player)
	{
		this.turn = player;
	}

	public Player getTurn()
	{
		return turn;
	}

	public void setWinner(Player player)
	{
		this.winner = player;
	}

	public Player getWinner()
	{
		return winner;
	}

	public void setStarted(boolean started)
	{
		this.started = started;
	}

	public boolean getStarted()
	{
		return started;
	}

	public boolean isStarted()
	{
		return started;
	}

	public void setBoards(Map<Player, Board> boards)
	{
		this.boards = boards;
	}

	public Map<Player, Board> getBoards()
	{
		return boards;
	}

	public List<Player> getPlayers()
	{
		return new ArrayList<Player>(boards.keySet());
	}

	public Player player(int i)
	{
		return getPlayer(i);
	}

	public Player getPlayer(int i)
	{
		return getPlayers().get(i);
	}

	public Player getPlayer(Board board)
	{
		Player player = null;
		for (Map.Entry<Player, Board> entry: getBoards().entrySet())
		{
			if (entry.getValue() == board)
			{
				player = entry.getKey();
				break;
			}
		}
		return player;
	}

	public Player getHuman()
	{
		Player player = null;
		for (Player p: getPlayers())
		{ 
			if (p.isHuman())
			{
				player = p;
				break;
			}
		}
		return player;
	}

	public Player getComputer()
	{
		Player player = null;
		for (Player p : getPlayers()) 
		{
			if (p.isComputer())
			{
				player = p;
				break;
			}
		}
		return player;
	}

	public Board getBoard(Player player)
	{
		return getBoards().get(player);
	}

	public Board getBoard(int i)
	{
		return (new ArrayList<Board>(getBoards().values())).get(i);
	}

	public Board board(int i)
	{
		return getBoard(i);
	}

	public Player opponent()
	{
		return opponent(player());
	}
	
	public Player opponent(Player player)
	{
		return player(0) == player ? player(1) : player(0);
	}

	public Player player()
	{
		return getTurn();
	}

	public boolean hasWinner()
	{
		return null != getWinner();
	}

	public boolean isComputerTurn()
	{
		return !(getTurn().getStrategy() instanceof BattleShipStrategyNull);
	}

	public void makeComputerTurn()
	{
		if (!isOver())
		{
			Point point = getTurn().getStrategy().getNextPoint(getBoard(player()));
			attack(point);
		}
	}

	public void start()
	{
		setTurn(Math.random() > 0.5 ? player(0) : player(1));
		setStarted(true);
	}

	public void attack(Point point)
	{
		getBoard(opponent()).attackAt(point);
		endTurn();
	}

	public void endTurn()
	{
		if (getBoard(opponent()).allSunk())
		{
			setWinner(player());
		}
		else
		{
			toggleTurn();
		}
	}

	public boolean isValidAttack(Point point)
	{
		Board opBoard = getBoard(opponent());
		return !opBoard.isAttacked(point);
	}

	private void toggleTurn()
	{
		this.turn = opponent();
	}

	public boolean isOver()
	{
		return hasWinner();
	}
}
