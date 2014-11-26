
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
		Path path = new TwoWayArrayPath(nodes.length);
		for (short i = 1; i < numNodes; i++) {
			path.setEdge((short)(i-1), (short)(i % numNodes), 1);
		}
		System.out.println(path.toString());
	}
}
