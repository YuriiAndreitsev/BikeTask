package ua.utilities;

import java.lang.reflect.Field;

import ua.model.Bike;
import ua.model.FoldingBike;
import ua.model.EBike;
import ua.model.Speedelec;

public class BikeReaderImpl implements BikeReader {
	public final static String SPEEDELEC = "SPEEDELEC";
	public final static String EBIKE = "E-BIKE";
	public final static String FOLDINGBIKE = "FOLDING BIKE";
	Bike bike;

	@Override
	public Bike readBikeFromTXT(String line) {

		if (line.startsWith(FOLDINGBIKE)) {
			bike = new FoldingBike();
			return readBike(line, bike, FOLDINGBIKE.length() + 1);
//			return readFoldingBike(line);
		} else if (line.startsWith(EBIKE)) {
			bike = new EBike();
			return readBike(line, bike, EBIKE.length() + 1);
//			return readEBike(line);
		} else {
			bike = new Speedelec();
			return readBike(line, bike, SPEEDELEC.length() + 1);
//			return readSpeedelecBike(line);
		}

	}

	public Bike readBike(String line, Bike bike, int brand) {
		Field[] fields = bike.getClass().getDeclaredFields();
		String[] tokens = line.split(";");
		int i = 1;
		for (Field field : fields) {
			
			field.setAccessible(true);
			try {
				if (field.getName().equals("brand")) {
//					bike.setBrand(tokens[0].substring(brand).trim());
					bike.setBrand(tokens[0].substring(brand).trim());

				} else {
					if (field.getType().equals(int.class)) {
						field.set(bike, Integer.valueOf(tokens[i].trim()));
					} else if (field.getType().equals(boolean.class)) {
						field.set(bike, Boolean.parseBoolean(tokens[i].trim()));
					} else {
						field.set(bike, tokens[i].trim());
					}
					i++;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return bike;
	}

	
	//GOOD IDEAS COME DURING HARD WORK. 
	//THIS TRASH BELOW WAS MY CODE ON THE START OF DEVELOPMENT
	//I KNOW THAT CODE DUPLICATING IS BAD, SO I TRIED TO FIND ANOTHER WAYS.
	//BUT STILL THERE ARE MUCH MORE WAYS TO DO A TASK BETTER
	
//	public Bike readSpeedelecBike(String line) {
//		bike = new Speedelec();
//
//		String[] tokens = line.split(";");
//
//		bike.setBrand(tokens[0].substring(SPEEDELEC.length() + 1));
//		bike.setMaxSpeed(Integer.valueOf(tokens[1].trim()));
//		bike.setWeight(Integer.valueOf(tokens[2].trim()));
//		bike.setLights(Boolean.parseBoolean((tokens[3].trim())));
//		bike.setmAh(Integer.valueOf(tokens[4].trim()));
//		bike.setColor(tokens[5].trim());
//		bike.setPrice(Integer.valueOf(tokens[6].trim()));
//		return bike;
//	}
//
//	public Bike readEBike(String line) {
//		bike = new EBike();
//		String[] tokens = line.split(";");
//
//		bike.setBrand(tokens[0].substring(EBIKE.length() + 1));
//		bike.setMaxSpeed(Integer.valueOf(tokens[1].trim()));
//		bike.setWeight(Integer.valueOf(tokens[2].trim()));
//		bike.setLights(Boolean.parseBoolean(tokens[3].trim()));
//		bike.setmAh(Integer.valueOf(tokens[4].trim()));
//		bike.setColor(tokens[5].trim());
//		bike.setPrice(Integer.valueOf(tokens[6].trim()));
//		return bike;
//	}
//
//	public Bike readFoldingBike(String line) {
//		bike = new FoldingBike();
//		String[] tokens = line.split(";");
//
//		bike.setBrand(tokens[0].substring(FOLDINGBIKE.length() + 1));
//		bike.setSizeOfWheels(Integer.valueOf(tokens[1].trim()));
//		bike.setNumberOfGears(Integer.valueOf(tokens[2].trim()));
//		bike.setWeight(Integer.valueOf(tokens[3].trim()));
//		bike.setLights(Boolean.parseBoolean(tokens[4].trim()));
//		bike.setColor(tokens[5].trim());
//		bike.setPrice(Integer.valueOf(tokens[6].trim()));
//		return bike;
//	}
}
