package competition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

//TODO:Implementar la parte de correr y las clasificaciones
public abstract class Race implements IRun {
	protected final String name;
	protected final RacesResults raceResultsManager = RacesResults.getInstance();
	protected Tournament eventWichItBelongs = null;
	protected ArrayList<Car> participants;

	public static final int STANDAR_RACE = 0;
	public static final int KNOCKOUT_RACE = 1;


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


}
