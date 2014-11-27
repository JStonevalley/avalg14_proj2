
public class DistanceNew {

	public int[][] calculateDistances(double[][] nodes) {
		int[][] distances = new int[nodes.length][nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				double cate1 = Math.abs(nodes[i][0] - nodes[j][0]);
				double cate2 = Math.abs(nodes[i][1] - nodes[j][1]);
				float hypotenuse = (float) Math.sqrt(cate1 * cate1 + cate2 * cate2);
				distances[i][j] = Math.round(hypotenuse);
			}
		}
		return distances;
	}
}
