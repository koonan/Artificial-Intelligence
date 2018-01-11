
public class RandomRestart {
	private HillClimbing hillClimber;
	private final static int TRIES = 100;
	private final static int NUMOFSTEPS = 10;
	private int steps;
	private double difference;

	public RandomRestart() {
		hillClimber = new HillClimbing();
	}

	public Node randomRestart() {
		Node currentNode = hillClimber.hillClimbing();
		int tries = 0;
		int minAttacks = currentNode.getAttacks();
		Node minAttackNode = currentNode;
		int attacks = minAttacks;
		long start_time = System.nanoTime();

		// Stops after number of tries which is 100 [Assumbtion]
		while (tries <= TRIES) {
			steps++;

			Node temp = hillClimber.hillClimbing();
			attacks = temp.getAttacks();

			if (attacks < minAttacks) {
				minAttackNode = temp;
				minAttacks = attacks;
			} else {
				if (steps < NUMOFSTEPS) {
					long end_time = System.nanoTime();
					difference = (end_time - start_time) / 1e6;
				}
				steps = 0;
				tries++;
				hillClimber = new HillClimbing();
				start_time = System.nanoTime();
			}
			// if (attacks == 0) {
			// if (steps < NUMOFSTEPS) {
			// long end_time = System.nanoTime();
			// difference = (end_time - start_time) / 1e6;
			// }
			// return temp;
			// }
		}
		return minAttackNode;
	}

	public int getSteps() {
		return steps;
	}

	public double getTime() {
		return difference;
	}

}
