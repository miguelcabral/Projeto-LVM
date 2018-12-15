package generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;


public class GeneratePromela{
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
	
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.print("Numero de vertices:");
		int numvertex = reader.nextInt();
		
		DGraph G = new DGraph(numvertex); // The first node is always the target
		
		//Write the promela file:
		PrintWriter writer = new PrintWriter("BGP.pml", "UTF-8"); 
		
		for(int i=0; i < numvertex; i++) {
			System.out.printf("O vertice %d tem arestas para os vertices:", i);
			String edges = reader.next(); // Scans the next token of the input as an int.
			
			for(int j=0; j < edges.length(); j++) {
				if (Character.isDigit(edges.charAt(j))) {
					G.add_edge(i, Character.getNumericValue(edges.charAt(j)));
					}
				}
			}
		
		DFSrecur(0, numvertex, G);
		
		
		
		//once finished
		reader.close();	
		writer.close();	
		}

					
			/*		if ( i > 0){
						// criar o processo do Vértice
						writer.printf("active proctype n%d() { ",i);
						writer.println("byte electNode;");// elected cost  SOME CODE FOR "NO ELECTED COST"
						writer.println("byte x;");// cost received
						writer.println("byte path;");// path received 
						writer.printf("byte state[%d] paths;",numvertex);// array of paths 
						writer.printf("byte state[%d] costs;", numvertex); // costs for adjacent nodes
					
						System.out.printf("O custo de %d ir para o target por %d quando o custo para %d é:", i, edges.charAt(j), i );
						int cost = reader.nextInt();
					
						writer.println("do");
					
						for( int k = 0; k < G.children(i).length; k++) { 
							writer.printf(":: c%d%d ? x, path;", G.children(i)[k], i); 
							//1. verificar o path
						
							loops = generateLoops(i);
			
							for ( int t = 0; t < loops.size(); t++) {
								String aux = String.valueOf(loops.get(t));
								int [ loops.get(t)).length()] lista;
							
								for(int p = 0; p < String.valueOf(loops.get(t)).length(); p++){
									aux = shift(aux);
									lista[p] = aux;
						        
								}
							}
						}
					
					}else {// criar o processo do Target 
							}
					}*/
							
		/**
	 	* Returns all the shifts of a word e.g. abc becomes abc, bca, cab
	 	* int s = vertex
	 	* int V = number of vertex
	 	* DGraph D = graph G
	 	* @param s, v, G
	 	* @return all the shifts of a word 
	 	*/	
		public static String shift(String s) {
		    return s.charAt(s.length()-1)+s.substring(0, s.length()-1);
		}
		 		
		
		/**
		 * Function used by DFS recursive
		 * int v = vertex
		 * boolean visited 
		 * DGraph D = graph G
		 * @param s, v, G
		 * @return 
		 */	
		// A function used by DFS 
	    public static void DFSUtil(int s,boolean visited[], String path, DGraph G) 
	    { 
	        // Mark the current node as visited and print it 
	        visited[s] = true;  
	        
	        LinkedList<Integer> childs = G.children(s);
	        // Recur for all the vertices adjacent to this vertex 
	        for( int j = 0; j < childs.size(); j++ )
            { 
                int n = childs.get(j); 
                if(!visited[n]) {  DFSUtil(n, visited, String.valueOf(n) + path, G); }
                else if (path.contains(String.valueOf(n))){ System.out.print("Loop : " + String.valueOf(n) + path + "\n"); }  
            } 
	    } 
	  
	    
	    /**
		 * DFS recursive
		 * int s = source vertex
		 * int numvertex = number of vertex
		 * DGraph D = graph G
		 * @param s, numvertex, G
		 * @return 
		 */	
	    // The function to do DFS. It uses recursive DFSUtil() 
	    public static void DFSrecur(int s, int numvertex, DGraph G) 
	    { 
	        // Mark all the vertices as not visited(set as 
	        // false by default in java) 
	        boolean visited[] = new boolean[numvertex]; 
	  
	        // Call the recursive helper function to print DFS traversal 
	        DFSUtil(s, visited, String.valueOf(s), G); 
	    }
}