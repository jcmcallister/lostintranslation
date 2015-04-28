public Class NaiveBC implements DecisionModel<String>{
	
	public DataSample<String>[] trainingSet;
	private Outcome<String> decision;

	public NaiveBC(){
		trainingSet = new DataSample<String>();
		decision = null;
	}

	public NaiveBC trainModel(DataSample<String>[] dataset){

	}

	public NaiveBC trainModel(DataSample<String>[] dataset, int maxrows){
		
	}

	public Outcome<String> getDecision(NaiveBC model, DataSample<String>[] dataset){

	}

	public Outcome<String> getDecision(){
		//check if decision != null
		if(this.decision != null){
			return this.decision;
		}else{
			System.out.println("You need to run getDecision() with a NaiveBC object and a dataset to generate a decision first!");
		}
	}
	
}