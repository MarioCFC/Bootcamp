package competition;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Garages {
	@JsonIgnore
	public static Garages garageManager = null;

	private ArrayList<Garage> garages;

	private Garages() {
		garages = new ArrayList();
	}

	public static Garages getInstance() {
		if (garageManager == null) {
			garageManager = new Garages();
		}
		return garageManager;
	}

	public void setGarages(ArrayList<Garage> garages) {
		this.garages = garages;
	}

	public void addGarage(Garage newGarage) {
		garages.add(newGarage);
	}

	public Garage getGarage(int index) {
		return garages.get(index);
	}

	@JsonIgnore
	public ArrayList<Garage> getAll() {
		return garages;
	}

	public void removeGarage(int index) {
		garages.remove(index);
	}

	public void removeGarage(Garage removedGarage) {
		garages.remove(removedGarage);
	}

	// TODO:Cambiar
	@JsonIgnore
	public int getNumberOfGarages() {
		return garages.size();
	}

	@JsonIgnore
	public ArrayList<Garage> getGarageWithCars() {
		ArrayList<Garage> garageWithCars = new ArrayList<Garage>();
		for (Garage garage : garageWithCars) {
			if (!garage.getCars().isEmpty()) {
				garageWithCars.add(garage);
			}
		}
		return garageWithCars;
	}

}
