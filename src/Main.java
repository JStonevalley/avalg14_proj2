
public class Main {
	public static void main(String[] args) throws Exception {
		Kattio io = new Kattio(System.in, System.out);
		short numNodes = (short)io.getInt();
		double[][] nodes = new double[numNodes][2];
		for (int i = 0; i < numNodes; i++) {
			nodes[i][0] = io.getDouble();
			nodes[i][1] = io.getDouble();
		}
//		Distance distance = new Distance(nodes);
//		ConstructionHeuristic construction = new Greedy(distance);
//		io.println(construction.getPath());

		int[][] distance = getDistances(nodes);
		Greedy greedy = new Greedy();
		Path path = greedy.initialize(distance);
		io.println(path);
		io.close();

//		TwoOptIterationHeuristic opt = new TwoOptIterationHeuristic(construction.getPath(), distance, new Random());
//		System.out.println(opt.getPath().toString());
	}

	private static int[][] getDistances(double[][] nodes) {
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
