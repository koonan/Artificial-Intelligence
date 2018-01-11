import java.util.HashMap;
import java.util.Map;

public class Engine {
	private Map<Integer, Character> direction = new HashMap<Integer, Character>();;
	private double gamma = 0.9;
	private double pToken = 0.8;
	private double pUnToken = 0.1;
	private int row = 3, col = 3;

	public void updateValueIterationUnity(int r, int c, double update[][], double reward[][], char policy[][],
			double unity[][]) {
		double actions[] = new double[4];
		direction.put(0, 'N');
		direction.put(1, 'S');
		direction.put(2, 'W');
		direction.put(3, 'E');
		if ((r == 0 && c == 2)) {
			update[r][c] = reward[r][c];
		} else {
			//Calculate the new reward in all diirection north , south , east , west and get the maximum one
			//North
			actions[0] = moveNorth(r, c, unity) * pToken + moveWest(r, c, unity) * pUnToken
					+ moveEast(r, c, unity) * pUnToken;
			//South
			actions[1] = moveSouth(r, c, unity) * pToken + moveWest(r, c, unity) * pUnToken
					+ moveEast(r, c, unity) * pUnToken;
			//East
			actions[2] = moveWest(r, c, unity) * pToken + moveSouth(r, c, unity) * pUnToken
					+ moveNorth(r, c, unity) * pUnToken;
			//West
			actions[3] = moveEast(r, c, unity) * pToken + moveSouth(r, c, unity) * pUnToken
					+ moveNorth(r, c, unity) * pUnToken;

			//Get the maximum direction
			int best = 0;
			for (int i = 1; i < 4; i++) {
				if (actions[i] > actions[best]) {
					best = i;
				}
			}

			//Update the policy according to the maximum direction
			switch (best) {
			case 0:
				policy[r][c] = direction.get(0);
				break;
			case 1:
				policy[r][c] = direction.get(1);
				break;
			case 2:
				policy[r][c] = direction.get(2);
				break;
			case 3:
				policy[r][c] = direction.get(3);
				break;
			}
			update[r][c] = reward[r][c] + gamma * actions[best];
		}
	}

	public void updatePolicyIterationUnity(int r, int c, double update[][], double reward[][], char policy[][],
			double unity[][]) {

		if ((r == 0 && c == 2)) {
			update[r][c] = reward[r][c];
		} else {
			//Get the random direction and calculate the new reword according to it 
			double action = 0;
			switch (policy[r][c]) {
			case 'N':
				action = moveNorth(r, c, unity) * pToken + moveWest(r, c, unity) * pUnToken
						+ moveEast(r, c, unity) * pUnToken;
				break;
			case 'W':
				action = moveWest(r, c, unity) * pToken + moveSouth(r, c, unity) * pUnToken
						+ moveNorth(r, c, unity) * pUnToken;
				break;
			case 'E':
				action = moveEast(r, c, unity) * pToken + moveSouth(r, c, unity) * pUnToken
						+ moveNorth(r, c, unity) * pUnToken;

				break;
			case 'S':
				action = moveSouth(r, c, unity) * pToken + moveWest(r, c, unity) * pUnToken
						+ moveEast(r, c, unity) * pUnToken;

				break;
			}

			update[r][c] = reward[r][c] + gamma * action;
		}
	}

	public double moveNorth(int r, int c, double[][] unity) {
		if ((r == 0))
			return unity[r][c];
		return unity[r - 1][c];
	}

	public double moveSouth(int r, int c, double[][] unity) {
		if ((r == row - 1))
			return unity[r][c];
		return unity[r + 1][c];
	}

	public double moveWest(int r, int c, double[][] unity) {
		if ((c == 0))
			return unity[r][c];
		return unity[r][c - 1];
	}

	public double moveEast(int r, int c, double[][] unity) {
		if ((c == col - 1))
			return unity[r][c];
		return unity[r][c + 1];
	}

}
