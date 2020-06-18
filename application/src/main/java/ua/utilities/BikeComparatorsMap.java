package ua.utilities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import ua.comparator.BikeComparators;
import ua.model.Bike;

public class BikeComparatorsMap {
	Map <String, Comparator<Bike>> map = new HashMap<String, Comparator<Bike>>();

	public BikeComparatorsMap() {
		map.put("brand", BikeComparators.BRAND);
		map.put("color", BikeComparators.COLOR);
		map.put("gears", BikeComparators.GEARS);
		map.put("lights", BikeComparators.LIGHTS);
		map.put("mAh", BikeComparators.MAH);
		map.put("maxSpeed", BikeComparators.MAXSPEED);
		map.put("price", BikeComparators.PRICE);
		map.put("wheels", BikeComparators.SIZEOFWHEELS);
		map.put("weight", BikeComparators.WEIGHT);
	}

	public Map<String, Comparator<Bike>> getMap() {
		return map;
	}
	
	public Comparator<Bike> getComparatorByName(String name){
		return map.get(name);
	}
}
