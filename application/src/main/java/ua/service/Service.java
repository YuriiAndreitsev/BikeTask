package ua.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import ua.model.Bike;
import ua.model.EBike;
import ua.model.Speedelec;
import ua.utilities.BikeReaderImpl;

public class Service {
	private String filename;

	public Service() {
	}

	public Service(String filename) {
		this.filename = filename;
	}

	public List<Bike> initializeBikeList(String filename) {
//		InputStream dataSource = App.class.getClassLoader().getResourceAsStream(filename);
//		Scanner scanner = new Scanner(dataSource);
		StringBuilder sb = null;
		try {
			File file = new File(filename);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); 
			sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				sb.append(line);
				sb.append("\n");
			}
			fr.close(); 

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

		Scanner scanner = new Scanner(sb.toString());
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

	public void showAllBikes(List<Bike> allBikes) {
		ListIterator<Bike> iterator = allBikes.listIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public Path createPathTest() {
		File file = new File(filename);
		return Paths.get(file.getPath());
//		return Paths.get("E:\\EcoBike\\BikeTask\\application\\target\\classes\\ecobike.txt");
	}

	public Path createPath() {
		// URL url =
		// Thread.currentThread().getContextClassLoader().getResource(filename);
		URL url = Thread.currentThread().getContextClassLoader().getResource("ecobike2.txt");
		File file = new File(url.getPath());
		return Paths.get(file.getPath());

	}

	public byte[] bikeToText(Bike bike) {

		Field[] fields = bike.getClass().getDeclaredFields();
		String delimiter = "; ";
		StringBuilder sb;
		if (bike instanceof Speedelec) {
			sb = new StringBuilder("SPEEDELEC ");
		} else if (bike instanceof EBike) {
			sb = new StringBuilder("E-BIKE ");
		} else {
			sb = new StringBuilder("FOLDING BIKE ");
		}
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				sb.append(field.get(bike) + delimiter);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return (sb.substring(0, (sb.length() - 2)) + "\n").getBytes();
	}

	public void writeToFile(List<Bike> newBikes) {
		if (!newBikes.isEmpty()) {
//			Path path = createPath();

			Path path = createPathTest();
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
