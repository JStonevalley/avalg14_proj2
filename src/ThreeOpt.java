public class ThreeOpt {

	public Path enhance(Path path, int[][] distances, int[][] closestNodes) {

		boolean noChange = false;
		while (!noChange) {
			noChange = true;
			for (int i = 0; i < distances.length; i++) {
				for (int j = 0; j < closestNodes[i].length; j++) {
					int node2 = closestNodes[i][j];
					if (node2 != path.getNext(i) && path.getNext(node2) != i) {
						for (int k = 0; k < closestNodes[j].length; k++) {
							int node3 = closestNodes[j][k];
							if (node3 != i && node3 != path.getNext(node2)
									&& path.getNext(node3) != node2
									&& node3 != path.getNext(i)
									&& path.getNext(node3) != i) { // nu är det bäst att hålla tungen rätt i mun!
								Edge[][] constructions = getConstructions(path, i, closestNodes[i][j],
										closestNodes[j][k]);
								Edge[] bestConstruction = getBestConstruction(path, distances, constructions, i,
										closestNodes[i][j], closestNodes[j][k]);
								if (bestConstruction != null) {
									//							System.out.println(path.toDebugString() + " | " + path.getLength(distances));
									//							System.out.println("Swap: " + i + "," + closestNodes[i][j] + "," + closestNodes[j][k]);
									threeSwap(path, bestConstruction, i, closestNodes[i][j], closestNodes[j][k]);
									noChange = false;
									//							System.out.println(path.toDebugString() + " | " + path.getLength(distances));
									//							System.out.println("-----------------------");
								}
							}
						}
					}
				}
			}
		}

		return path;
	}

	private void threeSwap(Path path, Edge[] bestConstruction, int node1, int node2, int node3) {
		int[] originNodes = new int[] { node1, node2, node3 };
		path.dynamicSwap(bestConstruction, originNodes);

		//		int one = bestConstruction[0].fromNode;
		//		int oneN = bestConstruction[1].toNode;
		//		int two = bestConstruction[2].fromNode;
		//		int twoN = bestConstruction[1].fromNode;
		//		int three = bestConstruction[0].toNode;
		//		int threeN = bestConstruction[2].toNode;

		//		path.swap(one, oneN, two, twoN);
		//		System.out.println(path.toDebugString());
		//		path.swap(three, threeN, one, two);
		//		System.out.println(path.toDebugString());
	}

	private Edge[] getBestConstruction(Path path, int[][] distances, Edge[][] constructions, int oldNode1, int oldNode2,
			int oldNode3) {
		if (constructions[0][0] == null)
			return null;
		int bestLength = distances[oldNode1][path.getNext(oldNode1)] + distances[oldNode2][path.getNext(oldNode2)]
				+ distances[oldNode3][path.getNext(oldNode3)];
		Edge[] bestConstruction = null;
		for (Edge[] edges : constructions) {
			int length = 0;
			for (Edge edge : edges) {
				length += distances[edge.fromNode][edge.toNode];
			}
			if (length < bestLength) {
				bestConstruction = edges;
				bestLength = length;
			}
		}
		return bestConstruction;
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

			constructions[1][0] = new Edge(node1, node2Neighbour);
			constructions[1][1] = new Edge(node3, node1Neighbour);
			constructions[1][2] = new Edge(node2, node3Neighbour);

			constructions[2][0] = new Edge(node1, node2);
			constructions[2][1] = new Edge(node1Neighbour, node3);
			constructions[2][2] = new Edge(node2Neighbour, node3Neighbour);

			constructions[3][0] = new Edge(node1, node2Neighbour);
			constructions[3][1] = new Edge(node3, node2);
			constructions[3][2] = new Edge(node1Neighbour, node3Neighbour);
		}

		return constructions;
	}
}
