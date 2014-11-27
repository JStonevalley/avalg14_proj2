import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Jonas on 2014-11-25.
 */
public class TwoWayArrayPath implements Path {
	short[][] nodes;
	int length;

	public TwoWayArrayPath(int numNodes) {
		nodes = new short[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			nodes[i] = new short[]{-1, -1};
		}
		length = 0;
	}

	public TwoWayArrayPath(TwoWayArrayPath path) {
		nodes = new short[path.nodes.length][];
		for (int i = 0; i < path.nodes.length; i++) {
			nodes[i] = Arrays.copyOfRange(path.nodes[i], 0, 2);
		}
		this.length = path.length;
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
	public void setEdge(short a, short b, int distance) {
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
	public void removeEdge(short a, short b, int distance) {
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
	 * @return short[] of neighbouring nodes. Values in the array that are -1 represent no neighbour
	 */
	@Override
	public short[] getNeighbourNodes(short currentNode) {
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
	@Override public boolean inPath(short a) {
		if (nodes[a][0] < 0 && nodes[a][1] < 0){
			return false;
		}
		return true;
	}

	/**
	 * @return path copy
	 */
	@Override public Path copy() {
		return new TwoWayArrayPath(this);
	}

	public String toString(){
		HashSet<Short> inString = new HashSet<Short>();
		StringBuilder sb = new StringBuilder();
		short start = -1;
		short prev = -1;
		short current = -1;
		while (inString.size() < nodes.length){
			if (current < 0){
				for (short i = 0; i < nodes.length; i++) {
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
					short next;
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
					sb.append("\n" + current);
					inString.add(current);
				}
			}
		}
		return sb.toString();
	}
}
