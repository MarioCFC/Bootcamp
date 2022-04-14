package competition;

import java.util.ArrayList;

public class Cars {
	private static Cars carManager = null;
	private ArrayList<Car> cars;

	private Cars() {
		cars = new ArrayList();
	}

	public static Cars getInstance() {
		if (carManager == null) {
			carManager = new Cars();
		}
		return carManager;
	}

	public void addCar(Car newCar) {
		cars.add(newCar);
	}

	public Car getCar(int index) {
		return cars.get(index);
	}

	public void removeCar(int index) {
		cars.remove(index);
	}

	public void removeCar(Car removedCar) {
		cars.remove(removedCar);
	}

	public int getNumberOfCars() {
		return cars.size();
	}

}
