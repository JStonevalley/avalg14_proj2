import java.util.Arrays;

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
			if (path.length == 1) {
				path[0] = a;
				indices[0] = 0;
			} else {
				path[0] = a;
				path[1] = b;
				indices[a] = 0;
				indices[b] = 1;
			}

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

	@Override public boolean inOrder(int a, int b, int c) {
		int aIndex = indices[a]; int bIndex = indices[b]; int cIndex = indices[c];
		if (aIndex < bIndex && bIndex < cIndex) return true;
		if (aIndex < bIndex && cIndex < aIndex) return true;
		if (aIndex > bIndex && bIndex < cIndex && cIndex < aIndex) return true;
		return false;
	}

	// TODO: Check if not equal in reversed order
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
		if (a < 0 || a >= path.length) throw new NullPointerException();
		return indices[a] != NULL_NODE;
	}

	@Override public void swap(int x, int y, int a, int b) {
//		if (x >= path.length || y >= path.length || a >= path.length || b >= path.length) {
//			throw new NullPointerException();
//		}
//		if (x < 0 || y < 0|| a < 0 || b < 0) {
//			throw new NullPointerException();
//		}

		int aIndex = indices[a];
		int yIndex = indices[y];

		int bIndex = indices[b];
		int xIndex = indices[x];

		if (yIndex < aIndex) reverse(yIndex, aIndex);
		else if (bIndex < xIndex) reverse(bIndex, xIndex);
	}

	private void reverse(int startIndex, int endIndex) {
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
