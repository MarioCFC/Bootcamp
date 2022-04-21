package competition;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Races {
	@JsonIgnore
	private static Races raceManager = null;

	private ArrayList<Race> races;


	public static Races getInstance() {
		if (raceManager == null) {
			raceManager = new Races();
		}
		return raceManager;
	}

	public void loadRacesList(ArrayList<Race> races) {
		this.races = races;
	}


	public void initiateRacesList() {
		races = new ArrayList();
	}

	public ArrayList<Race> getAll() {
		return races;
	}

	public void addRace(Race newRace) {
		races.add(newRace);
	}

}
