package ua.model;

import java.io.Serializable;

public class FoldingBike extends Bike implements Serializable{
	/*
	 * A folding bike is characterized by: - A brand - The size of the wheels (in
	 * inch) - The number of gears - The weight of the bike (in grams) - The
	 * availability of lights at front and back (TRUE/FALSE) - A color - The price
	 */
	private String brand;
	private int wheels;
	private int gears;
	private int weight;
	private boolean lights;
	private String color;
	private int price;

	public FoldingBike(String brand, int sizeOfWheels, int numberOfGears, int weight, boolean lights, String color,
			int price) {
		super();
		this.brand = brand;
		this.wheels = sizeOfWheels;
		this.gears = numberOfGears;
		this.weight = weight;
		this.lights = lights;
		this.color = color;
		this.price = price;
	}

	public FoldingBike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isLights() {
		return lights;
	}

	public void setLights(boolean lights) {
		this.lights = lights;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSizeOfWheels() {
		return wheels;
	}

	public void setSizeOfWheels(int sizeOfWheels) {
		this.wheels = sizeOfWheels;
	}

	public int getNumberOfGears() {
		return gears;
	}

	public void setNumberOfGears(int numberOfGears) {
		this.gears = numberOfGears;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (lights ? 1231 : 1237);
		result = prime * result + gears;
		result = prime * result + price;
		result = prime * result + wheels;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoldingBike other = (FoldingBike) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (lights != other.lights)
			return false;
		if (gears != other.gears)
			return false;
		if (price != other.price)
			return false;
		if (wheels != other.wheels)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String light = "";
		if (!isLights()) {
			light = " no";
		}
		return "FOLDING BIKE " + brand + " with " + getNumberOfGears() + " gear(s) and" + light
				+ " head/tail light.\nPrice: " + getPrice() + "euros.\n";

	}
}
