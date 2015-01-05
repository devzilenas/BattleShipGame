package battleship.grid.core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class BattleShipGridMain
	implements ActionListener
{
	private static BattleShipGrid gridMy;
	private static BattleShipGrid gridOpponent;
	private static Game           game;
	private static JLabel         statusLabel;

	public static Grid getGridMy()
	{
		return gridMy;
	}

	public static Grid getGridOpponent()
	{
		return gridOpponent;
	}

	public static void setGame(Game game)
	{
		BattleShipGridMain.game = game;
	}

	public static Game getGame()
	{
		return game;
	}

	public static void setStatusLabel(JLabel statusLabel)
	{
		BattleShipGridMain.statusLabel = statusLabel;
	}

	public static JLabel getStatusLabel()
	{
		return statusLabel;
	}

	public static void setStatusMessage(String msg)
	{
		getStatusLabel().setText(msg);
	}

	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "New game":
				newGame();
				break;
		}
	}

	public static void newGame()
	{
		setGame(GameFactory.getMiltonBradleyHumanAgainst(
					new BattleShipStrategyRandom()));

		gridMy.setBoard(getGame().board(1));
		gridOpponent.setBoard(getGame().board(0));

		getGame().start();

		gridMy.repaint();
		gridOpponent.repaint();
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Battle ship");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gridMy       = GridFactory.makeGrid();
		gridOpponent = GridFactory.invisibleShips();
		gridOpponent.addMouseListener(new BattleShipGridMouseListener());
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(bl);
		JButton newGameButton = new JButton("New game");
		newGameButton.addActionListener(new BattleShipGridMain());
		newGameButton.setActionCommand("New game");
		panel.add(newGameButton);
		panel.add(new JLabel("My ships"));
		panel.add(gridMy);
		panel.add(new JLabel("Opponent's ships"));
		panel.add(gridOpponent);
		setStatusLabel(new JLabel("Play!"));
		panel.add(getStatusLabel());

		frame.add(panel);

		(new Thread(new PlayerThread())).start();

		newGame();

		frame.pack();
		frame.setVisible(true);
	}
}
