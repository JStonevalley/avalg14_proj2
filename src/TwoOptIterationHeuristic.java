import java.util.Random;

/**
 * Created by Jonas on 2014-11-26.
 */
public class TwoOptIterationHeuristic {
	Path path;
	//FETT BROKEN
	public TwoOptIterationHeuristic(Path path, Distance distance, Random random){
		short a,b, c, d;
		for (int i = 0; i < 10; i++) {
			Path temp = path.copy();
			a = (short)(random.nextInt(8) + 1);
			b = a;
			while((b = (short)(random.nextInt(8) + 1)) == a){}
			c = temp.getNeighbourNodes(a)[0];
			temp.removeEdge(a, c, distance.getDistance(a, c));
			if(temp.getNeighbourNodes(b)[0] > 0){
				d = temp.getNeighbourNodes(b)[0];
			}
			else{
				d = temp.getNeighbourNodes(b)[1];
			}
			temp.removeEdge(b, d, distance.getDistance(b, d));
			temp.setEdge(a, d, distance.getDistance(a, d));
			temp.setEdge(b, c, distance.getDistance(b, c));
			if (temp.getLength() < path.getLength()){
				path = temp;
			}
		}
		this.path = path;
	}

	public Path getPath(){
		return path;
	}
}
