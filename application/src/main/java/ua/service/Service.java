package ua.service;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import javax.xml.transform.URIResolver;

import ua.App;
import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;

public class Service {
	private String filename;

	public List<Bike> initializeBikeList(String filename) {
		InputStream dataSource = App.class.getClassLoader().getResourceAsStream(filename);
		DataInputStream dis = new DataInputStream(dataSource);
		Scanner scanner = new Scanner(dataSource);
		scanner.useDelimiter("\n");
		BikeReaderImpl reader = new BikeReaderImpl();
		List<Bike> allBikes = new ArrayList<Bike>();
		while (scanner.hasNext()) {
			String line = scanner.next();
			allBikes.add(reader.readBikeFromTXT(line));
		}
		scanner.close();
		return allBikes;

	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

//	public void showAllBikesFromFile() {
//		Parser parser = new Parser();
//		parser.parseTXTFile(filename);
//		List<Bike> bikes = parser.getAllBikes();
//
//		ListIterator<Bike> iterator = bikes.listIterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//	}

	public void showAllBikes(List<Bike> allBikes) {
		ListIterator<Bike> iterator = allBikes.listIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public Path createPath() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("ecobike2.txt");
		File file = new File(url.getPath());
		return Paths.get(file.getPath());
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
		return b.getBytes();
	}

	public void writeToFile(List<Bike> newBikes) {
		if (!newBikes.isEmpty()) {
			Path path = createPath();
			for (Bike newBike : newBikes) {
				try {
					Files.write(path, bikeToText(newBike), StandardOpenOption.APPEND);
				} catch (IOException e) {
					System.out.println("Oops.. seems that path is incorrect");
				}
			}
		} else {
			System.out.println("Sorry, no new bikes to save to file\n");
		}
	}

}
