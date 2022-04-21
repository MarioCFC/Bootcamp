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

	public Car getCar() {
		return car;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}

	@Override
	public String toString() {
		return car.toString() + " - Puntuación: " + score;
	}

}
