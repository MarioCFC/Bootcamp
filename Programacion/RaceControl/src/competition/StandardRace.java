package competition;

import java.util.ArrayList;

public class StandardRace extends Race {
	private int durationInMin;

	private StandardRace() {
	}

	public StandardRace(String name, ArrayList<Car> participants, int durationInMin) {
		super(name, participants);
		this.durationInMin = durationInMin;
	}

	@Override
	public void run() {

		for (int i = 0; i < participants.size(); i++) {
			Car car = participants.get(i);
			car.setDistance(0.0);
			car.setVelocityKmH(0);

			for (int j = 0; j < durationInMin; j++) {
				runRound(car);
			}

		}
		calculateScore();
		finish();

		/*
		 * for (Car car : participants) { car.setDistance(0.0); car.setVelocityKmH(0);
		 * 
		 * for (int i = 0; i < durationInMin; i++) { runRound(car); }
		 * 
		 * calculateScore(); finish(); }
		 */

	}

}
