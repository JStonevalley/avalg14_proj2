public interface ConstructionHeuristic {

	/**
	 *
	 * @param distances matrix containing the distance between all nodes
	 * @return a initial path
	 */
	public Path initialize(int[][] distances);
}
