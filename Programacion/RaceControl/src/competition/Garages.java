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

	public void newCar(String name) {
		garages.add(new Garage(name));
	}
}
