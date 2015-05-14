package interfaces;

import java.util.ArrayList;

import com.opencsv.CSVReader;

public interface DataSample<T> {
	
	//bean functions
	public boolean setSample(T input);
	public ArrayList<T> getSample();
	public T getSampleColumn(int i);
	//needs more bean CRUD functions
	
	
	//actual good stuff
	//public T readSampleData(CSVReader csv); //moved to Controller, no interface method needed
	
}
