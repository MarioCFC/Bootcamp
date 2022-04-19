package competition;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ResultOfCarInARace {
	private static long nextId = 1;

	private long id;

	private Car car;
	private Race race;

	private Integer score;

	private ResultOfCarInARace() {
	}

	public ResultOfCarInARace(Car car, Race race, Integer score) {
		super();
		this.car = car;
		this.race = race;
		this.score = 0;
		id = nextId++;
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
