package kth.avalg.path;

public interface Path {
	/**
	 * Creates an edge between the the nodes a and b
	 *
	 * @param a node in the graph
	 * @param b node in the graph
	 * @param distance the distance between node a and b
	 * @throws IllegalArgumentException if a or b have two neighbours already
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

	/**
	 *
	 * @return true if node is in path
	 */
	public boolean inPath(short a);

	/**
	 *
	 * @return path copy
	 */
	public Path copy();

//	/**
//	 *
//	 * @return true if the graph only contains one component
//	 */
//	public boolean isComplete();
}
