package competition;

import java.util.ArrayList;
import java.util.Random;

import utils.VelocityConversor;

public class StandardRace extends Race {
	private int durationInMin;
	private int velocityChangePerMinuteInKmH = 10;

	public StandardRace(String name, ArrayList<Car> participants, int durationInMin) {
		super(name, participants);
		this.durationInMin = durationInMin;
	}




	@Override
	public void run() {
		Random rand = new Random();

		for (Car car : participants) {
			car.setDistance(0.0);
			car.setVelocityKmH(0);

			for (int i = 0; i < durationInMin; i++) {
				int finalVelocityChange = velocityChangePerMinuteInKmH;

				if (rand.nextInt(2) == 0) {
					finalVelocityChange = -velocityChangePerMinuteInKmH;
				} 

				if (!(car.getVelocityKmH() == 0 && finalVelocityChange < 0)
						&& !(car.getVelocityKmH() == car.getMaxVelocityKmH() && finalVelocityChange > 0)) {

					car.setVelocityKmH(car.getVelocityKmH() + finalVelocityChange);
				}

				double newDistance = car.getDistance() + VelocityConversor.convertKmHToMMin(car.getVelocityKmH());
				car.setDistance(newDistance);
			}
		}

		calculateScore();

	}

}
