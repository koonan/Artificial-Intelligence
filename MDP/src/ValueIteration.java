
public class ValueIteration {

	private static double deltaMin = 1e-10;
	private static double unity[][];
	private static double update[][];
	private static double reward[][];
	private static char policy[][];
	private static int row = 3, col = 3;
	private static Engine engine;
	private int iteration;

	public ValueIteration(int r ) {
		engine = new Engine();
		policy = new char[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				policy[i][j] = '.';
			}
		}
		unity = new double[row][col];
		reward = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				reward[i][j] = -1;
			}
		}
		update = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				update[i][j] = 0;
			}
		}

		reward[0][2] = 10;
		reward[0][0] = r;
	
	}

	public void runValueIteration(int iterationNum) {
		//This function Converge when the the iteration number exceeed the the given number or the the change in unity array is greater than e^10
		double delta = 0;
		int n = 0;
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
					engine.updateValueIterationUnity(i, j, update, reward, policy, unity);
					double diff = Math.abs(update[i][j] - unity[i][j]);
					if (diff > delta)
						delta = diff;
				}
			}
		} while (delta > deltaMin && n < iterationNum);
		iteration = n;
	}
	
	public void setPolicy(char[][]policy){
		ValueIteration.policy =policy ;
	}
	public void setUnity(double [][] unity){
		ValueIteration.unity =unity ;
	}
	public double[][] getUnity() {
		return unity;
	}

	public double[][] getReward() {
		return reward;
	}

	public double[][] getUpdate() {
		return update;
	}

	public char[][] getPolicy() {
		return policy;
	}

	public void print() {
		policy[0][2] = '+';

		System.out.println("Policy:\n");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(policy[i][j] + "   ");
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