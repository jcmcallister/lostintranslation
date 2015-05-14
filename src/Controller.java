import java.io.*;
import java.util.ArrayList;

import com.googler.Translator;
import com.opencsv.CSVReader;


public class Controller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//read in TRAINING data set, a list of phrases, and create an output file
		
		boolean trainingOK = getTrainingData("phrases.csv","trainingset.csv");
		System.out.println("Training Set Created?\t" + trainingOK);
		
		/*containsPhrase test!
		 * File f = new File("phrases.csv");
		if(f.exists()){
			String test = "hamb";
			boolean phraseExists = containsPhrase(f.getAbsolutePath(), test);
			System.out.println("File contains '" + test +  "'\t" + phraseExists);
		}else{
			System.out.println("File not found in main()!");
		}*/
		
		
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
	
	public static boolean getTrainingData(String filename, String output) {
		// create a CSV object
		File csv = new File(filename);
		File outfile = new File(output);
		boolean result = false, outfileIsNew = false;
		ArrayList<String> phrases = new ArrayList<String>();
		String newCSVContent = "",
				translation = "";
		Translator impl = new Translator("jcmcallister", "03.316746141:7fd1f25456fe5a4d6e318064528967b8", "ru");
		int resultCount = 0;
		
		
		
		
		//if output doesn't exist, make a blank one
		if(outfile.exists() == false){
			try {
				outfile.createNewFile();
				outfileIsNew = true;
				System.out.println("Output file not found, creating " + output);
			} catch (IOException e) {
				System.out.println("Could not create a new output file!!!");
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		//phrase file exists!
		if(csv.exists()){
			 CSVReader reader = null;
			try {
				reader = new CSVReader(new FileReader(filename));
			} catch (FileNotFoundException e) {
				System.out.println("Error finding phrase file!");
				e.printStackTrace();
				System.exit(0);
			}
		     String [] nextLine;
		     
		     try {
				while ((nextLine = reader.readNext()) != null) { 
					 phrases.add(nextLine[0]);
				    // nextLine[] is an array of comma-separated values from the line
					 System.out.println("READ phrase:\t" + nextLine[0]);
				    //System.out.println(nextLine[0] +", " + nextLine[3] + ", etc...");
				 }
			} catch (IOException e) {
				System.out.println("Error reading CSV phrase file!");
				e.printStackTrace();
				System.exit(0);
			}
		     
		     try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Error closing CSV phrase file reader!");
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		for(int i=0;i<phrases.size();i++){
			
			//check if new phrase is inside the outfile
			String thisPhrase = phrases.get(i);
			
			if(outfileIsNew == false){
				try{
					//check outfile for the phrase
					boolean phraseExists = containsPhrase(outfile.getAbsolutePath(), thisPhrase);
					
					//if containsPhrase is false, add the new phrase to outfileContent String!
					//outfileContent is in CSV format, with columns:
					//	eng phrase, phrase length, discretized length, RUS phrase, ENG>RUS>ENG phrase, results count, results count / 200k groupings 
					if(!phraseExists){
						newCSVContent.concat(thisPhrase + ",");//the original ENG phrase
						newCSVContent.concat(thisPhrase.length() + ",");//orig phrase length
						newCSVContent.concat(thisPhrase.length()/10 + ",");//discretized phrase length
						
						System.out.println("\nAdding metrics for '" + thisPhrase + "' to " + output);
						
						translation = impl.getTranslation(thisPhrase,"ru");//goes from ENG to RUS
						newCSVContent.concat(translation + ",");
						
						System.out.print("Metrics: '" + thisPhrase + "' -> RUS -> '" + translation);
						
						translation = impl.getTranslation(translation, "en");//goes from RUS to ENG
						newCSVContent.concat(translation + ",");
						
						System.out.print("' -> ENG '" + translation);
						
						resultCount = impl.getQuery(translation);//number of results
						newCSVContent.concat(resultCount + ",");
						
						System.out.print("' , with " + resultCount + " results, inside of discrete group # ");
						
						newCSVContent.concat(resultCount/200000 + ",\n");//discretized result count, in 200k groups
						
						System.out.print(resultCount/200000 + "\n");
						
						
					}
				}catch(Exception e){
					System.out.println("Error reading CSV phrase file!");
					e.printStackTrace();
					System.exit(0);
				}
			}else{
				newCSVContent.concat(thisPhrase + ",");//the original ENG phrase
				newCSVContent.concat(thisPhrase.length() + ",");//orig phrase length
				newCSVContent.concat(thisPhrase.length()/10 + ",");//discretized phrase length
				
				System.out.println("Adding metrics for '" + thisPhrase + "' to " + output);
				
				translation = impl.getTranslation(thisPhrase,"ru");//goes from ENG to RUS
				newCSVContent.concat(translation + ",");
				
				System.out.print("Metrics: '" + thisPhrase + "' -> RUS -> '" + translation);
				
				translation = impl.getTranslation(translation, "en");//goes from RUS to ENG
				newCSVContent.concat(translation + ",");
				
				System.out.print("' -> ENG '" + translation);
				
				resultCount = impl.getQuery(translation);//number of results
				newCSVContent.concat(resultCount + ",");
				
				System.out.print("' , with " + resultCount + " results, inside of discrete group # ");
				
				newCSVContent.concat(resultCount/200000 + ",\n");//discretized result count, in 200k groups
				
				System.out.print(resultCount/200000 + "\n");
			}
		}
		
		//write content to file
		try{
			
			System.out.println("DEBUG: would have written this to file:\n\n" + newCSVContent);
			/*FileWriter fw = new FileWriter(outfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			//check if 
			
			
			//bw.write("foo");
			bw.close();*/
			
		}catch(Exception e){
			System.out.println("Error reading writing to training set output file @\t" + outfile.getAbsolutePath());
			e.printStackTrace();
			System.exit(0);
		}
		
		return result;
	}
	
	public static boolean containsPhrase(String filepath, String searchterm){
		boolean found = false;
		
		//checks if the searchterm is inside of the output file
		try {
            BufferedReader in = new BufferedReader(new FileReader(filepath));
            String str;
            //str = in.readLine();
            while ((str = in.readLine()) != null) {
            	if(str.equals(searchterm + ",")){
            		found = true;
            		break;
            	}
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
		
		return found;
	}
	
	/*public static String[][] readSampleData(String filepath) {
		// TODO use CSVReader to read a given csv object
		CSVReader cr = new CSVReader();
		DataRow[] result = new DataRow()[];
		
		//this method may not belong here... could be better served in Controller.main()?
		return result;
	}*/

}
