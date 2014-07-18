import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BattleShipGridMouseListener
	extends GridMouseListener
{
	@Override
	public void mouseClicked(MouseEvent e)
	{
		BattleShipGrid grid  = (BattleShipGrid) e.getSource();

		Game   game          = BattleShipGridMain.getGame();
		Board  opponentBoard = grid.getBoard();
		Player opponent      = game.getPlayer(opponentBoard); 

		if (!game.isComputerTurn())
		{
			Point point = grid.getPoint(
					grid.getRectangle(e.getX(), e.getY()));
			if (game.isValidAttack(point))
			{
				game.attack(point);
			}
		}

		if (game.isOver())
		{
			BattleShipGridMain.setStatusMessage("Game over! You " + (game.getWinner() == opponent ? "loose" : "win") + "!" );
		}

		grid.repaint();
	}
}
