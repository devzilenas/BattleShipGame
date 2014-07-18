public class GridFactory
{
	public static BattleShipGrid makeGrid()
	{
		return new BattleShipGrid(201, 201, 10, 10);
	}

	public static BattleShipGrid invisibleShips()
	{
		BattleShipGrid grid = makeGrid();
		grid.setShowShips(false);
		return grid;
	}

}

