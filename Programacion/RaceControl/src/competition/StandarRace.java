package competition;

import java.util.ArrayList;

public class StandarRace extends Race {
	private double duration;

	public StandarRace(String name, ArrayList<Car> participants, double duration, Tournament eventWichItBelongs) {
		super(name, participants, eventWichItBelongs);
		this.duration = duration;
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
