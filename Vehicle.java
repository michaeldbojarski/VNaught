
abstract class Car {
	
	private String make, model;
	private double weight;
	private int year;
	
	public Car()
	//Setters
	/**
	*Sets the make of the current vehicle
	*$param makeIn The make of the current vehicle
	*/
	public void setMake(String makeIn) {
		make = makeIn;
	}
	/**
	*Sets the current vehicle model
	*@param mod The model of the current vehicle
	*/
	public void setModel(String mod){
		model = mod;
	}
	/**
	*Sets the weight
	*/
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
	
	
	//abstract public double convertWeight(double weightIn, )
}