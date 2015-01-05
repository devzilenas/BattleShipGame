package battleship.net.client;

import java.net.Socket;

public class BattleShipNetClientThread 
	extends BattleShipNetThread
{
	private BattleShipNetClientGame game   ;

	public BattleShipNetClientThread(Socket socket)
	{
		super(socket);
		this.game = BattleShipNetGameFactory.getClientGame();
	}

	public void setGame(BattleShipNetClientGame game)
	{
		this.game = game;
	}

	public BattleShipNetClientGame getGame()
	{
		return game;
	}

	@Override
	public void run()
	{
		getCommunicationModule().init();
	}

	public void say(String str)
	{
		getCommunicationModule().say(str);
	}

	public boolean gameOver()
	{
		return getGame().isOver();
	}

	public boolean isGetShips()
	{
		return BattleShipNetProtocol.isGetShips(getCommand());
	}

	public boolean attacks()
	{
		return BattleShipNetProtocol.attacks(
				getCommand());
	}

	public boolean attack()
	{
		return BattleShipNetProtocol.attack().equals(
				getCommand());
	}
	
	public void attackAt(Point point)
	{
		say(BattleShipNetProtocol.attackAt(point));
	}

	public void tellShips()
	{
		BattleShipNetClientGame game = getGame();
		Board board = game.getBoard(
				game.getClientPlayer());
		for (Ship ship : board.ships())
		{
			say(
					BattleShipNetProtocol.shipAt(
						board.points(ship)));
		}
		say(BattleShipNetProtocol.shipsEnd());
	}

	public boolean isHit()
	{
		return BattleShipNetProtocol.isHitAt(getCommand());
	}

	public boolean isMiss()
	{
		return BattleShipNetProtocol.isMissAt(getCommand());
	}

	public boolean isSunken()
	{
		return BattleShipNetProtocol.isSunken(getCommand());
	}

	public boolean isGameOver()
	{
		return BattleShipNetProtocol.isGameOver(getCommand());
	}
}
