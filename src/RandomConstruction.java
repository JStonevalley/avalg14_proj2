import java.util.ArrayList;
import java.util.Collections;

public class RandomConstruction implements ConstructionHeuristic {

	private int noNodes;

	public RandomConstruction(int noNodes) {
		this.noNodes = noNodes;
	}

	@Override
	public void construct(Path path) {
		ArrayList<Integer> nodes = initializePath(noNodes);
		Collections.shuffle(nodes);

		for (int i = 0; i < nodes.size() - 1; i++) {
			int fromNode = nodes.get(i);
			int toNode = nodes.get(i + 1);
			path.setEdge(fromNode, toNode);
		}

		int firstNode = nodes.get(0);
		int lastNode = nodes.get(nodes.size() - 1);
		path.setEdge(lastNode, firstNode);
	}

	private ArrayList<Integer> initializePath(int size) {
		ArrayList<Integer> nodes = new ArrayList<Integer>(size);
		for (int i = 0; i < size; i++) {
			nodes.add(i);
		}
		return nodes;
	}
}
