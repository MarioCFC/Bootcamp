package competition;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
public class Tournaments {
	@JsonIgnore
	private static Tournaments tournamentManager = null;

	private ArrayList<Tournament> tournaments = new ArrayList();;

	
	private Tournaments() {
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
	public ArrayList<Tournament> getTournamentByState(boolean tournamentIsFinished) {
		ArrayList<Tournament> foundTournaments = new ArrayList<Tournament>();
		for (Tournament tournament : tournaments) {
			if (tournament.isFinished() == tournamentIsFinished) {
				foundTournaments.add(tournament);
			}
		}

		return foundTournaments;
	}

}
