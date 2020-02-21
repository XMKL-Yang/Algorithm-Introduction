

public class Tile {
	
	private int Xcoor;
	
	private int Ycoor;
	
	private boolean IsCityBuilt;
	
	private boolean ReceivedImprovements;
	
	private ListOfUnits listofunits = new ListOfUnits();
	
	
	public Tile(int Xcoor, int Ycoor) {
			this.Xcoor = Xcoor;
			this.Ycoor = Ycoor;
			//this.listofunits = new ListOfUnits();
			this.IsCityBuilt = false;
			this.ReceivedImprovements = false;
			
	}
	
	public int getX() {
		return Xcoor;
	}
	
	public int getY() {
		return Ycoor;
	}
	
	public boolean isCity() {
		return IsCityBuilt;
	}
	
	public boolean isImproved() {
		return ReceivedImprovements;
	}
	
	public void foundCity() {
		IsCityBuilt = true;
	}
	
	public void buildImprovement() {
		ReceivedImprovements = true;
	}
	
	
	public boolean addUnit(Unit aa) {
		
		if (aa == null) return false;
		
		if ( (aa instanceof MilitaryUnit) ) {
			MilitaryUnit[]arm = this.listofunits.getArmy();
			
			if(arm.length==0) {
				listofunits.add(aa);
				return true;
			}
			for(int i=0;i<arm.length;i++) {
				if (!(aa.getFaction().equals(listofunits.get(i).getFaction()))) {
					return false;
				}
				else {
					listofunits.add(aa);
					return true;
				}
			}
		}else {
			listofunits.add(aa);
			return true;
		}
		return false;
		
		
		
		
	}
	
	// input a unit, adds it to the tile's ListOfUnits
//	public boolean addUnit (Unit aa) {
//		// check if this is military
//		if (aa instanceof Archer || aa instanceof Warrior) {
//			
//		}
//		// check faction
//		for (int i = 0; i < listofunits.SizeOfListofunits; i++) {
//			if (aa.getFaction() != listofunits.get(i).getFaction()) {
//				return false;
//			}else {
//				i++;
//			}	
//		}
//		// if passed, then return true
//		listofunits.add(aa);
//		return true;
//	}
	
	public boolean removeUnit(Unit bb) {
		if(this.listofunits.indexOf(bb)==-1)
		/*if(listofunits.remove(bb)==false) */return false;
		else
			this.listofunits.remove(bb);
		return true;	
	}
	
	public  Unit selectWeakEnemy(String Faction) {
		
		// count enermy number to initialize a new differentFaction unit.
		int Enermy_num = 0;
		for (int i = 0; i < listofunits.size(); i++) {
			if ( !( this.listofunits.get(i).getFaction().equals(Faction)) ) {						
				Enermy_num ++;
			}
		}
		if (Enermy_num == 0) {
			return null;
		}
		// initialize a new Unit for Enemy
		Unit[] differentFaction = new Unit[Enermy_num];
		int i = 0;
		int j = 0;
		for (i = 0; i < listofunits.size(); i++) {
			if ( ! ( this.listofunits.get(i).getFaction().equals(Faction)) ) {			
				differentFaction[j] = listofunits.get(i);
				j++;
			}
		}
		// find the lowest health point unit from differentFaction
		Unit temp = differentFaction[0];	
		for (int d = 0; d < Enermy_num;d++) {
			if(differentFaction[d].getHP()<temp.getHP()) {
				temp = differentFaction[d];
			}			
		}
		return temp;
	}
	

	public static double getDistance(Tile tile1, Tile tile2) {
		double distance = 0;
		distance = Math.sqrt( (tile1.Xcoor - tile2.Xcoor) * ( tile1.Xcoor - tile2.Xcoor )
				+ (tile1.Ycoor - tile2.Ycoor) * (tile1.Ycoor - tile2.Ycoor) );
		return distance;			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
