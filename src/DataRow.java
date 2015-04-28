import com.opencsv.CSVReader;


public class DataRow implements DataSample<String> {

	private String sample;
	
	@Override
	public boolean setSample(String input) {
		boolean res = true;
		try{
			sample = input;
		}catch(Exception e){
			res = false;
			System.out.println("An error has occurred!\n");
			e.printStackTrace(System.out);
		}
		return res;
	}

	@Override
	public String getSample() { return sample; }

	@Override
	public String readSampleData(CSVReader csv) {
		// TODO use CSVReader to read a given csv object
		//this method may not belong here... could be better served in Controller.main()?
		return null;
	}
	
	

}
