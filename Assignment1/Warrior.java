

public class Warrior extends MilitaryUnit{

	public Warrior(Tile GamePosition, double HealthPoint, String UnitFaction) {
		
		super(GamePosition, HealthPoint, 1, UnitFaction, 20.0, 1,25);
		
	}
	
	
	public boolean equals(Object obj) {
		if ( obj instanceof Warrior) {
			return super.equals(obj);
		}
		return false;
	}
		
}
