package ua.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;

import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;

public class SearchService implements Callable<Bike> {
	private boolean confirm = false;
	private int typeOfBike = 0;
	private Boolean fieldSearchBy = null;
	Bike bike;
	Map<String, Object> selectedParametersForSearch = new HashMap<>();
	Collection<Comparator<Bike>> comparators = new ArrayList<Comparator<Bike>>();
	BikeComparatorsMap map = new BikeComparatorsMap();
//	private Scanner sc;
	private List<Bike> allBikes;

	public SearchService(List<Bike> allBikes) {
//		this.sc = sc;
		this.allBikes = allBikes;
	}

	public Bike searchBike(Scanner sc, List<Bike> allBikes) {
		Bike result = null;
		while (!confirm) {
			resolveBikeType(sc);
			fieldsToSearchBy(sc);
			showSelectedBikeFieldsForSearch(sc);
			confirm = true;
		}
		return result;
	}

	public void resolveBikeType(Scanner sc) {
		System.out.println(
				"Define the type of bike:\n" + "1 - Folding Bike;\n" + "2 - E-Bike;\n" + "3 - Speedelec Bike;\n");
		while (typeOfBike <= 0) {
			String maxSpeedInput = sc.next();
			try {
				typeOfBike = (Integer.valueOf(maxSpeedInput));
			} catch (NumberFormatException ex) {
				System.out.println(
						"You have not entered a number.\nPlease, select option 1/2/3 depending on your choice\n");
			}
		}
		switch (typeOfBike) {
		case 1:
			bike = new FoldingBike();
			break;
		case 2:
			bike = new EBike();
			break;
		case 3:
			bike = new Speedelec();
			break;
		}

	}

	public void fieldsToSearchBy(Scanner sc) {
		Field[] fields = bike.getClass().getDeclaredFields();

		for (Field field : fields) {
			System.out.println("Would you like you include search by " + field.getName() + "? (Y / N)\n");
			while (fieldSearchBy == null) {
				String lightAnswer = sc.next();
				if (lightAnswer.equalsIgnoreCase("y")) {
					fieldSearchBy = true;
					defineParameterToSearchFor(sc, field);
					System.out.println(bike);
				} else if (lightAnswer.equalsIgnoreCase("n")) {
					fieldSearchBy = false;
				} else {
					System.out.println("Please, enter  ( Y / N )  (not) to include this parameter in search\n");
				}
			}
			fieldSearchBy = null;
		}
	}

	public void defineParameterToSearchFor(Scanner sc, Field field) {
		System.out.println("Set the parameter");

		field.setAccessible(true);
		String fieldName = field.getName();
		int temp = 0;
		Boolean bool = null;
		Object result = new Object();

		if (field.getType().equals(int.class)) {
			while (temp <= 0) {
				String input = sc.next();
				try {
					temp = Integer.valueOf(input);
				} catch (NumberFormatException ex) {
					System.out.println("You have not entered a number.\nPlease, enter an integer value\n");
				}
			}
			result = temp;
		} else if (field.getType().equals(boolean.class)) {
			while (bool == null) {
				System.out.println("Type 'y' if lights are available  or  'n' if bike has no lights");
				String input = sc.next();
				if (input.equalsIgnoreCase("y")) {
					bool = true;
				} else if (input.equalsIgnoreCase("n")) {
					bool = false;
				} else {
					System.out.println(
							"You have not entered a letter.\nPlease, enter ( Y / N ) (not) to include this parameter in search\n");
				}
			}
			result = bool;
		} else {
			result = sc.next();
		}
		try {
			field.set(bike, result);
			selectedParametersForSearch.put(fieldName, result);
			comparators.add(map.getComparatorByName(fieldName));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void showSelectedBikeFieldsForSearch(Scanner sc) {
		if (comparators.isEmpty()) {
			Boolean bool = null;
			System.out.println("I see, you havent selected anything, would you like to cancel search? ( Y / N )");
			while (bool == null) {
				String input = sc.next();
				if (input.equalsIgnoreCase("y")) {
//					bool = true;
					return;
				} else if (input.equalsIgnoreCase("n")) {
					bool = false;
				} else {
					System.out.println("You have not entered a letter.\nPlease, enter ( Y / N )\n");
				}
			}
			System.out.println(
					"If you still want to search a bike, enter at least brand name\nPlease, select at least a name of brand.");
			while (bike.getBrand() == null) {
				bike.setBrand(sc.next());
			}
		} else {
			System.out.println(" You have selected the next parameters:\n");
			selectedParametersForSearch.forEach((k, v) -> {
				System.out.println(k + " : " + v);
			});
		}
	}

	public static Comparator<Bike> createChainComparator(Collection<Comparator<Bike>> comparators) {
		return comparators.stream().reduce((o1, o2) -> 0, Comparator::thenComparing);
	}

	public Bike searchBikeBySelectedParameters(List<Bike> bikeList) {
		Comparator<Bike> bikeComp = createChainComparator(comparators);
		Collections.sort(bikeList, bikeComp);
		int index = Collections.binarySearch(bikeList, bike, bikeComp);
		if (index < 0) {
			System.out.println("Sorry, nothing is found");
			return null;
		} else {
			System.out.println(bikeList.get(index));
			return bikeList.get(index);
		}
	}

	@Override
	public Bike call() throws Exception {
		return searchBikeBySelectedParameters(allBikes);
	}

}
