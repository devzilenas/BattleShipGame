package battleship.grid.core;

import java.awt.Color;

public class BattleShipGridVisualizer
	extends GridVisualizer
{ 
	public static Color getBattleShipColor()
	{
		return Color.GRAY;
	}

	public static Color getOpponentBattleShipColor()
	{
		return getBattleShipColor();
	}

	public static Color getDamagedShipColor()
	{
		return Color.ORANGE;
	}

	public static Color getMissColor()
	{
		return Color.YELLOW;
	}

	public static Color getSunkenShipColor()
	{
		return Color.BLACK;
	}
}
