import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Jonas on 2014-11-25.
 */
public class TwoWayArrayPath implements Path {
	private int[][] nodes;
	private int length;

	public TwoWayArrayPath(int numNodes) {
		nodes = new int[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			nodes[i] = new int[]{-1, -1};
		}
		length = 0;
	}

	/**
	 * Creates an edge between the the nodes a and b
	 *
	 * @param a        node in the graph
	 * @param b        node in the graph
	 * @param distance the distance between node a and b
	 * @throws IllegalArgumentException if a or b have two neighbours already
	 */
	@Override
	public void setEdge(int a, int b, int distance) {
		if(nodes[a][0] >= 0 && nodes[a][1] >= 0){
			throw new IllegalArgumentException("Already two neighbours of a");
		}
		if (nodes[b][0] >= 0 && nodes[b][1] >= 0){
			throw new IllegalArgumentException("Already two neighbours of b");
		}
		for (int i = 0; i < 2; i++) {
			if(nodes[a][i] < 0){
				nodes[a][i] = b;
				break;
			}
		}
		for (int i = 0; i < 2; i++) {
			if(nodes[b][i] < 0){
				nodes[b][i] = a;
				break;
			}
		}
		length += distance;
	}

	/**
	 * Removes an edge between the the nodes a and b
	 *
	 * @param a        node in the graph
	 * @param b        node in the graph
	 * @param distance the distance between node a and b
	 * @throws IllegalArgumentException if no such edge exists
	 */
	@Override
	public void removeEdge(int a, int b, int distance) {
		byte aIndex, bIndex;
		aIndex = -1;
		bIndex = -1;
		for (byte i = 0; i < 2; i++) {
			if(nodes[a][i] == b){
				bIndex = i;
			}
			if(nodes[b][i] == a){
				aIndex = i;
			}
		}
		if(aIndex == -1 || bIndex == -1){
			throw new IllegalArgumentException("No such edge");
		}
		nodes[a][bIndex] = -1;
		nodes[b][aIndex] = -1;
		length -= distance;
	}

	/**
	 * Returns the next node in the path
	 *
	 * @param currentNode
	 * @return int[] of neighbouring nodes. Values in the array that are -1 represent no neighbour
	 */
	@Override
	public int[] getNeighbourNodes(int currentNode) {
		return nodes[currentNode];
	}

	/**
	 * @return the total length of the path
	 */
	@Override
	public int getLength() {
		return length;
	}

	/**
	 * @param a
	 * @return true if node is in path
	 */
	@Override public boolean inPath(int a) {
		if (nodes[a][0] < 0 && nodes[a][1] < 0){
			return false;
		}
		return true;
	}

	private String getString(boolean debug){
		HashSet<Integer> inString = new HashSet<Integer>();
		StringBuilder sb = new StringBuilder();
		int start = -1;
		int prev = -1;
		int current = -1;
		while (inString.size() < nodes.length){
			if (current < 0){
				for (int i = 0; i < nodes.length; i++) {
					if (!inString.contains(i)){
						inString.add(i);
						sb.append(i);
						current = i;
						break;
					}
				}
			}
			else {
				if (prev < 0) {
					prev = current;
					if (nodes[current][0] > 0) {
						current = nodes[current][0];
					} else {
						current = nodes[current][1];
					}
					if (start < 0){
						start = prev;
					}
				} else {
					int next;
					if (nodes[current][0] == prev) {
						next = nodes[current][1];
					} else {
						next = nodes[current][0];
					}
					prev = current;
					current = next;
				}
				if (current == start || current < 1){
					start = -1;
					prev = -1;
					current = -1;
					sb.append("\n");
				}
				else{
					if(debug) {
						sb.append("-" + current);
					}
					else{
						sb.append("\n" + current);
					}
					inString.add(current);
				}
			}
		}
		return sb.toString();
	}
	public String toDebugString(){
		return getString(true);
	}

	@Override
	public String toString(){
		return getString(false);
	}
}
