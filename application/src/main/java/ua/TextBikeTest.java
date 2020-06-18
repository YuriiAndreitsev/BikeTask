package ua;

import ua.model.Bike;
import ua.model.EBike;
import ua.model.FoldingBike;
import ua.model.Speedelec;
import ua.service.CreateBikeByReflection;
import ua.service.Service;

public class TextBikeTest {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Service s = new Service();
		CreateBikeByReflection cs = new CreateBikeByReflection();
		Bike folding = new FoldingBike("test brand", 20, 5, 10000, false, "red", 999);
		Bike ebike = new EBike("E-Bike Cool Brand", 35, 9500, true, 20000, "purple haze", 599);
		Bike speedelec = new Speedelec("Speedelec Brand", 35, 9500, false, 25000, "military green", 699);
		cs.getNewBikes().add(folding);
		cs.getNewBikes().add(ebike);
		cs.getNewBikes().add(speedelec);
		System.out.println(cs.getNewBikes());
		s.writeToFile(cs.getNewBikes());
	}

}
