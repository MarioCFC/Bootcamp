package competition;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tournaments {
	@JsonIgnore
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

	public void setTournaments(ArrayList<Tournament> tournaments) {
		this.tournaments = tournaments;
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

	// TODO:Cambiar
	@JsonIgnore
	public int getNumberOfTournaments() {
		return tournaments.size();
	}

	@JsonIgnore
	public ArrayList<Tournament> getAll() {
		return tournaments;
	}

	@JsonIgnore
	public ArrayList<Tournament> getTournamentsByState(boolean tournamentIsFinished) {
		ArrayList<Tournament> foundTournaments = new ArrayList<Tournament>();
		for (Tournament tournament : tournaments) {
			if (tournament.isFinished() == tournamentIsFinished) {
				foundTournaments.add(tournament);
			}
		}

		return foundTournaments;
	}

	@JsonIgnore
	public ArrayList<Tournament> getTournamentsThatCanAddNewRaces() {

		ArrayList<Tournament> foundTournaments = new ArrayList<Tournament>();
		for (Tournament actualTournament : tournaments) {
			if (!actualTournament.isFinished()) {
				foundTournaments.add(actualTournament);
			}
		}
		return foundTournaments;

	}

	@JsonIgnore
	public ArrayList<Tournament> getTounamentsThatCanRunNextRace() {
		ArrayList<Tournament> foundTournaments = new ArrayList<Tournament>();
		for (Tournament tournament : tournaments) {
			if (tournament.getLastRaceRunned() < tournament.getNumberOfActualRaces()) {
				foundTournaments.add(tournament);
			}
		}
		return foundTournaments;
	}
}
