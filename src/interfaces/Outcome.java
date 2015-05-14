package interfaces;

public interface Outcome<T> {

		//Outcomes are the DecisionClasses, and in our case should be in this set :
		//		{ Correct (y==0), Incorrect (y==1), or Partially Correct (0>y>1) }
	
	public String toString();
	
	public void setValue(T input);//string input or whatever type you want
	//public void setValue(String input);//parse into desired structure from String?
	public T[] getValue();
	
	public String getLabel();//like "Correct" or "Incorrect"
	public void setLabel(String label);
	
	//END OF BEAN FNS

	//any meaty functions here? 
	//compareTo(Outcome<T> other)?
		//ultimately we'll be comparing Outcome VALUES but not any other Outcome details, I think 
	
}
