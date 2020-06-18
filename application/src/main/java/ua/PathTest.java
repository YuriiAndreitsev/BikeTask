package ua;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import ua.model.Bike;
import ua.model.FoldingBike;
import ua.service.Service;

public class PathTest {

	public static void main(String[] args) {
		Service s = new Service();
//		s.setFilename("ecobike.txt");
		
//		s.initializeBikeList("ecobike.txt");
		File file = new File("ecobike.txt");
//		
//		System.out.println(file.getAbsolutePath());
//		System.out.println(s.createPathTest());
//		Bike folding = new FoldingBike("test brand", 20, 5, 10000, false, "red", 999);
////		System.out.println(s.createPathTest());
//		try {
//			Files.write(s.createPathTest(), s.bikeToText(folding), StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		File file = new File(App.class.getClassLoader().getResource("ecobike.txt").getPath());
//		 System.out.println(file.getPath());
	}

}
