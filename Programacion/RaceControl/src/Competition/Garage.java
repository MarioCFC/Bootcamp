package Competition;

import java.util.HashSet;

public class Garage {
	private final String name;
	private HashSet<Car> cars = new HashSet();

	public Garage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public HashSet<Car> getCars() {
		return cars;
	}

	public int getNumeberOfCars() {
		return cars.size();
	}

	public Car getCarByIndex(int index) throws Exception {
		int position = 0;

		for (Car car : cars) {
			if (position++ == index)
				return car;
		}
		throw new Exception("Index error");


	}

	public void addCar(Car newCar) {
		cars.add(newCar);
	}
}
