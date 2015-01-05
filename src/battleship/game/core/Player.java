package battleship.core;

public class Player
{
	private static final String DEFAULT_NAME   = "Player";
	private static final String DEFAULT_NAME_I = "Player %d";
	private String   name    ;
	private Board    board   ;
	private Strategy strategy;

	public Player()
	{
	}

	public Player(Strategy strategy)
	{
		this.strategy = strategy;
	}

	public Player(Board board)
	{
		this.board = board;
		this.name  = getDefaultName();
	}

	public Player(int i)
	{
		this.name = getDefaultName(i);
	}

	public Player(String name)
	{
		this.name = name;
	}

	public Player(int i, Board board)
	{
		this(i); 
		this.board = board;
	}

	public Player(String name, Board board)
	{
		this(name);
		this.board = board;
	}

	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}

	public Strategy getStrategy()
	{
		return strategy;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}

	public Board getBoard()
	{
		return board;
	}

	public String getDefaultName()
	{
		return DEFAULT_NAME;
	}

	public String getDefaultName(int i)
	{
		return String.format(getDefaultNameI(), i);
	}

	private String getDefaultNameI()
	{
		return DEFAULT_NAME_I;
	}

	public Board board()
	{
		return getBoard();
	}

	public boolean isHuman()
	{
		return getStrategy() instanceof BattleShipStrategyNull;
	}

	public boolean isComputer()
	{
		return !isHuman();
	}
}
