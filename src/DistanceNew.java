
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
		}
		return distances;
	}
}
