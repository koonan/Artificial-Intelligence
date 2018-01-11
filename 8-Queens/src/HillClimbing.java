import java.util.ArrayList;
import java.util.Random;

public class HillClimbing {

	private Queen[] startState;
	private final static int N = 8;
	private final static int NUMOFSTEPS = 10;
	private Node start;
	private int steps;
	private double difference;

	// Constructor which create random initial state
	public HillClimbing() {
		startState = generateState();
		start = new Node();
		start.setState(startState);
		start.computeAttacks();
	}

	// HillClimbing Algorithm
	public Node hillClimbing() {
		Node currentNode = start;
		steps = 0;
		long start_time = System.nanoTime();

		while (true) {
			ArrayList<Node> successors = currentNode.getSuccessors();
			steps++;

			Node nextNode = null;
			for (int i = 0; i < successors.size(); i++) {

				if (successors.get(i).getAttacks() < currentNode.getAttacks()) {
					nextNode = successors.get(i);
				}
			}

			if (nextNode == null) {
				if (steps < NUMOFSTEPS) {
					long end_time = System.nanoTime();
					difference = (end_time - start_time) / 1e6;
				}
				return currentNode;
			}

			// if (nextNode.getAttacks() == 0){
			// if (steps < NUMOFSTEPS) {
			// long end_time = System.nanoTime();
			// difference = (end_time - start_time) / 1e6;
			// }
			// return nextNode;
			// }

			currentNode = nextNode;
		}
	}

	// Create Random State
	public Queen[] generateState() {
		Queen[] start = new Queen[N];
		Random gen = new Random();

		for (int i = 0; i < 8; i++) {
			start[i] = new Queen(gen.nextInt(8), i);
		}
		return start;
	}

	public int getSteps() {
		return steps;
	}

	public double getTime() {
		return difference;
	}

}
