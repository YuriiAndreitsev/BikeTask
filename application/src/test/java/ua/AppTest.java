package ua;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ua.model.Bike;
import ua.model.Speedelec;
import ua.service.Service;

/**
 * Unit test for simple App.
 */
public class AppTest {
	Service service;
	List<Bike> allBikes;

	@Before
	public void initializeApp() {
		service = new Service();
		allBikes = service.initializeBikeList("ecobike.txt");
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
		
	}
}
