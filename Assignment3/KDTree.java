package assignments2019.a3posted;
// Yujing Yang
// 260827923

import java.util.ArrayList;
import java.util.Iterator;
public class KDTree implements Iterable<Datum>{ 

	KDNode 		rootNode;
	int    		k; 
	int			numLeaves;
	
	// constructor

	public KDTree(ArrayList<Datum> datalist) throws Exception {

		Datum[]  dataListArray  = new Datum[ datalist.size() ]; 

		if (datalist.size() == 0) {
			throw new Exception("Trying to create a KD tree with no data");
		}
		else
			this.k = datalist.get(0).x.length;

		int ct=0;
		for (Datum d :  datalist) {
			dataListArray[ct] = datalist.get(ct);
			ct++;
		}
		
	//   Construct a KDNode that is the root node of the KDTree.

		rootNode = new KDNode(dataListArray);
	}
	
	//   KDTree methods
	
	public Datum nearestPoint(Datum queryPoint) {
		return rootNode.nearestPointInNode(queryPoint);
	}
	

	public int height() {
		return this.rootNode.height();	
	}

	public int countNodes() {
		return this.rootNode.countNodes();	
	}
	
	public int size() {
		return this.numLeaves;	
	}

	//-------------------  helper methods for KDTree   ------------------------------

	public static long distSquared(Datum d1, Datum d2) {

		long result = 0;
		for (int dim = 0; dim < d1.x.length; dim++) {
			result +=  (d1.x[dim] - d2.x[dim])*((long) (d1.x[dim] - d2.x[dim]));
		}
		// if the Datum coordinate values are large then we can easily exceed the limit of 'int'.
		return result;
	}

	public double meanDepth(){
		int[] sumdepths_numLeaves =  this.rootNode.sumDepths_numLeaves();
		return 1.0 * sumdepths_numLeaves[0] / sumdepths_numLeaves[1];
	}
	
	class KDNode { 

		boolean leaf;
		Datum leafDatum;           //  only stores Datum if this is a leaf
		
		//  the next two variables are only defined if node is not a leaf

		int splitDim;      // the dimension we will split on
		int splitValue;    // datum is in low if value in splitDim <= splitValue, and high if value in splitDim > splitValue  

		KDNode lowChild, highChild;   //  the low and high child of a particular node (null if leaf)
		  //  You may think of them as "left" and "right" instead of "low" and "high", respectively

		KDNode(Datum[] datalist) throws Exception{

			/*
			 *  This method takes in an array of Datum and returns 
			 *  the calling KDNode object as the root of a sub-tree containing  
			 *  the above fields.
			 */

			//   ADD YOUR CODE BELOW HERE			
			if (datalist.length == 1) {
				leaf = true;	
				leafDatum = datalist[0];
				numLeaves++;				
			}else {
				leaf = false;
				splitDim = this.findSplitDim(datalist);				
				ArrayList dimension = new ArrayList<Integer>();
				for(int i = 0; i < datalist.length; i++) {
					dimension.add(datalist[i].x[splitDim]);
				}				
				splitValue = this.computeSplitValue (dimension);
				ArrayList<Datum> Lowdatalist = new ArrayList<Datum>();
				ArrayList<Datum> Highdatalist = new ArrayList<Datum>();
				for(int i = 0; i < datalist.length; i++) {
					if (datalist[i].x[splitDim] <= splitValue)	Lowdatalist.add(datalist[i]);
					else Highdatalist.add(datalist[i]);
				}
				if (Highdatalist.size() == 0) {
					leaf = true;
					leafDatum = Lowdatalist.get(0);
					numLeaves++;
				}else {
					Datum low[] = new Datum[Lowdatalist.size()];
					for (int k = 0; k < Lowdatalist.size(); k++) {
						low[k] = Lowdatalist.get(k);
					}
					Datum high[] = new Datum[Highdatalist.size()];
					for (int k = 0; k < Highdatalist.size(); k++) {
						high[k] = Highdatalist.get(k);
					}			
					lowChild = new KDNode(low);
					highChild = new KDNode(high);
				}
			}
			//   ADD YOUR CODE ABOVE HERE

		}

		public Datum nearestPointInNode(Datum queryPoint) {
			Datum nearestPoint, nearestPoint_otherSide;
			KDNode thisSide, otherSide;
			int distance;
		
			//   ADD YOUR CODE BELOW HERE
			if (this.leaf==true){
			    return this.leafDatum;
			} else {	
				
			    if (queryPoint.x[splitDim] > splitValue){
			    	thisSide = highChild;
			    	otherSide = lowChild;
			    } else {
			    	thisSide = lowChild;
			    	otherSide = highChild;
			    }
			    
		    	nearestPoint = thisSide.nearestPointInNode(queryPoint);	 
			    distance = (int) distSquared(queryPoint, nearestPoint);
		    	
			    if (distance < Math.pow(splitValue - queryPoint.x[splitDim], 2)) {
			    	return nearestPoint;
			    } else {
			     nearestPoint_otherSide = otherSide.nearestPointInNode(queryPoint);
			     	if (distance < distSquared(queryPoint, nearestPoint_otherSide)) {
			     		return nearestPoint;
			     	} else {
			     		return nearestPoint_otherSide;
			     	}
			    }
			    
			   }
			//   ADD YOUR CODE ABOVE HERE

					

		}
		


		
		// -----------------  KDNode helper methods (might be useful for debugging) -------------------
		
