package competition;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Races {
	@JsonIgnore
	private static Races raceManager = null;

	private ArrayList<Race> races;

	private Races() {
		races = new ArrayList();
	}

	public static Races getInstance() {
		if (raceManager == null) {
			raceManager = new Races();
		}
		return raceManager;
	}

	public void setRaces(ArrayList<Race> races) {
		this.races = races;
	}

	public void addRace(Race newRace) {
		races.add(newRace);
	}

}
