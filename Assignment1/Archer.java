

public class Archer extends MilitaryUnit{
	
	private int ArrowNumber;
	
	public Archer(Tile GamePosition, double HealthPoint, String UnitFaction) {
		
		super (GamePosition, HealthPoint, 2, UnitFaction, 15.0, 2, 0);
		
		this.ArrowNumber = 5;
	}
	
	private int getArrowNumber() {
		return this.ArrowNumber;
	}
	
	@Override
	public void takeAction(Tile tt) {
		if (this.ArrowNumber == 0) {
			// take time to return
			this.ArrowNumber = 5;
			return;
		}else {
			this.ArrowNumber = this.ArrowNumber -1;			
		}
		super.takeAction(tt);
	}
	
	@Override
	public boolean  equals(Object oo) {
		if (oo instanceof Archer)	{
			if (this.ArrowNumber == ((Archer)oo).getArrowNumber()) {
			return super.equals(oo);
			}
		}
		return false;
	}
	
	
	
	

}
