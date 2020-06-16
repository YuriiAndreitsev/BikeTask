package ua.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;

public class CreateBikeService {
	private String brand = "";
	private int batteryCapacity = 0;
	private int maxSpeed = 0;
	private int sizeOfWheels = 0;
	private int gearsQuantity = 0;
	private int weight = 0;
	private Boolean lights = null;
	private String color = "";
	private int price = 0;
	private Boolean create = false;
	private boolean confirmCreate = false;
	private Boolean cancelBikeCreation = null;
	private String result;
	private Bike bike;

	private List<Bike> newBikes = new ArrayList<Bike>();

	public List<Bike> getNewBikes() {
		return newBikes;
	}

	public String creationInfoConfirmSpeedelecAndEBike() {
		return result = "\nCheck the bike parameters before creation: \n" + "brand - " + brand + "\n"
				+ "max speed in km/h - " + maxSpeed + "\n" + "weight of the bike in grams - " + weight + "\n"
				+ "lights - " + lightsFromBooleanToText() + "\n" + "battery capacity - " + batteryCapacity + "\n"
				+ "color - " + color + "\n" + "price - " + price + "\nCreate new bike? ( Y / N )";
	}

	public String creationInfoConfirmFoldingBike() {
		return result = "\nCheck the bike parameters before creation: \n" + "brand - " + brand + "\n"
				+ "size of wheels in inches - " + sizeOfWheels + "\n" + "number of gears - " + gearsQuantity + "\n"
				+ "weight of the bike in grams - " + weight + "\n" + "lights - " + lightsFromBooleanToText() + "\n"
				+ "color - " + color + "\n" + "price - " + price + "\nCreate new bike? ( Y / N )";
	}

//	public void defineParameter (Scanner sc , String message, ) {
//		System.out.println("Enter the name of a brand\n");
//		
//	}

	public void defineBrand(Scanner scanner) {
		System.out.println("Enter the name of a brand\n");
		while (brand.isEmpty()) {
			brand = scanner.next();
		}
		if (brand.isEmpty()) {
			System.out.println("Enter a brand name");
		}
	}

	public void defineMaxSpeed(Scanner scanner) {
		System.out.println("Enter the maximum speed of the bike in km/h(Integer value)\n");
		while (maxSpeed <= 0) {
			String maxSpeedInput = scanner.next();
			try {
				maxSpeed = (Integer.valueOf(maxSpeedInput));
			} catch (NumberFormatException ex) {
				System.out.println(
						"You have not entered a number.\nPlease, enter the maximum speed of the bike as an integer value\n");
			}
		}
	}

	public void defineWeight(Scanner scanner) {
		System.out.println("How much does the bikes weight in grams?(Integer value)");

		while (weight <= 0) {
			String weightInput = scanner.next();
			try {
				weight = Integer.valueOf(weightInput);
			} catch (NumberFormatException ex) {
				System.out.println(
						"You have not entered a number.\nPlease, enter the weight of the bike as an integer value\n");
			}
		}
	}

	public void lightsAvailability(Scanner scanner) {
		System.out.println("Does the bike have rear and front lights? (Y/N)");
		while (lights == null) {
			String lightAnswer = scanner.next();
			if (lightAnswer.equalsIgnoreCase("y")) {
				lights = true;
			} else if (lightAnswer.equalsIgnoreCase("n")) {
				lights = false;
			} else {
				System.out.println(
						"You have not entered a letter.\nPlease, enter the availability of lights at front and back as a letter( Y / N )\n");
			}
		}
	}

	public void defineBatteryCapacity(Scanner scanner) {
		System.out.println("What is the battery capacity in mA/h ?(Integer value)");
		while (batteryCapacity <= 0) {
			String batteryCapacityInput = scanner.next();
			try {
				batteryCapacity = Integer.valueOf(batteryCapacityInput);
			} catch (NumberFormatException ex) {
				System.out.println(
						"You have not entered a number.\nPlease, enter the battery capacity as an integer value in mA/h \n");
			}
		}
	}

	public void defineColor(Scanner scanner) {
		System.out.println("What is the color of a bike?");
		Integer flag = null;
		while (color.isEmpty()) {
			color = scanner.next();
			try {
				flag = Integer.valueOf(color);
			} catch (NumberFormatException nfe) {
				flag = null;
			}
			if (flag != null) {
				System.out.println("You were supposed to enter a name of color ( red, green, blue ), try again.");
				color = "";
			}
		}
	}

	public void definePrice(Scanner scanner) {
		System.out.println("Enter the price of the bike (in euros)");
		while (price <= 0) {
			String priceInput = scanner.next();
			try {
				price = Integer.valueOf(priceInput);
			} catch (NumberFormatException ex) {
				System.out.println(
						"You have not entered a number.\nPlease, enter the price of the bike as an integer value\n");
			}
		}
	}

	public String lightsFromBooleanToText() {
		String hasLights = "not available";
		if (lights != null && lights) {
			hasLights = "available";
		}
		return hasLights;
	}

