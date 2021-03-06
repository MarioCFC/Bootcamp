package competition;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Garage {
	private String name;
	private String id;

	@JsonIdentityReference(alwaysAsId = true)
	private ArrayList<Car> cars = new ArrayList();

	private Garage() {
	}

	public Garage(String name) {
		this.name = name;
		this.id = UUID.randomUUID().toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	// TODO:Cambiar
	@JsonIgnore
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

	public void removeCar(Car removedCar) {
		cars.remove(removedCar);
		removedCar.setSticker(null);
	}

	@Override
	public String toString() {
		return "Nombre: " + name;
	}
}
