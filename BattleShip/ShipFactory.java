public class ShipFactory
{
	public static final int AIRCRAFT_CARIER = 5;
	public static final int BATTLE_SHIP     = 4;
	public static final int SUBMARINE       = 3;
	public static final int CRUISER         = 3;
	public static final int DESTROYER       = 2;

	public static Ship createShip(String shipType)
	{
		int size = 0;
		switch (shipType)
		{
			case "AircraftCarrier":
				size = AIRCRAFT_CARIER;
				break;
			case "BattleShip":
				size = BATTLE_SHIP;
				break;
			case "Submarine":
				size = SUBMARINE; 
				break;
			case "Cruiser":
				size = CRUISER;
				break;
			case "Destroyer":
				size = DESTROYER;
				break;
		}
		return new Ship(size);
	}

	public static Ship[] createShips()
	{
		Ship[] ships = { 
			createShip("AircraftCarrier"), 
			createShip("BattleShip"), 
			createShip("Submarine"),
			createShip("Cruiser"),
			createShip("Destroyer") };
		return ships;
	}
	
	public static Ship sunken(int size)
	{
		Ship ship = new Ship(size);
		ship.sunk();
		return ship;
	}
}
