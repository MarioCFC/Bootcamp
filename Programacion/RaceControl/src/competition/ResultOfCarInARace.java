package competition;

public class ResultOfCarInARace {
	private Car car;
	private Race race;
	private int score;

	public ResultOfCarInARace(Car car, Race race, int score) {
		super();
		this.car = car;
		this.race = race;
		this.score = score;
	}

	public Car getCar() {
		return car;
	}

	public Race getRace() {
		return race;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
