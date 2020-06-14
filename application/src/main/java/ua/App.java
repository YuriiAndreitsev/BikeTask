package ua;

import java.util.Scanner;

import ua.service.CreateBikeService;
import ua.service.Service;

/**
 * Hello world!
 *
 */
public class App {

	private static final String MENU = "Please make your choice :\n" + "1 - Show all bikes\n"
			+ "2  - Add a new folding bike\n" + "3  - Add a new speedelec\n" + "4  - Add a new e-bike\n"
			+ "5  - Find the first item of a particular brand\n" + "6 – Write to file\n" + "7  - Stop the program\n";

	public static void main(String[] args) {
		Service service = new Service();
		CreateBikeService createBikeService = new CreateBikeService();
		System.out.println(MENU);

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (scanner.hasNext() && !exit) {
			String option = scanner.next();

			switch (option) {
			case "1":
				service.showAllBikesFromFile();
				break;
			case "2": {
				createBikeService.addFoldingBike(scanner);
				break;
			}
			case "3": {
				createBikeService.addSpeedelec(scanner);
				break;
			}
			case "4": {
				createBikeService.addEBike(scanner);
				break;
			}

			case "7": {
				scanner.close();
				System.exit(0);
			}
			default:
				System.out.println(MENU);
			}
			System.out.println(MENU);
		}

	}
}
