public class NaiveBC implements DecisionModel<String>{
	
	public DataRow trainingSet;
	private Outcome<String> decision;

	public NaiveBC(){
		trainingSet = new DataRow();
		decision = null;
	}

	public NaiveBC trainModel(DataRow[] dataset){
		return null;

	}

	public NaiveBC trainModel(DataRow[] dataset, int maxrows){
		return null;
		
	}

	public Outcome<String> getDecision(NaiveBC model, DataRow[] dataset){
		return decision;

	}

	public Outcome<String> getDecision(){
		//check if decision != null
		if(this.decision != null){
			return this.decision;
		}else{
			throw new RuntimeException("You need to run getDecision() with a NaiveBC object and a dataset to generate a decision first!");
			//System.out.println();
			//could be a throw
		}
	}

	/*@Override
	public Outcome<String> getDecision(DecisionModel m,
			DataRow[] dataset) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}