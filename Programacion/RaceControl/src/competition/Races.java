package competition;

import java.util.ArrayList;

public class Races {
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


	public void addRace(Race newRace) {
		races.add(newRace);
	}



}
