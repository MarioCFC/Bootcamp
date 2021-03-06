package competition;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Garages {
	// TODO:Mirar metodo para borrar coche de garaje
	@JsonIgnore
	public static Garages garageManager = null;

	private ArrayList<Garage> garages;

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

	public void loadGarageList(ArrayList<Garage> garages) {
		this.garages = garages;
	}

	public void initiateGarageList() {
		garages = new ArrayList<Garage>();
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
		for (Garage garage : garages) {
			if (!garage.getCars().isEmpty()) {
				garageWithCars.add(garage);
			}
		}
		return garageWithCars;
	}

	@JsonIgnore
	public ArrayList<Garage> getGarageWithOutCars() {
		ArrayList<Garage> garageWithCars = new ArrayList<Garage>();
		for (Garage garage : garages) {
			if (garage.getCars().isEmpty()) {
				garageWithCars.add(garage);
			}
		}
		return garageWithCars;
	}

}
