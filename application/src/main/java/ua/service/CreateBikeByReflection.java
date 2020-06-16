package ua.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ua.model.Bike;

public class CreateBikeByReflection {

	private static final String BRAND = "Enter the name of brand\n";
	private static final String COLOR = "Enter the color of a bike\n";
	private static final String GEARS = "Enter the number of gears (integer value)\n";
	private static final String LIGHTS = "Is the lights availiable ( Y / N )?\n";
	private static final String MAH = "Enter the battery capacity (in mAh)\n";
	private static final String MAXSPEED = "Enter the max speed (in km/h)\n";
	private static final String PRICE = "Enter the price\n";
	private static final String SIZEOFWHEELS = "Enter the size of wheels (integer value in inches)\n";
	private static final String WEIGHT = "Enter the weight (integer value in grams)";
	Map<String, String> fieldMessage = new HashMap<String, String>();
	private boolean create = false;
	private boolean confirmCreate = false;
	private Boolean cancelBikeCreation = null;
	Map<String, Object> bikeReview = new HashMap<>();
	private List<Bike> newBikes = new ArrayList<Bike>();

	public List<Bike> getNewBikes() {
		return newBikes;
	}

	public CreateBikeByReflection() {
		fieldMessage.put("brand", BRAND);
		fieldMessage.put("color", COLOR);
		fieldMessage.put("gears", GEARS);
		fieldMessage.put("lights", LIGHTS);
		fieldMessage.put("mAh", MAH);
		fieldMessage.put("maxSpeed", MAXSPEED);
		fieldMessage.put("price", PRICE);
		fieldMessage.put("wheels", SIZEOFWHEELS);
		fieldMessage.put("weight", WEIGHT);
	}

	public void createBike(Scanner sc, Bike bike) {
		while (!create) {
			Field[] fields = bike.getClass().getDeclaredFields();
			for (Field field : fields) {
				String message = fieldMessage.get(field.getName());
				defineParameter(sc, message, field, bike);
			}
			confirmCreate = false;
			while (!confirmCreate) {
				createBikeYorN(sc, bike);
			}
		}
		newBikes.add(bike);
		System.out.println("Bike has been CREATED\n");
		create = false;
	}

	public void defineParameter(Scanner sc, String message, Field field, Bike bike) {
		System.out.println(message);
		field.setAccessible(true);
		int temp = 0;
		Boolean bool = null;
		Object result = new Object();
		if (field.getType().equals(int.class)) {
			while (temp <= 0) {
				String input = sc.next();
				try {
					temp = Integer.valueOf(input);
				} catch (NumberFormatException ex) {
					System.out.println("You are inserting incorrect data, try again\n" + message);
				}
			}
			result = temp;
		} else if (field.getType().equals(boolean.class)) {
			while (bool == null) {
				System.out.println("  Type 'y'  or  'n'");
				String input = sc.next();
				if (input.equalsIgnoreCase("y")) {
					bool = true;
				} else if (input.equalsIgnoreCase("n")) {
					bool = false;
				} else {
					System.out.println("You are inserting incorrect data, try again\n" + message);
				}
			}
			result = bool;
		} else {
			result = sc.next();
		}
		try {
			field.set(bike, result);
			bikeReview.put(field.getName(), result);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void createBikeYorN(Scanner scanner, Bike bike) {
		bikeReview.forEach((k, v) -> {
			System.out.println(k + " : " + v);
		});
		System.out.println("Here is what we've created. Is everything okay? ( Y / N )\n");
		String usersAnswerForBikeCreation = scanner.next();

		if (usersAnswerForBikeCreation.equalsIgnoreCase("y")) {
			confirmCreate = true;
			create = true;

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
			} else if (cancelBikeCreationAnswer.equalsIgnoreCase("n")) {
				cancelBikeCreation = false;
				System.out.println("Okay, let's try again!");
			} else {
				System.out.println(
						"You have not entered a letter.\nPlease, enter a letter to cancel bike creation or try once again( Y / N )\n");
			}
		}
	}
}
