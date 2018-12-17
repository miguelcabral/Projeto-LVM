package generate;

import java.util.ArrayList;
import java.util.LinkedList;


public class Path {
	
		ArrayList<ArrayList<LinkedList<Integer>>> allPaths;
		
		public Path(int dim) {
			this.allPaths = new ArrayList<ArrayList<LinkedList<Integer>>>();
			for(int i = 0; i < dim; i++) {
				allPaths.add(i, new ArrayList<LinkedList<Integer>>());
				}
			} 
		
	/*
	 * Adds path to position pos
	 * @param id1 : parent node id 
	 * @param id2 : node id
	 */
	public void add_path(int pos, LinkedList<Integer> paths ) {  
		int dim = (allPaths.get(pos)).size();
		allPaths.get(pos).add(new LinkedList<Integer>());
		LinkedList<Integer> aux = (allPaths.get(pos)).get(dim);
		
		for ( int i : paths) { aux.add(i); }
 
		}
	
	/*
	 * Get path to position pos
	 * @param id1 : parent node id 
	 * @param id2 : node id
	 */
	public ArrayList<LinkedList<Integer>> get_path(int dest) { return this.allPaths.get(dest); }
	
	/*
	 * Returns the size of  
	 * @param id1 : parent node id 
	 * @param id2 : node id
	 */
	public int size() { return this.allPaths.size(); }
}
