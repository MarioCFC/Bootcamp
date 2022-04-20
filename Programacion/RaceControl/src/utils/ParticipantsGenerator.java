package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import competition.Car;
import competition.Garage;

public class ParticipantsGenerator {

	public static ArrayList<Car> generateParticipants(List<Garage> garages) {
		if (garages.size() == 1) {
			return generateParticipantsOfOneGarage(garages.get(0));
		} else {
			return generateParticipantsOfMultipleGarages(garages);
		}

	}

	/*
	 * private static void garageValidation(List<Garage> garages) throws Exception {
	 * for (Garage garage : garages) { if (garage.getCars().isEmpty()) throw new
	 * Exception("The garage " + garage.getName() + " is empty"); } }
	 */

	private static ArrayList<Car> generateParticipantsOfMultipleGarages(List<Garage> garages) {
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

	private static ArrayList<Car> generateParticipantsOfOneGarage(Garage garages) {
		HashSet<Car> participants = new HashSet();
		for (Car car : garages.getCars()) {
			participants.add(car);
		}

		return new ArrayList<Car>(participants);
	}

}
