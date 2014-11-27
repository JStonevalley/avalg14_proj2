/**
 * Created by Johan Arnör on 26/11/14.
 */
public class TestGenerator {

	private static final int NO_EDGES = 2000;

	public static void main(String[] args) {
		System.out.println(NO_EDGES);
		for (int i = 0; i < NO_EDGES; i++) {
			System.out.println(Math.random() * 100 + " " + Math.random() * 100);
		}
	}
}
