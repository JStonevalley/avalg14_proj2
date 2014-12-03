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
	 * @return true if a --> b --> c
	 */
	public boolean inOrder(int a, int b, int c);

	/**
	 * @param distance matrix of all the distances in the graph
	 * @return the total length of the path
	 */
	public int getLength(int[][] distances);

	/**
	 *
	 * @return true if node is in path
	 */
	public boolean inPath(int a);

	/**
	 * x - y - k - l - m - a - b - n - o - x ->
	 * x - a - k - l - m - y - b - n - o - x
	 * @param x to be connected to a was connected to y
	 * @param y to be connected to b was connected to x
	 * @param a to be connected to x was connected to b
	 * @param b to be connected to y was connected to a
	 */
	public void swap(int x, int y, int a, int b);

	/**
	 * Performs a given number of shuffles in the path. Each shuffle takes two nodes and exchanges them.
	 *
	 * @param noShuffles the number of shuffles to be made.
	 */
	public void shuffle(int noShuffles);

	public Path getCopy();

	/**
	 *
	 * @return a string to follow the path
	 */
	public String toDebugString();
}
