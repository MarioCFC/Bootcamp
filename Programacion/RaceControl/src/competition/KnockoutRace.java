package competition;

import java.util.ArrayList;

public class KnockoutRace extends Race {

	private KnockoutRace() {
	}

	public KnockoutRace(String name, ArrayList<Car> participants) {
		super(name, participants);
	}

	@Override
	public void run() {

		// Precalentamiento
		for (Car car : participants) {
			car.setDistance(0.0);
			car.setVelocityKmH(0);

			for (int i = 0; i < 5; i++) {
				runRound(car);
			}
		}

		sortParticipantsByDistance();

		// Empiza la carrera
		for (int j = participants.size() - 1; j > 0; j--) {

			for (int i = j; i >= 0; i--) {
				runRound(participants.get(i));
			}

			sortParticipantsByDistance();
		}

		calculateScore();
		finish();
	}

}
