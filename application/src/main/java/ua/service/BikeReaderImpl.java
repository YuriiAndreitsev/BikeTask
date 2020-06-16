package ua.service;

import ua.model.Bike;
import ua.model.FoldingBike;
import ua.model.EBike;
import ua.model.Speedelec;

public class BikeReaderImpl implements BikeReader {
	public final static String SPEEDELEC = "SPEEDELEC";
	public final static String EBIKE = "E-BIKE";
	public final static String FOLDINGBIKE = "FOLDING BIKE";

	@Override
	public Bike readBikeFromTXT(String line) {
		if (line.startsWith(FOLDINGBIKE)) {
			readFoldingBike(line);
		}
		if (line.startsWith(SPEEDELEC)) {
			return readSpedelecBike(line);
		} else if (line.startsWith(EBIKE)) {
			return readEBike(line);
		} else {
			return readFoldingBike(line);
		}
	}

	public Bike readSpedelecBike(String line) {
		Speedelec bike = new Speedelec();

		String[] tokens = line.split(";");

		bike.setBrand(tokens[0].substring(10));
		bike.setMaxSpeed(Integer.valueOf(tokens[1].trim()));
		bike.setWeight(Integer.valueOf(tokens[2].trim()));
		bike.setLights(Boolean.parseBoolean((tokens[3].trim())));
		bike.setmAh(Integer.valueOf(tokens[4].trim()));
		bike.setColor(tokens[5].trim());
		bike.setPrice(Integer.valueOf(tokens[6].trim()));
		return bike;
	}

	public Bike readEBike(String line) {
		EBike bike = new EBike();
		String[] tokens = line.split(";");

		bike.setBrand(tokens[0].substring(7));
		bike.setMaxSpeed(Integer.valueOf(tokens[1].trim()));
		bike.setWeight(Integer.valueOf(tokens[2].trim()));
		bike.setLights(Boolean.parseBoolean(tokens[3].trim()));
		bike.setmAh(Integer.valueOf(tokens[4].trim()));
		bike.setColor(tokens[5].trim());
		bike.setPrice(Integer.valueOf(tokens[6].trim()));
		return bike;
	}

	public Bike readFoldingBike(String line) {
		FoldingBike bike = new FoldingBike();
		String[] tokens = line.split(";");

		bike.setBrand(tokens[0].substring(13));
		bike.setSizeOfWheels(Integer.valueOf(tokens[1].trim()));
		bike.setNumberOfGears(Integer.valueOf(tokens[2].trim()));
		bike.setWeight(Integer.valueOf(tokens[3].trim()));
		bike.setLights(Boolean.parseBoolean(tokens[4].trim()));
		bike.setColor(tokens[5].trim());
		bike.setPrice(Integer.valueOf(tokens[6].trim()));
		return bike;
	}

}
