import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Johan Arnör on 27/11/14.
 */
public class ArrayPath implements Path {

	private ArrayList<Integer> path;
	private int length;
	private HashMap<Integer, Integer> indexMapping;
	private int[][] distances;

	public ArrayPath(int[][] distances) {
		this.distances = distances;
		indexMapping = new HashMap<Integer, Integer>(distances.length+1);
		path = new ArrayList<Integer>(distances.length+1);
	}

	@Override public void setEdge(int a, int b) {
		if (path.size() > 0 && path.get(path.size()-1) != a){
			throw new IllegalArgumentException("a already has an edge from it or a is not in the path");
		}
		if (path.size() == distances.length+1){
			throw new IllegalArgumentException("path is already complete");
		}
		if (path.size() == 0){
			indexMapping.put(a, path.size());
			path.add(a);
		}
		indexMapping.put(b, path.size());
		path.add(b);
		length += distances[a][b];
	}

	@Override public void removeEdge(int a, int b) {
		if(path.get(path.size()-1) != b){
			throw new IllegalArgumentException("there is such edge or the arguments are swaped");
		}
		path.remove(path.size()-1);
		indexMapping.remove(path.size());
		length -= distances[a][b];
	}

	@Override
	public int getNext(int node) {
		return path.get(indexMapping.get(node));
	}

	@Override public int[] getNeighbourNodes(int currentNode) {
		return new int[]{path.get(indexMapping.get(currentNode))};
	}

	@Override public int getLength() {
		return length;
	}

	@Override public boolean inPath(int a) {
		return indexMapping.containsKey(a);
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
		ArrayList<Integer> reverse = new ArrayList<Integer>();
		for (int i = indexMapping.get(y); i < Math.max(path.size(), indexMapping.get(a) + 1); i++){
			reverse.add(path.get(i));
		}
		if (path.size() > indexMapping.get(a) + 1){
			for (int i = 1; i < indexMapping.get(a) + 1; i++){
				reverse.add(path.get(i));
			}
		}
		for (int i = indexMapping.get(y); i < Math.max(path.size(), indexMapping.get(a) + 1); i++){
			path.set(i, reverse.remove(path.size()-1));
		}
		reverse.add(path);
		while(current != a){
			current = path[current];
			reverse.add(current);
		}
		for (int i = reverse.size()-1; i > 0; i--) {
			path[reverse.get(i)] = reverse.get(i-1);
		}
		path[x] = a;
		length -= distances[x][y];
		length += distances[x][a];
		path[y] = b;
		length -= distances[a][b];
		length += distances[y][b];
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
