import java.util.ArrayList;
import java.util.Random;

public class SidewaysMoves {

	private final static int N = 8;
	private Node start;
	private final static int NODES = 10;
	private final static int NUMOFSTEPS = 10;
	private int steps;
	private double difference;

	public SidewaysMoves() {
		start = new Node();
		start.setState(generateState());
		start.computeAttacks();

	}

	public Node sidewaysMoves() {
		Node currentNode = start;
		int count = 0;
		long start_time = System.nanoTime();

		while (true) {
			steps++;

			ArrayList<Node> successors = currentNode.getSuccessors();

			Node nextNode = null;
			for (int i = 0; i < successors.size(); i++) {
				if (successors.get(i).getAttacks() == 0) {
					return successors.get(i);
				}
				if (successors.get(i).getAttacks() == currentNode.getAttacks()) {
					nextNode = successors.get(i);
				}
				if (successors.get(i).getAttacks() < currentNode.getAttacks()) {
					nextNode = successors.get(i);
				}
			}

			if (count > NODES || nextNode == null) {
				if (steps < NUMOFSTEPS) {
					long end_time = System.nanoTime();
					difference = (end_time - start_time) / 1e6;
				}
				return currentNode;
			}

			// if (nextNode.computeAttacks() == 0) {
			// if (steps < NUMOFSTEPS) {
			// long end_time = System.nanoTime();
			// difference = (end_time - start_time) / 1e6;
			// }
			// return nextNode;
			// }
			if (nextNode.getAttacks() == currentNode.getAttacks()) {
				count++;
			} else {
				count = 0;
			}
			currentNode = nextNode;
		}
	}

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
