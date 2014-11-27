import java.util.Arrays;

/**
 * Created by Johan Arnör on 27/11/14.
 */
public class ArrayPath implements Path {

	private int[] path;
	private int length;

	public ArrayPath(int noNodes) {
		path = new int[noNodes];
		Arrays.fill(path, -1);
	}

	@Override public void setEdge(int a, int b, int distance) {
		if(path[a] >= 0){
			throw new IllegalArgumentException("a already has an edge from it");
		}
		path[a] = b;
		length += distance;
	}

	@Override public void removeEdge(int a, int b, int distance) {
		if(path[a] < 0){
			throw new IllegalArgumentException("a does not have an edge from it");
		}
		path[a] = -1;
		length -= distance;
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

	/**
	 * @return a string to follow the path
	 */
	@Override public String toDebugString() {
		return null;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		int start = 0;
		int current = start;
		sb.append(start);
		while (path[current] > -1 && path[current] != start){
			current = path[current];
			sb.append("\n"+current);
		}
		return sb.toString();
	}

}
