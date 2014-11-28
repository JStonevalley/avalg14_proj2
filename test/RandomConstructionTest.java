import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class RandomConstructionTest {

	@Test
	public void testInitialize() throws Exception {
		int noNodes = 100;
		HashSet<Integer> seenNodes = new HashSet<Integer>();
		int[][] nullArray = new int[noNodes][noNodes];
		Path path = new LinkedListPath(nullArray);
		new RandomConstruction(noNodes).construct(path);

		for (int i = 0; i < noNodes; i++) {
			if (path.getNext(i) < noNodes) {
				seenNodes.add(path.getNext(i));
			}
		}

		Assert.assertEquals(noNodes, seenNodes.size());
	}
}