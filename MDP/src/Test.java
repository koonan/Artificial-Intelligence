
public class Test {
	private static ValueIteration valueIteration;
	private static PolicyIteration policyIteration;

	public static void main(String[] args) {
		int r[] = { 100, -3, 0, 3 };
		for (int i = 0; i < 4; i++) {
			System.out.println("\n----------------------Value Iteration----------------------");
			System.out.println("For r = " + r[i]);
			valueIteration = new ValueIteration(r[i]);
			valueIteration.runValueIteration(1000);
			valueIteration.print();
			System.out.println("\n----------------------Policy Iteration----------------------");
			System.out.println("For r = " + r[i]);
			policyIteration = new PolicyIteration(r[i]);
			policyIteration.runPolicyIteration(1000);
			policyIteration.print();
			
		}
		

	}

}
