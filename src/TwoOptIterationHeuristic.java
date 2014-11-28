
public class TwoOptIterationHeuristic {

	public Path enhance(Path path, int[][] distances, int[][] closestNodes){

		boolean noChange = false;
		while (!noChange) {
			noChange = true;
//			outer: // TODO: Maybe enable this optimization later
			for (int i = 0; i < distances.length; i++) {
				for (int j = 0; j < closestNodes[i].length; j++) {
//					if (path.getNext(j) == closestNodes[j][0]) // TODO: Examine this optimization
//						break;
					int closeNode = closestNodes[i][j];
					if (shouldSwap(path, distances, i, closeNode)) {
						path.swap(i, path.getNext(i), closeNode, path.getNext(closeNode));
//						System.out.println(path.getLength(distances));
//						System.out.println(i + "," + closeNode + " --> " + path.toDebugString());
						noChange = false;
//						break outer; // TODO: Fiddle between breaking the inner or outer loop
					}
//					if (!noChange) { // TODO: Strange break which gives higher score in kattis
//						break;
//					}
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
