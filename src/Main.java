import java.io.File;
import java.io.FileInputStream;

public class Main {

	private static final boolean DEBUG = false;

	public static void main(String[] args) throws Exception {
		Kattio io;
		if (DEBUG) {
			io = new Kattio(new FileInputStream(new File("data/10000.txt")), System.out);
		} else {
			io = new Kattio(System.in, System.out);
		}

		short numNodes = (short)io.getInt();
		double[][] nodes = new double[numNodes][2];
		for (int i = 0; i < numNodes; i++) {
			nodes[i][0] = io.getDouble();
			nodes[i][1] = io.getDouble();
		}

		int[][] distance = new DistanceNew().calculateDistances(nodes);
		ConstructionHeuristic heuristic = new RandomConstruction(distance.length);
		Path path = new ArrayPath(distance);
		heuristic.construct(path);
//		path = new TwoOptIterationHeuristic().enhance(path, distance);
		io.println(path);
		io.close();
	}
}
