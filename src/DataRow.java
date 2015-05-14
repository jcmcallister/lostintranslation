import java.util.ArrayList;
import interfaces.DataSample;


public class DataRow implements DataSample<String> {

	private ArrayList<String> sample;//each string is a cell of the spreadsheet row?
	
	@Override
	public boolean setSample(String input) {
		boolean res = true;
		try{
			int i=0, lastCut = 0;
			String inputDelim = "\t" ;//could also be "," for CSV
			/*while(i < input.length()){
				Character x = new Character(input.charAt(i));
				if( x.toString().equals(inputDelim)){
					sample.add( input.substring(lastCut, i) );//assign the cell.
				}
				i++;
			}*/
			
			
			//assign each cell value to the sample ArrayList
			int cutHere = input.indexOf(inputDelim); 
			while(cutHere != -1){
				sample.add( input.substring( i, cutHere ) );
				i = cutHere;
				cutHere = input.indexOf(inputDelim, i+1);
			}
			
			
		}catch(Exception e){
			res = false;
			System.out.println("An error has occurred!\n");
			e.printStackTrace(System.out);
		}
		return res;
	}

	@Override
	public ArrayList<String> getSample() { return sample; }

	public String getSampleColumn(int i){ return sample.get(i); }

	
	

}
