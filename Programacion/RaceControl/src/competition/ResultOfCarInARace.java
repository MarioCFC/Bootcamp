package competition;

public class ResultOfCarInARace {
	private Car car;
	private Race race;
	private Integer score;

	public ResultOfCarInARace(Car car, Race race, Integer score) {
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