	public void defineSizeOfWheels(Scanner scanner) {
		System.out.println("Define the size of wheels in inches(Integer value)");
		while (sizeOfWheels <= 0) {
			String sizeOfWheelsInput = scanner.next();
			try {
				sizeOfWheels = Integer.valueOf(sizeOfWheelsInput);
			} catch (NumberFormatException ex) {
				System.out.println(
						"You have not entered a number.\nPlease, enter the size of the wheels of the bike as an integer value\n");
			}
		}
	}

	public void defineNumberOfGears(Scanner scanner) {
		System.out.println("Define the number of gears(Integer value)");
		while (gearsQuantity <= 0) {
			String gearsInput = scanner.next();
			try {
				gearsQuantity = Integer.valueOf(gearsInput);

			} catch (NumberFormatException ex) {
				System.out.println(
						"You have not entered a number.\nPlease, enter the number of gears of the bike as an integer value\n");
			}
		}
	}

	public void createBikeYorN(Scanner scanner, String typeOfBike) {
		String usersAnswerForBikeCreation = "";
		if (typeOfBike.equals("e-bike") || typeOfBike.equals("speedelec")) {
			System.out.println(creationInfoConfirmSpeedelecAndEBike());
		} else {
			System.out.println(creationInfoConfirmFoldingBike());
		}
		usersAnswerForBikeCreation = scanner.next();

		if (usersAnswerForBikeCreation.equalsIgnoreCase("y")) {
			confirmCreate = true;
			create = true;
			System.out.println("Bike has been CREATED");
		} else if (usersAnswerForBikeCreation.equalsIgnoreCase("n")) {
			confirmCreate = true;
			cancelBikeCreation(scanner);
		} else {
			System.out.println("Make your decision: to create a bike or not...( Y / N )\n");
		}
	}

	public void cancelBikeCreation(Scanner scanner) {
		System.out.println("Cancel bike creation? (Y/N)");
		while (cancelBikeCreation == null) {
			String cancelBikeCreationAnswer = scanner.next();
			if (cancelBikeCreationAnswer.equalsIgnoreCase("y")) {
				cancelBikeCreation = true;
				create = true;
				confirmCreate = true;
				resetCreationParameters();
			} else if (cancelBikeCreationAnswer.equalsIgnoreCase("n")) {
				cancelBikeCreation = false;
				System.out.println("Okay, let's try again!");
			} else {
				System.out.println(
						"You have not entered a letter.\nPlease, enter a letter to cancel bike creation or try once again( Y / N )\n");
			}
		}
	}

	public void addSpeedelec(Scanner scanner) {
		System.out.println(" >> You are now adding a SPEEDELEC BIKE <<");
		create = false;
		String typeOfBike = "speedelec";
		while (!create) {
			defineBrand(scanner);
			defineMaxSpeed(scanner);
			defineWeight(scanner);
			lightsAvailability(scanner);
			defineBatteryCapacity(scanner);
			defineColor(scanner);
			definePrice(scanner);
			confirmCreate = false;
			while (!confirmCreate) {
				createBikeYorN(scanner, typeOfBike);
			}
		}
//		bike = new Speedelec(brand, color, price, weight, maxSpeed, lights, batteryCapacity);
		newBikes.add(bike);
//		System.out.println(getNewBikes());
//		System.out.println(bike);
		resetCreationParameters();
	}

	public void addEBike(Scanner scanner) {
		System.out.println(" >> You are now adding a E-BIKE <<");

		String typeOfBike = "e-bike";
		create = false;
		while (!create) {
			defineBrand(scanner);
			defineMaxSpeed(scanner);
			defineWeight(scanner);
			lightsAvailability(scanner);
			defineBatteryCapacity(scanner);
			defineColor(scanner);
			definePrice(scanner);
			confirmCreate = false;
			while (!confirmCreate) {
				createBikeYorN(scanner, typeOfBike);
			}
		}
//		bike = new EBike(brand, color, price, weight, maxSpeed, lights, batteryCapacity);
		newBikes.add(bike);
//		System.out.println(bike);
		resetCreationParameters();
	}

	public void addFoldingBike(Scanner scanner) {
		System.out.println(" >> You are now adding a FOLDING BIKE <<");

		String typeOfBike = "foldingbike";
		create = false;
		while (!create) {
			defineBrand(scanner);
			defineSizeOfWheels(scanner);
			defineNumberOfGears(scanner);
			defineWeight(scanner);
			lightsAvailability(scanner);
			defineColor(scanner);
			definePrice(scanner);
			confirmCreate = false;
			while (!confirmCreate) {
				createBikeYorN(scanner, typeOfBike);
			}
		}
		bike = new FoldingBike(brand, sizeOfWheels, gearsQuantity, weight, lights, color, price);
		newBikes.add(bike);
//		System.out.println(bike);
		resetCreationParameters();
	}

	public void resetCreationParameters() {
		brand = "";
		batteryCapacity = 0;
		maxSpeed = 0;
		sizeOfWheels = 0;
		gearsQuantity = 0;
		weight = 0;
		lights = null;
		color = "";
		price = 0;
	}

}
