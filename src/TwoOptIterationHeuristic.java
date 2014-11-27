
public class TwoOptIterationHeuristic {

	public Path enhance(Path path, int[][] distances, int[][] closestNodes){
		int length = path.getLength();
		int oldLength = Integer.MAX_VALUE;
		while (length < oldLength) {
			oldLength = length;
//			outer: // TODO: Maybe enable this optimization later
			for (int i = 0; i < distances.length; i++) {
				for (int j = 0; j < closestNodes[i].length; j++) {
					int index = closestNodes[i][j];
					if (i != index) {
						path = checkSwap(path, distances, i, index);
						length = Math.min(path.getLength(), length);
						if (length < oldLength)
							break; // TODO: Fiddle between breaking the inner or outer loop
					}
				}
			}
		}
		return path;
	}

	private Path checkSwap(Path path, int[][] distances, int node1, int node2){
		int oldNode1Neighbour, oldNode2Neighbour;
		oldNode1Neighbour = path.getNeighbourNodes(node1)[0];
		oldNode2Neighbour = path.getNeighbourNodes(node2)[0];
		if (oldNode1Neighbour != node2 && oldNode2Neighbour != node1) {
			int swapDistance = distances[node1][node2] + distances[oldNode1Neighbour][oldNode2Neighbour];
			int oldDistance = distances[node1][oldNode1Neighbour] + distances[node2][oldNode2Neighbour];
			if (swapDistance < oldDistance) {
				path.swap(node1, oldNode1Neighbour, node2, oldNode2Neighbour);
			}
		}
		return path;
	}
}
