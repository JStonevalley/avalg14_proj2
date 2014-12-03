import org.junit.Assert;
import org.junit.Test;

public class ArrayPathNewTest {

	@Test
	public void testSwap() throws Exception {
		int noNodes = 10;
		Path path = new ArrayPathNew(new int[noNodes][noNodes]);
		for (int i = 0; i < noNodes - 1; i++) {
			path.setEdge(i, i+1);
		}

		Assert.assertEquals("0-1-2-3-4-5-6-7-8-9\n", path.toDebugString());
		path.swap(2, 3, 6, 7);
		Assert.assertEquals("0-1-2-6-5-4-3-7-8-9\n", path.toDebugString());
		path.swap(2, 6, 3, 7);
		Assert.assertEquals("0-1-2-3-4-5-6-7-8-9\n", path.toDebugString());
		path.swap(1, 2, 6, 7);
		Assert.assertEquals("0-1-6-5-4-3-2-7-8-9\n", path.toDebugString());
		path.swap(9, 0, 4, 3);
		Assert.assertEquals("4-5-6-1-0-3-2-7-8-9\n", path.toDebugString());
		path.swap(4, 5, 4, 5);
		Assert.assertEquals("4-5-6-1-0-3-2-7-8-9\n", path.toDebugString());
	}

	@Test
	public void testInOrder() {
		int noNodes = 10;
		Path path = new ArrayPathNew(new int[noNodes][noNodes]);
		for (int i = 0; i < noNodes - 1; i++) {
			path.setEdge(i, i+1);
		}

		Assert.assertTrue(path.inOrder(0, 2, 4));
		Assert.assertTrue(path.inOrder(5, 6, 0));
		Assert.assertTrue(path.inOrder(8, 3, 6));
		Assert.assertFalse(path.inOrder(8, 2, 9));
		Assert.assertFalse(path.inOrder(5, 9, 6));
		Assert.assertFalse(path.inOrder(2, 0, 4));
	}
}