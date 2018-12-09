abstract class Car {
	
	private String make, model;
	private double weight;
	private int year;
	
	public Car() {
		
	}
	
	//Setters
	public void setMake(String makeIn) {
		make = makeIn;
	}
	public void setModel(String mod){
		model = mod;
	}
	public void setWeight(double weightIn) {
		weight = weightIn;
	}
	public void setYear(int yearIn) {
		year = yearIn;
	}
	
	//Getters
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}
	public double getWeight() {
		return weight;
	}
	public int getYear() {
		return year;
	}
}