

// implement your own arraylist of unit
public class ListOfUnits {
	
	//array: store the units that are part of the ListOfUnits
	private Unit[] newOne;
	
	//size: how many units have been stored in this list
	private int SizeOfListofunits;
	
	//constructor
	//no inputs
	public ListOfUnits() {		
		this.newOne = new Unit[10];
		this.SizeOfListofunits = 0;
	}
	
	
	// no inputs, return number of units
	public int size() {		
		return this.SizeOfListofunits;
	}
		
	//no inputs, returns an array containing all units, no null element
	// how to ensure there is no null element 
	public Unit[] getUnits() {		
		Unit[] newUnit = new Unit[this.SizeOfListofunits];
		for (int i = 0; i < this.SizeOfListofunits; i++) {
			newUnit[i] = this.newOne[i];
		}
		//this.newOne = newUnit;
		return newUnit;
	}
	
	
	//input with int, return the unit at the specified position
	// if out of range, throw IndexOutOfBoundsException
	// ???
	public Unit get(int number){
		
		if (!(number < 0 || number >= SizeOfListofunits) ){return this.newOne[number];
			
		}else {
			throw new IndexOutOfBoundsException("you must input a number that is in the range.");
			
		}
	}
	
	
	// input Unit, no return
	// add the Unit at the end of this list
	// if not enough space available, resize the array of units
	// new_capacity = old_capacity + old_capacity/2 + 1
//	public void add(Unit uu) {
//		
//		// if the last one in the newOne is not null
//		// find the last one in the list, change the next one to uu
//		if (this.SizeOfListofunits<newOne.length) {
//			//int i = 0;
//			// iterate until the next one is null
//			// do i need this while loop? Does the length mean the last one or last one in size
//			/*while ( newOne[i] != null ){
//				i++;
//			}*/
//			// when the i one is null, change newOne[i] to uu
//			newOne[SizeOfListofunits] = uu;
//		}else {
//			// when the last one in newOne is null
//			// resize the array of units
//			int new_capacity = this.newOne.length + this.newOne.length/2 +1;
//			Unit AnotherNew[] = new Unit[new_capacity];
//			// copy the old one the new one
//			for(int i=0;i<SizeOfListofunits;i++) {
//				AnotherNew[i] = newOne[i];
//			}
//			// add the uu to the AnotherNew
//			//int j = 0;
//			/*while ( AnotherNew[j] != null ){
//				j++;
//			}*/
//			// when the i one is null, change newOne[i] to uu
//			// do I need this??
//			AnotherNew[SizeOfListofunits] = uu;	
//			// refer back
//			this.newOne = AnotherNew;
//			}
//		SizeOfListofunits++;
//	}*/
	
	
	
	public void add(Unit uu) {
		
		if (uu==null) return;
		if (this.SizeOfListofunits>=newOne.length) {

		int new_capacity = this.newOne.length + this.newOne.length/2 +1;
			Unit[] AnotherNew = new Unit[new_capacity];

			for(int i=0;i<SizeOfListofunits;i++) {
			AnotherNew[i] = newOne[i];}
			newOne = AnotherNew;
			}
			
				newOne[SizeOfListofunits] = uu;
				SizeOfListofunits++;
	}
//	
////	public void add(Unit uu) {
//		if ( newOne[this.newOne.length] != null) {
//			
//		}else {
//			
//		}
//	}
	
		// input a Unit, returns an int, return -1 if no matches
		public int indexOf(Unit bb) {
		// search for bb
			if(bb == null) return -1;
			for (int i = 0; i < SizeOfListofunits; i++) {
				if (bb.equals(newOne[i])) {
					return i;
				}
				
			}	
		return -1;		
		}
	
		
		/*public boolean remove(Unit cc) {
			// check if cc exist in newOne
			for (int i = 0; i < this.SizeOfListofunits; i++) {
				// if find it, then remove it
				if (this.newOne[i].equals(cc)) {
					for (int j = i; j < SizeOfListofunits-1; j++) {
						this.newOne[j] = this.newOne [j+1];
					}
					break;
				}else {
					if (i == this.newOne.length-1) return false;			
				}
			}
			Unit[] anotherOne = new Unit[this.newOne.length-1];
			System.arraycopy(newOne, 0, anotherOne, 0, newOne.length-1);
			this.newOne = anotherOne;
			SizeOfListofunits--;
			return true;
		}*/
		
		
		public boolean remove(Unit cc) {
			// check if cc exist in newOne
			for (int i = 0; i < this.SizeOfListofunits; i++) {
				// if find it, then remove it
				if (this.newOne[i].equals(cc)) {
					for (int j = i; j < SizeOfListofunits-1; j++) {
						this.newOne[j] = this.newOne [j+1];}
						SizeOfListofunits--;
						return true;
					
					
				}}
			return false;
		}
		
		
		// no inputs, return an array of MilitaryUnits
		public MilitaryUnit[] getArmy() {
			int num = 0;
			for (int j = 0; j < this.SizeOfListofunits; j++) {
				if (this.newOne[j] instanceof MilitaryUnit) {
					num ++;
				}
			}			
			MilitaryUnit[] army = new MilitaryUnit[num];
			int m = 0;
			int i = 0;
			for (i = 0; i < this.SizeOfListofunits; i++) {
				if (this.newOne[i] instanceof MilitaryUnit) {
				army[m] = (MilitaryUnit) this.newOne[i];
				m++;
				}
			}
			return army;			
		}
	
	
	

}
