public class Player
{
	public static final String DEFAULT_NAME_I = "Player %d";
	public static final String DEFAULT_NAME   = "Player";
	private Board  board;
	private String name ;

	public Player()
	{
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

	public void setBoard(Board board)
	{
		this.board = board;
	}

	public Board getBoard()
	{
		return board;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String getDefaultNameI()
	{
		return DEFAULT_NAME_I;
	}

	public String getDefaultName()
	{
		return DEFAULT_NAME;
	}

	public String getDefaultName(int i)
	{
		return String.format(getDefaultName(), i);
	}

	public Board board()
	{
		return getBoard();
	}
}
