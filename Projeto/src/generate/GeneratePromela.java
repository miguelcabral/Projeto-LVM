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
		
		for(int i=0; i < numvertex; i++) {
			System.out.printf("O vertice %d tem arestas para os vertices:", i);
			String edges = reader.next(); // Scans the next token of the input as an int.
			
			for(int j=0; j < edges.length(); j++) {
				if (Character.isDigit(edges.charAt(j))) {
					G.add_edge(i, Character.getNumericValue(edges.charAt(j)));
					}
				}
			}
		// Perguntar os valores de custo possiveis (nao tem de ser necessariamente {0,1,...,numvertex})
		System.out.println("Insira os valores possiveis dos custos dos contratos:");
		String StringCustos = reader.next();
		LinkedList<Integer> Custos = new LinkedList<Integer>();
		for(int j=0; j < StringCustos.length(); j++) {
			if (Character.isDigit(StringCustos.charAt(j))) {
				Custos.add(Character.getNumericValue(StringCustos.charAt(j)));
				}
			}
		// Criar o ficheiro em PROMELA
		PrintWriter writer = new PrintWriter("BGP.pml", "UTF-8");
		
		//For each edge create a channel. Each channel sends a pair with elected cost and path to reach the target.
				for(int i=0; i < numvertex; i++) {
					int [] pais = G.parents(i);
					for(int j=0;j<pais.length;j++) {
						writer.printf("chan c%d%d = [1] of {byte,byte};",i,j); 
						// O canal cxy e de x para y, porque ha uma aresta de y para x.
						// O segundo byte e tipo: o caminho 1->2->3 e representado pelo numero 123. (nao ha problemas porque o 0 e o target)
						}
				}
		
		for(int i=0; i < numvertex; i++) {
			
		if ( i > 0){
			// criar os processos dos vertices que nao sao o target
			writer.printf("active proctype n%d() { ",i);
			writer.println("byte electNode;");// elected node
			writer.println("byte x;");// cost received
			writer.println("byte path;");// path received 
			writer.printf("byte state[%d] paths;",numvertex);// array of paths 
			writer.printf("byte state[%d] costs;", numvertex); // costs for adjacent nodes
			writer.println("do");
			// Receber do canal de algum dos vertices filhos de i.
			LinkedList<Integer> children = G.children(i);
			for( int k = 0; k < children.size(); k++) { 
				writer.printf("	:: c%d%d ? x, path;", children.get(k), i);
				// Perguntar o contrato entre os dois nos
				int[] contrato = Costs 
				for(int n = 0; n<)
				System.out.printf("Insira o custo de %d aceder o target usando %d quando o custo para %d � %d.", i,children.get(k), n);
				System.out.println("Insira aqui: ");
				int cost = reader.nextInt();
				//1. verificar o path
			
			/*	loops = generateLoops(i);

				for ( int t = 0; t < loops.size(); t++) {
					String aux = String.valueOf(loops.get(t));
					int [ loops.get(t)).length()] lista;
				
					for(int p = 0; p < String.valueOf(loops.get(t)).length(); p++){
						aux = shift(aux);
						lista[p] = aux;
			        
					}
				}*/
				// Caso em que o path e aceite:
				// Calcular o novo custo atraves do contrato
			}
		writer.println("od");
			
		}else {// criar o processo do Target 
			
				}
		}
		
		//once finished
		reader.close();	
		writer.close();	
		}

			
							
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