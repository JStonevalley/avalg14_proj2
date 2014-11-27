import java.util.Arrays;

public class DistanceNew {

	public int[][] calculateDistances(double[][] nodes) {
		int[][] distances = new int[nodes.length][nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			for (int j = i; j < nodes.length; j++) { // j = i because of symmetric matrix
				double cate1 = Math.abs(nodes[i][0] - nodes[j][0]);
				double cate2 = Math.abs(nodes[i][1] - nodes[j][1]);
				float hypotenuse = (float) Math.sqrt(cate1 * cate1 + cate2 * cate2);
				int distance = (int) (hypotenuse + 0.5f); // faster then Math.round()
				distances[i][j] = distance;
				distances[j][i] = distance;
			}
			distances[i][i] = Integer.MAX_VALUE; // distance to itself = inf
		}
		return distances;
	}

	// TODO: if edges are of the same length, only one of them get selected
	public int[][] getClosestNodes(int[][] distances, int noClosestNodes) {
		int maxNoNodes = Math.min(noClosestNodes, distances.length - 1); // TODO: fix this /2
		int[][] closestNodes = new int[distances.length][maxNoNodes];

//		int[][] tempDistance = Arrays.copyOf(distances, distances.length);

		for (int i = 0; i < distances.length; i++) {
			int[] tempDistance = Arrays.copyOf(distances[i], distances[i].length);
//			int prevMin = 0;
			for (int k = 0; k < maxNoNodes; k++) {
				int min = Integer.MAX_VALUE;
				int minIndex = -1;
				for (int j = 0; j < distances.length; j++) {
					if (tempDistance[j] < min) {
						min = tempDistance[j];
//						tempDistance[j] = Integer.MAX_VALUE;
						minIndex = j;
					}
				}
				closestNodes[i][k] = minIndex;
				tempDistance[minIndex] = Integer.MAX_VALUE; // make sure not taken again
//				prevMin = min;
			}
		}

		return closestNodes;
	}
}
