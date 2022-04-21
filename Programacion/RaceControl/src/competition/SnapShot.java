package competition;

import java.util.ArrayList;

public class SnapShot {
//	private LocalDate lastChange;
	private ArrayList<Tournament> tournaments;
	private ArrayList<Garage> garages;
	private ArrayList<Car> cars;

	private ArrayList<Race> races;
	private ArrayList<ScoreOfCarInARace> racesResults;

	private SnapShot() {
	}

	public SnapShot(ArrayList<Tournament> tournaments, ArrayList<Garage> garages, ArrayList<Car> cars,
			ArrayList<Race> races, ArrayList<ScoreOfCarInARace> racesResults) {
		this.tournaments = tournaments;
		this.garages = garages;
		this.cars = cars;
		this.races = races;
		this.racesResults = racesResults;
	}

	public ArrayList<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(ArrayList<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

	public ArrayList<Garage> getGarages() {
		return garages;
	}

	public void setGarages(ArrayList<Garage> garages) {
		this.garages = garages;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public ArrayList<Race> getRaces() {
		return races;
	}

	public void setRaces(ArrayList<Race> races) {
		this.races = races;
	}

	public ArrayList<ScoreOfCarInARace> getRacesResults() {
		return racesResults;
	}

	public void setRacesResults(ArrayList<ScoreOfCarInARace> racesResults) {
		this.racesResults = racesResults;
	}

	public boolean areListsInitiated() {
		return tournaments != null && garages != null && cars != null && races != null && racesResults != null;
	}
	/*
	 * public LocalDate getLastChange() { return lastChange; }
	 * 
	 * public void setLastChange(LocalDate lastChange) { this.lastChange =
	 * lastChange; }
	 */

}
