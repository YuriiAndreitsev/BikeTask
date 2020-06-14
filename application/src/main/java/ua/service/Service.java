package ua.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ListIterator;

import ua.App;
import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;

public class Service {
	public void showAllBikesFromFile() {
		Parser parser = new Parser();
		parser.parseTXTFile();
		List<Bike> bikes = parser.getAllBikes();

		ListIterator<Bike> iterator = bikes.listIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public byte[] bikeToText(Bike bike) {
		String b = "";
		String delimiter = "; ";
		if (bike instanceof Speedelec) {
			b = "SPEEDELEC " + bike.getBrand() + delimiter + bike.getMaxSpeed() + delimiter + bike.getWeight()
					+ delimiter + bike.isLights() + delimiter + bike.getmAh() + delimiter + bike.getColor() + delimiter
					+ bike.getPrice() + "\n";
		} else if (bike instanceof EBike) {
			b = "E-BIKE " + bike.getBrand() + delimiter + bike.getMaxSpeed() + delimiter + bike.getWeight() + delimiter
					+ bike.isLights() + delimiter + bike.getmAh() + delimiter + bike.getColor() + delimiter
					+ bike.getPrice() + "\n";
		} else {
			b = "FOLDING BIKE " + bike.getBrand() + delimiter + bike.getSizeOfWheels() + delimiter
					+ bike.getNumberOfGears() + delimiter + bike.getWeight() + delimiter + bike.isLights() + delimiter
					+ bike.getColor() + delimiter + bike.getPrice() + "\n";
		}
		System.out.println(bike instanceof FoldingBike);
		System.out.println(b);
		return b.getBytes();
	}

	public void writeToFile(Bike bike) {
		try {
//			URI uri = this.getClass().getClassLoader().getResource("ecobike2.txt").toURI();
			Path p =Paths.get(Thread.currentThread().getContextClassLoader().getResource("ecobike2.txt").toURI());
			System.out.println(p);
//		Path p = Paths.get(uri);
//			Files.write(Paths.get("E:\\EcoBike\\application\\src\\main\\java\\resources\\ecobike2.txt"), bikeToText(bike), StandardOpenOption.APPEND);
			Files.write(p, bikeToText(bike), StandardOpenOption.APPEND);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
