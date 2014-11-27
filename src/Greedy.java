
/**
 * Created by Johan Arnör on 27/11/14.
 */
public class Greedy implements ConstructionHeuristic {

	@Override
	public Path initialize(int[][] distances) {
		Path path = new ArrayPath(distances.length);
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

			path.setEdge(currentNode, closestAvailableNode, distanceToClosestAvailableNode);
			currentNode = closestAvailableNode;
		}

		path.setEdge(currentNode, startNode, distances[currentNode][startNode]);

		return path;
	}
}
