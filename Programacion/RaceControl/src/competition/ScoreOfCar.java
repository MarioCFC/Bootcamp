package competition;

import java.util.UUID;


public class ScoreOfCar {

	protected String id;
	protected Car car;

	protected Integer score;
	protected Double totalDistance;

	protected ScoreOfCar() {
	}

	public ScoreOfCar(Car car, Integer score, Double totalDistance) {
		this.car = car;
		this.score = score;
		this.totalDistance = totalDistance;
		id = UUID.randomUUID().toString();
	}

	protected Car getCar() {
		return car;
	}

	protected Integer getScore() {
		return score;
	}

	protected void setScore(Integer score) {
		this.score = score;
	}

	protected Double getTotalDistance() {
		return totalDistance;
	}

	protected void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}

	@Override
	public String toString() {
		return car.toString() + " - Score: " + score;
	}

}
