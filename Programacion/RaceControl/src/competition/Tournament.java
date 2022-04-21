package competition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Tournament {
	private String id;
	private String name;
	@JsonIdentityReference(alwaysAsId = true)
	private ArrayList<Car> participants = new ArrayList<Car>();
	@JsonIdentityReference(alwaysAsId = true)
	private ArrayList<Race> races = new ArrayList<Race>();

	@JsonIgnore
	private static RacesResults resultsManages = null;

	private int numberLimitOfRaces;
	private int lastRaceRunned = 0;

	private Tournament() {
	}

	public Tournament(String name, ArrayList<Car> participants, int numberLimitOfRaces) {
		this.name = name;
		this.participants = participants;
		this.numberLimitOfRaces = numberLimitOfRaces;
		this.id = UUID.randomUUID().toString();
		
		resultsManages = RacesResults.getInstance();
	}

	public String getName() {
		return name;
	}

	@JsonIgnore
	public boolean isFinished() {
		return lastRaceRunned >= numberLimitOfRaces;
	}

	public boolean canAddNewRace() {
		return races.size() < numberLimitOfRaces;
	}


	@JsonIgnore
	public int getNumberOfActualRaces() {
		return races.size();
	}

	public ArrayList<Race> getRaces() {
		return races;
	}



	public ArrayList<Car> getParticipants() {
		return participants;
	}

	public void addRace(Race newRace) {
		races.add(newRace);
		// TODO:
		newRace.setEventWichItBelongs(this);
	}

	public boolean haveAnotherRace() {
		return lastRaceRunned <= (races.size() - 1);
	}

	// ATENTO
	@JsonIgnore
	public Race getNextRaceToRun() {
		return races.get(lastRaceRunned++);
	}


	// TODO:Cambiar
	@JsonIgnore
	public ArrayList<Entry<Car, Integer>> getPodium() {
		return (ArrayList<Entry<Car, Integer>>) getPodium().subList(0, 3);
	}

	private void sortRanking(ArrayList<Entry<Car, Integer>> ranking) {
		Comparator<Entry<Car, Integer>> comp = new Comparator<Entry<Car, Integer>>() {
			@Override
			public int compare(Entry<Car, Integer> o1, Entry<Car, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		};

		ranking.sort(comp.reversed());
	}

	@Override
	public String toString() {
		String state = isFinished() ? "Finished" : "In progress";
		return "Name: " + name + "- State: " + state;
	}
}
