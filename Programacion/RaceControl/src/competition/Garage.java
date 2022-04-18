package competition;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Garage {
	private final String name;

	@JsonIdentityReference(alwaysAsId = true)
	private ArrayList<Car> cars = new ArrayList();

	public Garage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Car> getCars() {
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
		newCar.setSticker(this);
	}
}
