package competition;

import java.util.ArrayList;

public class Races {
	private static Races raceManager = null;
	private ArrayList<Race> races;

	private Races() {
		races = new ArrayList<Race>();
	}

	public static Races getInstance() {
		if (raceManager == null) {
			raceManager = new Races();
		}
		return raceManager;
	}


	public Race newStandarRace(String name, ArrayList<Car> participants, double duration,
			Tournament eventWichItBelongs) {
		StandarRace newRace = new StandarRace(name, participants, duration, eventWichItBelongs);
		races.add(newRace);
		return newRace;
	}


	public Race newKnockOutRace(String name, ArrayList<Car> participants, Tournament eventWichItBelongs) {
		KnockoutRace newRace = new KnockoutRace(name, participants, eventWichItBelongs);
		races.add(newRace);
		return newRace;
	}
}
