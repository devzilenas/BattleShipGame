import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridMain
{
	public static void showGrid()
	{ 
		JFrame frame = new JFrame("Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Grid grid = new Grid(201, 201, 10, 10);

		grid.addMouseListener(new GridMouseListener());

		JPanel panel = new JPanel();
		panel.add(grid);

		frame.add(panel);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(
			new Runnable()
			{
				public void run() 
				{
					showGrid();
				}
			});
	}
}
