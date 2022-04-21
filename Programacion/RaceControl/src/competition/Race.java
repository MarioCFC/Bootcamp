package competition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import utils.VelocityConversor;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public abstract class Race implements IRun {
	protected String name;
	protected String id;
	protected boolean finished;

	@JsonIgnore
	protected final RacesResults raceResultsManager = RacesResults.getInstance();

	@JsonIdentityReference(alwaysAsId = true)
	protected Tournament eventWichItBelongs = null;

	@JsonIdentityReference(alwaysAsId = true)
	protected ArrayList<Car> participants;
	protected int velocityChangePerMinuteInKmH = 10;
	@JsonIgnore
	protected Random rand = new Random();

	protected Race() {
	}

	protected Race(String name, ArrayList<Car> participants) {
		this.name = name;
		this.participants = participants;
		this.id = UUID.randomUUID().toString();
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

	protected boolean isFinished() {
		return finished;
	}

	protected void finish() {
		finished = true;
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
			Car actualCar = participants.get(i);
			raceResultsManager.addResult(new ScoreOfCarInARace(actualCar, this, points, actualCar.getDistance()));
			if (points > 0) {
				points--;
			}
		}
	}

	// TODO:Validar en caso de que haya menos de 3 coches
	protected ArrayList<ScoreOfCarInARace> getPodium() {
		return (ArrayList<ScoreOfCarInARace>) raceResultsManager.getResultOfARace(this).subList(0, 3);
	}

	protected ArrayList<ScoreOfCarInARace> getRanking() {
		return raceResultsManager.getResultOfARace(this);
	}

	protected int getCarScore(Car car) {
		return raceResultsManager.getResultOfCarInARace(this, car);
	}

}
