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
		return new int[]{path[currentNode]};
	}

	@Override public int getLength() {
		return length;
	}

	@Override public boolean inPath(int a) {
		return path[a] >= 0;
	}

	/**
	 * @return a string to follow the path
	 */
	@Override public String toDebugString() {
		StringBuilder sb = new StringBuilder();
		int numInPath = 1;
		int start = 0;
		int current = 0;

		while(numInPath < path.length) {
			for (int i = 0; i < path.length; i++) {
				if(!inPath(i)){
					start = i;
					current = start;
					sb.append(start);
					numInPath++;
				}
			}
			while (path[current] > -1 && path[current] != start) {
				current = path[current];
				sb.append("-" + current);
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
