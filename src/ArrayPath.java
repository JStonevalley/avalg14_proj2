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
		if(path.size() != distances.length) {
			indexMapping.put(b, path.size());
			path.add(b);
		}
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
		return path.get((indexMapping.get(node)+1) % path.size());
	}

	@Override public int getLength(int[][] distances) {
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
		int stop = indexMapping.get(a) + 1;
		if (indexMapping.get(y) > stop){
			stop = path.size();
		}
		for (int i = indexMapping.get(y); i < stop; i++){
			reverse.add(path.get(i));
		}
		if (stop == path.size()){
			for (int i = 0; i < indexMapping.get(a) + 1; i++){
				reverse.add(path.get(i));
			}
		}
		for (int i = indexMapping.get(y); i < stop; i++){
			path.set(i, reverse.remove(reverse.size() - 1));
		}
		if (stop == path.size()){
			for (int i = 0; i < indexMapping.get(a) + 1; i++){
				path.set(i, reverse.remove(reverse.size() - 1));
			}
		}

		length -= distances[x][y];
		length += distances[x][a];

		length -= distances[a][b];
		length += distances[y][b];
	}

	/**
	 * @return a string to follow the path
	 */
	@Override public String toDebugString() {
		StringBuilder sb = new StringBuilder();
		if(path.size() > 0) {
			sb.append(path.get(0));
			for (int i = 1; i < path.size(); i++) {
				sb.append("-" + path.get(i));
			}
		}
		return sb.toString();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(path.size() > 0) {
			sb.append(path.get(0));
			for (int i = 1; i < path.size(); i++) {
				sb.append("\n" + path.get(i));
			}
		}
		return sb.toString();
	}

}
