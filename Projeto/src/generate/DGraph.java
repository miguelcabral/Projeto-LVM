package generate;
import java.util.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;


public class DGraph{
	
	ArrayList<LinkedList<Integer>> nodes;
	private int d;
	
	public DGraph(int dim) {
		this.nodes = new ArrayList<LinkedList<Integer>>();
		this.d = dim;
		for(int i = 0; i < this.d; i++) {
			nodes.add(i, new LinkedList<Integer>());
		}
	}
	
	/**
	 * Adds edge from node id1 to node id2 
	 * @param id1 : parent node id 
	 * @param id2 : node id
	 */
	public void add_edge(int id1, int id2) {
		if( id1 < 0 || id2 < 0 || id1 > this.d || id2 > this.d) {
			System.out.println("Index out of bounds");
		} 
		else {
			LinkedList<Integer> aux = nodes.get(id2);
			int size = aux.size();
			boolean b = true;
			for(int i = 0; i < size; i++) {
				if(aux.get(i) == id1) {
					System.out.println("The edge already exists");
					b = false;
					break; //stops the loop
				}
			}
			if(b == true) {
				aux.add(id1);
				nodes.set(id2, aux);
			}
		}
	}
	
	/**
	 * Removes edges from id1 to id2
	 * @param id1 : parent node
	 * @param id2 : node id
	 */
	
	public void remove_edge(int id1, int id2) {
		if(nodes.isEmpty()) {
			System.out.println("The graph is empty");
		}
		else {
			nodes.get(id2).removeFirstOccurrence(id1);
		}
	}
	
	/**
	 * Returns an array with the ids of the parents of the node with
	 * node id = nodeID
	 * @param nodeID
	 * @return arrays with the parents
	 */
	public int[] parents(int nodeID){
		LinkedList<Integer> aux = this.nodes.get(nodeID);
		int[] r = new int[aux.size()];
		for(int i = 0; i < r.length; i++) {
			r[i] = aux.get(i);
		}
		return r;
	}
	
	
	/**
	 * Returns an array with the ids of the childrens of the node with
	 * node id = nodeID
	 * @param nodeID
	 * @return arrays with the childrens
	 */
	public LinkedList<Integer> children(int nodeID){
		int k = 0;
		LinkedList<Integer> r = new LinkedList<Integer>();
		for(int i = 0; i < this.d; i++) {
			if(nodeID == i){ continue; } 
			LinkedList<Integer> aux = this.nodes.get(i);
			for(int j = 0; j < aux.size(); j++) {
				if ( aux.get(j) == nodeID) { r.add(i); k = k + 1; break; } 
			}
		}
		return r;
	}
	
	/**
	 * Returns an array with the paths, starting in v, that have loops 
	 * vertex = v
	 * @param v 
	 * @return arrays with the loops
	 */
		public void generateLoops(int numvertex){
			
		}

	
	/**
	 * Prints the graph to the screen
	 * node id = nodeID
	 * @param nodeID
	 * @return arrays with the childrens
	 */
	public void show() {
		for(int i = 0; i < this.d; i++) {
			LinkedList<Integer> l = nodes.get(i);
			Object[] aux = l.toArray();
			System.out.println(Arrays.toString(aux));	
		}
	}
	
	// Another show:
	public void show2() {
		for(int i=0; i<this.d; i++) {
			LinkedList<Integer> l = this.nodes.get(i);
			if(!l.isEmpty()) {
				for(int j=0; j<l.size(); j++) {
					System.out.println(l.get(j) + " -> " + i);
				}
			}
			}
	}

	/**
	 * Returns num of edges
	 * node id = nodeID
	 * @param nodeID
	 * @return arrays with the childrens
	 */
	public int numEdges() {
		int count = 0;
		for(int i=0; i<this.d;i++) {
			count = count + parents(i).length;
	}
		return count;
	}

	// A fazer:
		// 1. Funcao que calcula todos os possiveis caminhos sem loops desde o target ate ao no que estamos a considerar i.
		// 2. Continuar esses caminhos com todos os poss�veis loops contendo i. - uma funcao
		// 3. Transposta do grafo (por causa dos canais)
		// A funcao que usa as anteriores para devolver todos os caminhos que comecam no target e vao ate ao no i (sem loops) e depois fazem um loop contendo i.
		// (no final tem de se retirar o n� i do final do caminho, porque no BGP o no i recebe esse caminho com excecao dele proprio.)
		
		public LinkedList<Integer> AllCyclesContaining (int origem){
			LinkedList<Integer> lista = new LinkedList<Integer>();
			return lista;
		}

		public ArrayList<LinkedList<Integer>> printAllPaths(int i, int i2) {
			// TODO Auto-generated method stub
			return null;
		}
	
}
