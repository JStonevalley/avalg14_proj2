/**
 * Created by Johan Arnör on 02/12/14.
 */
public class TwoOptSimple implements IterationHeuristic {
	
	public Path enhance(Path path, int[][] distances, int[][] closestNodes) {
		boolean noChange = false;
		while (!noChange) {
			noChange = true;
			outer:
			for (int i = 0; i < distances.length - 1; i++) {
				for (int j = 0; j < closestNodes[i].length; j++) {
					int node = closestNodes[i][j];
					if (shouldSwap(path, distances, i, node)) {
						path.swap(i, path.getNext(i), node, path.getNext(node));
						noChange = false;
//						break outer;
					}
				}
			}
		}
		return path;
	}

	// Checks if x-y, a-b should be swapped to x-a, y-b
	private boolean shouldSwap(Path path, int[][] distances, int x, int a) {
		int y = path.getNext(x);
		int b = path.getNext(a);

		int oldDistance = distances[x][y] + distances[a][b];
		int newDistance = distances[x][a] + distances[y][b];

		if (newDistance < oldDistance) {
			return true;
		}
		return false;
	}
}
