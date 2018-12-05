package generate;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class GeneratePromela{
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
	
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.print("Numero de vertices:");
		int numvertex = reader.nextInt();
		
		DGraph G = new DGraph(numvertex); // The first node is always the target
		int[][] Contract = new int[numvertex][numvertex];
		
		for(int i=0; i < numvertex; i++) {
			System.out.printf("O vertice %d tem arestas para os vertices:", i);
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
		
		//Write the promela file:
		PrintWriter writer = new PrintWriter("BGP.txt", "UTF-8");
		//For each edge create a channel. Each channel sends a pair with elected value and path to reach the target.
		for(int i=0; i < numvertex; i++) {
			int [] pais = G.parents(i);
			for(int j=0;j<pais.length;j++) {
				writer.printf("chan c%d%d = [1] of {byte,byte}",i,j); 
				// O canal cxy e de x para y, porque ha uma aresta de y para x.
				// O segundo byte e tipo: o caminho 1->2->3 e representado pelo numero 123. (nao ha problemas porque o 0 e o target)
				}
		}
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
		
		
		
	
	}
}
