import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridMouseListener
	implements MouseListener
{
	public void mouseClicked(MouseEvent e)
	{
		Grid grid = (Grid) e.getSource(); 
		Rectangle r = grid.getRectangle(e.getX(), e.getY());
		r.toggleActive();

		grid.repaint();
	}

	public void mousePressed(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}
	
	public void mouseExited(MouseEvent e)
	{
	}
}
