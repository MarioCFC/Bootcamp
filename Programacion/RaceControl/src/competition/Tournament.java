package competition;

import java.util.ArrayList;

public class Tournament {
	private String name;
	private ArrayList<Car> participants;
	private ArrayList<Race> races;
	private int numberOfRaces;
	private int lastRaceRunned = 0;

	public Tournament(String name, ArrayList<Car> participants,int numberOfRaces) {
		this.name = name;
		this.participants = participants;
		this.numberOfRaces = numberOfRaces;
		races = new ArrayList<Race>();
	}

	public String getName() {
		return name;
	}

	public void addRace(Race newRace) {
		races.add(newRace);
		newRace.setEventWichItBelongs(this);
	}

	public void runRace() {
		if (races.size() - 1 <= lastRaceRunned) {
			races.get(lastRaceRunned++).run();
		}
	}



	




}
