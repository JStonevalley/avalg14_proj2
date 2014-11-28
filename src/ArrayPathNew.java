import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Johan Arnör on 28/11/14.
 */
public class ArrayPathNew implements Path {

	private final int NULL_NODE = -1;
	private int[] path;
	private int[] indices;

	public ArrayPathNew(int[][] distances) {
		path = new int[distances.length];
		indices = new int[distances.length];
		Arrays.fill(indices, NULL_NODE);
	}

	// TODO: Make it less dangerous.
	@Override public void setEdge(int a, int b) {
		if (indices[a] == NULL_NODE) { // node a has no edge yet
			path[0] = a;
			path[1] = b;
			indices[a] = 0;
			indices[b] = 1;
		} else {
			int bIndex = (indices[a] + 1) % path.length;
			indices[b] = bIndex;
			path[bIndex] = b;
		}
	}

	@Override public void removeEdge(int a, int b) {
		throw new NullPointerException();
	}

	@Override public int getNext(int node) {
		return path[(indices[node] + 1) % path.length];
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
		return indices[a] != NULL_NODE;
	}

//	@Override public void swap(int x, int y, int a, int b) {
//		int aIndex = indices[a];
//		int yIndex = indices[y];
//		ArrayList<Integer> reversedList = getReversedList(Math.min(aIndex, yIndex), Math.max(aIndex, yIndex));
//	}
//
//	private ArrayList<Integer> getReversedList(int from, int to) {
//		ArrayList<Integer> reversed = new ArrayList<Integer>();
//		while (from != to) {
//			reversed.add()
//		}
//	}

	// Old broken swap
	@Override public void swap(int x, int y, int a, int b) {
		int aIndex = indices[a];
		int yIndex = indices[y];

		int bIndex = indices[b];
		int xIndex = indices[x];

		if (yIndex < aIndex) reverse(yIndex, aIndex);
		else if (bIndex < xIndex) reverse(bIndex, xIndex);
//		reverse(Math.min(aIndex, yIndex), Math.max(aIndex, yIndex));
	}

	private void reverse(int startIndex, int endIndex) {
//		startIndex++; endIndex--; // do not reverse the endpoint nodes
		while (endIndex > startIndex) {
			int tempNode = path[endIndex];
			int tempIndex = indices[path[endIndex]];

			indices[path[endIndex]] = indices[path[startIndex]];
			path[endIndex] = path[startIndex];

			indices[path[startIndex]] = tempIndex;
			path[startIndex] = tempNode;

			endIndex--; startIndex++;
		}
	}

	@Override public String toDebugString() {
		StringBuilder sb = new StringBuilder();

		sb.append(path[0]);
		for (int i = 1; i < path.length; i++) {
			sb.append("-");
			sb.append(path[i]);
		}

		sb.append("\n");
		return sb.toString();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append(path[0]);
		for (int i = 1; i < path.length; i++) {
			sb.append("\n");
			sb.append(path[i]);
		}

		return sb.toString();
	}
}
