

public abstract class Unit {
		
	private Tile GamePosition;
	
	private double HealthPoint;
	
	private int AvailableMovingRange;
	
	private String UnitFaction;
	
	public Unit (Tile GamePosition, double HealthPoint,int AvailableMovingRange, String UnitFaction) {
		
		
		this.GamePosition = GamePosition;
		
		this.HealthPoint = HealthPoint;
		
		this.AvailableMovingRange = AvailableMovingRange;
		
		this.UnitFaction = UnitFaction;
		
		if (GamePosition.addUnit(this) == true);
		else {
			throw new IllegalArgumentException("IllegalArguementException");
		}				
	}


	public final Tile getPosition() {
		
		return this.GamePosition;
		
	}
	
	public final double getHP() {
		
		return this.HealthPoint;
	}
	
	public final String getFaction() {
		
		return this.UnitFaction;
	}
	
	public boolean moveTo(Tile tt) {
		
		// check if within moving range
		if (this.AvailableMovingRange +1 > tt.getDistance(tt, this.GamePosition)) {
			if (tt.addUnit(this)) {
				this.getPosition().removeUnit(this);
				this.GamePosition = tt;
				return true;
			}			
		}
		return false;
	}
	
	public void receiveDamage(double damage) {
		if(GamePosition.isCity() == true) {
			damage = damage * 0.9;
		}
		this.HealthPoint = this.HealthPoint - damage;
		if (this.HealthPoint <= 0) {
			this.GamePosition.removeUnit(this);
		}
	}
	
	public abstract void takeAction(Tile tt);
	
	public boolean equals(Object haha) {	
		if (haha instanceof Unit) {
		   if (    (  ( (Unit)haha ).getPosition().equals(this.getPosition()) )    
			    && (  ( (Unit)haha ).getFaction().equals(this.getFaction())   ) 
			    && (  ( (Unit)haha ).getHP() == (this.getHP())            )   ){
			   return true;
			   }		   		
		}
		return false;		
	}
	
	
}
