package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import competition.Car;
import competition.Garage;

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

	public static ArrayList<Car> generateParticipants(List<Garage> garages) throws Exception {
		garageValidation(garages);
		if (garages.size() == 1) {
			return generateParticipantsOfOneGarage(garages.get(0));
		}else {
			return generateParticipantsOfMultipleGarages(garages);
		}

	}

	private static ArrayList<Car> generateParticipantsOfMultipleGarages(List<Garage> garages) throws Exception {
		garageValidation(garages);
		HashSet<Car> participants = new HashSet();
		Random rand = new Random();
		for (Garage garage : garages) {
			Car randomCar;
			try {
				randomCar = garage.getCarByIndex(rand.nextInt(garage.getNumeberOfCars()));
				participants.add(randomCar);
			} catch (Exception e) {
				Logger.getLogger("ParticipantsGeneratorLog").info("Error al generar participantes");
			}

		}

		return new ArrayList<Car>(participants);

	}

	private static ArrayList<Car> generateParticipantsOfOneGarage(Garage garages) throws Exception {
		garageValidation(garages);
		HashSet<Car> participants = new HashSet();
		for (Car car : garages.getCars()) {
			participants.add(car);
		}

		return new ArrayList<Car>(participants);
	}

}
