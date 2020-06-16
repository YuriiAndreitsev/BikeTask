package ua;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import ua.comparator.BikeComparators;
import ua.model.Bike;
import ua.model.FoldingBike;
import ua.model.Speedelec;
import ua.service.Parser;
import ua.service.SearchService;
import ua.service.Service;

public class TestApp {

	public static Comparator<Bike> createChainComparator(Collection<Comparator<Bike>> comparators) {
		return comparators.stream().reduce((o1, o2) -> 0, Comparator::thenComparing);
	}

	public static void main(String[] args) throws IOException {
		Service s = new Service();
		List<Bike> allBikes = s.initializeBikeList("ecobike.txt");
		
//		SearchService search = new SearchService();
//		Scanner sc = new Scanner(System.in);
//		search.searchBike(sc, allBikes);
//		sc.close();
////		Service s = new Service();
////		s.setFilename("ecobike.txt");
//		Parser parser = new Parser();
//		parser.parseTXTFile("ecobike.txt");
//		List<Bike> bikeList = parser.getAllBikes();
//		System.out.println("=================================");
//
////		Collections.sort(bikeList, BikeComparators.BRAND); SPEEDELEC Freego; 55; 9000; false; 15800; grenadine; 1505
//		Speedelec sp = new Speedelec("Dualtron", "dark gray", 1019, 14400, 30, true, 6500);
////		Speedelec sp = new Speedelec();
////		sp.setBrand("EcoRide");
//		Collection<Comparator<Bike>> comps = Arrays.asList(BikeComparators.BRAND, BikeComparators.MAXSPEED, BikeComparators.WEIGHT);
////		Collection<Comparator<Bike>> comps = Arrays.asList(BikeComparators.BRAND);
////		
//		Comparator<Bike> bikeComp = createChainComparator(comps);
//		
//		Collections.sort(bikeList, bikeComp);
//		
//		int index = Collections.binarySearch(bikeList, sp, bikeComp);
//
//		System.out.println(index);
//
//		System.out.println(bikeList.get(index));
	}

}
