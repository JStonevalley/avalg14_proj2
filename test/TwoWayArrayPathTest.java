import org.junit.Assert;
import org.junit.Test;

public class TwoWayArrayPathTest {


	@Test
	public void testSetEdge() throws Exception {
		// Initializing Path
		int numNodes = 5;
		Path path = new TwoWayArrayPath(numNodes);
		for (short i = 1; i < numNodes; i++) {
			path.setEdge((short)(i-1), i, 7);
		}
		path.setEdge((short)0, (short)(numNodes-1), 7);
		try{
			path.setEdge((short)0, (short)(numNodes-1), 7);
			Assert.fail("Should not be able to add 0, " + (numNodes-1) + ". Should already exist");
		}catch (IllegalArgumentException e){}
	}
	@Test
	public void testRemoveEdge() throws Exception {
		// Initializing Path
		int numNodes = 5;
		Path path = new TwoWayArrayPath(numNodes);
		for (short i = 1; i < numNodes; i++) {
			path.setEdge((short)(i-1), i, 7);
		}
		path.setEdge((short)0, (short)(numNodes-1), 7);
		// Removing edges
		path.removeEdge((short)0, (short)(numNodes-1), 7);
		try{
			path.removeEdge((short)0, (short)(numNodes-1), 7);
			Assert.fail();
		}catch (IllegalArgumentException e){}
	}

	@Test
	public void testGetNeighbourNodes() throws Exception {
		// Initializing Path
		int numNodes = 5;
		Path path = new TwoWayArrayPath(numNodes);
		for (short i = 1; i < numNodes; i++) {
			path.setEdge((short)(i-1), i, 7);
		}
		path.setEdge((short)0, (short)(numNodes-1), 7);

		short[] expected = new short[]{(short)1, (short)4};
		Assert.assertEquals(expected[0], path.getNeighbourNodes((short)0)[0]);
		Assert.assertEquals(expected[1], path.getNeighbourNodes((short)0)[1]);
	}

	@Test
	public void testGetLength() throws Exception {
		// Initializing Path
		int numNodes = 5;
		Path path = new TwoWayArrayPath(numNodes);
		for (short i = 1; i < numNodes; i++) {
			path.setEdge((short)(i-1), i, 7);
		}
		path.setEdge((short)0, (short)(numNodes-1), 7);
		Assert.assertEquals(35, path.getLength());
		path.removeEdge((short)0, (short)(numNodes-1), 7);
		Assert.assertEquals(28, path.getLength());
	}

	@Test
	public void testToDebugString() throws Exception {
		// Initializing Path
		int numNodes = 5;
		Path path = new TwoWayArrayPath(numNodes);
		for (short i = 1; i < numNodes; i++) {
			path.setEdge((short) (i - 1), i, 7);
		}
		path.setEdge((short)0, (short)(numNodes-1), 7);
		Assert.assertEquals("0-1-2-3-4", path.toDebugString());
		path.removeEdge((short)0, (short)4, 7);
		path.removeEdge((short)3, (short)4, 7);
		path.setEdge((short)3, (short)0, 7);
		Assert.assertEquals("0-1-2-3\n4", path.toDebugString());
	}
}