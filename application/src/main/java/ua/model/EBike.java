package ua.model;

import java.io.Serializable;

public class EBike extends Bike implements Serializable{
	/*
	 * An e-bike is characterized by: - A brand - The maximum speed (in km/h) - The
	 * weight of the e-bike (in grams) - The availability of lights at front and
	 * back (TRUE/FALSE) - The battery capacity (in mAh) - A color - The price
	 */
	private String brand;
	private int maxSpeed;
	private int weight;
	private boolean lights;
	private int mAh;
	private String color;
	private int price;

	public EBike() {
		super();
		// TODO Auto-generated constructor stub
	}

public EBike(String brand, int maxSpeed, int weight, boolean lights, int mAh, String color, int price) {
		super();
		this.brand = brand;
		this.maxSpeed = maxSpeed;
		this.weight = weight;
		this.lights = lights;
		this.mAh = mAh;
		this.color = color;
		this.price = price;
	}

//	public EBike(String brand, String color, int price, int weight, int maxSpeed, boolean lights, int mAh) {
//		super();
//		this.brand = brand;
//		this.color = color;
//		this.price = price;
//		this.weight = weight;
//		this.maxSpeed = maxSpeed;
//		this.lights = lights;
//		this.mAh = mAh;
//	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (lights ? 1231 : 1237);
		result = prime * result + mAh;
		result = prime * result + maxSpeed;
		result = prime * result + price;
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
		EBike other = (EBike) obj;
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
		if (mAh != other.mAh)
			return false;
		if (maxSpeed != other.maxSpeed)
			return false;
		if (price != other.price)
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
		return "E-BIKE " + brand + " with " + mAh + " mAh battery and" + light + " head/tail light.\nPrice: "
				+ getPrice() + "euros.\n";
	}

}
