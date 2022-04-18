package competition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

import utils.VelocityConversor;

//TODO:Implementar la parte de correr y las clasificaciones
public abstract class Race implements IRun {
	protected final String name;
	protected final RacesResults raceResultsManager = RacesResults.getInstance();
	protected Tournament eventWichItBelongs = null;
	protected ArrayList<Car> participants;
	protected int velocityChangePerMinuteInKmH = 10;
	protected Random rand = new Random();



	protected Race(String name, ArrayList<Car> participants) {
		this.name = name;
		this.participants = participants;
	}

	protected String getName() {
		return name;
	}

	protected Tournament geteventWichItBelongs() {
		return eventWichItBelongs;
	}

	protected void setEventWichItBelongs(Tournament eventWichItBelongs) {
		this.eventWichItBelongs = eventWichItBelongs;
	}

	protected ArrayList<Car> getParticipants() {
		return participants;
	}

	protected ArrayList<Garage> getParticipantsGarages() {
		HashSet<Garage> garages = new HashSet<>();
		for (Car car : participants) {
			garages.add(car.getSticker());
		}

		return new ArrayList<Garage>(garages);
	}
	
	protected void runRound(Car car) {

		int finalVelocityChange = velocityChangePerMinuteInKmH;

		if (rand.nextInt(2) == 0) {
			finalVelocityChange = -velocityChangePerMinuteInKmH;
		}

		car.setVelocityKmH(car.getVelocityKmH() + finalVelocityChange);

		double newDistance = car.getDistance() + VelocityConversor.convertKmHToMMin(car.getVelocityKmH());
		car.setDistance(newDistance);
	}

	protected void sortParticipantsByDistance() {
		Comparator<Car> comp = new Comparator<Car>() {
			@Override
			public int compare(Car car1, Car car2) {
				return car1.getDistance().compareTo(car2.getDistance());
			}
		};

		participants.sort(comp.reversed());

	}

	protected void calculateScore() {
		sortParticipantsByDistance();

		for (int i = 0, points = 3; i < participants.size(); i++) {
			raceResultsManager.addResult(new ResultOfCarInARace(participants.get(i), this, points));
			if (points > 0) {
				points--;
			}
		}
	}

	// TODO:Validar en caso de que haya menos de 3 coches
	protected ArrayList<ResultOfCarInARace> getPodium() {
		return (ArrayList<ResultOfCarInARace>) raceResultsManager.getResultOfARace(this).subList(0, 3);
	}

	protected ArrayList<ResultOfCarInARace> getRanking() {
		return raceResultsManager.getResultOfARace(this);
	}

	protected int getCarScore(Car car) {
		return raceResultsManager.getResultOfCarInARace(this, car);
	}


}
