import java.util.Iterator;

/**
 * Created by Jonas on 2014-11-26.
 */
public class GreedyConstructionHeuristic implements ConstructionHeuristic {
	Path path;
	public GreedyConstructionHeuristic(Distance distance){
		path = new TwoWayArrayPath(distance.getDistancesForNode((short)0).size());
		Iterator<Distance.DistanceNode> it = distance.getDistancesForNode((short)0).iterator();
		it.next();
		Distance.DistanceNode next = it.next();
		path.setEdge((short)0, next.getNodeNr(), next.getDistance());
		Distance.DistanceNode current = next;
		for (short i = 1; i < distance.getDistancesForNode((short)0).size()-1; i++) {
			Iterator<Distance.DistanceNode> iterator = distance.getDistancesForNode(current.getNodeNr()).iterator();
			while(iterator.hasNext()){
				next = iterator.next();
				if (!path.inPath(next.getNodeNr())){
					break;
				}
			}
			path.setEdge(current.getNodeNr(), next.getNodeNr(), next.getDistance());
			current = next;
		}
		path.setEdge(current.getNodeNr(), (short)0, distance.getDistance(current.getNodeNr(), (short)0).getDistance());
	}
	/**
	 * @return a path
	 */
	@Override public Path getPath() {
		return path;
	}
}
