
public class ThreeOpt {

	public Path enhance(Path path, int[][] distances, int[][] closestNodes) {

		boolean noChange = false;
		while (!noChange) {
			noChange = true;
			for (int i = 0; i < distances.length; i++) {
				for (int j = 0; j < closestNodes[i].length; j++) {
					for (int k = 0; k < closestNodes[j].length; k++) {
						if (closestNodes[j][k] != i) { // nu är det bäst att hålla tungen rätt i mun!
							Edge[][] constructions = getConstructions(path, i, closestNodes[i][j], closestNodes[j][k]);
							Edge[] bestConstruction = getBestConstruction(path, distances, constructions, i, closestNodes[i][j], closestNodes[j][k]);
							if (bestConstruction != null) {
								//							System.out.println(path.toDebugString() + " | " + path.getLength(distances));
								//							System.out.println("Swap: " + i + "," + closestNodes[i][j] + "," + closestNodes[j][k]);
								threeSwap(path, bestConstruction);
								noChange = false;
								//							System.out.println(path.toDebugString() + " | " + path.getLength(distances));
								//							System.out.println("-----------------------");
							}
						}
					}
				}
			}
		}

		return path;
	}

	private void threeSwap(Path path, Edge[] bestConstruction) {
		int one = bestConstruction[0].fromNode;
		int oneN = bestConstruction[1].toNode;
		int two = bestConstruction[2].fromNode;
		int twoN = bestConstruction[1].fromNode;
		int three = bestConstruction[0].toNode;
		int threeN = bestConstruction[2].toNode;

		path.swap(one, oneN, two, twoN);
//		System.out.println(path.toDebugString());
		path.swap(three, threeN, one, two);
//		System.out.println(path.toDebugString());
	}

	// Only checks the first construction
	private Edge[] getBestConstruction(Path path, int[][] distances, Edge[][] constructions, int oldNode1, int oldNode2, int oldNode3) {
		if (constructions[0][0] == null)
			return null;

		Edge[] best = constructions[0];
		int newDistance = distances[best[0].fromNode][best[0].toNode] +
				distances[best[1].fromNode][best[1].toNode] +
				distances[best[2].fromNode][best[2].toNode];
		int oldDistance = distances[oldNode1][path.getNext(oldNode1)] +
				distances[oldNode2][path.getNext(oldNode2)] +
				distances[oldNode3][path.getNext(oldNode3)];

		if (newDistance < oldDistance) {
			return best;
		} else {
			return null;
		}
	}

	// Only returns the (f) version in the example
	private Edge[][] getConstructions(Path path, int node1, int node2, int node3) {
		Edge[][] constructions = new Edge[4][3];
		int node1Neighbour = path.getNext(node1);
		int node2Neighbour = path.getNext(node2);
		int node3Neighbour = path.getNext(node3);

		if (path.inOrder(node1, node2, node3)) {
			constructions[0][0] = new Edge(node1, node3);
			constructions[0][1] = new Edge(node2Neighbour, node1Neighbour);
			constructions[0][2] = new Edge(node2, node3Neighbour);
		}

		return constructions;
	}

	private class Edge {
		public int fromNode;
		public int toNode;

		private Edge(int fromNode, int toNode) {
			this.fromNode = fromNode;
			this.toNode = toNode;
		}
	}
}
