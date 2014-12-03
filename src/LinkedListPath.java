import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class LinkedListPath implements Path {

	private int[] path;

	public LinkedListPath(int[][] distances) {
		path = new int[distances.length];
		Arrays.fill(path, -1);
	}

	@Override public void setEdge(int a, int b) {
		if(path[a] >= 0){
			throw new IllegalArgumentException("a already has an edge from it");
		}
		path[a] = b;
//		length += distances[a][b];
	}

	@Override public void removeEdge(int a, int b) {
		if(path[a] < 0){
			throw new IllegalArgumentException("a does not have an edge from it");
		}
		path[a] = -1;
//		length -= distances[a][b];
	}

	@Override
	public int getNext(int node) {
		return path[node];
	}

	@Override public boolean inOrder(int a, int b, int c) {
		throw new NotImplementedException();
	}

	@Override public int getLength(int[][] distances) {
		int length = 0;
		int startNode = path[0];
		int currentNode = startNode;

		do {
			int nextNode = getNext(currentNode);
			length += distances[currentNode][nextNode];
			currentNode = nextNode;
		} while (currentNode != startNode);

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
		ArrayList<Integer> reverse = new ArrayList<Integer>();
		int current = y;
		reverse.add(current);
		while(current != a){
			current = path[current];
			reverse.add(current);
		}
		for (int i = reverse.size()-1; i > 0; i--) {
			path[reverse.get(i)] = reverse.get(i-1);
		}
		path[x] = a;
		path[y] = b;
	}

	@Override public void shuffle(int noShuffles) {
		Random random = new Random();
		for (int i = 0; i < noShuffles; i++) {
			int node = random.nextInt(path.length);
		}
	}

	@Override public Path getCopy() {
		throw new NotImplementedException();
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
