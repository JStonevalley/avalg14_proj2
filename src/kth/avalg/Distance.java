package kth.avalg;

import java.util.Arrays;

/**
 * Created by Jonas on 2014-11-26.
 */
public class Distance {
	DistanceNode[][] sortedDistances;
	int[][] distanceMatrix;
	public Distance(double[][] nodes) {
		updateDistances(nodes);
	}

	public int getDistance(short from , short to){
		return distanceMatrix[from][to];
	}

	public DistanceNode[] getSortedDistancesForNode(short nodeNr){
		return sortedDistances[nodeNr];
	}

	private void updateDistances(double[][] nodes){
		sortedDistances = new DistanceNode[nodes.length][];
		distanceMatrix = new int[nodes.length][];
		for (short i = 0; i < nodes.length; i++) {
			sortedDistances[i] = new DistanceNode[nodes.length];
			distanceMatrix[i] = new int[nodes.length];
			for (short j = 0; j < nodes.length; j++) {
				DistanceNode node = new DistanceNode(j, (int) Math
						.round(Math.sqrt(Math.pow(Math.abs(nodes[i][0] - nodes[j][0]), 2) + Math.pow(Math.abs(
								nodes[i][1] - nodes[j][1]), 2))));
				sortedDistances[i][j] = node;
				distanceMatrix[i][j] = node.getDistance();
			}
			Arrays.sort(sortedDistances[i]);
		}
	}

	public class DistanceNode implements Comparable<DistanceNode>{
		private short nodeNr;
		private int distance;
		private DistanceNode(short nodeNr, int distance){
			this.nodeNr = nodeNr;
			this.distance = distance;
		}

		public short getNodeNr() {
			return nodeNr;
		}

		public int getDistance() {
			return distance;
		}

		/**
		 * Compares this object with the specified object for order.  Returns a
		 * negative integer, zero, or a positive integer as this object is less
		 * than, equal to, or greater than the specified object.
		 * <p/>
		 * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
		 * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
		 * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
		 * <tt>y.compareTo(x)</tt> throws an exception.)
		 * <p/>
		 * <p>The implementor must also ensure that the relation is transitive:
		 * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
		 * <tt>x.compareTo(z)&gt;0</tt>.
		 * <p/>
		 * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
		 * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
		 * all <tt>z</tt>.
		 * <p/>
		 * <p>It is strongly recommended, but <i>not</i> strictly required that
		 * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
		 * class that implements the <tt>Comparable</tt> interface and violates
		 * this condition should clearly indicate this fact.  The recommended
		 * language is "Note: this class has a natural ordering that is
		 * inconsistent with equals."
		 * <p/>
		 * <p>In the foregoing description, the notation
		 * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
		 * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
		 * <tt>0</tt>, or <tt>1</tt> according to whether the value of
		 * <i>expression</i> is negative, zero or positive.
		 *
		 * @param o the object to be compared.
		 * @return a negative integer, zero, or a positive integer as this object
		 * is less than, equal to, or greater than the specified object.
		 * @throws NullPointerException if the specified object is null
		 * @throws ClassCastException   if the specified object's type prevents it
		 *                              from being compared to this object.
		 */
		@Override public int compareTo(DistanceNode o) {
			if (this.getDistance() < o.getDistance()){
				return -1;
			}
			else if (this.getDistance() > o.getDistance()) {
				return 1;
			}
			else{
				return 0;
			}

		}
	}
}
