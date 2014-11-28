import java.io.File;
import java.io.FileInputStream;

public class Main {

	private static boolean DEBUG = true;
	private static int MAX_NO_CLOSEST_NODES = 120;

	public static void main(String[] args) throws Exception {

		String osVersion = System.getProperty("os.name");
		if (!(osVersion.contains("Mac") || osVersion.contains("Windows")))
			DEBUG = false;

		long startTime = System.nanoTime();
		Kattio io;
		if (DEBUG) {
			io = new Kattio(new FileInputStream(new File("data/10.txt")), System.out);
		} else {
			io = new Kattio(System.in, System.out);
		}

		short numNodes = (short)io.getInt();
		double[][] nodes = new double[numNodes][2];
		for (int i = 0; i < numNodes; i++) {
			nodes[i][0] = io.getDouble();
			nodes[i][1] = io.getDouble();
		}

		DistanceNew distanceClass = new DistanceNew();
		int[][] distance = distanceClass.calculateDistances(nodes);
		int[][] closestNodes = distanceClass.getClosestNodes(distance, MAX_NO_CLOSEST_NODES);
		ConstructionHeuristic heuristic = new GreedyConstruction(distance);

		Path path = new ArrayPath(distance); // Swap datastructure here
		heuristic.construct(path);
		if (DEBUG) {
			io.println(path.getLength());
			io.println(path.toDebugString());
		}
		path = new TwoOptIterationHeuristic().enhance(path, distance, closestNodes);
		if (DEBUG) {
			io.println(path.getLength());
			io.println(path.toDebugString());
			io.println((System.nanoTime() - startTime)/(1000*1000) + "ms");
		}
		else {
			io.println(path);
		}
		io.close();
	}
}
