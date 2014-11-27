import java.util.Arrays;

/**
 * Created by Johan Arnör on 27/11/14.
 */
public class ArrayPath implements Path {

	private int[] path;

	public ArrayPath(int noNodes) {
		path = new int[noNodes];
		Arrays.fill(path, -1);
	}

	@Override public void setEdge(int a, int b, int distance) {
		path[a] = b;
	}

	@Override public void removeEdge(int a, int b, int distance) {

	}

	@Override public int[] getNeighbourNodes(int currentNode) {
		return new int[0];
	}

	@Override public int getLength() {
		return 0;
	}

	@Override public boolean inPath(int a) {
		return path[a] >= 0;
	}

	@Override public Path copy() {
		return null;
	}
}
