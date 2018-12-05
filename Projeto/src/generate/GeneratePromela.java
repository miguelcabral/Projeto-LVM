package generate;
public class GeneratePromela{
	public static void main(String args[]) {
	DGraph G = new DGraph(2); // The first node is always the target
	G.add_edge(1,0); // adds edge from 1 to 0 (target)
	int numEdge = G.numEdges();
	int[][] Contract = new int[numEdge][];
	
	PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
	writer.println("The first line");
	writer.println("The second line");
	writer.close();
	}
}
