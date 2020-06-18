package ua;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;
import ua.service.CreateBikeByReflection;
import ua.service.SearchService;
import ua.service.Service;

/**
 * Unit test for simple App.
 */
public class AppTest {
	Service service;
	List<Bike> allBikes;
	CreateBikeByReflection createBike;
	SearchService search;
	ExecutorService es;
	String ecobike = "";
	Service serviceMock;

	@Before
	public void initializeApp() {
		ecobike = "ecobike.txt";
		service = new Service();
		allBikes = service.initializeBikeList(ecobike);
		createBike = new CreateBikeByReflection();
		search = new SearchService(allBikes);
		es = Executors.newFixedThreadPool(1);
		serviceMock = Mockito.mock(Service.class);
	}

	@Test
	public void mockService() {
		Bike folding = new FoldingBike("test brand", 20, 5, 10000, false, "red", 999);
		Bike ebike = new EBike("E-Bike Cool Brand", 35, 9500, true, 20000, "purple haze", 599);
		Bike speedelec = new Speedelec("Speedelec Brand", 35, 9500, false, 25000, "military green", 699);
		
		List<Bike> mockList = Arrays.asList(folding,ebike,speedelec);
		Mockito.when(serviceMock.initializeBikeList(ecobike)).thenReturn(mockList);
		assertTrue(mockList.equals(serviceMock.initializeBikeList(ecobike)));
	}

	@Test
	public void shouldPrintAllBikesParsedFromTXT() {
		Bike expected = new Speedelec("Booster", 35, 10900, false, 13200, "green", 1279);
		assertEquals(expected, service.initializeBikeList("ecobike.txt").get(0));
	}

	@Test
	public void shouldAddNewFoldingBikeInList() {
		Bike testBike = new FoldingBike("test brand", 20, 5, 10000, false, "red", 999);
		String dataSourse = "test brand\n20\n5\n10000\n" + "n\nred\n999\ny\n";
		createBike.createBike(new Scanner(dataSourse), new FoldingBike());
		assertEquals(testBike, createBike.getNewBikes().get(0));
	}

	@Test
	public void shouldAddNewEBikeInList() {
		Bike testBike = new EBike("E-Bike Cool Brand", 35, 9500, true, 20000, "purple haze", 599);
//		Bike testBike = new EBike("E-Bike Cool Brand", "purple haze", 599, 9500, 35, true, 20000);
		String dataSourse = "E-Bike Cool Brand\n35\n9500\ny\n20000\npurple haze\n599\ny\n";
		createBike.createBike(new Scanner(dataSourse), new EBike());
		assertEquals(testBike, createBike.getNewBikes().get(0));
	}

	@Test
	public void shouldAddNewSpeedelecBikeInList() {
		Bike testBike = new Speedelec("Speedelec Brand", 35, 9500, false, 25000, "military green", 699);
		String dataSourse = "Speedelec Brand\n35\n9500\nn\n25000\nmilitary green\n699\ny\n";
		createBike.createBike(new Scanner(dataSourse), new Speedelec());
		assertEquals(testBike, createBike.getNewBikes().get(0));
	}

	@Test
	public void shouldSearchForABike() {
		// FOLDING BIKE Stern; 14; 8; 9600; true; violet; 1539
		/*
		 * Method should return the object of parsed txt file, doesnt include bikes that
		 * havent been saved (written to file)
		 */
		Bike expectedBike = new FoldingBike("Stern", 14, 8, 9600, true, "violet", 1539);
		String dataSourse = "1\ny\nStern\ny\n14\ny\n8\ny\n9600\ny\ny\ny\nviolet\ny\n1539\n";
		search.searchBike(new Scanner(dataSourse), allBikes);
		Future<Bike> searchResult = es.submit(search);
		try {
			System.out.println("\nSEARCH RESULT = " + searchResult.get());
			assertEquals(expectedBike, searchResult.get());
		} catch (InterruptedException e) {
			System.out.println("Thread was interrupted");
		} catch (ExecutionException e) {
			System.out.println("Failed to make search");
		}

	}

	@Test
	public void shouldSearchByThreeParams() {
		/* Looking for : E-BIKE ElectrO; 45; 24100; false; 23000; silver; 919 */

		Bike expetedBike = new EBike("ElectrO", 45, 24100, false, 23000, "silver", 919);
		String dataSourse = "2\n" + "y\nElectrO\n" + "n\n" + "n\n" + "y\nn\n" + "n\n" + "n\n" + "y\n919";
		search.searchBike(new Scanner(dataSourse), allBikes);
		Future<Bike> searchResult = es.submit(search);
		try {
			System.out.println("\nSEARCH RESULT = " + searchResult.get());
			assertEquals(expetedBike, searchResult.get());
		} catch (InterruptedException e) {
			System.out.println("Thread was interrupted");
		} catch (ExecutionException e) {
			System.out.println("Failed to make search");
		}
	}

	@Test
	public void shouldWriteToFile() {
		Bike folding = new FoldingBike("test brand", 20, 5, 10000, false, "red", 999);
		Bike ebike = new EBike("E-Bike Cool Brand", 35, 9500, true, 20000, "purple haze", 599);
		Bike speedelec = new Speedelec("Speedelec Brand", 35, 9500, false, 25000, "military green", 699);
		byte[] foldingByteArray = service.bikeToText(folding);
		byte[] ebikeByteArray = service.bikeToText(ebike);
		byte[] speedelecByteArray = service.bikeToText(speedelec);

		assertTrue(
				Arrays.equals(foldingByteArray, "FOLDING BIKE test brand; 20; 5; 10000; false; red; 999\n".getBytes()));
		assertTrue(Arrays.equals(ebikeByteArray,
				"E-BIKE E-Bike Cool Brand; 35; 9500; true; 20000; purple haze; 599\n".getBytes()));
		assertTrue(Arrays.equals(speedelecByteArray,
				"SPEEDELEC Speedelec Brand; 35; 9500; false; 25000; military green; 699\n".getBytes()));

	}

}
