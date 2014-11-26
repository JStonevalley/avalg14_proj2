import java.util.HashSet;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		io.println("Hello World!");
		short numNodes = (short)io.getInt();
		double[][] nodes = new double[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			nodes[i] = new double[2];
			nodes[i][0] = io.getDouble();
			nodes[i][1] = io.getDouble();
		}
		Distance distance = new Distance(nodes);
		ConstructionHeuristic construction = new GreedyConstructionHeuristic(distance);
		System.out.println(construction.getPath().toString());
	}
}
