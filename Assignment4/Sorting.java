import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

public class Sorting {

	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
	
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1)))>0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {
    	
    	// ADD YOUR CODE HERE
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls
             
        return Sorting.mergeSort(sortedUrls, results);  
    }
    
    private static <K, V extends Comparable> ArrayList<K> mergeSort(ArrayList<K> inputList, HashMap<K, V> results) {   
  	
    	if(inputList.size()==1) {
    		return inputList;
    	}else {
    		int mid = (inputList.size()-1)/2;
    		ArrayList<K>  list1 = new ArrayList<K> ();
    		ArrayList<K>  list2 = new ArrayList<K> ();
    		for(int i = 0; i <= mid; i++) {
    			list1.add(inputList.get(i));
    		}
    		int size = inputList.size();
    		for(int i = mid+1; i <size; i++) {
    			list2.add(inputList.get(i));
    		}
    		list1 = Sorting.mergeSort(list1, results);
    		list2 = Sorting.mergeSort(list2, results);
    		return Sorting.merge(list1,list2, results);
    	}	
    }
    
	private static <K,V extends Comparable> ArrayList<K> merge(ArrayList<K> inputList1, ArrayList<K> inputList2, HashMap<K, V> results){
    	
		ArrayList<K> list = new ArrayList<K> ();
		
		while(!inputList1.isEmpty() && !inputList2.isEmpty()) {
			if((results.get(inputList1.get(0))).compareTo(results.get(inputList2.get(0))) > 0) {
				list.add(inputList1.remove(0));
			}else {
				list.add(inputList2.remove(0));			
			}
		}
		
		while (!inputList1.isEmpty()) {
			list.add(inputList1.remove(0));
		}
		
		while (!inputList2.isEmpty()) {
			list.add(inputList2.remove(0));
		}
		
		return list;  	
    }


}
