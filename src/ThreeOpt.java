public class ThreeOpt{
	int length;
	public Path enhance(Path path, int[][] distances, int[][] closestNodes, long nanoSeconds) {

		boolean noChange = false;
		long startTime = System.nanoTime();
		length = path.getLength(distances);
		int prevLength = length;
		int numImprovements = 0;
		while (!noChange) {
			noChange = true;
			for (int i = 0; i < distances.length; i++) {
				int node1 = i;
				for (int j = 0; j < closestNodes[i].length; j++) {
					int node2 = closestNodes[i][j];
					if (path.getNext(node1) == node2){
						break;
					}
					if (path.getNext(node2) != node1) {
						for (int k = 0; k < closestNodes[j].length; k++) {
							if (startTime + nanoSeconds < System.nanoTime()) {
								return path;
							}
							int node3 = closestNodes[j][k];
							if (path.getNext(node2) == node3){
								break;
							}
							if (node3 != node1 && path.getNext(node3) != node2
									&& node3 != path.getNext(node1)
									&& path.getNext(node3) != node1) {
								Edge[][] constructions = getConstructions(path, node1, node2,
										node3);
								Edge[] bestConstruction = getBestConstruction(path, distances, constructions, node1,
										node2, node3);
								if (bestConstruction != null) {
									threeSwap(path, bestConstruction, node1, node2, node3);
									numImprovements++;
									noChange = false;
								}
							}
						}
					}
				}
			}
		}
//		System.out.println("----------------------------------------------------");
//		System.out.println("Length was: " + prevLength);
//		System.out.println("Number of improvements: " + numImprovements);
//		System.out.println("Length is: " + path.getLength(distances));
//		System.out.println("Length should be: " + length);
//		System.out.println("----------------------------------------------------");
		return path;
	}

	private void threeSwap(Path path, Edge[] bestConstruction, int node1, int node2, int node3) {
		int[] originNodes = new int[] { node1, node2, node3 };
		path.dynamicSwap(bestConstruction, originNodes);
	}

	private Edge[] getBestConstruction(Path path, int[][] distances, Edge[][] constructions, int oldNode1, int oldNode2,
			int oldNode3) {
		if (constructions[0][0] == null)
			return null;
		int bestLength = distances[oldNode1][path.getNext(oldNode1)] + distances[oldNode2][path.getNext(oldNode2)]
				+ distances[oldNode3][path.getNext(oldNode3)];
		int prevLength = bestLength;
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
		this.length = length - (prevLength - bestLength);
		return bestConstruction;
	}

	// Only returns the (f) version in the example
	private Edge[][] getConstructions(Path path, int node1, int node2, int node3) {
		Edge[][] constructions = new Edge[4][3];
		int node1Neighbour = path.getNext(node1);
		int node2Neighbour = path.getNext(node2);
		int node3Neighbour = path.getNext(node3);

		int first, middle, last, firstN, middleN, lastN;
		first = middle = last = firstN = middleN = lastN = 0;
		if (path.inOrder(node1, node2, node3)) {
			first = node1;
			middle = node2;
			last = node3;
			firstN = node1Neighbour;
			middleN = node2Neighbour;
			lastN = node3Neighbour;
		} else if (path.inOrder(node1, node3, node2)) {
			first = node1;
			middle = node3;
			last = node2;
			firstN = node1Neighbour;
			middleN = node3Neighbour;
			lastN = node2Neighbour;
		} else if (path.inOrder(node2, node1, node3)) {
			first = node2;
			middle = node1;
			last = node3;
			firstN = node2Neighbour;
			middleN = node1Neighbour;
			lastN = node3Neighbour;
		} else if (path.inOrder(node2, node3, node1)) {
			first = node2;
			middle = node3;
			last = node1;
			firstN = node2Neighbour;
			middleN = node3Neighbour;
			lastN = node1Neighbour;
		} else if (path.inOrder(node3, node1, node2)) {
			first = node3;
			middle = node1;
			last = node2;
			firstN = node3Neighbour;
			middleN = node1Neighbour;
			lastN = node2Neighbour;
		} else if (path.inOrder(node3, node2, node1)) {
			first = node3;
			middle = node2;
			last = node1;
			firstN = node3Neighbour;
			middleN = node2Neighbour;
			lastN = node1Neighbour;
		}
		constructions[0][0] = new Edge(first, last);
		constructions[0][1] = new Edge(middleN, firstN);
		constructions[0][2] = new Edge(middle, lastN);

		constructions[1][0] = new Edge(first, middleN);
		constructions[1][1] = new Edge(last, firstN);
		constructions[1][2] = new Edge(middle, lastN);

		constructions[2][0] = new Edge(first, middle);
		constructions[2][1] = new Edge(firstN, last);
		constructions[2][2] = new Edge(middleN, lastN);

		constructions[3][0] = new Edge(first, middleN);
		constructions[3][1] = new Edge(last, middle);
		constructions[3][2] = new Edge(firstN, lastN);

		return constructions;
	}
}
