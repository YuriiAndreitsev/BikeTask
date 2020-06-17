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

	public static void main(String[] args) throws IOException {

		String dataSourse = "test brand\nsome other\n and another\n";
		
		Scanner sc = new Scanner(dataSourse);

		String a = sc.nextLine();

		String b = sc.nextLine();

		String c = sc.nextLine();

		System.out.println("a = " + a + "\n" + "b = " + b + "\n" + "c = " + c + "\n");
	}

}
