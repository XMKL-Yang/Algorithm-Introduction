
public abstract class MilitaryUnit extends Unit{
	
	private double AttactDamage;
	
	private int AttactRange;
	
	private int Armor;

		
	//constructor: four from super, and three private
	public MilitaryUnit(Tile GamePosition, double HealthPoint, int AvailableMovingRange, 
			String UnitFaction, double AttactDamage, int AttactRange, int Armor) {
		
		super(GamePosition, HealthPoint, AvailableMovingRange , UnitFaction);
				
		this.AttactDamage = AttactDamage;
		
		this.AttactRange = AttactRange;
		
	    this.Armor = Armor;		
	}
	
	public void takeAction(Tile ii) {
		
		if (ii.getDistance(ii, this.getPosition()) < (this.AttactRange+1) ) {
			if(ii.selectWeakEnemy(this.getFaction()) != null) {
			if (this.getPosition().isImproved() == true) {
				ii.selectWeakEnemy(this.getFaction()).receiveDamage(this.AttactDamage* 1.05);
				//this.AttactDamage = this.AttactDamage * 1.05;
			}
			
			if (this.getPosition().isImproved() == false) {
				ii.selectWeakEnemy(this.getFaction()).receiveDamage(this.AttactDamage);
			}	}
			
		}else {
			return;
		}
	}
	
	@Override
	public void receiveDamage(double damage_received) {
		double multi = 100.0/(100.0 + this.Armor);
		super.receiveDamage(damage_received * multi);
		return;
	}
	
	
	
}
