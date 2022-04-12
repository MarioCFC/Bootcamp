package IO;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import Competition.Car;
import Competition.Garage;

public class ParticipantsGenerator {

	private static void garageValidation(Garage gar) throws Exception {
		if (gar.getCars().isEmpty())
			throw new Exception("The garage " + gar.getName() + " is empty");
	}

	private static void garageValidation(List<Garage> garages) throws Exception {
		for (Garage garage : garages) {
			garageValidation(garage);
		}
	}

	public static HashMap<Car, Double> generateParticipants(List<Garage> garages) throws Exception {
		garageValidation(garages);
		if (garages.size() == 1) {
			return generateParticipantsOfOneGarage(garages.get(0));
		}else {
			return generateParticipantsOfMultipleGarages(garages);
		}

	}

	private static HashMap<Car, Double> generateParticipantsOfMultipleGarages(List<Garage> garages) throws Exception {
		garageValidation(garages);
		HashMap<Car, Double> participants = new HashMap();
		Random rand = new Random();
		for (Garage garage : garages) {
			Car randomCar;
			try {
				randomCar = garage.getCarByIndex(rand.nextInt(garage.getNumeberOfCars()));
				participants.put(randomCar, 0.0);
			} catch (Exception e) {
				Logger.getLogger("ParticipantsGeneratorLog").info("Error al generar participantes");
			}

		}

		return participants;

	}

	private static HashMap<Car, Double> generateParticipantsOfOneGarage(Garage garages) throws Exception {
		garageValidation(garages);
		HashMap<Car, Double> participants = new HashMap();
		for (Car car : garages.getCars()) {
			participants.put(car, 0.0);
		}

		return participants;
	}

}
