package ua;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;
import ua.service.CreateBikeByReflection;
import ua.service.CreateBikeService;
import ua.service.SearchService;
import ua.service.Service;

/**
 * Hello world!
 *
 */
public class App {

	private static final String MENU = "Please make your choice :\n" + "1 - Show all bikes\n"
			+ "2 - Add a new folding bike\n" + "3 - Add a new speedelec\n" + "4 - Add a new e-bike\n"
			+ "5 - Find the first item of a particular brand\n" + "6 - Write to file\n" + "7 - Stop the program\n";

	public static void main(String[] args) {
		String ecobike = "ecobike.txt";
//		String ecobike = args[0];
		Service service = new Service();
		service.setFilename(ecobike);
		List<Bike> allBikes = service.initializeBikeList("ecobike.txt");
		Scanner scanner = new Scanner(System.in);
		CreateBikeByReflection createBike = new CreateBikeByReflection();
//		CreateBikeService createBikeService = new CreateBikeService();
		SearchService search = new SearchService(allBikes);
		System.out.println(MENU);

		boolean exit = false;
		ExecutorService es = Executors.newFixedThreadPool(1);

		while (scanner.hasNext() && !exit) {
			String option = scanner.next();
			switch (option) {
			case "1":
				service.showAllBikes(allBikes);
				break;
			case "2": {
				System.out.println(" >> You are now creating a FOLDING BIKE <<");
				createBike.createBike(scanner, new FoldingBike());
				break;
			}
			case "3": {
				System.out.println(" >> You are now creating a SPEEDELEC BIKE <<");
				createBike.createBike(scanner, new Speedelec());
				break;
			}
			case "4": {
				System.out.println(" >> You are now creating a E-BIKE <<");
				createBike.createBike(scanner, new EBike());
				break;
			}
			case "5": {
				search.searchBike(scanner, allBikes);
				Future<Bike> searchResult = es.submit(search);
				try {
					System.out.println("THREAD RESULT = " + searchResult.get());
				} catch (InterruptedException e) {
					System.out.println("Thread was interrupted");
				} catch (ExecutionException e) {
					System.out.println("Failed to make search");
				}

				break;
			}
			case "6": {
				service.writeToFile(createBike.getNewBikes());
				;
				break;
			}
			case "7": {
				exit = true;
				scanner.close();
				System.exit(0);
			}
			case "8": {
				System.out.println(createBike.getNewBikes());
			}
			default:
				System.out.println(MENU);
			}
			
			System.out.println(MENU);
			
		}
		
	}
}
