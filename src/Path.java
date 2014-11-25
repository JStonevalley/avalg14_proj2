
public interface Path {
	/**
	 * Creates an edge between the the nodes a and b
	 *
	 * @param a node in the graph
	 * @param b node in the graph
	 * @param distance the distance between node a and b
	 */
	public void setEdge(short a, short b, int distance);

	/**
	 * Removes an edge between the the nodes a and b
	 *
	 * @param a node in the graph
	 * @param b node in the graph
	 * @param distance the distance between node a and b
	 * @throws IllegalArgumentException if no such edge exists
	 */
	public void removeEdge(short a, short b, int distance);

	/**
	 * Returns the next node in the path
	 *
	 * @param currentNode
	 * @return
	 */
	public short[] getNeighbourNodes(short currentNode);

	/**
	 *
	 * @return the total length of the path
	 */
	public int getLength();

//	/**
//	 *
//	 * @return true if the graph only contains one component
//	 */
//	public boolean isComplete();
}
