import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private Queen[] state;
	private final static int N = 8;
	private int attacks;
	private ArrayList<Node> successors;

	public Node() {
		state = new Queen[N];
		successors = new ArrayList<Node>();
		this.attacks = 0;
	}

	public void setState(Queen[] state) {
		this.state = state;
	}

	public Queen[] getState() {
		return this.state;
	}

	//Compute number of attacks for each queen 
	public int computeAttacks() {
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (state[i].attack(state[j])) {
					this.attacks++;
				}
			}
		}
		return this.attacks;
	}

	public int getAttacks() {
		return this.attacks;
	}

	//Get all possible successor of the current state 
	public ArrayList<Node> getSuccessors() {
		int index = 0;
		//Change the row for each queen in all possible moves
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				successors.add(index, new Node());
				successors.get(index).setState(state);
				successors.get(index).state[i].moveDown(j);
				successors.get(index++).computeAttacks();

			}
		}

		//Change the column for each queen in all possible moves
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				successors.add(index, new Node());
				successors.get(index).setState(state);
				successors.get(index).state[i].moveLeft(j);
				successors.get(index++).computeAttacks();

			}
		}
		//Change both the row and column by the same amount  for each queen in all possible moves 
		for (int i = 0; i < N; i++) { 
			for (int j = 1; j < N; j++) {
				successors.add(index, new Node());
				successors.get(index).setState(state);
				successors.get(index).state[i].moveLeft(j);
				successors.get(index).state[i].moveDown(j);
				successors.get(index++).computeAttacks();

			}
		}
		//Change both the row and column by different amounts for each queen in all possible moves 
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				successors.add(index, new Node());
				successors.get(index).setState(state);
				successors.get(index).state[i].moveLeft(j);
				successors.get(index).state[i].moveDown(j+1);
				successors.get(index++).computeAttacks();

			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				successors.add(index, new Node());
				successors.get(index).setState(state);
				successors.get(index).state[i].moveLeft(j+1);
				successors.get(index).state[i].moveDown(j);
				successors.get(index++).computeAttacks();

			}
		}

		return successors;

	}

	
	//Comparing to be use in priority queue 
	public int compareTo(Node n) {
		if (this.attacks < n.getAttacks())
			return -1;
		else if (this.attacks > n.getAttacks())
			return 1;
		else
			return 0;
	}

}
