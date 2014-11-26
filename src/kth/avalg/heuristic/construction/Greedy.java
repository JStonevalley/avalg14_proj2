package kth.avalg.heuristic.construction;

import kth.avalg.Distance;
import kth.avalg.path.Path;
import kth.avalg.path.TwoWayArrayPath;

/**
 * Created by Jonas on 2014-11-26.
 */
public class Greedy implements ConstructionHeuristic {
	Path path;
	public Greedy(Distance distance){
		path = new TwoWayArrayPath(distance.getSortedDistancesForNode((short) 0).length);
		Distance.DistanceNode next = distance.getSortedDistancesForNode((short) 0)[1];
		path.setEdge((short)0, next.getNodeNr(), next.getDistance());
		Distance.DistanceNode current = next;
		for (short i = 1; i < distance.getSortedDistancesForNode((short) 0).length-1; i++) {
			Distance.DistanceNode[] distanceNodes = distance.getSortedDistancesForNode(current.getNodeNr());
			for (int j = 1; j < distanceNodes.length; j++) {
				next = distanceNodes[j];
				if (!path.inPath(next.getNodeNr())){
					break;
				}
			}
			path.setEdge(current.getNodeNr(), next.getNodeNr(), next.getDistance());
			current = next;
		}
		path.setEdge(current.getNodeNr(), (short)0, distance.getDistance(current.getNodeNr(), (short) 0));
	}
	/**
	 * @return a path
	 */
	@Override public Path getPath() {
		return path;
	}
}
