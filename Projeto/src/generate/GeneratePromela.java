package generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
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
			System.out.printf("O vertice %d tem arestas para outros vertices? (1 para sim e 0 para nao): ", i);
			int resposta = reader.nextInt();
			if(resposta == 1) {
				System.out.printf("O vertice %d tem arestas para os vertices: ", i);
				String edges = reader.next(); // Scans the next token of the input as an int.
				
				for(int j=0; j < edges.length(); j++) {
					if (Character.isDigit(edges.charAt(j))) {
						G.add_edge(i, Character.getNumericValue(edges.charAt(j)));
						}
					}
			}
			}
		// Perguntar os valores de custo possiveis (nao tem de ser necessariamente {0,1,...,numvertex})
		System.out.println("Insira os valores possiveis dos custos dos contratos: ");
		String StringCustos = reader.next();
		LinkedList<Integer> Custos = new LinkedList<Integer>();
		int Max = Integer.MIN_VALUE;
		for(int j=0; j < StringCustos.length(); j++) {
			if (Character.isDigit(StringCustos.charAt(j))) {
				if(Max < Character.getNumericValue(StringCustos.charAt(j))) {
					Max = Character.getNumericValue(StringCustos.charAt(j));
				}
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
			// inicializar os custos no valor maximo
			for(int p = 0; p < numvertex; p++) {
				writer.printf("costs[%d] = %d;", p, Max);
			}
			writer.println("byte min;"); // auxiliary variable to determine the minimum of array costs
			writer.println("do");
			// Receber do canal de algum dos vertices filhos de i.
			LinkedList<Integer> children = G.children(i);
			for( int k = 0; k < children.size(); k++) { 
				int currentChild = children.get(k);
				writer.printf(" 	:: c%d%d ? x, path;", currentChild, i);
				if(currentChild == 0) {
					writer.println(" 		if");
					writer.println(" 		:: costs[0] = x;");
					// Verificar se o minimo do array costs foi alterado
					writer.println(" 			if");
					writer.printf(" 				:: costs[electNode] > costs[0] -> electNode = 0;",currentChild,currentChild);
					//Alterar o path e enviar nao-deterministicamente para um no pai
					writer.println(" 					x = 0;"); // x e um counter que determina o tamanho do caminho path
					writer.println(" 					if");
					int[] pais = G.parents(i);
					for(int j =0; j < pais.length; j++) {
						writer.printf(" 						:: c%d%d ! costs[electNode], path;",i,pais[j]);
					}
 					writer.println(" 					fi");
					writer.println(" 			fi");
					
					writer.println(" 		fi");
				}
				else {
					// Perguntar o contrato entre os dois nos (se nenhum deles e o target)
					for(int n = 0; n < Custos.size(); n++) {
						System.out.printf("Insira o custo de %d aceder o target usando %d quando o custo para este �ltimo � %d. %n", i,currentChild, Custos.get(n));
						// Imprimir os custos possiveis (variavel Custos) numa string
						String possiveis = new String();
						for(int p = 0; p < Custos.size(); p++) {
							possiveis = possiveis + ", " + Custos.get(p);
							}
						System.out.printf("Os valores possiveis sao: %s %n", possiveis);
						System.out.println("Insira o custo aqui: ");
						int newcost = reader.nextInt();
						int oldcost = Custos.get(n);
						// Verificar o path
						ArrayList<LinkedList<Integer>> allPaths = G.printAllPaths(0,i);
						writer.println(" 	if");
						for(int p = 0; p < allPaths.size(); p++) {
							LinkedList<Integer> pathList = allPaths.get(p);
							int pathInt = 0;
							for(int m = 0; m < pathList.size()-1/* -1 para retirar o ultimo no (o repetido)*/; m++) {
								pathInt = pathInt + pathList.get(m)*10^m;
							}
							writer.printf(" 		:: path == %d -> ", pathInt);
							// Caso em que o path nao e aceite:
							writer.println( 		"if"); // Se ele era o no mais barato entao tem de se ver qual e o mais barato a seguir dele.
							writer.printf(" 			:: electNode == %d -> ", i);
							// determinar um outro 'minimo' - percorrer o array costs
							writer.println(" 			x = 1;");
							writer.println(" 			min = costs[0];");
							writer.println(" 			do");
							writer.printf(" 				:: x >= %d -> break",numvertex);
							writer.printf(" 				:: costs[x] < min && x!=%d -> min = costs[x]; electNode = x; x = x+1",i);
							writer.println(" 				:: x = x+1");
							writer.println(" 			od");
							writer.println(" 		fi");
							writer.println(" 		if");
							writer.printf(" 			:: electNode != %d -> path = paths[electNode];", i,i);
							// Alterar o path 
							writer.println(" 					x = 0;"); // x e um counter que determina o tamanho do caminho path
							writer.println(" 					min = path;"); // Aqui min e so uma variavel auxiliar que nada tem a ver com o valor minimo
							writer.println(" 					do");
							writer.println(" 						:: min == 0 -> break");
							writer.println(" 						:: min = (min - (min % 10))/10; x = x+1");
							writer.println(" 					od");
							writer.printf(" 					path = %d * 10^x + path;",i);
							// Enviar nao-deterministicamente para um no pai
							writer.println(" 			if");
							int[] pais = G.parents(i);
							for(int j =0; j < pais.length; j++) {
								writer.printf(" 			:: c%d%d ! costs[electNode], path",i,pais[j]);
							}
							writer.println(" 			fi");
							writer.println( 		"fi");
						}
						// Caso em que o path e aceite:
						writer.println(" 		else ->");
						writer.println(" 		if");
						writer.printf(" 		:: x == %d -> costs[%d] = %d;",currentChild,oldcost,currentChild,newcost);
						// Verificar se o minimo do array costs foi alterado
						writer.println(" 			if");
						writer.printf(" 				:: costs[electNode] > costs[%d] -> electNode = %d;",currentChild,currentChild);
						//Alterar o path e enviar nao-deterministicamente para um no pai
						writer.println(" 					x = 0;"); // x e um counter que determina o tamanho do caminho path
						writer.println(" 					min = path;"); // Aqui min e so uma variavel auxiliar que nada tem a ver com o valor minimo
						writer.println(" 					do");
						writer.println(" 						:: min == 0 -> break");
						writer.println(" 						:: min = (min - (min % 10))/10; x = x+1");
						writer.println(" 					od");
						writer.printf(" 					path = %d * 10^x + path",i);
						writer.println(" 					if");
						int[] pais = G.parents(i);
						for(int j =0; j < pais.length; j++) {
							writer.printf(" 						:: c%d%d ! costs[electNode], path;",i,pais[j]);
						}
	 					writer.println(" 					fi");
						writer.println(" 			fi");
						
						writer.println(" 		fi");
						writer.println(" 	fi");
				
					}				
				}
				}
		writer.println("od");
			
		}else {// criar o processo do Target 
			writer.println("active proctype n0() { ");
			String possiveis = new String();
			for(int p = 0; p < Custos.size(); p++) {
				if(p == 0) {
					possiveis = Integer.toString(Custos.get(p));
				}else {
					possiveis = possiveis + ", " + Custos.get(p);
				}
			}
			// enviar para todos os nos que sao pais do target
			int[] pais = G.parents(0);
			for(int j = 0; j < pais.length; j++) {	
				System.out.printf("Insira o custo de %d aceder ao target diretamente.%n",pais[j]);
				// Imprimir os custos possiveis (variavel Custos) numa string
				System.out.printf("Os valores possiveis sao: %s %n", possiveis);
				System.out.println("Insira o custo aqui: ");
				int cost = reader.nextInt();
				writer.printf("c0%d ! %d, 0;", pais[j],cost);
			}
				}
		}
		
		//once finished
		reader.close();	
		writer.close();	
		}


	// FIM
	
	
	
	
	
	
	
	
	
	
	
	/*	loops = generateLoops(i);

	for ( int t = 0; t < loops.size(); t++) {
		String aux = String.valueOf(loops.get(t));
		int [ loops.get(t)).length()] lista;
	
		for(int p = 0; p < String.valueOf(loops.get(t)).length(); p++){
			aux = shift(aux);
			lista[p] = aux;
        
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