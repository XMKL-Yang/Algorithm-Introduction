

public class Worker extends Unit{
	
	private int JobPerformed;
		
	public Worker(Tile GamePosition, double HealthPoint, String UnitFaction) {
		
		super(GamePosition, HealthPoint,2, UnitFaction);
		
		this.JobPerformed = 0;
	}
	
	private int get_jobs_done() {
		return this.JobPerformed;
	}

	@Override
	public void takeAction(Tile tt) {
		
		if ( this.getPosition().equals(tt) && tt.isImproved() == false ) {
			tt.buildImprovement();
			this.JobPerformed = this.JobPerformed+1;
			
			if (this.JobPerformed >= 10) {
				tt.removeUnit(this);
			}
		}
	}
	
	@Override
	public boolean equals(Object oo) {
		if (oo instanceof Worker) {
			if (((Worker) oo).get_jobs_done() == this.JobPerformed) {
				return super.equals(oo);
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
}
