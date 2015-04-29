import java.util.ArrayList;
import java.util.HashMap;

public class NaiveBC implements DecisionModel<String>{
	
	public DataRow trainingSet;
	private Classifier decision;
	private ArrayList< HashMap<String, Double> > probDists;//the prob. distributions for each data subset.
	/*
	
	Visual idea:

		probDists = [ 
						{ "blue":  p(x0 == 'blue'), "green": p(x0=="green"), "red": p(x0=="red") },
						{ "some" : p(x1=="some"), "none" : p(x1=="none"), "full" : p(x1=="full") },
						... 
					];

	*/

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

	public NaiveBC DERPtrainModel(DataRow[] dataset, int classifierColumn){
		//loop over dataset
			//for each row's cell, record a count of each occurrence of the values 
			//separate into different buckets (or Outcome<String> instances) depending on the column that is the classifier (default, this is the last column)
			//put each chance of Bucket and chance of column being a value into the probDists structure
			//problems? we have this algorithm in our notes!!!

		HashMap< String, ArrayList<Integer> > dataSubsets = new HashMap< String, ArrayList<Integer> >();
		//ArrayList<String> bucketLabels = new ArrayList<String>();

		String classModel = "ends";//can also be "match" or "divide"
		/* Class Models
			'ends'	:	classifiers match the end values, and go in between (good for a range of deterministic values)
			'match'	:	classifiers match the column values exactly (good for strings!)
			'divide':	classifiers evenly divide up the values over the input range
		*/
		int classDivisions = 0;//applicable only when classModel == "divide"

		//Step 1: Get all distinct Bucket values
		for(int i=0;i<dataset.length;i++){

			String[] row = dataset[i].getSample();

			for(int j=0;j<row.length;j++){

				if(j==classifierColumn){
					//see if the bucket exists already
					//String currBucketValue = row[j];
					switch(classModel){
						case "ends":

							break;

						case "match":
							if(allBuckets.indexOf(row[j]) == -1){
								allBuckets.add(row[j]);
							} 
							break;
						case "divide": 
							break;
					}
				}else{
					//it's an input column!

				}
			}
		}

		//Step 2: Fit the buckets into Classifiers
		HashMap< String, ArrayList<Integer> > bucketIndexes = new HashMap< String,ArrayList<Integer> >();

		//Step 3: Count all unique x[i] occurrences and store them.

		for(int i=0;i<dataset.length;i++){
			//break up the data into sets of the buckets
			String currBucket = dataset[i].getSampleColumn(classifierColumn);
			bucketIndexes[bucketLabels.indexOf(currBucket)] += 1;
		}

		return null;
	}


	public void trainModel(DataRow[] dataset, int classifierColumn){
		//loop over dataset
			//for each row's cell, record a count of each occurrence of the values 
			//separate into different buckets (or Outcome<String> instances) depending on the column that is the classifier (default, this is the last column)
			//put each chance of Bucket and chance of column being a value into the probDists structure
			//problems? we have this algorithm in our notes!!!

		HashMap< String, ArrayList<Integer> > levels = new HashMap< String, ArrayList<Integer> >();

		for(int i=0;i<dataset.length; i++){

			String[] row = dataset[i];

			if(levels.containsKey( row[classifierColumn] ) == false){
				levels.put( row[classifierColumn], (new ArrayList<Integer>()).add(i) );
			}else{
				levels.get( row[classifierColumn] ).add(i);
			}

			for(int j=0; j< row.length; j++){
				if(j == classifierColumn){ continue; }

				//TODO: finish idea here... if probDist[row[classifierColumn]]
				//TODO: clean up the spaghetti... holy crap...
				if( this.probDists.get(j).containsKey( row[j] ) == false){
					this.probDists.get(j).put( row[j], (levels.get(row[classifierColumn]).size()) );
				}else{
					int oldentry = this.probDists.get(j).get(row[j]);
					this.probDists.get(j).put( row[j], oldentry.length++ );
				}
			}
			
			//if()

		}


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