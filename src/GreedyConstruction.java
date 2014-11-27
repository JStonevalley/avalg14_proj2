
public class GreedyConstruction implements ConstructionHeuristic {

	private int[][] distances;

	public GreedyConstruction(int[][] distances) {
		this.distances = distances;
	}

	@Override
	public void construct(Path path) {
		int startNode = 0; // TODO: check why random gets stuck
		int currentNode = startNode;

		for (int i = 0; i < distances.length - 1; i++) {
			int[] distancesFromCurrentNode = distances[currentNode];
			int distanceToClosestAvailableNode = Integer.MAX_VALUE;
			int closestAvailableNode = -1;

			for (int j = 0; j < distancesFromCurrentNode.length; j++) {
				if (!path.inPath(j) && distanceToClosestAvailableNode >= distancesFromCurrentNode[j] && currentNode != j) {
					distanceToClosestAvailableNode = distancesFromCurrentNode[j];
					closestAvailableNode = j;
				}
			}

			path.setEdge(currentNode, closestAvailableNode);
			currentNode = closestAvailableNode;
		}

		path.setEdge(currentNode, startNode);
	}
}
