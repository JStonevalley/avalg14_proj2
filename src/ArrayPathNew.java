import java.util.*;

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

	// May perform differently form LinkedList due to it might swap the other part instead.
	@Override public void swap(int x, int y, int a, int b) {
		if (getNext(x) != y) { // TODO: Fix better than these workarounds
			int tmp = x;
			x = y;
			y = tmp;
		}
		if (getNext(a) != b) { // TODO: Fix better than these workarounds
			int tmp = a;
			a = b;
			b = tmp;
		}

		int aIndex = indices[a];
		int yIndex = indices[y];

		int bIndex = indices[b];
		int xIndex = indices[x];

		if (yIndex == aIndex || bIndex == xIndex) {
			return; // if x --> y/a --> b, don't swap
		}

		if (yIndex < aIndex) reverse(yIndex, aIndex);
		else if (bIndex < xIndex) reverse(bIndex, xIndex);
	}

	@Override public void dynamicSwap(Edge[] construction, int[] originNodes) {
		ArrayList<Edge> edges = new ArrayList<Edge>(Arrays.asList(construction));
		HashSet<Integer> fromNodes = new HashSet<Integer>();
		HashSet<Integer> toNodes = new HashSet<Integer>();
		for (Edge edge : edges){
			fromNodes.add(edge.fromNode);
			toNodes.add(edge.toNode);
		}
		int direction = 1;
		int[] newPath = new int[path.length];
		int currentIndex = indices[edges.get(0).fromNode];
		int arrayIndex = 0;
		while(arrayIndex < path.length){
			newPath[arrayIndex] = path[currentIndex];
			arrayIndex++;
			if (fromNodes.contains(path[currentIndex])){
				for (int i = 0; i < edges.size(); i++){
					if (edges.get(i).fromNode == path[currentIndex]){
						currentIndex = indices[edges.get(i).toNode];
						break;
					}
				}
				Boolean directionSet = false;
				for (int node : originNodes){
					if (node == path[currentIndex]){
						direction = -1;
						directionSet = true;
						break;
					}
				}
				if (directionSet == false){
					direction = 1;
				}
			}
			else{
				currentIndex = (currentIndex + direction + path.length) % path.length;
			}
		}
		path = newPath;
		for(int i = 0; i < path.length; i++){
			indices[path[i]] = i;
		}
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
