package competition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONFileManager implements IDataManager {
	private static final String JSON_FILE_PATH = "res/StateFile.json";

	@Override
	public void load(SnapShot state) throws FileNotFoundException {
//		gsonManager.fromJson(new BufferedReader(new FileReader(JSON_FILE_PATH)), SnapShot.class);
	}

	@Override
	public void save(SnapShot state) throws IOException {
		Writer writter = Files.newBufferedWriter(Paths.get(JSON_FILE_PATH));
		ArrayList<SnapShot> states = new ArrayList<SnapShot>();
		states.add(state);
//		gsonManager.toJson(states, writter);
	}
}
