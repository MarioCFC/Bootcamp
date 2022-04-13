package competition;

import java.util.ArrayList;
import java.util.HashSet;

//TODO:Implementar la parte de correr y las clasificaciones
public abstract class Race implements IRun {
	protected final String name;
	protected final RacesResults raceResultsManager = RacesResults.getInstance();
	protected Tournament eventWichItBelongs = null;
	protected ArrayList<Car> participants;

	public static final int STANDAR_RACE = 0;
	public static final int KNOCKOUT_RACE = 1;


	protected Race(String name, ArrayList<Car> participants, Tournament eventWichItBelongs) {
		this.name = name;
		this.participants = participants;
		this.eventWichItBelongs = eventWichItBelongs;
	}

	protected String getName() {
		return name;
	}

	public Tournament geteventWichItBelongs() {
		return eventWichItBelongs;
	}

	public void setEventWichItBelongs(Tournament eventWichItBelongs) {
		this.eventWichItBelongs = eventWichItBelongs;
	}

	public ArrayList<Car> getParticipants() {
		return participants;
	}

	protected ArrayList<Garage> getParticipantsGarages() {
		HashSet<Garage> garages = new HashSet<>();
		for (Car car : participants) {
			garages.add(car.getSticker());
		}

		return new ArrayList<Garage>(garages);
	}
	


}
