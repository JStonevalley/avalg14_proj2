import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Johan Arnör on 27/11/14.
 */
public class ArrayPath implements Path {

	private int[] path;
	private int length;
	private int[][] distances;

	public ArrayPath(int[][] distances) {
		this.distances = distances;
		path = new int[distances.length];
		Arrays.fill(path, -1);
	}

	@Override public void setEdge(int a, int b) {
		if(path[a] >= 0){
			throw new IllegalArgumentException("a already has an edge from it");
		}
		path[a] = b;
		length += distances[a][b];
	}

	@Override public void removeEdge(int a, int b) {
		if(path[a] < 0){
			throw new IllegalArgumentException("a does not have an edge from it");
		}
		path[a] = -1;
		length -= distances[a][b];
	}

	@Override
	public int getNext(int node) {
		return path[node];
	}

	@Override public int[] getNeighbourNodes(int currentNode) {
		return new int[]{path[currentNode]};
	}

	@Override public int getLength() {
		return length;
	}

	@Override public boolean inPath(int a) {
		return path[a] >= 0;
	}

	/**
	 * x - y - a - b - x
	 *
	 * @param x
	 * @param y
	 * @param a
	 * @param b
	 */
	@Override public void swap(int x, int y, int a, int b) {
		int[] newPath = new int[path.length];
		int newLength = 0;
		newPath[x] = a;
		newLength += distances[x][a];
		newPath[y] = b;
		newLength += distances[y][b];
		int current = b;
		while (path[current] != x){
			newPath[current] = path[current];
			newLength += distances[current][path[current]];
			current = path[current];
		}
		current = a;
		while (path[current] != y){
			newPath[current] = path[current];
			newLength += distances[current][path[current]];
			current = path[current];
		}
		path = newPath;
		length = newLength;
	}

	/**
	 * @return a string to follow the path
	 */
	@Override public String toDebugString() {
		HashSet<Integer> inString = new HashSet<Integer>();
		StringBuilder sb = new StringBuilder();
		int numInPath = 1;
		int start = 0;
		int current = 0;

		while(numInPath < path.length) {
			for (int i = 0; i < path.length; i++) {
				if(!inString.contains(i)){
					start = i;
					current = start;
					sb.append(start);
					inString.add(start);
					numInPath++;
					break;
				}
			}
			while (path[current] > -1 && path[current] != start) {
				current = path[current];
				sb.append("-" + current);
				inString.add(current);
				numInPath++;
			}
			sb.append("\n");
		}
		return sb.toString();
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
