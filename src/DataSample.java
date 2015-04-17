import com.opencsv.CSVReader;

public interface DataSample<T> {
	
	//bean functions
	public boolean setSample(T input);
	public T getSample();
	//needs more bean CRUD functions
	
	
	//actual good stuff
	public T readSampleData(CSVReader csv); 
	
}
