

public class Settler extends Unit{
	
	
	//private int AvailableMovingRange;
			
	public Settler(Tile GamePosition, double HealthPoint, String UnitFaction){		
		super (GamePosition,HealthPoint,2,UnitFaction);		
	}
	

	@Override
	public void takeAction(Tile tt) {
		// position & not city yet
		if (this.getPosition().equals(tt) && tt.isCity() == false ) {
			// a city should be built
			tt.foundCity();
			tt.removeUnit(this);
		}
		
	}
	
	@Override
	public boolean  equals(Object oo) {
		if (oo instanceof Settler) {
			  return super.equals(oo);	
			}
			return false;			
	}
	
}
	

