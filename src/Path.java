public interface Path {
	/**
	 * Creates an edge between the the nodes a and b
	 *
	 * @param a node in the graph
	 * @param b node in the graph
	 * @throws IllegalArgumentException if a or b have two neighbours already
	 */
	public void setEdge(int a, int b);

	/**
	 * Removes an edge between the the nodes a and b
	 *
	 * @param a node in the graph
	 * @param b node in the graph
	 * @throws IllegalArgumentException if no such edge exists
	 */
	public void removeEdge(int a, int b);

	/**
	 * Returns the next node in the path
	 *
	 * @param currentNode
	 * @return
	 */
	public int[] getNeighbourNodes(int currentNode);

	/**
	 *
	 * @param node the reference node
	 * @return the next node in the path
	 */
	public int getNext(int node);

	/**
	 *
	 * @return the total length of the path
	 */
	public int getLength();

	/**
	 *
	 * @return true if node is in path
	 */
	public boolean inPath(int a);

	/**
	 * x - y - a - b - x
	 * @param x
	 * @param y
	 * @param a
	 * @param b
	 */
	public void swap(int x, int y, int a, int b);

	/**
	 *
	 * @return a string to follow the path
	 */
	public String toDebugString();
}
