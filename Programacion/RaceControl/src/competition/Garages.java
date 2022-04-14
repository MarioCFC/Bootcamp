package competition;

import java.util.ArrayList;

public class Garages {
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

	public void addGarage(Garage newGarage) {
		garages.add(newGarage);
	}

	public Garage getGarage(int index) {
		return garages.get(index);
	}

	public void removeGarage(int index) {
		garages.remove(index);
	}

	public void removeGarage(Garage removedGarage) {
		garages.remove(removedGarage);
	}

	public int getNumberOfGarages() {
		return garages.size();
	}
}
