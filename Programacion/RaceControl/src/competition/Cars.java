package competition;

import java.util.ArrayList;

public class Cars {
	public static Cars carManager = null;
	private ArrayList<Car> cars;

	private Cars() {
	}

	public static Cars getInstance() {
		if (carManager == null) {
			carManager = new Cars();
		}
		return carManager;
	}

	public void newCar(String brand, String model) {
		cars.add(new Car(brand, model));
	}

}
