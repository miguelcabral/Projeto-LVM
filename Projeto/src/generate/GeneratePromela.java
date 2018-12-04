package generate;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class GeneratePromela{
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
		
		//PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		//writer.println("The first line");
		//writer.println("The second line");
		//writer.close();
	
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.print("Número de vértices:");
		int numvertex = reader.nextInt();
		
		DGraph G = new DGraph(numvertex); // The first node is always the target
		int[][] Contract = new int[numvertex][numvertex];
		
		for(int i=0; i < numvertex; i++) {
			System.out.printf("O vértice %d tem arestas para os vértices:", i);
			String edges = reader.next(); // Scans the next token of the input as an int.
			
			for(int j=0; j < edges.length(); j++) {
				if (Character.isDigit(edges.charAt(j))) {
					G.add_edge(i, Character.getNumericValue(edges.charAt(j)));
					System.out.printf("O custo de ir para %d por %d:", i,j);
					int cost = reader.nextInt();
					Contract[i][j] = cost;
					
				}		
			}
		}
		
		//once finished
		reader.close();
		
		
		
		
		
	
	}
}
