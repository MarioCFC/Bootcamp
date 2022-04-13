package competition;

import java.util.ArrayList;

public class RacesResults {
	private static RacesResults raceResultManager = null;
	private ArrayList<ResultOfCarInARace> results;

	private RacesResults() {
		results = new ArrayList<ResultOfCarInARace>();
	}

	public static RacesResults getInstance() {
		if (raceResultManager == null) {
			raceResultManager = new RacesResults();
		}
		return raceResultManager;
	}

	private void addNewResult(Race race, Car car, int score) {
		results.add(new ResultOfCarInARace(car, race, score));
	}

	public ArrayList<ResultOfCarInARace> getResultOfARace(Race race) {
		ArrayList<ResultOfCarInARace> raceResult = new ArrayList();
		
		for (ResultOfCarInARace carResultInRace : results) {
			if (carResultInRace.getRace().equals(race))
				raceResult.add(carResultInRace);
		}
		
		return raceResult;
		
	}

	public ArrayList<ResultOfCarInARace> getResultOfACar(Car car) {
		ArrayList<ResultOfCarInARace> carResults = new ArrayList();

		for (ResultOfCarInARace carResultInRace : results) {
			if (carResultInRace.getCar().equals(car))
				carResults.add(carResultInRace);
		}

		return carResults;

	}

	public ResultOfCarInARace getResultOfCarInARace(Race race, Car car) {
		for (ResultOfCarInARace carResultInRace : results) {
			if (carResultInRace.getRace().equals(race) && carResultInRace.getCar().equals(car))
				return carResultInRace;
		}
		
		return null;
	}

}
