package competition;

import java.util.ArrayList;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RacesResults {
	@JsonIgnore
	private static RacesResults raceResultManager = null;
	private ArrayList<ScoreOfCarInARace> results;

	private RacesResults() {
		results = new ArrayList();
	}

	public static RacesResults getInstance() {
		if (raceResultManager == null) {
			raceResultManager = new RacesResults();
		}
		return raceResultManager;
	}

	public void addResult(ScoreOfCarInARace newResult) {
		results.add(newResult);
	}

	@JsonIgnore
	public static RacesResults getRaceResultManager() {
		return raceResultManager;
	}

	// TODO:Quitar pudiendo crear los Score necesarios
	public static void setRaceResultManager(RacesResults raceResultManager) {
		RacesResults.raceResultManager = raceResultManager;
	}

	public ArrayList<ScoreOfCarInARace> getResultOfARace(Race race) {
		ArrayList<ScoreOfCarInARace> raceResult = new ArrayList();

		for (ScoreOfCarInARace carResultInRace : results) {
			if (carResultInRace.getRace().equals(race))
				raceResult.add(carResultInRace);
		}
		sortResultsList(raceResult);
		return raceResult;

	}

	public ArrayList<ScoreOfCarInARace> getResultOfACar(Car car) {
		ArrayList<ScoreOfCarInARace> carResults = new ArrayList();

		for (ScoreOfCarInARace carResultInRace : results) {
			if (carResultInRace.getCar().equals(car))
				carResults.add(carResultInRace);
		}
		sortResultsList(carResults);
		return carResults;

	}

	public int getResultOfCarInARace(Race race, Car car) {
		for (ScoreOfCarInARace carResultInRace : results) {
			if (carResultInRace.getRace().equals(race) && carResultInRace.getCar().equals(car))
				return carResultInRace.getScore();
		}

		return -1;
	}

	public ArrayList<ScoreOfCar> getResultsOfTournament(Tournament tournament) {
		ArrayList<ScoreOfCar> tournamentRanking = new ArrayList<ScoreOfCar>();

		for (ScoreOfCarInARace scoreOfCarInARace : results) {

			if (tournament.getRaces().contains(scoreOfCarInARace.getRace())) {

				ScoreOfCar actualScore = indexOfByCarObject(tournamentRanking, scoreOfCarInARace.getCar());
				if (actualScore != null) {
					actualScore.setScore(actualScore.getScore() + scoreOfCarInARace.getScore());
					actualScore.setTotalDistance(actualScore.getTotalDistance() + scoreOfCarInARace.getTotalDistance());
				} else {
					actualScore = new ScoreOfCar(scoreOfCarInARace.getCar(), scoreOfCarInARace.getScore(),
							scoreOfCarInARace.getTotalDistance());
				}
			}
		}

		sortTournamentRanking(tournamentRanking);
		return tournamentRanking;
	}

	private ScoreOfCar indexOfByCarObject(ArrayList<ScoreOfCar> tournamentRanking, Car car) {
		for (ScoreOfCar scoreOfCar : tournamentRanking) {
			if (scoreOfCar.getCar() == car) {
				return scoreOfCar;
			}
		}
		return null;
	}

	public void sortResultsList(ArrayList<ScoreOfCarInARace> sortedList) {
		Comparator<ScoreOfCarInARace> resultComparator = new Comparator<ScoreOfCarInARace>() {
			@Override
			public int compare(ScoreOfCarInARace o1, ScoreOfCarInARace o2) {
				int resultOfCompare = o1.getScore().compareTo(o2.getScore());

				if (resultOfCompare == 0) {
					resultOfCompare = o1.getTotalDistance().compareTo(o2.getTotalDistance());
				}
				return resultOfCompare;
			}
		};

		sortedList.sort(resultComparator.reversed());
	}

	// TODO:Arreglar
	public void sortTournamentRanking(ArrayList<ScoreOfCar> sortedList) {
		Comparator<ScoreOfCar> resultComparator = new Comparator<ScoreOfCar>() {
			@Override
			public int compare(ScoreOfCar o1, ScoreOfCar o2) {
				int resultOfCompare = o1.getScore().compareTo(o2.getScore());

				if (resultOfCompare == 0) {
					resultOfCompare = o1.getTotalDistance().compareTo(o2.getTotalDistance());
				}
				return resultOfCompare;
			}
		};

		sortedList.sort(resultComparator.reversed());
	}

}
