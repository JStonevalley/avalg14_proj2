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
		Path path = new TwoWayArrayPath(nodes.length);
		HashSet<Short> inPath = new HashSet<Short>();
		Iterator<Distance.DistanceNode> it = distance.getDistancesForNode((short)0).iterator();
		it.next();
		Distance.DistanceNode next = it.next();
		path.setEdge((short)0, next.getNodeNr(), next.getDistance());
		Distance.DistanceNode current = next;
		inPath.add(current.getNodeNr());
		inPath.add((short)0);
		for (short i = 1; i < numNodes-1; i++) {
			Iterator<Distance.DistanceNode> iterator = distance.getDistancesForNode(current.getNodeNr()).iterator();
			while(iterator.hasNext()){
				next = iterator.next();
				if (!inPath.contains(next.getNodeNr())){
					break;
				}
			}
			path.setEdge(current.getNodeNr(), next.getNodeNr(), next.getDistance());
			current = next;
			inPath.add(current.getNodeNr());
		}
		path.setEdge(current.getNodeNr(), (short)0, distance.getDistance(current.getNodeNr(), (short)0).getDistance());
		System.out.println(path.toString());
	}
}
