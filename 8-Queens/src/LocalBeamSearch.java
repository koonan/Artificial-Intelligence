import java.util.ArrayList;
import java.util.Random;
import java.util.PriorityQueue;

public class LocalBeamSearch {
	private Queen[] startState;
	private final static int N = 8;
	private PriorityQueue<Node> states;
	private Node start;
	private int size;
	private final static int NUMOFSTEPS = 10;
	private int steps;
	private double difference;

	// Constructor to generate k random states
	public LocalBeamSearch(int k) {
		size = k;
		states = new PriorityQueue<Node>();
		for (int i = 0; i < k; i++) {
			startState = generateState();
			start = new Node();
			start.setState(startState);
			start.computeAttacks();
			states.add(start);
		}

	}

	// Local Beam algorithm to start from k nodes and get the top k node in the
	// next round till found the best solution or stuck at flat points
	public Node localBeamSearch() {
		ArrayList<Node> currentNodes = new ArrayList<Node>();
		Node min = states.peek();
		for (int i = 0; i < size; i++) {
			currentNodes.add(states.poll());
		}
		long start_time = System.nanoTime();
		while (true) {
			PriorityQueue<Node> queue = new PriorityQueue<Node>();
			for (int i = 0; i < size; i++) {
				ArrayList<Node> successors = currentNodes.get(i).getSuccessors();
				for (int j = 0; j < successors.size(); j++) {
					steps++;

					if (successors.get(j).getAttacks() < currentNodes.get(i).getAttacks())
						queue.add(successors.get(j));
				}
			}
			if (queue.isEmpty() || queue.peek().getAttacks() > min.getAttacks()) {
				if (steps < NUMOFSTEPS) {
					long end_time = System.nanoTime();
					difference = (end_time - start_time) / 1e6;
				}
				return min;
			} else {
				min = queue.peek();
				// if(min.getAttacks() == 0 ){
				// if (steps < NUMOFSTEPS) {
				// long end_time = System.nanoTime();
				// difference = (end_time - start_time) / 1e6;
				// }
				// return min;
				// }
				currentNodes = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					currentNodes.add(queue.poll());

				}
			}
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
