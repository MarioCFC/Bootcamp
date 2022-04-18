package competition;

public class SnapShot {
//	private LocalDate lastChange;
	private Tournaments tournaments;
	private Garages garages;
	private Cars cars;

	private Races races;
	private RacesResults racesResults;

	private SnapShot() {
	}
	public SnapShot(Tournaments tournaments, Garages garages, Cars cars, Races races,
			RacesResults racesResults) {
//		this.lastChange = LocalDate.now();
		this.tournaments = tournaments;
		this.garages = garages;
		this.cars = cars;
		this.races = races;
		this.racesResults = racesResults;
	}

	/*
	 * public LocalDate getLastChange() { return lastChange; }
	 * 
	 * public void setLastChange(LocalDate lastChange) { this.lastChange =
	 * lastChange; }
	 */

	public Tournaments getTournaments() {
		return tournaments;
	}

	public void setTournaments(Tournaments tournaments) {
		this.tournaments = tournaments;
	}

	public Garages getGarages() {
		return garages;
	}

	public void setGarages(Garages garages) {
		this.garages = garages;
	}

	public Cars getCars() {
		return cars;
	}

	public void setCars(Cars cars) {
		this.cars = cars;
	}

	public Races getRaces() {
		return races;
	}

	public void setRaces(Races races) {
		this.races = races;
	}

	public RacesResults getRacesResults() {
		return racesResults;
	}

	public void setRacesResults(RacesResults racesResults) {
		this.racesResults = racesResults;
	}
	
	

}