		private int findSplitDim (Datum[] inputDataList) {
			long difference = 0;
			int MaxDiff = -1;
			int num = 0; // number of dimension, start from 1		
			for (int j = 0; j < k; j++) {
				ArrayList<Integer> dimension = new ArrayList<Integer>();
				for (int i = 0; i < inputDataList.length; i++) {
					dimension.add(inputDataList[i].x[j]);
				}
				difference = computeDifference(dimension);
				if(difference > MaxDiff) {
					MaxDiff = (int)difference;
					num = j;
				}
			}
			return num;
		}
		
		private long computeDifference(ArrayList<Integer> dimension) {		
			int min = dimension.get(0);
			int max = dimension.get(0);		
			for(int i = 0; i < dimension.size(); i++) {
				if (dimension.get(i) < min) 		min = dimension.get(i);
				if (dimension.get(i) > max) 		max = dimension.get(i);
			}
			int diff = max - min;		
			return diff;		
		}
		
		private int computeSplitValue(ArrayList<Integer> array){
			int average = 0;
			int min = array.get(0);
			int max = array.get(0);		
			for(int i = 0; i < array.size(); i++) {
				if (array.get(i) < min) 		min = array.get(i);
				if (array.get(i) > max) 		max = array.get(i);
			}
			
			if (max + min >= 0) {
				average = (max + min)/2;
			}else if (max == min) {
				average = (max + min)/2;
			}else {
				average = (max + min)/2 -1;				
			}				
			return average;
			
		}
		
		public int height() {
			if (this.leaf) 	
				return 0;
			else {
				return 1 + Math.max( this.lowChild.height(), this.highChild.height());
			}
		}

		public int countNodes() {
			if (this.leaf)
				return 1;
			else
				return 1 + this.lowChild.countNodes() + this.highChild.countNodes();
		}
		
		/*  
		 * Returns a 2D array of ints.  The first element is the sum of the depths of leaves
		 * of the subtree rooted at this KDNode.   The second element is the number of leaves
		 * this subtree.    Hence,  I call the variables  sumDepth_size_*  where sumDepth refers
		 * to element 0 and size refers to element 1.
		 */
				
		public int[] sumDepths_numLeaves(){
			int[] sumDepths_numLeaves_low, sumDepths_numLeaves_high;
			int[] return_sumDepths_numLeaves = new int[2];
			
			/*     
			 *  The sum of the depths of the leaves is the sum of the depth of the leaves of the subtrees, 
			 *  plus the number of leaves (size) since each leaf defines a path and the depth of each leaf 
			 *  is one greater than the depth of each leaf in the subtree.
			 */
			
			if (this.leaf) {  // base case
				return_sumDepths_numLeaves[0] = 0;
				return_sumDepths_numLeaves[1] = 1;
			}
			else {
				sumDepths_numLeaves_low  = this.lowChild.sumDepths_numLeaves();
				sumDepths_numLeaves_high = this.highChild.sumDepths_numLeaves();
				return_sumDepths_numLeaves[0] = sumDepths_numLeaves_low[0] + sumDepths_numLeaves_high[0] + sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
				return_sumDepths_numLeaves[1] = sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
			}	
			return return_sumDepths_numLeaves;
		}
		
	}

	public Iterator<Datum> iterator() {
		return new KDTreeIterator();
	}
	
	private class KDTreeIterator implements Iterator<Datum> {	
		
	//   ADD YOUR CODE BELOW HERE
		
		ArrayList<Datum> SortedArrayList = new ArrayList<Datum>();
		private int num = 0;
		private int size;
		
		//constructor
		public KDTreeIterator() {	
			this.insertion(rootNode);
			size = SortedArrayList.size();
		}
				
		private void insertion(KDNode kdNode){			
			if (kdNode.leaf==true) {
				SortedArrayList.add(kdNode.leafDatum);
			}else {	
					insertion(kdNode.lowChild);					
					insertion(kdNode.highChild);
				}
			}				  
		
		public boolean hasNext(){			
			if(num < size) {
				return true;
			}else {
				return false;
			}
		}
		
		public Datum next() {
			num++;
			return SortedArrayList.get(num-1);			
		}		
		//   ADD YOUR CODE ABOVE HERE
	}
}

