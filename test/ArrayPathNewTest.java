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

		Assert.assertEquals("0-1-2-3-4-5-6-7-8-9", path.toDebugString());
		path.swap(2, 3, 6, 7);
		Assert.assertEquals("0-1-2-6-5-4-3-7-8-9", path.toDebugString());
		path.swap(2, 6, 3, 7);
		Assert.assertEquals("0-1-2-3-4-5-6-7-8-9", path.toDebugString());
		path.swap(1, 2, 6, 7);
		Assert.assertEquals("0-1-6-5-4-3-2-7-8-9", path.toDebugString());
	}
}