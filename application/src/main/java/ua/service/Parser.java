package ua.service;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ua.App;
import ua.model.Bike;
import ua.model.Speedelec;

public class Parser {
	List<Bike> allBikes = new ArrayList<Bike>();
	
	public void parseTXTFile(String filename) {
		InputStream dataSource = App.class.getClassLoader().getResourceAsStream(filename);

		DataInputStream dis = new DataInputStream(dataSource);
		
		Scanner scanner = new Scanner(dataSource);
		scanner.useDelimiter("\n");
		BikeReaderImpl reader = new BikeReaderImpl();

		while (scanner.hasNext()) {
			String line = scanner.next();
			allBikes.add(reader.readBikeFromTXT(line));
		}
		scanner.close();
	}

	public List<Bike> getAllBikes() {
		return allBikes;
	}

	
}
