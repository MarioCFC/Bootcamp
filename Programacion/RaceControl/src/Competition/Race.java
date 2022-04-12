package Competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


public abstract class Race {
	private final String name;

	private HashMap<Car, Double> ranking = new HashMap();

	protected Race(String name, HashMap<Car, Double> ranking) {
		this.name = name;
		this.ranking = ranking;
	}



	protected String getName() {
		return name;
	}

	protected HashMap<Car, Double> getRanking() {
		return ranking;
	}



	protected List<Garage> getParticipantsGarages() {
		HashSet<Garage> garages = new HashSet();
		Iterator carIterator = ranking.entrySet().iterator();
		while (carIterator.hasNext()) {
			Entry<Car, Double> couple = (Entry<Car, Double>) carIterator.next();
			garages.add(couple.getKey().getSticker());
		}

		return new ArrayList<Garage>(garages);
	}
	
	protected void run() throws Exception {
		// TODO:Implementar
		throw new Exception();
	}

}
