
public class Main {
	public static void main(String[] args) throws Exception {
		Kattio io = new Kattio(System.in, System.out);
		short numNodes = (short)io.getInt();
		double[][] nodes = new double[numNodes][2];
		for (int i = 0; i < numNodes; i++) {
			nodes[i][0] = io.getDouble();
			nodes[i][1] = io.getDouble();
		}

		int[][] distance = new DistanceNew().calculateDistances(nodes);
		ConstructionHeuristic greedy = new Greedy();
		Path path = greedy.initialize(distance);
		path = new TwoOptIterationHeuristic().enhance(path, distance);
		io.println(path);
		io.close();
	}
}
