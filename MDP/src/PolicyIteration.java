public class PolicyIteration {
	private char newPolicy[][];
	private char currPolicy[][];
	private int row = 3, col = 3;
	private double unity[][];
	private ValueIteration valueIteration;
	private double update[][];
	private double reward[][];
	private double deltaMin = 1e-10;
	private int iteration;
	private static Engine engine;

	public PolicyIteration(int r) {

		valueIteration = new ValueIteration(r);
		update = valueIteration.getUpdate();
		reward = valueIteration.getReward();
		unity = valueIteration.getUnity();
		engine = new Engine();

	}

	public void runPolicyIteration(int iterationNum) {
		// Start using random policy
		char policy[][] = { { 'E', 'E', '+' }, { 'N', 'N', 'N' }, { 'N', 'N', 'N' } };
		iteration = iterationNum;
		double delta = 0;
		newPolicy = policy;
		// First loop converge after specific number of iteration
		for (int count = 0; count < iterationNum; count++) {
			int n = 0;
			policy = newPolicy;
			// First loop converge after specific number of iteration or when
			// the change is greater than e^10
			do {
				for (int x = 0; x < row; x++) {
					for (int y = 0; y < col; y++) {
						unity[x][y] = update[x][y];
					}
				}
				n++;
				delta = 0;
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < col; j++) {
						engine.updatePolicyIterationUnity(i, j, update, reward, policy, unity);
						double diff = Math.abs(update[i][j] - unity[i][j]);
						if (diff > delta)
							delta = diff;
					}
				}
			} while (delta > deltaMin && n < iterationNum);
			// We perform one look a head by using Value Iteration for 1 time
			// only and update the policy to continue
			currPolicy = policy;
			valueIteration.setUnity(unity);
			valueIteration.runValueIteration(1);
			newPolicy = valueIteration.getPolicy();
			unity = valueIteration.getUnity();

		}

	}

	// Printing the Policy and Values
	public void print() {
		currPolicy[0][2] = '+';

		System.out.println("Policy:\n");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(currPolicy[i][j] + "   ");
			}
			System.out.print("\n");
		}

		System.out.println("\n" + iteration + " iterations:\n");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.printf(unity[i][j] + "     ");
			}
			System.out.print("\n");
		}
	}
}
