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
}
