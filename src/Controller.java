import com.opencsv.CSVReader;


public class Controller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//read in TRAINING data set, a list of phrases, and create an output file
		//get their translations and record them if we don't have them already
			//check if CSV output file exists
				//if not, create an empty one
		
			//for each phrase
				//check if ENG phrase is in the table
				//if so, skip
				//else
					//declare string for the the CSV row to be inserted, start with ENG phrase
					//find the RUS phrase from ENG using the Translator
					//concat the RUS phrase onto our new CSV Row
					//find the ENG phrase from our generated RUS phrase
					//concat the ENG->RUS->ENG phrase onto the new CSV row
					//compare the ENG->RUS->ENG phrase to the ENG input phrase as a ratio of lengths
					//concat the length ratio to the row of output data file
		
		
		//Create our 3 Classifier instances
			//y = 0, 100% correct translation (no change in length going from ENG -> RUS -> ENG
			//0 < y < 1, partial length ratio changes detected
			//y = 1, 0% correct translation 
		
		//Read in the cleaned training set file from above
			//store row numbers pertaining to each class into instances of Classifier
		
		
		
		//Pass clean training set into NaiveBC :: trainModel()
		//Model m = trainModel( allData, -1 )
		  
		        
       /* while( waitingForInput ){
            //...twiddle thumbs...

            if(input comes){
                input = parse input into a DataSample type
                relAccuracy = getDecision( m, input );
                //do DB things here, maybe save accuracy/input
                //do fancy UI & output things here
                //show main menu or some control mech for inf. wait loop
            }
        }*/
		
	}
	
	public static DataRow[] readSampleData(String filepath) {
		// TODO use CSVReader to read a given csv object
		CSVReader cr = new CSVReader();
		DataRow[] result = new DataRow()[];
		
		//this method may not belong here... could be better served in Controller.main()?
		return result;
	}

}
