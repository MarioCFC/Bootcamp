package competition;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cars {
	@JsonIgnore
	private static Cars carManager = null;
	private ArrayList<Car> cars;

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

	public void loadCarsList(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public void initiateCarsList() {
		cars = new ArrayList();
	}

	@JsonIgnore
	public ArrayList<Car> getAll() {
		return cars;
	}

	public void removeCar(int index) {
		cars.remove(index);
	}

	public void removeCar(Car removedCar) {
		cars.remove(removedCar);
	}

	// TODO:Cambiar
	@JsonIgnore
	public int getNumberOfCars() {
		return cars.size();
	}

	@JsonIgnore
	public ArrayList<Car> getCarsWithoutGarage() {
		ArrayList<Car> carsWithoutGarage = new ArrayList<Car>();

		for (Car car : cars) {
			if (car.getSticker() == null) {
				carsWithoutGarage.add(car);
			}
		}

		return carsWithoutGarage;
	}

}
