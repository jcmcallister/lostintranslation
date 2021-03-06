import java.util.ArrayList;

import interfaces.Outcome;


public class Classifier implements Outcome<Double> {

	private Double value;
	private Double minValue;
	private Double maxValue;
	private String label;
	private ArrayList<Integer> rowIndices;
	
	
	@Override
	public String toString() {
		return getValue() + getLabel();
	}

	@Override
	public void setValue(Double input) {
		this.value = input;
	}

	@Override
	public Double[] getValue() {
		// returns an array. if there's a single value, the returned array has length 1.
		// if a range of values, returned array has length 2.
		
		boolean valuesWithRange = (this.value == null);
		int arrayLen = 1;
		Double[] result;
		
		if(valuesWithRange){
			arrayLen++;
			result = new Double[arrayLen];
			result[0] = this.minValue;
			result[1] = this.maxValue;
		}else{
			result = new Double[arrayLen];
			result[0] = this.value;
		}
		
		return result;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public void setLabel(String lbl) {
		// TODO Auto-generated method stub
		this.label = lbl;
	}
	
	public Double getMinValue(){
		return this.minValue;
	}
	
	public Double getMaxValue(){
		return this.maxValue;
	}

	public ArrayList<Integer> getRowIndices() {
		return rowIndices;
	}

	public void setRowIndices(ArrayList<Integer> rowIndices) {
		this.rowIndices = rowIndices;
	}
	
	public void addRowIndex(Integer i){
		this.rowIndices.add(i);
	}

	public boolean removeRowIndex(Integer i){
		boolean res = false;
		if(this.rowIndices.contains(i)){
			res = this.rowIndices.remove(i);
		}
		return res;
	}
	
}
