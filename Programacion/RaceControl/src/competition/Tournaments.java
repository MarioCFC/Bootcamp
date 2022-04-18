package competition;

import java.util.ArrayList;

public class Tournaments {
	private static Tournaments tournamentManager = null;
	private ArrayList<Tournament> tournaments;

	private Tournaments() {
		tournaments = new ArrayList();
	}

	public static Tournaments getInstance() {
		if (tournamentManager == null) {
			tournamentManager = new Tournaments();
		}
		return tournamentManager;
	}

	public void addTournament(Tournament newTournament) {
		tournaments.add(newTournament);
	}

	public Tournament getTournament(int index) {
		return tournaments.get(index);
	}

	public void removeTournament(int index) {
		tournaments.remove(index);
	}

	public void removeTournament(Tournament removedTournament) {
		tournaments.remove(removedTournament);
	}

	public int getNumberOfTournaments() {
		return tournaments.size();
	}

}
