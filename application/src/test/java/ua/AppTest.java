package ua;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.model.Bike;
import ua.model.FoldingBike;
import ua.model.Speedelec;
import ua.service.CreateBikeByReflection;
import ua.service.Service;

/**
 * Unit test for simple App.
 */
public class AppTest {
	Service service;
	List<Bike> allBikes;
	CreateBikeByReflection createBike;

	@Before
	public void initializeApp() {
		service = new Service();
		allBikes = service.initializeBikeList("ecobike.txt");
		createBike = new CreateBikeByReflection();
	}

	/**
	 * ECOBIKE APPLICATION Test SPEEDELEC Booster; 35; 10900; false; 13200; green;
	 * 1279
	 */

	@Test
	public void shouldPrintAllBikesParsedFromTXT() {
		assertEquals(new Speedelec("Booster", 35, 10900, false, 13200, "green", 1279),
				service.initializeBikeList("ecobike.txt").get(0));
	}

	@Test
	public void shouldAddNewBikeInList() {
		Bike testBike = new FoldingBike("test brand", 20, 5, 10000, false, "red", 999);
		String dataSourse = "test brand\n20\n5\n10000\n"+"n\nred\n999\ny\n";
		createBike.createBike(new Scanner(dataSourse), new FoldingBike());
		assertEquals(testBike, createBike.getNewBikes().get(0));
	}
}
