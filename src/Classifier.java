import interfaces.Outcome;


public class Classifier implements Outcome<Double> {

	private Double value;
	private Double minValue;
	private Double maxValue;
	private String label;
	
	
	@Override
	public String toString(Outcome<Double> obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Double input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValue(String input) {
		// TODO Auto-generated method stub

	}

	@Override
	public Double getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLabel(String label) {
		// TODO Auto-generated method stub

	}

}
