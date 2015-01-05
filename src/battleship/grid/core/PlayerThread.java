package battleship.grid.core;

public class PlayerThread
	implements Runnable
{
	public void doRun()
		throws InterruptedException
	{
		for (;;)
		{
			Game game = BattleShipGridMain.getGame();
			Thread.sleep(100);
			if (null != game 
					&& game.isStarted()
					&& !game.isOver()
					&& game.isComputerTurn())
			{
				BattleShipGridMain.getGame().makeComputerTurn();
				BattleShipGridMain.getGridMy().repaint();
			}
		}

	}

	public void run()
	{
		try
		{ 
			doRun();
		} 
		catch (InterruptedException e)
		{
			System.out.println("interrupted");
		}
	}
}
