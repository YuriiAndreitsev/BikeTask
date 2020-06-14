package ua;

import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;
import ua.service.CreateBikeService;
import ua.service.Service;

public class TestApp {

	public static void main(String[] args) {
		CreateBikeService createBikeService = new CreateBikeService();
		Service s = new Service();
		
		FoldingBike bike3 = new FoldingBike("folding bike nigga", 23, 3, 3333, true, "red", 99999);
		EBike bike2 = new EBike();
		Bike bike = new Speedelec("asdasd", "asdasd", 123, 123, 12323, false, 12313);
//		s.bikeToText(bike);

			s.writeToFile(bike3);
	
	}

}
