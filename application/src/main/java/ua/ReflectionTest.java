package ua;

import java.util.Scanner;

import ua.model.Bike;
import ua.model.Speedelec;
import ua.service.CreateBikeByReflection;

public class ReflectionTest {

	public static void main(String[] args) {
		
		CreateBikeByReflection create = new CreateBikeByReflection();
		Speedelec bike = new Speedelec();

		Scanner sc = new Scanner(System.in);

			create.createBike(sc, bike);
		
	}

}
