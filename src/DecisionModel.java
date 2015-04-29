import java.util.ArrayList;
import java.util.HashMap;

//WARNING: this is really rough!!!

public interface DecisionModel<E> {
	
	//public DataSample trainingSet;
	//private Outcome decision;

	//each column of our dataset is a probDist array element 
	//it's really a HashMap of { xValue: probability of xValue }
	//private ArrayList< HashMap<String, Double> > probDistributions;


	public HashMap<String, Double> getProbDist(int i);//get a column
	public ArrayList< HashMap<String, Double> > getAllProbDists();
	
	public DecisionModel trainModel(DataSample<E>[] dataset, int classifierColumn );//use the WHOLE set
	
	public Outcome<E> getDecision(DataSample<E>[] dataset);//fires the Naive BC code, returns an outcome from a set of outcomes
	
	
	//constructors + bean functions for Model, DataSample, Outcome?
	
	//classes/funcs to help assist the protocol accessibility from other packages

	
}
