/**
 * Created by Jonas on 2014-11-26.
 */
public class TwoOptIterationHeuristic {

	public Path enhance(Path path, int[][] distances){
		int length = path.getLength();
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances.length; j++) {
				path = swap(path, distances, i, j);
				length = Math.min(path.getLength(), length);
			}
		}
		return path;
	}

	private Path swap (Path path, int[][]distances, int node1, int node2){
		int oldNode1Neighbour, oldNode2Neighbour;
		oldNode1Neighbour = path.getNeighbourNodes(node1)[0];
		oldNode2Neighbour = path.getNeighbourNodes(node2)[0];
		int swapDistance = distances[node1][node2] + distances[oldNode1Neighbour][oldNode2Neighbour];
		int oldDistance = distances[node1][oldNode1Neighbour] + distances[node2][oldNode2Neighbour];
		if(swapDistance < oldDistance){
			path.swap(node1, oldNode1Neighbour, node2, oldNode2Neighbour);
		}
		return path;
	}
}
