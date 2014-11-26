package kth.avalg;

import kth.avalg.heuristic.construction.ConstructionHeuristic;
import kth.avalg.heuristic.construction.Greedy;

public class Main {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		io.println("Hello World!");
		short numNodes = (short)io.getInt();
		double[][] nodes = new double[numNodes][];
		for (int i = 0; i < numNodes; i++) {
			nodes[i] = new double[2];
			nodes[i][0] = io.getDouble();
			nodes[i][1] = io.getDouble();
		}
		Distance distance = new Distance(nodes);
		ConstructionHeuristic construction = new Greedy(distance);
		System.out.println(construction.getPath().toString());
//		TwoOptIterationHeuristic opt = new TwoOptIterationHeuristic(construction.getPath(), distance, new Random());
		//System.out.println(opt.getPath().toString());
	}
}
