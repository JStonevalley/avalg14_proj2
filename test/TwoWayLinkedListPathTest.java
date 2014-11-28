import org.junit.Assert;
import org.junit.Test;

public class TwoWayLinkedListPathTest {


	@Test
	public void testSetEdge() throws Exception {
		// Initializing Path
		int numNodes = 5;
		int[][] distances = new int[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			distances[i] = new int[numNodes];
			for (int j = 0; j < numNodes; j++) {
				distances[i][j] = 7;
			}
		}
		Path path = new TwoWayArrayPath(distances);
		for (int i = 1; i < numNodes; i++) {
			path.setEdge((i-1), i);
		}
		path.setEdge(0, (numNodes-1));
		try{
			path.setEdge(0, (numNodes-1));
			Assert.fail("Should not be able to add 0, " + (numNodes-1) + ". Should already exist");
		}catch (IllegalArgumentException e){}
	}

	@Test
	public void testRemoveEdge() throws Exception {
		// Initializing Path
		int numNodes = 5;
		int[][] distances = new int[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			distances[i] = new int[numNodes];
			for (int j = 0; j < numNodes; j++) {
				distances[i][j] = 7;
			}
		}
		Path path = new TwoWayArrayPath(distances);
		for (int i = 1; i < numNodes; i++) {
			path.setEdge((i-1), i);
		}
		path.setEdge(0, (numNodes-1));
		// Removing edges
		path.removeEdge(0, (numNodes-1));
		try{
			path.removeEdge(0, (numNodes-1));
			Assert.fail();
		}catch (IllegalArgumentException e){}
	}

	@Test
	public void testGetNeighbourNodes() throws Exception {
		// Initializing Path
		int numNodes = 5;
		int[][] distances = new int[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			distances[i] = new int[numNodes];
			for (int j = 0; j < numNodes; j++) {
				distances[i][j] = 7;
			}
		}
		TwoWayArrayPath path = new TwoWayArrayPath(distances);
		for (int i = 1; i < numNodes; i++) {
			path.setEdge((i-1), i);
		}
		path.setEdge(0, (numNodes-1));

		int[] expected = new int[]{1, 4};
		Assert.assertEquals(expected[0], path.getNeighbourNodes(0)[0]);
		Assert.assertEquals(expected[1], path.getNeighbourNodes(0)[1]);
	}

	@Test
	public void testGetLength() throws Exception {
		// Initializing Path
		int numNodes = 5;
		int[][] distances = new int[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			distances[i] = new int[numNodes];
			for (int j = 0; j < numNodes; j++) {
				distances[i][j] = 7;
			}
		}
		Path path = new TwoWayArrayPath(distances);
		for (int i = 1; i < numNodes; i++) {
			path.setEdge((i-1), i);
		}
		path.setEdge(0, (numNodes-1));
		Assert.assertEquals(35, path.getLength(distances));
		path.removeEdge(0, (numNodes-1));
		Assert.assertEquals(28, path.getLength(distances));
	}

	@Test
	public void testToDebugString() throws Exception {
		// Initializing Path
		int numNodes = 5;
		int[][] distances = new int[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			distances[i] = new int[numNodes];
			for (int j = 0; j < numNodes; j++) {
				distances[i][j] = 7;
			}
		}
		Path path = new TwoWayArrayPath(distances);
		for (int i = 1; i < numNodes; i++) {
			path.setEdge( (i - 1), i);
		}
		path.setEdge(0, (numNodes-1));
		Assert.assertEquals("0-1-2-3-4", path.toDebugString());
		path.removeEdge(0, 4);
		path.removeEdge(3, 4);
		path.setEdge(3, 0);
		Assert.assertEquals("0-1-2-3\n4", path.toDebugString());
	}
}