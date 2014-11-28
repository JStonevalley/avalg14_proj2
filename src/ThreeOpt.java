
public class ThreeOpt {

	public Path enhance(Path path, int[][] distances, int[][] closestNodes) {
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < closestNodes[i].length; j++) {
				for (int k = 0; k < closestNodes[j].length; k++) {
					if (closestNodes[j][k] != i) { // nu är det bäst att hålla tungen rätt i mun!

					}
				}
			}
		}
	}

	private Edge[][] getConstructions(Path path, int node1, int node2, int node3) {
		int node1Neighbour = path.getNext(node1);
		int node2Neighbour = path.getNext(node2);
		int node3Neighbour = path.getNext(node3);
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
