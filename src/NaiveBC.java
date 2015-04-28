public class NaiveBC implements DecisionModel<String>{
	
	public DataRow trainingSet;
	private Outcome<String> decision;
	private ArrayList< HashMap<String, Double> > probDists;//the prob. distributions

	public NaiveBC(){
		trainingSet = new DataRow();
		probDists = new ArrayList<HashMap<String,Double>>();
		decision = null;
	}

	public HashMap<String, Double> getProbDist(int i){
		//get a column	
		return probDists.get(i);
	}
	public ArrayList< HashMap<String, Double> > getAllProbDists(){
		return probDists;
	}

	public NaiveBC trainModel(DataRow[] dataset, int classifierColumn){
		//loop over dataset
			//for each row's cell, record a count of each occurrence of the values 
			//separate into different buckets (or Outcome<String> instances) depending on the column that is the classifier (default, this is the last column)
			//put each chance of Bucket and chance of column being a value into the probDists structure
			//problems? we have this algorithm in our notes!!!


		return null;
	}

	public NaiveBC trainModel(DataRow[] dataset, int maxrows, int classifierColumn){
		//make a separate dataSubSet and pass that into the first trainModel() function

		return null;
	}

	public Outcome<String> getDecision(DataRow[] sampleset){
		return decision;
	}

	public Outcome<String> getDecision(){
		//check if decision != null
		if(this.decision != null){
			return this.decision;
		}else{
			throw new RuntimeException("You need to run getDecision() with a sample dataset to generate a decision first!");
		}
	}

	/*@Override
	public Outcome<String> getDecision(DecisionModel m,
			DataRow[] dataset) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}