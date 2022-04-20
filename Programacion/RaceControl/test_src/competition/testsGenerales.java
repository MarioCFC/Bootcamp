package competition;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import IO.JSONFileManager;

class testsGenerales {

	private static JSONFileManager jsonManager = new JSONFileManager();
	private static SnapShot actualSnapShot;

	private static Tournaments tournaments;
	private static Garages garages;
	private static Cars cars;
	private static Races races;
	private static RacesResults racesResults;

	@Before
	private void init() {
		try {
			actualSnapShot = jsonManager.load();

			tournaments = actualSnapShot.getTournaments();
			garages = actualSnapShot.getGarages();
			cars = actualSnapShot.getCars();
			races = actualSnapShot.getRaces();
			racesResults = actualSnapShot.getRacesResults();

		} catch (Exception e) {
			tournaments = Tournaments.getInstance();
			garages = Garages.getInstance();
			cars = Cars.getInstance();
			races = Races.getInstance();
			racesResults = RacesResults.getInstance();

			actualSnapShot = new SnapShot(tournaments, garages, cars, races, racesResults);
		}
	}

	@Test
	void test() {
		System.out.println(actualSnapShot);
	}

}
