import org.junit.Assert;
import org.junit.Test;

public class GreedyTest {

	@Test
	public void testGetPath() throws Exception {
		short numNodes = 10;
		double[][] nodes = new double[numNodes][];
		nodes[0] = new double[]{95.0129, 61.5432};
		nodes[1] = new double[]{23.1139, 79.1937};
		nodes[2] = new double[]{60.6843, 92.1813};
		nodes[3] = new double[]{48.5982, 73.8207};
		nodes[4] = new double[]{89.1299, 17.6266};
		nodes[5] = new double[]{76.2097, 40.5706};
		nodes[6] = new double[]{45.6468, 93.5470};
		nodes[7] = new double[]{1.8504, 91.6904};
		nodes[8] = new double[]{82.1407, 41.0270};
		nodes[9] = new double[]{44.4703, 89.3650};

		ConstructionHeuristic greedy = new Greedy();
		Path path = greedy.initialize(new DistanceNew().calculateDistances(nodes));
		Assert.assertEquals(323, path.getLength());
		Assert.assertEquals("0-8-5-4-3-9-6-2-1-7\n", path.toDebugString());
	}
}