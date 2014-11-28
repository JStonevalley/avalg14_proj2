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
	 * x - y - k - l - m - a - b - n - o - x -> x - a - k - l - m - y - b - n - o - x
	 * @param x to be connected to a was connected to y
	 * @param y to be connected to b was connected to x
	 * @param a to be connected to x was connected to b
	 * @param b to be connected to y was connected to a
	 */
	public void swap(int x, int y, int a, int b);

	/**
	 *
	 * @return a string to follow the path
	 */
	public String toDebugString();
}
