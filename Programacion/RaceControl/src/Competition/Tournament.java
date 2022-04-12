package Competition;

import java.util.HashMap;

public class Tournament {
	private String name;
	private HashMap<Car, Double> ranking = new HashMap();

	public Tournament(String name, HashMap<Car, Double> ranking) {
		this.name = name;
		this.ranking = ranking;
	}

	public String getName() {
		return name;
	}


	




}
