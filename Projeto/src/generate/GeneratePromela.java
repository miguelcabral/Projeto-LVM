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
			writer.println("byte x;");// cost received/sent
			writer.println("byte path;");// path received/sent
			writer.printf("byte state[%d] paths;",numvertex);// array of paths 
			writer.printf("byte state[%d] costs;", numvertex); // costs for adjacent nodes
			writer.println("do");
			// Receber do canal de algum dos vertices filhos de i.
			LinkedList<Integer> children = G.children(i);
			for( int k = 0; k < children.size(); k++) { 
				int currentChild = children.get(k);
				writer.printf(" 	:: c%d%d ? x, path;", currentChild, i);
				writer.println(" 		if");
				// Perguntar o contrato entre os dois nos
			//	LinkedList<int[]> contrato = new LinkedList<int[]>(); // E uma lista de pares: (x,y) - Se o custo recebido e x, passa a ser y.
				for(int n = 0; n < Custos.size(); n++) {
					System.out.printf("Insira o custo de %d aceder o target usando %d quando o custo para %d � %d.", i,currentChild, Custos.get(n));
					// Imprimir os custos possiveis (variavel Custos) numa string
					String possiveis = new String();
					for(int p = 0; p < Custos.size(); p++) {
						possiveis = possiveis + ", " + Custos.get(p);
						}
					System.out.printf("Os valores possiveis sao: %d %n"
							+ "Insira o custo aqui: ", possiveis);
					int newcost = reader.nextInt();
					int oldcost = Custos.get(n);
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
					
					// Caso em que o path nao e aceite:
					
					// Caso em que o path e aceite:
					
					writer.printf(" 		:: x == %d -> costs[%d] = %d;",currentChild,oldcost,currentChild,newcost);
					// Verificar se o minimo do array costs foi alterado
					writer.println(" 			if");
					writer.printf(" 				:: costs[electNode] > costs[%d] -> electNode = %d;",currentChild,currentChild);
					//Alterar o path e enviar nao-deterministicamente para um no pai
					writer.printf(" 				path = path * 10 + %d;",i);
					writer.println(" 					if");
					int[] pais = G.parents(i);
					for(int j =0; j < pais.length; j++) {
						writer.printf(" 						:: c%d%d ! costs[electNode], path;",i,pais[j]);
					}
 					writer.println(" 					fi");
					writer.println(" 			fi");
				}

				writer.println(" 		fi");
				
				
			}
		writer.println("od");
			
		}else {// criar o processo do Target 
			writer.println("active proctype n0() { ");
			String possiveis = new String();
			for(int p = 0; p < Custos.size(); p++) {
				possiveis = possiveis + ", " + Custos.get(p);
				}
			// enviar para todos os nos que sao pais do target
			int[] pais = G.parents(0);
			for(int j = 1; j < pais.length; j++) {	
				System.out.printf("Insira o custo de %d aceder ao target diretamente.",j);
				// Imprimir os custos possiveis (variavel Custos) numa string
				System.out.printf("Os valores possiveis sao: %d %n"
						+ "Insira o custo aqui: ", possiveis);
				int cost = reader.nextInt();
				writer.printf(, pais[j]);
			}
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