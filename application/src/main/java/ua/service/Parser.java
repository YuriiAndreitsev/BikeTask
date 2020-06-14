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
//	public final static String SPEEDELEC = "SPEEDELEC";
//	public final static String EBIKE = "E-BIKE";
//	public final static String FOLDINGBIKE = "FOLDING BIKE";
	List<Bike> allBikes = new ArrayList<Bike>();

	public void parseTXTFile() {
		InputStream dataSource = App.class.getClassLoader().getResourceAsStream("ecobike.txt");
		DataInputStream dis = new DataInputStream(dataSource);
		Scanner scanner = new Scanner(dataSource);
		scanner.useDelimiter("\n");
		BikeReaderImpl reader = new BikeReaderImpl();

		while (scanner.hasNext()) {
			String line = scanner.next();
//			System.out.println("check : "+line);
			allBikes.add(reader.readBikeFromTXT(line));
		}
		scanner.close();
	}

	public List<Bike> getAllBikes() {
		return allBikes;
	}

	
}
