package competition;

import java.util.ArrayList;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RacesResults {
	@JsonIgnore
	private static RacesResults raceResultManager = null;
	private ArrayList<ResultOfCarInARace> results;

	private RacesResults() {
		results = new ArrayList();
	}

	public static RacesResults getInstance() {
		if (raceResultManager == null) {
			raceResultManager = new RacesResults();
		}
		return raceResultManager;
	}

	public void addResult(ResultOfCarInARace newResult) {
		results.add(newResult);
	}

	public ArrayList<ResultOfCarInARace> getResultOfARace(Race race) {
		ArrayList<ResultOfCarInARace> raceResult = new ArrayList();

		for (ResultOfCarInARace carResultInRace : results) {
			if (carResultInRace.getRace().equals(race))
				raceResult.add(carResultInRace);
		}
		sortResultsList(raceResult);
		return raceResult;

	}

	public ArrayList<ResultOfCarInARace> getResultOfACar(Car car) {
		ArrayList<ResultOfCarInARace> carResults = new ArrayList();

		for (ResultOfCarInARace carResultInRace : results) {
			if (carResultInRace.getCar().equals(car))
				carResults.add(carResultInRace);
		}
		sortResultsList(carResults);
		return carResults;

	}

	public int getResultOfCarInARace(Race race, Car car) {
		for (ResultOfCarInARace carResultInRace : results) {
			if (carResultInRace.getRace().equals(race) && carResultInRace.getCar().equals(car))
				return carResultInRace.getScore();
		}

		return -1;
	}

	public void sortResultsList(ArrayList<ResultOfCarInARace> sortedList) {
		Comparator<ResultOfCarInARace> resultComparator = new Comparator<ResultOfCarInARace>() {
			@Override
			public int compare(ResultOfCarInARace o1, ResultOfCarInARace o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
		};

		sortedList.sort(resultComparator.reversed());
	}
}
