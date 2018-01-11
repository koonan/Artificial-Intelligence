
public class Engine {
	private final static int K = 50;
	private final static int NUMOFSTEPS = 10;
	
	public static void main(String[] args) {
		int runnningTimes = 100;
		double hillClimbTime = 0 , randomTime = 0 , sidewaysTime = 0, beamTime = 0 ;
		int hillClimbCount = 0 , randomCount = 0 , sidewaysCount = 0 , beamCount =0 ; 
		int hillClimbAttacks = 0, randomAttacks = 0, sidewaysAttacks = 0, beamAttacks = 0;
		int hillClimbSuccesses = 0, randomSuccesses = 0, sidewaysSuccesses = 0, beamSuccesses = 0;
		for (int i = 0; i < runnningTimes; i++) {

			HillClimbing hillClimber = new HillClimbing();
			Node hillSolved = hillClimber.hillClimbing();
			hillClimbAttacks += hillSolved.getAttacks();

			RandomRestart randomRestart = new RandomRestart();
			Node randomSolved = randomRestart.randomRestart();
			randomAttacks += randomSolved.getAttacks();

			SidewaysMoves sidewaysMoves = new SidewaysMoves();
			Node sidewaysSolved = sidewaysMoves.sidewaysMoves();
			sidewaysAttacks += sidewaysSolved.getAttacks();

			LocalBeamSearch beam = new LocalBeamSearch(K);
			Node beamSloved = beam.localBeamSearch();
			beamAttacks += beamSloved.getAttacks();

			if (hillSolved.getAttacks() == 0) {
				hillClimbSuccesses++;
			}
			if (hillClimber.getSteps() <  NUMOFSTEPS ){
				hillClimbTime += hillClimber.getTime();
				hillClimbCount ++;
			}
			
			if (randomRestart.getSteps() <  NUMOFSTEPS ){
				randomTime += randomRestart.getTime();
				randomCount ++;
			}
			if (sidewaysMoves.getSteps() <  NUMOFSTEPS ){
				sidewaysTime+= sidewaysMoves.getTime();
				sidewaysCount ++;
			}
			if (beam.getSteps() <  NUMOFSTEPS ){
				beamTime+= beam.getTime();
				beamCount ++;
			}
			
			if (randomSolved.getAttacks() == 0) {
				randomSuccesses++;
			}

			if (sidewaysSolved.getAttacks() == 0) {
				sidewaysSuccesses++;
			}

			if (beamSloved.getAttacks() == 0) {
				beamSuccesses++;
			}
			
			
		}
		if(hillClimbCount == 0){
			hillClimbCount = 1;
		}
		if(randomCount == 0){
			randomCount = 1;
		}
		if(sidewaysCount == 0){
			sidewaysCount  = 1;
		}
		if(beamCount == 0){
			beamCount  = 1;
		}
		System.out.println("Hill Climbing Success %     : " + (double) hillClimbSuccesses / (double) runnningTimes
				+ "    Number Of Success :" + hillClimbSuccesses + "   Averag Attacks  : "
				+ Math.round((double) hillClimbAttacks / runnningTimes) + "   Average Time in m seconds : " + hillClimbTime / hillClimbCount );
		System.out.println("Random Restart Success %    : " + (double) randomSuccesses / (double) runnningTimes
				+ "   Number Of Success :" + randomSuccesses + "   Averag Attacks : "
				+ Math.round((double) randomAttacks / runnningTimes) + "   Average Time in m seconds : " + randomTime / randomCount);
		System.out.println("Sideways Moves Success %    : " + (double) sidewaysSuccesses / (double) runnningTimes
				+ "   Number Of Success  :" + sidewaysSuccesses + "   Averag Attacks : "
				+ Math.round((double) sidewaysAttacks / runnningTimes)+ "   Average Time in m seconds : " + sidewaysTime / sidewaysCount);
		System.out.println("Local Beam Search Success % : " + (double) beamSuccesses / (double) runnningTimes
				+ "   Number Of Success :" + beamSuccesses + "   Averag Attacks : "
				+ Math.round((double) beamAttacks / runnningTimes)+ "   Average Time in m seconds : " + beamTime / beamCount);
		
	}

}
