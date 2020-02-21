import java.util.HashMap;
import java.util.ArrayList;

public class SearchEngine {
	public HashMap<String, ArrayList<String> > wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception {
		this.wordIndex = new HashMap<String, ArrayList<String>>();  // word, hyperLink(edge)(list of url contain them)
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}
	
	/* 
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */

	public void crawlAndIndex(String url) throws Exception {
		ArrayList<String> content = parser.getContent(url);
		for(int i = 0; i < content.size(); i++) {
			content.set(i, content.get(i).toLowerCase());
			if (wordIndex.containsKey(content.get(i))) {
				if (!(wordIndex.get(content.get(i)).contains(url))) {
					wordIndex.get(content.get(i)).add(url);
				}
			}else {
				ArrayList<String> haha = new ArrayList<String>();
				haha.add(url);
				wordIndex.put(content.get(i), haha);
			}
		}
		internet.addVertex(url);
		internet.setVisited(url, true);
		ArrayList<String>link = parser.getLinks(url);
		for(int j = 0; j < link.size(); j++) {
			internet.addVertex(link.get(j));
			internet.addEdge(url, link.get(j));
			boolean visited = internet.getVisited(link.get(j));
			if (visited == false) crawlAndIndex(link.get(j));
		}		
	}
	
	
	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */
	
	public void assignPageRanks(double epsilon) {
		// TODO : Add code here
		ArrayList<Double> previous_rank = new ArrayList<Double>();
		ArrayList<Double> current_rank = new ArrayList<Double>();	
		//System.out.println("1");
		for(int num = 0; num < internet.getVertices().size(); num++) {
			previous_rank.add(num, 1.0);
			internet.setPageRank(internet.getVertices().get(num), 1.0);
			//System.out.println("1");
		}		
		boolean convergence = false;
		//System.out.println("2");
		while (convergence == false) {	
			//System.out.println("3");
			for(int k = 0; k < internet.getVertices().size(); k++) {
				previous_rank.add(k, internet.getPageRank(internet.getVertices().get(k)));			
			}
			current_rank = computeRanks(internet.getVertices());			
			for(int k = 0; k < internet.getVertices().size(); k++) {
				internet.setPageRank(internet.getVertices().get(k), current_rank.get(k));
			}			
			convergence = true;
			for(int k = 0; k < internet.getVertices().size(); k++) {
				double diff = current_rank.get(k)-previous_rank.get(k);
				if(! (0 - epsilon < diff && diff < epsilon)) {
					convergence = false;
					//System.out.println("4");
				}
				//System.out.println("5");
			}
			//System.out.println("6");
		}
		//System.out.println("7");
		
	}

	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph 
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls. 
	 * Note that the double in the output list is matched to the url in the input list using 
	 * their position in the list.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		// TODO : Add code here
		double d = 0.5;
		ArrayList<Double> pr = new  ArrayList<Double>();
		for(int i = 0; i < vertices.size(); i++) {
			double pr_value = 0;
			double w_value = 0;
			ArrayList<String> w = internet.getEdgesInto(vertices.get(i));
			for(int j = 0; j < w.size(); j++) {
				double pr_wj = internet.getPageRank(w.get(j));
				int out_wj = internet.getOutDegree(w.get(j));
				w_value = w_value + pr_wj / out_wj;
			}
			pr_value = (1 - d) + d * w_value;
			pr.add(i, pr_value);
		}		
		return pr;
	}

	
	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 * 
	 * This method should take about 25 lines of code.
	 */
	
	public ArrayList<String> getResults(String query) {
		// TODO: Add code here
		ArrayList<String> answer = wordIndex.get(query);
		HashMap<String, Double> b = new HashMap<String, Double>();
		for(int i=0; i<answer.size(); i++) {
			b.put(answer.get(i), internet.getPageRank(answer.get(i)));
		}
		return Sorting.fastSort(b);
	}
}
