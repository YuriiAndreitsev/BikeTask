package ua.model;

public abstract class Bike {
	String brand;
	int maxSpeed;
	int weight;
	boolean lights;
	int mAh;
	String color;
	int price;
	int sizeOfWheels;
	int numberOfGears;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isLights() {
		return lights;
	}

	public void setLights(boolean lights) {
		this.lights = lights;
	}

	public int getmAh() {
		return mAh;
	}

	public void setmAh(int mAh) {
		this.mAh = mAh;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSizeOfWheels() {
		return sizeOfWheels;
	}

	public void setSizeOfWheels(int sizeOfWheels) {
		this.sizeOfWheels = sizeOfWheels;
	}

	public int getNumberOfGears() {
		return numberOfGears;
	}

	public void setNumberOfGears(int numberOfGears) {
		this.numberOfGears = numberOfGears;
	}

}
