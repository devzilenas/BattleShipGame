public class BattleShipPlayer
	extends Player
{
	private BattleShipStrategy strategy;

	public BattleShipPlayer(BattleShipStrategy strategy)
	{
		this.strategy = strategy;
	}

	public void setStrategy(BattleShipStrategy strategy)
	{
		this.strategy = strategy;
	}

	public BattleShipStrategy getStrategy()
	{
		return strategy;
	}

	public boolean isHuman()
	{
		return getStrategy() instanceof BattleShipStrategyNull;
	}

	public boolean isComputer()
	{
		return !isHuman();
	}
}

