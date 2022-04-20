package competition;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ScoreOfCarInARace extends ScoreOfCar {

	private Race race;

	public ScoreOfCarInARace(Car car, Race race, Integer score, Double totalDistance) {
		super(car, score, totalDistance);
		this.race = race;
	}


	public Race getRace() {
		return race;
	}




}
