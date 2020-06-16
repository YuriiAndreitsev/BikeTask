package ua.comparator;

import java.util.Comparator;

import ua.model.Bike;

public enum BikeComparators implements Comparator<Bike> {

	BRAND(Comparator.comparing(Bike::getBrand)), MAXSPEED(Comparator.comparing(Bike::getMaxSpeed)),
	WEIGHT(Comparator.comparing(Bike::getWeight)), LIGHTS(Comparator.comparing(Bike::isLights)),
	MAH(Comparator.comparing(Bike::getmAh)), COLOR(Comparator.comparing(Bike::getColor)),
	PRICE(Comparator.comparing(Bike::getPrice)), SIZEOFWHEELS(Comparator.comparing(Bike::getSizeOfWheels)),
	GEARS(Comparator.comparing(Bike::getNumberOfGears));

	Comparator<Bike> comparator;

	BikeComparators(Comparator<Bike> comparator) {
		this.comparator = comparator;
	}

	@Override
	public int compare(Bike o1, Bike o2) {
		int result = comparator.compare(o1, o2);
		if (result != 0) {
			return result;
		}
		return 0;
	}

}
