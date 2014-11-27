
public class Main {
	public static void main(String[] args) throws Exception {
		Kattio io = new Kattio(System.in, System.out);
		short numNodes = (short)io.getInt();
		double[][] nodes = new double[numNodes][2];
		for (int i = 0; i < numNodes; i++) {
			nodes[i][0] = io.getDouble();
			nodes[i][1] = io.getDouble();
		}
		Distance distance = new Distance(nodes);
		ConstructionHeuristic construction = new Greedy(distance);
		io.println(construction.getPath());
		io.close();

//		TwoOptIterationHeuristic opt = new TwoOptIterationHeuristic(construction.getPath(), distance, new Random());
//		System.out.println(opt.getPath().toString());
	}
}
