import java.io.File;
import java.io.FileInputStream;

public class Main {

	private static boolean DEBUG = true;
	private static int MAX_NO_CLOSEST_NODES = 40;
	private static int NO_SHUFFLES = 10;
	private static int NO_ITERATIONS = 100;
	private static long TIME_LIMIT = 1000000000;

	public static void main(String[] args) throws Exception {

//		long startTime = System.currentTimeMillis();

		String osVersion = System.getProperty("os.name");
		if (!(osVersion.contains("Mac") || osVersion.contains("Windows")))
			DEBUG = false;

		long startTime = System.nanoTime();
		Kattio io;
		if (DEBUG) {
			io = new Kattio(new FileInputStream(new File("data/100.txt")), System.out);
		} else {
			io = new Kattio(System.in, System.out);
		}

		short numNodes = (short)io.getInt();
//		NO_SHUFFLES = Math.max(numNodes/10, 10);
		double[][] nodes = new double[numNodes][2];
		for (int i = 0; i < numNodes; i++) {
			nodes[i][0] = io.getDouble();
			nodes[i][1] = io.getDouble();
		}

		DistanceNew distanceClass = new DistanceNew();
		int[][] distances = distanceClass.calculateDistances(nodes);
		int[][] closestNodes = distanceClass.getClosestNodes(distances, MAX_NO_CLOSEST_NODES);
		ConstructionHeuristic heuristic = new GreedyConstruction(distances);

		Path path = new ArrayPathNew(distances); // Swap datastructure here

		heuristic.construct(path);
		if (DEBUG) {
			io.println(path.getLength(distances));
			io.println(path.toDebugString());
			io.flush();
		}

		IterationHeuristic iterationHeuristic = new TwoOptSimple();
		int length = Integer.MAX_VALUE;
		Path bestPath = path;

		while (System.nanoTime() - startTime < TIME_LIMIT) {
			path = iterationHeuristic.enhance(path, distances, closestNodes);
			int newLength = path.getLength(distances);
			if (newLength < length) {
				bestPath = path.getCopy();
				length = newLength;
			}
			path.shuffle(NO_SHUFFLES);
		}

		path = bestPath;

		if (DEBUG) {
			io.println(path.getLength(distances));
			io.println(path.toDebugString());
			io.println((System.nanoTime() - startTime)/(1000*1000) + "ms");
		}
		else {
			io.println(path);
		}

		io.close();
	}
}
