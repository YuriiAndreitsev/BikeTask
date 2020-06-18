package ua;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;
import ua.service.CreateBikeByReflection;
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
//		String ecobike = "ecobike.txt";
		String ecobike = "";
		//JUST IN CASE THE USER FORGOT TO ENTER THE START KEY :  java -jar application-1.0-SNAPSHOT.jar ecobike.txt 
			// I JUST WANT TO INSURE, THAT MY PROGRAMM WILL START WITH NO EXCEPTIONS. SO YOU WONT THINK THAT I'VE DONE SOMETHING WRONG
		try {
			ecobike = args[0];
		} catch (IndexOutOfBoundsException e) {
			ecobike = "ecobike.txt";
		}

		Service service = new Service(ecobike);
		List<Bike> allBikes = service.initializeBikeList(ecobike);
		CreateBikeByReflection createBike = new CreateBikeByReflection();
		SearchService search = new SearchService(allBikes);
//		service.setFilename(ecobike);

		Scanner scanner = new Scanner(System.in);

		boolean exit = false;
		ExecutorService es = Executors.newFixedThreadPool(1);
		System.out.println(MENU);

		while (scanner.hasNext() && !exit) {

			String option = scanner.nextLine();
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
					System.out.println("SEARCH RESULT = " + searchResult.get());
				} catch (InterruptedException e) {
					System.out.println("Thread was interrupted");
				} catch (ExecutionException e) {
					System.out.println("Failed to make search");
				}

				break;
			}
			case "6": {
				service.writeToFile(createBike.getNewBikes());
				// HERE WE ARE TRANSFERRING BIKES FROM UNSAVED STATE TO ALLBIKES COLLECTION,
				// SO WHEN AFTER WRITING TO FILE (OPTION 6), WE WOULD BE ABLE TO SEE NEW ITEMS (
				// NO NEED TO PARSE TXT FILE), AS IT IS PARSED ONCE IN THE START
				allBikes.addAll(createBike.getNewBikes());
				createBike.getNewBikes().clear();
				break;
			}
			case "7": {
				exit = true;
				scanner.close();
				System.exit(0);
			}
			case "8": {
				Bike folding = new FoldingBike("test brand", 20, 5, 10000, false, "red", 999);

				try {
					Files.write(service.createPathTest(), service.bikeToText(folding), StandardOpenOption.APPEND);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			default:
				System.out.println(MENU);
			}

			System.out.println(MENU);

		}

	}
}
