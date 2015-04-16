//WARNING: this is really rough!!!

public interface DecisionModel<E> {
	
	//public DataSample trainingSet;
	//private Outcome decision;

	
	public DecisionModel trainModel(DataSample<E>[] dataset );//use the WHOLE set
	
	public DecisionModel trainModel(DataSample<E>[] dataset, int maxRows ); //to use a part of a dataset
	
	public Outcome<E> getDecision(DecisionModel m, DataSample<E>[] dataset);//fires the Naive BC code, returns an outcome from a set of outcomes
	
	
	
	//constructors + bean functions for Model, DataSample, Outcome?
	
	//classes/funcs to help assist the protocol accessibility from other packages

	
}
